/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.TreeMap;

public class BiomeConfigData
{
    @SerializedName("standard_weights")
    public TreeMap<String, WeightedBiomeEntry> standardBiomeWeights = Maps.newTreeMap();

    @SerializedName("technical_biome_toggles")
    public TreeMap<String, ToggleableBiomeEntry> technicalBiomeEntries = Maps.newTreeMap();

    @SerializedName("sub_biome_weights")
    public TreeMap<String, SubBiomeEntry> subBiomeEntries = Maps.newTreeMap();

    @SerializedName("island_biome_toggles")
    public TreeMap<String, ToggleableBiomeEntry> islandBiomeEntries = Maps.newTreeMap();

    @SerializedName("vanilla_biome_weights")
    public TreeMap<String, WeightedBiomeEntry> vanillaBiomeEntries = Maps.newTreeMap();

    public static class WeightedBiomeEntry
    {
        public int weight;

        public WeightedBiomeEntry(int weight)
        {
            this.weight = weight;
        }
    }

    public static class ToggleableBiomeEntry
    {
        public boolean enabled;

        public ToggleableBiomeEntry(boolean enabled)
        {
            this.enabled = enabled;
        }
    }

    public static class SubBiomeEntry
    {
        public int weight;
        public float rarity;

        public SubBiomeEntry(int weight, float rarity)
        {
            this.weight = weight;
            this.rarity = rarity;
        }
    }
}
