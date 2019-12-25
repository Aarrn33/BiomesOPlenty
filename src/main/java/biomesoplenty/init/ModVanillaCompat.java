package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.biome.BiomeFeatureHelper;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class ModVanillaCompat
{
    public static void setup()
    {
        //Vanilla Biome Features
        addFeature(Biomes.BADLANDS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        addFeature(Biomes.BADLANDS_PLATEAU, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        addFeature(Biomes.WOODED_BADLANDS_PLATEAU, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        addFeature(Biomes.ERODED_BADLANDS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        addFeature(Biomes.MODIFIED_BADLANDS_PLATEAU, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        addFeature(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));

        addFeature(Biomes.DARK_FOREST, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.toadstool.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(3)));
        addFeature(Biomes.DARK_FOREST_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.toadstool.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(3)));

        addFeature(Biomes.FOREST, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        addFeature(Biomes.FOREST, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.blue_hydrangea.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
        addFeature(Biomes.WOODED_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
        addFeature(Biomes.WOODED_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.blue_hydrangea.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));

        addFeature(Biomes.FLOWER_FOREST, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_SELECTOR, new MultipleRandomFeatureConfig(ImmutableList.of(BOPBiomeFeatures.BIG_FLOWERING_OAK_TREE, BOPBiomeFeatures.JACARANDA_TREE, BOPBiomeFeatures.BIG_JACARANDA_TREE}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}, new float[]{0.4F, 0.2F, 0.1F}, BOPBiomeFeatures.FLOWERING_OAK_TREE, IFeatureConfig.NO_FEATURE_CONFIG), Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.5F, 1)));
        addFeature(Biomes.FLOWER_FOREST, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.blue_hydrangea.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(3)));
        addFeature(Biomes.FLOWER_FOREST, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.goldenrod.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));

        addFeature(Biomes.JUNGLE, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.ORANGE_COSMOS_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(4)));
        addFeature(Biomes.JUNGLE_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.ORANGE_COSMOS_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(4)));
        addFeature(Biomes.MODIFIED_JUNGLE, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.ORANGE_COSMOS_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(4)));

        addFeature(Biomes.MOUNTAINS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
        addFeature(Biomes.WOODED_MOUNTAINS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
        addFeature(Biomes.GRAVELLY_MOUNTAINS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
        addFeature(Biomes.MODIFIED_GRAVELLY_MOUNTAINS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));

        addFeature(Biomes.MUSHROOM_FIELDS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.toadstool.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(3)));
        addFeature(Biomes.MUSHROOM_FIELDS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.glowshroom.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
        addFeature(Biomes.MUSHROOM_FIELD_SHORE, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.toadstool.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));

        addFeature(Biomes.SAVANNA, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.WILDFLOWER_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(3)));
        addFeature(Biomes.SAVANNA_PLATEAU, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.WILDFLOWER_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(3)));
        addFeature(Biomes.SHATTERED_SAVANNA, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.WILDFLOWER_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(3)));
        addFeature(Biomes.SHATTERED_SAVANNA_PLATEAU, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.WILDFLOWER_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(3)));

        addFeature(Biomes.SNOWY_TUNDRA, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));
        addFeature(Biomes.SNOWY_MOUNTAINS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)));

        addFeature(Biomes.SNOWY_TAIGA, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
        addFeature(Biomes.SNOWY_TAIGA_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
        addFeature(Biomes.SNOWY_TAIGA_MOUNTAINS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));

        addFeature(Biomes.SWAMP, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.cattail.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(20)));
        addFeature(Biomes.SWAMP, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.tall_cattail.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(10)));
        addFeature(Biomes.SWAMP, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.reed.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
        addFeature(Biomes.SWAMP, GenerationStage.Decoration.UNDERGROUND_ORES, BiomeBOP.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(BOPBlocks.mud.getDefaultState(), 8, 2, Lists.newArrayList(new BlockState[]{Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState()})), Placement.COUNT_TOP_SOLID, new FrequencyConfig(5)));
        addFeature(Biomes.SWAMP, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.WILLOW_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig((int)0.75, 0.1F, 1)));

        addFeature(Biomes.SWAMP_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.cattail.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(20)));
        addFeature(Biomes.SWAMP_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.tall_cattail.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(10)));
        addFeature(Biomes.SWAMP_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(Feature.RANDOM_PATCH, BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.reed.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)));
        addFeature(Biomes.SWAMP_HILLS, GenerationStage.Decoration.UNDERGROUND_ORES, BiomeBOP.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(BOPBlocks.mud.getDefaultState(), 8, 2, Lists.newArrayList(new BlockState[]{Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState()})), Placement.COUNT_TOP_SOLID, new FrequencyConfig(5)));
        addFeature(Biomes.SWAMP_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.WILLOW_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig((int)0.75, 0.1F, 1)));

        addFeature(Biomes.TAIGA, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
        addFeature(Biomes.TAIGA_HILLS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
        addFeature(Biomes.TAIGA_MOUNTAINS, GenerationStage.Decoration.VEGETAL_DECORATION, BiomeBOP.createDecoratedFeature(BOPBiomeFeatures.VIOLET_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));


        //Flammability
        registerFlammable(BOPBlocks.origin_leaves, 30, 60);
        registerFlammable(BOPBlocks.flowering_oak_leaves, 30, 60);
        registerFlammable(BOPBlocks.yellow_autumn_leaves, 30, 60);
        registerFlammable(BOPBlocks.orange_autumn_leaves, 30, 60);
        registerFlammable(BOPBlocks.maple_leaves, 30, 60);
        registerFlammable(BOPBlocks.fir_leaves, 30, 60);
        registerFlammable(BOPBlocks.fir_log, 5, 5);
        registerFlammable(BOPBlocks.fir_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_fir_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_fir_wood, 5, 5);
        registerFlammable(BOPBlocks.fir_planks, 5, 20);
        registerFlammable(BOPBlocks.fir_slab, 5, 20);
        registerFlammable(BOPBlocks.fir_stairs, 5, 20);
        registerFlammable(BOPBlocks.fir_fence, 5, 20);
        registerFlammable(BOPBlocks.fir_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.redwood_leaves, 30, 60);
        registerFlammable(BOPBlocks.redwood_log, 5, 5);
        registerFlammable(BOPBlocks.redwood_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_redwood_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_redwood_wood, 5, 5);
        registerFlammable(BOPBlocks.redwood_planks, 5, 20);
        registerFlammable(BOPBlocks.redwood_slab, 5, 20);
        registerFlammable(BOPBlocks.redwood_stairs, 5, 20);
        registerFlammable(BOPBlocks.redwood_fence, 5, 20);
        registerFlammable(BOPBlocks.redwood_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.white_cherry_leaves, 30, 60);
        registerFlammable(BOPBlocks.pink_cherry_leaves, 30, 60);
        registerFlammable(BOPBlocks.cherry_log, 5, 5);
        registerFlammable(BOPBlocks.cherry_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_cherry_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_cherry_wood, 5, 5);
        registerFlammable(BOPBlocks.cherry_planks, 5, 20);
        registerFlammable(BOPBlocks.cherry_slab, 5, 20);
        registerFlammable(BOPBlocks.cherry_stairs, 5, 20);
        registerFlammable(BOPBlocks.cherry_fence, 5, 20);
        registerFlammable(BOPBlocks.cherry_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.mahogany_leaves, 30, 60);
        registerFlammable(BOPBlocks.mahogany_log, 5, 5);
        registerFlammable(BOPBlocks.mahogany_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_mahogany_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_mahogany_wood, 5, 5);
        registerFlammable(BOPBlocks.mahogany_planks, 5, 20);
        registerFlammable(BOPBlocks.mahogany_slab, 5, 20);
        registerFlammable(BOPBlocks.mahogany_stairs, 5, 20);
        registerFlammable(BOPBlocks.mahogany_fence, 5, 20);
        registerFlammable(BOPBlocks.mahogany_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_leaves, 30, 60);
        registerFlammable(BOPBlocks.jacaranda_log, 5, 5);
        registerFlammable(BOPBlocks.jacaranda_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_jacaranda_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_jacaranda_wood, 5, 5);
        registerFlammable(BOPBlocks.jacaranda_planks, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_slab, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_stairs, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_fence, 5, 20);
        registerFlammable(BOPBlocks.jacaranda_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.palm_leaves, 30, 60);
        registerFlammable(BOPBlocks.palm_log, 5, 5);
        registerFlammable(BOPBlocks.palm_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_palm_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_palm_wood, 5, 5);
        registerFlammable(BOPBlocks.palm_planks, 5, 20);
        registerFlammable(BOPBlocks.palm_slab, 5, 20);
        registerFlammable(BOPBlocks.palm_stairs, 5, 20);
        registerFlammable(BOPBlocks.palm_fence, 5, 20);
        registerFlammable(BOPBlocks.palm_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.willow_leaves, 30, 60);
        registerFlammable(BOPBlocks.willow_log, 5, 5);
        registerFlammable(BOPBlocks.willow_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_willow_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_willow_wood, 5, 5);
        registerFlammable(BOPBlocks.willow_planks, 5, 20);
        registerFlammable(BOPBlocks.willow_slab, 5, 20);
        registerFlammable(BOPBlocks.willow_stairs, 5, 20);
        registerFlammable(BOPBlocks.willow_fence, 5, 20);
        registerFlammable(BOPBlocks.willow_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.dead_leaves, 30, 60);
        registerFlammable(BOPBlocks.dead_log, 5, 5);
        registerFlammable(BOPBlocks.dead_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_dead_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_dead_wood, 5, 5);
        registerFlammable(BOPBlocks.dead_planks, 5, 20);
        registerFlammable(BOPBlocks.dead_slab, 5, 20);
        registerFlammable(BOPBlocks.dead_stairs, 5, 20);
        registerFlammable(BOPBlocks.dead_fence, 5, 20);
        registerFlammable(BOPBlocks.dead_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.magic_leaves, 30, 60);
        registerFlammable(BOPBlocks.magic_log, 5, 5);
        registerFlammable(BOPBlocks.magic_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_magic_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_magic_wood, 5, 5);
        registerFlammable(BOPBlocks.magic_planks, 5, 20);
        registerFlammable(BOPBlocks.magic_slab, 5, 20);
        registerFlammable(BOPBlocks.magic_stairs, 5, 20);
        registerFlammable(BOPBlocks.magic_fence, 5, 20);
        registerFlammable(BOPBlocks.magic_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.umbran_leaves, 30, 60);
        registerFlammable(BOPBlocks.umbran_log, 5, 5);
        registerFlammable(BOPBlocks.umbran_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_umbran_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_umbran_wood, 5, 5);
        registerFlammable(BOPBlocks.umbran_planks, 5, 20);
        registerFlammable(BOPBlocks.umbran_slab, 5, 20);
        registerFlammable(BOPBlocks.umbran_stairs, 5, 20);
        registerFlammable(BOPBlocks.umbran_fence, 5, 20);
        registerFlammable(BOPBlocks.umbran_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.ethereal_leaves, 30, 60);
        registerFlammable(BOPBlocks.ethereal_log, 5, 5);
        registerFlammable(BOPBlocks.ethereal_wood, 5, 5);
        registerFlammable(BOPBlocks.stripped_ethereal_log, 5, 5);
        registerFlammable(BOPBlocks.stripped_ethereal_wood, 5, 5);
        registerFlammable(BOPBlocks.ethereal_planks, 5, 20);
        registerFlammable(BOPBlocks.ethereal_slab, 5, 20);
        registerFlammable(BOPBlocks.ethereal_stairs, 5, 20);
        registerFlammable(BOPBlocks.ethereal_fence, 5, 20);
        registerFlammable(BOPBlocks.ethereal_fence_gate, 5, 20);
        registerFlammable(BOPBlocks.rose, 60, 100);
        registerFlammable(BOPBlocks.violet, 60, 100);
        registerFlammable(BOPBlocks.lavender, 60, 100);
        registerFlammable(BOPBlocks.wildflower, 60, 100);
        registerFlammable(BOPBlocks.pink_daffodil, 60, 100);
        registerFlammable(BOPBlocks.pink_hibiscus, 60, 100);
        registerFlammable(BOPBlocks.glowflower, 60, 100);
        registerFlammable(BOPBlocks.wilted_lily, 60, 100);
        registerFlammable(BOPBlocks.blue_hydrangea, 60, 100);
        registerFlammable(BOPBlocks.goldenrod, 60, 100);
        registerFlammable(BOPBlocks.willow_vine, 15, 100);
        registerFlammable(BOPBlocks.sprout, 60, 100);
        registerFlammable(BOPBlocks.bush, 60, 100);
        registerFlammable(BOPBlocks.barley, 60, 100);
        registerFlammable(BOPBlocks.dune_grass, 60, 100);
        registerFlammable(BOPBlocks.desert_grass, 60, 100);
        registerFlammable(BOPBlocks.dead_grass, 60, 100);

        //Log Stripping
        registerStrippable(BOPBlocks.fir_log, BOPBlocks.stripped_fir_log);
        registerStrippable(BOPBlocks.fir_wood, BOPBlocks.stripped_fir_wood);
        registerStrippable(BOPBlocks.redwood_log, BOPBlocks.stripped_redwood_log);
        registerStrippable(BOPBlocks.redwood_wood, BOPBlocks.stripped_redwood_wood);
        registerStrippable(BOPBlocks.cherry_log, BOPBlocks.stripped_cherry_log);
        registerStrippable(BOPBlocks.cherry_wood, BOPBlocks.stripped_cherry_wood);
        registerStrippable(BOPBlocks.mahogany_log, BOPBlocks.stripped_mahogany_log);
        registerStrippable(BOPBlocks.mahogany_wood, BOPBlocks.stripped_mahogany_wood);
        registerStrippable(BOPBlocks.jacaranda_log, BOPBlocks.stripped_jacaranda_log);
        registerStrippable(BOPBlocks.jacaranda_wood, BOPBlocks.stripped_jacaranda_wood);
        registerStrippable(BOPBlocks.palm_log, BOPBlocks.stripped_palm_log);
        registerStrippable(BOPBlocks.palm_wood, BOPBlocks.stripped_palm_wood);
        registerStrippable(BOPBlocks.willow_log, BOPBlocks.stripped_willow_log);
        registerStrippable(BOPBlocks.willow_wood, BOPBlocks.stripped_willow_wood);
        registerStrippable(BOPBlocks.dead_log, BOPBlocks.stripped_dead_log);
        registerStrippable(BOPBlocks.dead_wood, BOPBlocks.stripped_dead_wood);
        registerStrippable(BOPBlocks.magic_log, BOPBlocks.stripped_magic_log);
        registerStrippable(BOPBlocks.magic_wood, BOPBlocks.stripped_magic_wood);
        registerStrippable(BOPBlocks.umbran_log, BOPBlocks.stripped_umbran_log);
        registerStrippable(BOPBlocks.umbran_wood, BOPBlocks.stripped_umbran_wood);
        registerStrippable(BOPBlocks.hellbark_log, BOPBlocks.stripped_hellbark_log);
        registerStrippable(BOPBlocks.hellbark_wood, BOPBlocks.stripped_hellbark_wood);
        registerStrippable(BOPBlocks.ethereal_log, BOPBlocks.stripped_ethereal_log);
        registerStrippable(BOPBlocks.ethereal_wood, BOPBlocks.stripped_ethereal_wood);

        //Compostable Blocks
        registerCompostable(0.3F, BOPBlocks.origin_sapling);
        registerCompostable(0.3F, BOPBlocks.origin_leaves);
        registerCompostable(0.3F, BOPBlocks.flowering_oak_sapling);
        registerCompostable(0.3F, BOPBlocks.flowering_oak_leaves);
        registerCompostable(0.3F, BOPBlocks.yellow_autumn_sapling);
        registerCompostable(0.3F, BOPBlocks.yellow_autumn_leaves);
        registerCompostable(0.3F, BOPBlocks.orange_autumn_sapling);
        registerCompostable(0.3F, BOPBlocks.orange_autumn_leaves);
        registerCompostable(0.3F, BOPBlocks.maple_sapling);
        registerCompostable(0.3F, BOPBlocks.maple_leaves);
        registerCompostable(0.3F, BOPBlocks.fir_sapling);
        registerCompostable(0.3F, BOPBlocks.fir_leaves);
        registerCompostable(0.3F, BOPBlocks.redwood_sapling);
        registerCompostable(0.3F, BOPBlocks.redwood_leaves);
        registerCompostable(0.3F, BOPBlocks.white_cherry_sapling);
        registerCompostable(0.3F, BOPBlocks.white_cherry_leaves);
        registerCompostable(0.3F, BOPBlocks.pink_cherry_sapling);
        registerCompostable(0.3F, BOPBlocks.pink_cherry_leaves);
        registerCompostable(0.3F, BOPBlocks.mahogany_sapling);
        registerCompostable(0.3F, BOPBlocks.mahogany_leaves);
        registerCompostable(0.3F, BOPBlocks.jacaranda_sapling);
        registerCompostable(0.3F, BOPBlocks.jacaranda_leaves);
        registerCompostable(0.3F, BOPBlocks.palm_sapling);
        registerCompostable(0.3F, BOPBlocks.palm_leaves);
        registerCompostable(0.3F, BOPBlocks.willow_sapling);
        registerCompostable(0.3F, BOPBlocks.willow_leaves);
        registerCompostable(0.3F, BOPBlocks.dead_sapling);
        registerCompostable(0.3F, BOPBlocks.dead_leaves);
        registerCompostable(0.3F, BOPBlocks.magic_sapling);
        registerCompostable(0.3F, BOPBlocks.magic_leaves);
        registerCompostable(0.3F, BOPBlocks.umbran_sapling);
        registerCompostable(0.3F, BOPBlocks.umbran_leaves);
        registerCompostable(0.3F, BOPBlocks.hellbark_sapling);
        registerCompostable(0.3F, BOPBlocks.hellbark_leaves);
        registerCompostable(0.3F, BOPBlocks.ethereal_sapling);
        registerCompostable(0.3F, BOPBlocks.ethereal_leaves);

        registerCompostable(0.65F, BOPBlocks.rose);
        registerCompostable(0.65F, BOPBlocks.violet);
        registerCompostable(0.65F, BOPBlocks.lavender);
        registerCompostable(0.65F, BOPBlocks.wildflower);
        registerCompostable(0.65F, BOPBlocks.orange_cosmos);
        registerCompostable(0.65F, BOPBlocks.pink_daffodil);
        registerCompostable(0.65F, BOPBlocks.pink_hibiscus);
        registerCompostable(0.65F, BOPBlocks.glowflower);
        registerCompostable(0.65F, BOPBlocks.wilted_lily);
        registerCompostable(0.65F, BOPBlocks.burning_blossom);

        registerCompostable(0.65F, BOPBlocks.blue_hydrangea);
        registerCompostable(0.65F, BOPBlocks.goldenrod);

        registerCompostable(0.5F, BOPBlocks.willow_vine);

        registerCompostable(0.5F, BOPBlocks.sprout);
        registerCompostable(0.5F, BOPBlocks.bush);
        registerCompostable(0.5F, BOPBlocks.barley);
        registerCompostable(0.5F, BOPBlocks.dune_grass);
        registerCompostable(0.5F, BOPBlocks.desert_grass);
        registerCompostable(0.5F, BOPBlocks.dead_grass);

        registerCompostable(0.5F, BOPBlocks.cattail);
        registerCompostable(0.5F, BOPBlocks.tall_cattail);

        registerCompostable(0.5F, BOPBlocks.reed);
        registerCompostable(0.5F, BOPBlocks.watergrass);
        registerCompostable(0.5F, BOPBlocks.mangrove_root);

        registerCompostable(0.5F, BOPBlocks.bramble);

        registerCompostable(0.65F, BOPBlocks.toadstool);
        registerCompostable(0.65F, BOPBlocks.glowshroom);
    }

    public static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, stripped_log);
    }

    public static void registerCompostable(float chance, IItemProvider itemIn) {
        ComposterBlock.CHANCES.put(itemIn.asItem(), chance);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFireInfo(blockIn, encouragement, flammability);
    }

    public static void addFeature(Biome biome, GenerationStage.Decoration decorationStage, ConfiguredFeature<?> featureIn)
    {
        biome.addFeature(decorationStage, featureIn);
    }
}
