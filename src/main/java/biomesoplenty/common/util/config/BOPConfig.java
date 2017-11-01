/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockStateUtils;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class BOPConfig
{
    
    public static Gson serializer = new GsonBuilder().setPrettyPrinting().create();
    public static JsonParser parser = new JsonParser();
    private static enum Types {BOOLEAN, STRING, INTEGER, FLOAT, BLOCKSTATE, BLOCKPOSQUERY, RESOURCELOCATION}
    
    public static boolean writeFile(File outputFile, Object obj)
    {
        try {
            FileUtils.write(outputFile, serializer.toJson(obj));
            return true;
        } catch (Exception e) {
            BiomesOPlenty.logger.error("Error writing config file " + outputFile.getAbsolutePath() + ": " + e.getMessage());
            return false;
        }
    }
    
    // Abstract base class for a config object
    public static abstract class ConfigObjBase implements IConfigObj
    {
        protected Map<String,JsonElement> members;
        protected Map<String, JsonElement> defaults;
        protected Map<String, ConfigChildObj> childObjs;
        protected List<String> messages = new ArrayList<String>();
        protected String prefix = "";
        protected boolean warnIfDefault;

        private ConfigObjBase(boolean warnIfDefault)
        {
            this.defaults = Maps.newHashMap();
            this.childObjs = Maps.newHashMap();
            this.warnIfDefault = warnIfDefault;
        }

        public void parse(String jsonString)
        {
            this.members = new HashMap<String, JsonElement>();
            if (jsonString == null) {return;}
            JsonElement rootElement = null;
            try
            {
                rootElement = parser.parse(jsonString);
                if (rootElement != null)
                {
                    if (rootElement.isJsonObject())
                    {
                        for (Entry<String, JsonElement> entry : rootElement.getAsJsonObject().entrySet())
                        {
                            this.members.put(entry.getKey(), entry.getValue());
                        }
                    } else {
                        this.addMessage("Error parsing config: not a JSON object");
                    }
                }
            } catch (Exception e) {
                this.addMessage("Error parsing config: "+e.getMessage());
            }
        }

        @Override
        public JsonElement serializeDefaults()
        {
            if (this.defaults.isEmpty())
            {
                this.addMessage("No defaults found!");
            }

            // add top-level properties
            JsonObject rootObject = new JsonObject();
            for (Entry<String, JsonElement> entry : defaults.entrySet())
            {
                rootObject.add(entry.getKey(), entry.getValue());
            }

            // serialize children
            for (Entry<String, ConfigChildObj> entry : this.childObjs.entrySet())
            {
                rootObject.add(entry.getKey(), entry.getValue().serializeDefaults());
            }

            return rootObject;
        }

        @Override
        public void addMessage(String message)
        {
            this.messages.add(this.prefix + ": " + message);
        }
        @Override
        public void addMessage(String extraPrefix, String message)
        {
            this.messages.add(this.prefix + "." + extraPrefix + ": " + message);
        }
        @Override
        public List<String> flushMessages()
        {
            ArrayList out = new ArrayList<String>(this.messages);
            this.messages.clear();
            return out;
        }
        @Override
        public boolean isEmpty()
        {
            return this.members == null || this.members.isEmpty();
        }
        @Override
        public boolean has(String name)
        {
            return this.members != null && this.members.containsKey(name);
        }
        @Override
        public Set<String> getKeys()
        {
            return this.members.keySet();
        }
        
        @Override
        public IConfigObj getObject(String name)
        {
            return this.getObject(name, false);
        }        
        @Override
        public IConfigObj getObject(String name, boolean warnIfMissing)
        {
            JsonObject obj = new JsonObject();

            // attempt to return cached child first
            if (this.childObjs.containsKey(name))
            {
                return this.childObjs.get(name);
            }

            // check if the object exists and read it if it does
            if (!this.has(name))
            {
                if (warnIfMissing)
                {
                    this.addMessage(name, "Error - missing value");
                }
            }
            else
            {
                try
                {
                    obj = this.members.get(name).getAsJsonObject();
                } catch (Exception e) {
                    this.addMessage("Error fetching object " + name + ": " + e.getMessage());
                }
            }

            ConfigChildObj childObj = new ConfigChildObj(this.prefix + "." + name, obj, this.warnIfDefault);
            // store the child for later serialization
            this.childObjs.put(name, childObj);
            return childObj;
        }
 
        @Override
        public ArrayList<IConfigObj> getObjectArray(String name)
        {
            return this.getObjectArray(name, false);
        }
        @Override
        public ArrayList<IConfigObj> getObjectArray(String name, boolean warnIfMissing)
        {
            if (!this.has(name))
            {
                if (warnIfMissing)
                {
                    this.addMessage(name, "Error - missing value");
                }
                return null;
            }
            ArrayList<IConfigObj> list = new ArrayList<IConfigObj>();
            try
            {
                JsonArray arr = this.members.get(name).getAsJsonArray();
                for (int i = 0; i < arr.size(); i++)
                {
                    try
                    {
                        JsonObject obj = arr.get(i).getAsJsonObject();
                        if (obj != null) {list.add( new ConfigChildObj(this.prefix + "." + name + "." + i, obj) );}
                    } catch (Exception e) {
                        this.addMessage("Error fetching object from array " + name + " at index " + i + ": " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                this.addMessage("Error fetching object array " + name + ": " + e.getMessage());
                return null;               
            }
            return list;
        }
        
 
        // Implement the simple getter methods
        
        @Override
        public Boolean getBool(String name, Boolean defaultVal) {return this.<Boolean>get(name, defaultVal, false, Types.BOOLEAN);}
        @Override
        public String getString(String name, String defaultVal) {return this.<String>get(name, defaultVal, false, Types.STRING);}
        @Override
        public Integer getInt(String name, Integer defaultVal) {return this.<Integer>get(name, defaultVal, false, Types.INTEGER);}
        @Override
        public Float getFloat(String name, Float defaultVal) {return this.<Float>get(name, defaultVal, false, Types.FLOAT);}
        @Override
        public IBlockState getBlockState(String name, IBlockState defaultVal) {return this.<IBlockState>get(name, defaultVal, false, Types.BLOCKSTATE);}
        @Override
        public IBlockPosQuery getBlockPosQuery(String name, IBlockPosQuery defaultVal) {return this.<IBlockPosQuery>get(name, defaultVal, false, Types.BLOCKPOSQUERY);}
        @Override
        public ResourceLocation getResourceLocation(String name, ResourceLocation defaultVal) {return this.<ResourceLocation>get(name, defaultVal, false, Types.RESOURCELOCATION);}
        @Override
        public <E extends Enum> E getEnum(String name, E defaultVal, Class<E> clazz) {return this.getEnum(name, defaultVal, false, clazz);}

        @Override
        public Boolean getBool(String name) {return this.<Boolean>get(name, null, true, Types.BOOLEAN);}
        @Override
        public String getString(String name) {return this.<String>get(name, null, true, Types.STRING);}
        @Override
        public Integer getInt(String name) {return this.<Integer>get(name, null, true, Types.INTEGER);}
        @Override
        public Float getFloat(String name) {return this.<Float>get(name, null, true, Types.FLOAT);}
        @Override
        public IBlockState getBlockState(String name) {return this.<IBlockState>get(name, null, true, Types.BLOCKSTATE);}
        @Override
        public IBlockPosQuery getBlockPosQuery(String name) {return this.<IBlockPosQuery>get(name, null, true, Types.BLOCKPOSQUERY);}
        @Override
        public ResourceLocation getResourceLocation(String name) {return this.<ResourceLocation>get(name, null, true, Types.RESOURCELOCATION);}
        @Override
        public <E extends Enum> E getEnum(String name, Class<E> clazz) {return this.getEnum(name, null, true, clazz);}
        
        @Override
        public ArrayList<Boolean> getBoolArray(String name, ArrayList<Boolean> defaultVal) {return this.<Boolean>getArray(name, defaultVal, false, Types.BOOLEAN);}
        @Override
        public ArrayList<String> getStringArray(String name, ArrayList<String> defaultVal) {return this.<String>getArray(name, defaultVal, false, Types.STRING);}
        @Override
        public ArrayList<Integer> getIntArray(String name, ArrayList<Integer> defaultVal) {return this.<Integer>getArray(name, defaultVal, false, Types.INTEGER);}
        @Override
        public ArrayList<Float> getFloatArray(String name, ArrayList<Float> defaultVal) {return this.<Float>getArray(name, defaultVal, false, Types.FLOAT);}
        @Override
        public ArrayList<IBlockState> getBlockStateArray(String name, ArrayList<IBlockState> defaultVal) {return this.<IBlockState>getArray(name, defaultVal, false, Types.BLOCKSTATE);}
        @Override
        public ArrayList<IBlockPosQuery> getBlockPosQueryArray(String name, ArrayList<IBlockPosQuery> defaultVal) {return this.<IBlockPosQuery>getArray(name, defaultVal, false, Types.BLOCKPOSQUERY);}
        @Override
        public ArrayList<ResourceLocation> getResourceLocationArray(String name, ArrayList<ResourceLocation> defaultVal) {return this.<ResourceLocation>getArray(name, defaultVal, false, Types.RESOURCELOCATION);}
        @Override
        public <E extends Enum> ArrayList<E> getEnumArray(String name, ArrayList<E> defaultVal, Class<E> clazz) {return this.getEnumArray(name, defaultVal, false, clazz);}
 
        @Override
        public ArrayList<Boolean> getBoolArray(String name) {return this.<Boolean>getArray(name, null, true, Types.BOOLEAN);}
        @Override
        public ArrayList<String> getStringArray(String name) {return this.<String>getArray(name, null, true, Types.STRING);}
        @Override
        public ArrayList<Integer> getIntArray(String name) {return this.<Integer>getArray(name, null, true, Types.INTEGER);}
        @Override
        public ArrayList<Float> getFloatArray(String name) {return this.<Float>getArray(name, null, true, Types.FLOAT);}
        @Override
        public ArrayList<IBlockState> getBlockStateArray(String name) {return this.<IBlockState>getArray(name, null, true, Types.BLOCKSTATE);}
        @Override
        public ArrayList<IBlockPosQuery> getBlockPosQueryArray(String name) {return this.<IBlockPosQuery>getArray(name, null, true, Types.BLOCKPOSQUERY);}
        @Override
        public ArrayList<ResourceLocation> getResourceLocationArray(String name) {return this.<ResourceLocation>getArray(name, null, true, Types.RESOURCELOCATION);}
        @Override
        public <E extends Enum> ArrayList<E> getEnumArray(String name, Class<E> clazz) {return this.getEnumArray(name, null, true, clazz);}




        protected <E extends Enum> ArrayList<E> getEnumArray(String name, ArrayList<E> defaultVal, boolean warnIfMissing, Class<E> clazz)
        {
            if (!this.has(name))
            {
                if (warnIfMissing)
                {
                    this.addMessage(name, "Error - missing value");
                }
                return defaultVal;
            }
            ArrayList<E> list = new ArrayList<E>();
            try
            {
                JsonArray arr = this.members.get(name).getAsJsonArray();
                for (int i = 0; i < arr.size(); i++)
                {
                    E ele = this.<E>asEnum(arr.get(i), clazz, name + "." + i);
                    if (ele != null) {list.add(ele);}
                }
            } catch (Exception e) {
                this.addMessage(name, "Error fetching " + clazz.getName() + " array: " + e.getMessage());
            }
            return list;
        }
        
        protected <E extends Enum> E getEnum(String name, E defaultVal, boolean warnIfMissing, Class<E> clazz)
        {
            if (!this.has(name))
            {
                if (warnIfMissing)
                {
                    this.addMessage(name, "Error - missing value");
                }
                return defaultVal;
            }
            E out = this.<E>asEnum(this.members.get(name), clazz, name);
            return out == null ? defaultVal : out;
        }
        
        private <T> T get(String name, T defaultVal, boolean warnIfMissing, Types type)
        {
            // if a default value is set, store it for later use
            if (defaultVal != null)
            {
                JsonElement element = this.from(defaultVal, type);
                // don't store if conversion to a JsonElement failed
                if (element != null) this.defaults.put(name, element);
            }
            // check if the property exists, if not, use the default
            if (!this.has(name))
            {
                if (warnIfMissing)
                {
                    this.addMessage(name, "Error - missing value");
                }
                return defaultVal;
            }
            T out = this.<T>as(this.members.get(name), type, name);
            // warn people who try to copy-paste default configs
            if (this.warnIfDefault && out != null && out.equals(defaultVal))
            {
                this.addMessage("You can't set a property to its default value, only changed properties can be included in config files. \n Property: " + name + ", Value: " + out);
            }
            return out == null ? defaultVal : out;
        }
        
        private <T> ArrayList<T> getArray(String name, ArrayList<T> defaultVal, boolean warnIfMissing, Types type)
        {
            if (!this.has(name))
            {
                if (warnIfMissing)
                {
                    this.addMessage(name, "Error - missing value");
                }
                return defaultVal;
            }
            ArrayList<T> list = new ArrayList<T>();
            try
            {
                JsonArray arr = this.members.get(name).getAsJsonArray();
                for (int i = 0; i < arr.size(); i++)
                {
                    T ele = this.<T>as(arr.get(i), type, name + "." + i);
                    if (ele != null) {list.add(ele);}
                }
            } catch (Exception e) {
                this.addMessage(name, "Error fetching " + type.toString().toLowerCase() + " array: " + e.getMessage());
            }
            return list;
        }
        
        private <T> T as(JsonElement ele, Types type, String extraPrefix)
        {
            switch (type) {
                case BOOLEAN:
                    return (T)this.asBool(ele, extraPrefix);
                case STRING:
                    return (T)this.asString(ele, extraPrefix);
                case INTEGER:
                    return (T)this.asInt(ele, extraPrefix);
                case FLOAT:
                    return (T)this.asFloat(ele, extraPrefix);
                case BLOCKSTATE:
                    return (T)this.asBlockState(ele, extraPrefix);
                case BLOCKPOSQUERY:
                    return (T)this.asBlockPosQuery(ele, extraPrefix);
                case RESOURCELOCATION:
                    return (T)this.asResourceLocation(ele, extraPrefix);
                default:
                    return null;
            }
        }
        
        protected <E extends Enum> E asEnum(JsonElement ele, Class<E>clazz, String extraPrefix)
        {
            try
            {
                String val = ele.getAsString();
                E[] enums = clazz.getEnumConstants();
                if (enums == null)
                {
                    this.addMessage(extraPrefix, "Class " + clazz.getName() + " contains no enum constants");
                    return null;
                }
                for (E enumVal : enums)
                {
                    if (enumVal.name().equalsIgnoreCase(val))
                    {
                        return enumVal;
                    }
                }
                this.addMessage(extraPrefix, "Value " + val + " does not exist in enum " + clazz);
                return null;
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching string: " + e.getMessage());
                return null;
            }
        }
                
        protected Boolean asBool(JsonElement ele, String extraPrefix)
        {
            try
            {
                return ele.getAsBoolean();
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching boolean: " + e.getMessage());
                return null;
            }
        }
        
        protected String asString(JsonElement ele, String extraPrefix)
        {
            try
            {
                return ele.getAsString();
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching string: " + e.getMessage());
                return null;
            }
        }
        
        protected Integer asInt(JsonElement ele, String extraPrefix)
        {
            try
            {
                return ele.getAsInt();
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching integer: " + e.getMessage());
                return null;
            }
        }
        
        protected Float asFloat(JsonElement ele, String extraPrefix)
        {
            try
            {
                return ele.getAsFloat();
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching float: " + e.getMessage());
                return null;
            }
        }
        
        protected IBlockPosQuery asBlockPosQuery(JsonElement ele, String extraPrefix)
        {
            try
            {
                String queryString = ele.getAsString();
                try {
                    return BlockQuery.parseQueryString(queryString);
                } catch (BlockQueryParseException e) {
                    this.addMessage(extraPrefix, "Error parsing BlockPosQuery " + queryString + " - " + e.getMessage());
                    return null;
                }
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching string: " + e.getMessage());
                return null;
            }
        }
        
        protected IBlockState asBlockState(JsonElement ele, String extraPrefix)
        {
            try {
                
                JsonObject obj = ele.getAsJsonObject();

                // attempt to load the specified block
                if (!obj.has("block"))
                {
                    this.addMessage(extraPrefix, "Block name missing");
                    return null;
                }
                JsonElement blockName = obj.get("block");
                if (!blockName.isJsonPrimitive())
                {
                    this.addMessage(extraPrefix, "Invalid block name - must be a string");
                    return null;
                }
                Block block = Block.getBlockFromName(blockName.getAsString());
                if (block == null)
                {
                    this.addMessage(extraPrefix, "Unrecognised block name " + blockName.getAsString());
                    return null;
                }

                IBlockState state = block.getDefaultState();
                
                // attempt to add properties
                if (obj.has("properties")) {
                    
                    JsonElement properties = obj.get("properties");
                    if (!properties.isJsonObject())
                    {
                        this.addMessage(extraPrefix, "Invalid properties list - must be a JSON object");
                        return state;
                    }
                    
                    for (Entry<String, JsonElement> entry : properties.getAsJsonObject().entrySet())
                    {
                        IProperty property = BlockStateUtils.getPropertyByName(state, entry.getKey());
                        if (property != null)
                        {
                            Comparable propertyValue = BlockStateUtils.getPropertyValueByName(state, property, entry.getValue().getAsString());
                            if (propertyValue != null)
                            {
                                state = state.withProperty(property, propertyValue);
                            }
                            else
                            {
                                this.addMessage(extraPrefix, "Invalid value " + entry.getValue().getAsString() + " for property " + entry.getKey());
                            }
                        }
                        else
                        {
                            this.addMessage(extraPrefix, "Invalid property name: " + entry.getKey());
                        }
                    }
                }
                
                return state;
                
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching blockstate: " + e.getMessage());
                return null;
            }
        }

        protected ResourceLocation asResourceLocation(JsonElement ele, String extraPrefix)
        {
            try
            {
                return new ResourceLocation(asString(ele, extraPrefix));
            } catch (Exception e) {
                this.addMessage(extraPrefix, "Error fetching resourcelocation: " + e.getMessage());
                return null;
            }
        }

        private JsonElement from(Object value, Types type)
        {
            switch (type) {
                case BOOLEAN:
                    return new JsonPrimitive((Boolean)value);
                case STRING:
                    return new JsonPrimitive((String)value);
                case INTEGER:
                    return new JsonPrimitive((Integer)value);
                case FLOAT:
                    return new JsonPrimitive((Float)value);
                case BLOCKSTATE:
                    return fromState((IBlockState)value);
                case BLOCKPOSQUERY:
                    return null; // ignore for now
                case RESOURCELOCATION:
                    return fromResourceLocation((ResourceLocation)value);
                default:
                    this.addMessage("Undefined type " + type);
                    return null;
            }
        }

        private JsonElement fromState(IBlockState state)
        {
            JsonObject root = new JsonObject();

            // add block name
            root.add("block", new JsonPrimitive(state.getBlock().getRegistryName().toString()));
            JsonObject properties = new JsonObject();
            root.add("properties", properties);

            // add all property values
            for (Entry<IProperty<?>, Comparable<?>> entry : state.getProperties().entrySet())
            {
                properties.add(entry.getKey().getName(), new JsonPrimitive(entry.getValue().toString()));
            }

            return root;
        }

        private JsonElement fromResourceLocation(ResourceLocation location)
        {
            return new JsonPrimitive(location.toString());
        }
    }
    
    // Concrete class for a config object created by supplying the JSON in a string
    public static class ConfigObj extends ConfigObjBase
    {
        public ConfigObj(String jsonString)
        {
            super(false);
            this.parse(jsonString);
        }
    }
    
    // Concrete class for a config object created from a file
    public static class ConfigFileObj extends ConfigObjBase
    {
        public ConfigFileObj(File configFile)
        {
            this(configFile, false, false);
        }
        
        public ConfigFileObj(File configFile, boolean warnIfMissing, boolean warnIfDefault)
        {
            super(warnIfDefault);
            this.prefix = configFile.getName();
            String jsonString = null;
            if (configFile.exists())
            {
                this.addMessage("Detected config file ");
                try
                {
                    jsonString = FileUtils.readFileToString(configFile);
                } catch (IOException e) {
                    this.addMessage("Error reading config file "+e.getMessage());
                }
            } else {
                if (warnIfMissing)
                {
                    this.addMessage("File missing");
                }
            }
            this.parse(jsonString);
        }
    }
    
    // Concrete class for a config object which is a child of another config object
    public static class ConfigChildObj extends ConfigObjBase
    {
        protected ConfigChildObj(String prefix, JsonObject obj, boolean warnIfDefault)
        {
            super(warnIfDefault);
            this.prefix = prefix;
            this.members = new HashMap<String, JsonElement>();
            for (Entry<String, JsonElement> entry : obj.entrySet())
            {
                this.members.put(entry.getKey(), entry.getValue());
            }
        }

        protected ConfigChildObj(String prefix, JsonObject obj)
        {
            this(prefix, obj, false);
        }
    }
}