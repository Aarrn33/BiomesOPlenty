/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.generator;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.generation.BOPGeneratorBase;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.world.generator.GeneratorColumns.Builder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorBigMushroom extends BOPGeneratorBase
{       
    
    public static enum BigMushroomType
    {
        BROWN, RED;
        public IBlockState getDefaultState()
        {
            switch (this)
            {
                case BROWN:
                    return Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState();
                case RED: default:
                    return Blocks.RED_MUSHROOM_BLOCK.getDefaultState();
            }
        }
        public int getCapHeight(int height)
        {
            switch (this)
            {
                case BROWN:
                    return 1;
                case RED: default:
                    return 4;
            }
        }
        public int getCapDiameter(int y, int height)
        {
            switch (this)
            {
                case BROWN:
                    // only one layer - 7 blocks across
                    return 7;
                case RED: default:
                    // top layer 3 blocks across, other layers 5 blocks across
                    return (y < (height - 1)) ? 5 : 3;
            }           
        }
        public boolean cutCorners(int y, int height)
        {
            switch (this)
            {
                case BROWN:
                    return true;
                case RED: default:
                    // cur corners of every layer except the top layer on red mushroom
                    return (y < (height - 1));
            }
        }
    }
    
    public static class Builder extends BOPGeneratorBase.InnerBuilder<Builder, GeneratorBigMushroom> implements IGeneratorBuilder<GeneratorBigMushroom>
    {
        protected int minHeight;
        protected int maxHeight;
        protected BigMushroomType mushroomType;
        protected IBlockPosQuery placeOn;
        protected IBlockPosQuery replace;
        protected ScatterYMethod scatterYMethod;
        
        public Builder minHeight(int a) {this.minHeight = a; return this.self();}
        public Builder maxHeight(int a) {this.maxHeight = a; return this.self();}
        public Builder mushroomType(BigMushroomType a) {this.mushroomType = a; return this;}
        public Builder placeOn(IBlockPosQuery a) {this.placeOn = a; return this.self();}
        public Builder placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQuery.parseQueryString(a); return this.self();}
        public Builder placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this.self();}
        public Builder placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this.self();}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this.self();}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this.self();}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this.self();}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this.self();}
        public Builder scatterYMethod(ScatterYMethod a) {this.scatterYMethod = a; return this.self();}
        
        public Builder()
        {
            this.minHeight = 5;
            this.maxHeight = 8;
            this.amountPerChunk = 1.0F;
            this.mushroomType = BigMushroomType.BROWN;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            // always at world surface
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
        }
        
        @Override
        public GeneratorBigMushroom create()
        {
            return new GeneratorBigMushroom(this.amountPerChunk, this.placeOn, this.replace, this.mushroomType, this.scatterYMethod, this.minHeight, this.maxHeight);
        }
    }
    
    protected int minHeight;
    protected int maxHeight;
    protected IBlockPosQuery placeOn;
    protected IBlockPosQuery replace;
    protected BigMushroomType mushroomType;
    protected IBlockState mushroomState;
    protected ScatterYMethod scatterYMethod;
    
    public GeneratorBigMushroom(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, BigMushroomType mushroomType, ScatterYMethod scatterYMethod, int minHeight, int maxHeight)
    {
        super(amountPerChunk);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.placeOn = placeOn;
        this.replace = replace;
        this.setMushroomType(mushroomType);
        this.scatterYMethod = scatterYMethod;
    }
    
    public void setMushroomType(BigMushroomType type)
    {
        this.mushroomType = type;
        this.mushroomState = type.getDefaultState();
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        return this.scatterYMethod.getBlockPos(world, random, x, z);
    }
    
    protected void replaceWithMushroom(World world, BlockPos pos, BlockHugeMushroom.EnumType side)
    {
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.mushroomState.withProperty(BlockHugeMushroom.VARIANT, side), 2);
        }
    }
    
    public boolean isEnoughSpace(World world, BlockPos pos, int height)
    {                
        if (pos.getY() < 1 || pos.getY() + height > 255)
        {
            return false;
        }
        for (int y = pos.getY(); y <= pos.getY() + height; ++y)
        {
            int radius = (y <= pos.getY() + 3) ? 0 : 3;

            for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
                {
                    if (!this.replace.matches(world, new BlockPos(x, y, z)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {

        int height = rand.nextInt(this.maxHeight - this.minHeight) + this.minHeight;
                
        // check that there's room
        if (!this.isEnoughSpace(world, pos, height)) {return false;}
        
        if (!this.placeOn.matches(world, pos.down()))
        {
            return false;
        }
        
        // create the mushroom cap
        for (int y = height - this.mushroomType.getCapHeight(height); y < height; ++y)
        {
            
            // not strictly the radius, but the number of blocks either side of the center
            int radius = ((this.mushroomType.getCapDiameter(y, height) - 1) / 2);

            for (int x = -radius; x <= radius; ++x)
            {
                for (int z = -radius; z <= radius; ++z)
                {
                    // work out which sides are exposed
                    BlockHugeMushroom.EnumType side;
                    if (x == -radius)
                    {
                        if (z == radius) {
                            side = BlockHugeMushroom.EnumType.SOUTH_WEST;
                        } else if (z == -radius) {
                            side = BlockHugeMushroom.EnumType.NORTH_WEST;
                        } else {
                            side = BlockHugeMushroom.EnumType.WEST;
                        }
                    }
                    else if (x == radius)
                    {
                        if (z == radius) {
                            side = BlockHugeMushroom.EnumType.SOUTH_EAST;
                        } else if (z == -radius) {
                            side = BlockHugeMushroom.EnumType.NORTH_EAST;
                        } else {
                            side = BlockHugeMushroom.EnumType.EAST;
                        }
                    }
                    else
                    {
                        if (z == radius) {
                            side = BlockHugeMushroom.EnumType.SOUTH;
                        } else if (z == -radius) {
                            side = BlockHugeMushroom.EnumType.NORTH;
                        } else {
                            side = BlockHugeMushroom.EnumType.CENTER;
                        }
                    }
                    

                    if (this.mushroomType.cutCorners(y, height))
                    {
                        // skip the corner blocks
                        if ((x == -radius || x == radius) && (z == -radius || z == radius))
                        {
                            continue;
                        }

                        // fix the sides which now become exposed after cutting the corners
                        if (x == -(radius - 1) && z == -radius)
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_WEST;
                        }
                        if (x == -radius && z == -(radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_WEST;
                        }
                        if (x == (radius - 1) && z == -radius)
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_EAST;
                        }
                        if (x == radius && z == -(radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.NORTH_EAST;
                        }
                        if (x == -(radius - 1) && z == radius)
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_WEST;
                        }
                        if (x == -radius && z == (radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_WEST;
                        }
                        if (x == (radius - 1) && z == radius)
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_EAST;
                        }
                        if (x == radius && z == (radius - 1))
                        {
                            side = BlockHugeMushroom.EnumType.SOUTH_EAST;
                        }
                    }

                    // skip blocks which are totally inside the cap (unless the mushroom is really small)
                    if (side == BlockHugeMushroom.EnumType.CENTER && y < (height - 1))
                    {
                        if (height <= 2)
                        {
                            side = BlockHugeMushroom.EnumType.ALL_INSIDE;
                        } else {
                            continue;
                        }
                    }

                    // finally add the mushroom block
                    this.replaceWithMushroom(world, pos.add(x, y, z), side);
                }
            }
        }

        // create the mushroom stem
        for (int y = 0; y < height - 1; ++y)
        {
            this.replaceWithMushroom(world, pos.up(y), BlockHugeMushroom.EnumType.STEM);
        }

        return true;

    }
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.maxHeight = conf.getInt("maxHeight", this.maxHeight);
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.setMushroomType(conf.getEnum("type", this.mushroomType, BigMushroomType.class));
    }
    
}