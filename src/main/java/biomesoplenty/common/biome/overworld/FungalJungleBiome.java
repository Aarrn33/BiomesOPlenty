/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class FungalJungleBiome extends BiomeTemplate
{
    public FungalJungleBiome()
    {
        this.addWeight(BOPClimates.TROPICAL, 1);
        this.setBeachBiome(null);
        this.setGrassColorFunction(this::getGrassColor);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.JUNGLE).depth(0.15F).scale(0.1F).temperature(0.9F).downfall(0.9F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(4445678).waterFogColor(270131).fogColor(12638463).skyColor(calculateSkyColor(0.9F)).grassColorOverride(0x4AA2F9).foliageColorOverride(0x4ADCF9).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        // Structures
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(builder);

        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.FUNGAL_JUNGLE_TREES);

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.FERN_GRASS_16);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.GLOWSHROOM_NORMAL);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.HUGE_GLOWSHROOM);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.LARGE_FERN_7);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.MYCELIUM_SPLATTER);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.SMALL_GLOWSHROOM);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.TALL_GRASS_12);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_NORMAL);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_NORMAL);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        // Entities
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.MOOSHROOM, 8, 4, 8));
        builder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
    }

    public int getGrassColor(double x, double z)
    {
        double d0 = Biome.BIOME_INFO_NOISE.getValue(x * 0.0225D, z * 0.0225D, false);
        return d0 < -0.1D ? 0x4AA2F9 : 0x4DD6CA;
    }
}
