/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.world.biome.BiomeFeatureHelper;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.common.world.gen.feature.StandardGrassFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CherryBlossomGroveBiome extends BiomeBOP
{
    public CherryBlossomGroveBiome()
    {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS)).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(-0.1F).scale(0.1F).temperature(0.6F).downfall(0.9F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).parent((String)null));

        // Structures
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(this);
        this.addStructureStart(DefaultBiomeFeatures.RUINED_PORTAL_STANDARD);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(this);
        DefaultBiomeFeatures.addDefaultLakes(this);
        DefaultBiomeFeatures.addDefaultMonsterRoom(this);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(this);
        DefaultBiomeFeatures.addDefaultOres(this);

        ////////////////////////////////////////////////////////////

        // Vegetation
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPBiomeFeatures.WHITE_CHERRY_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_WITH_BEES_002_CONFIG).weighted(0.1F), BOPBiomeFeatures.PINK_CHERRY_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_WITH_BEES_002_CONFIG).weighted(0.2F), BOPBiomeFeatures.BIG_WHITE_CHERRY_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.3F), BOPBiomeFeatures.FLOWERING_OAK_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_WITH_BEES_002_CONFIG).weighted(0.15F), BOPBiomeFeatures.FLOWERING_BUSH.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG).weighted(0.3F)), BOPBiomeFeatures.BIG_PINK_CHERRY_TREE.configured(DefaultBiomeFeatures.NORMAL_TREE_CONFIG))).decorated(Placement.COUNT_EXTRA_HEIGHTMAP.configured(new AtSurfaceWithExtraConfig(5, 0.3F, 1))));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.PEONY.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(1))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LILAC.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(4))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPBiomeFeatures.CHERRY_BLOSSOM_GROVE_FLOWERS.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_32.configured(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new StandardGrassFeature(NoFeatureConfig.CODEC).configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(11))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPBiomeFeatures.SHORT_BAMBOO.configured(IFeatureConfig.NONE).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.BROWN_MUSHROOM.defaultBlockState())).decorated(Placement.CHANCE_HEIGHTMAP_DOUBLE.configured(new ChanceConfig(2))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.RED_MUSHROOM.defaultBlockState())).decorated(Placement.CHANCE_HEIGHTMAP_DOUBLE.configured(new ChanceConfig(4))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(DefaultBiomeFeatures.SUGAR_CANE_CONFIG).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(15))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(DefaultBiomeFeatures.WATERLILLY_CONFIG).decorated(Placement.COUNT_HEIGHTMAP_DOUBLE.configured(new FrequencyConfig(5))));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new SeaGrassConfig(32, 0.4D)).decorated(Placement.TOP_SOLID_HEIGHTMAP.configured(IPlacementConfig.NONE)));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(this);
        DefaultBiomeFeatures.addSurfaceFreezing(this);

        // Entities
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PANDA, 80, 1, 2));
        this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
        
        this.addWeight(BOPClimates.COOL_TEMPERATE, 1);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double x, double z)
    {
    	return 0x85CE71;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()
    {
    	return 0x63BF66;
    }
}
