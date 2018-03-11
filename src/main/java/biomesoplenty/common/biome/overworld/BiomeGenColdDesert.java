/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.world.generator.GeneratorBlobs;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorSplotches;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenColdDesert extends BOPOverworldBiome
{    
    
	public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    
    public BiomeGenColdDesert()
    {
        super("cold_desert", new PropsBuilder("Cold Desert").withGuiColour(0xB3AF9B).withTemperature(0.2F).withRainfall(0.0F).withRainDisabled());
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(5, 10).sidewaysNoise(0.7D);
        
        this.topBlock = Blocks.GRAVEL.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = Blocks.SNOW.getDefaultState();
        
        this.canGenerateRivers = false;

        this.addWeight(BOPClimates.ICE_CAP, 10);

        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPolarBear.class, 1, 1, 2));
        
        // gravel, stone and boulders
        IBlockPosQuery surface = new BlockQueryBlock(Blocks.STONE, Blocks.GRAVEL);
        this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(6).splotchSize(24).placeOn(surface).replace(surface).with(Blocks.STONE.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        this.addGenerator("boulders", GeneratorStage.SAND_PASS2, (new GeneratorBlobs.Builder()).amountPerChunk(0.2F).placeOn(surface).with(Blocks.COBBLESTONE.getDefaultState()).minRadius(1.0F).maxRadius(3.0F).numBalls(4).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        this.addGenerator("hard_ice_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(1.0F).replace(surface).with(BOPBlocks.hard_ice.getDefaultState()).create());
        
        // gem
        this.addGenerator("tanzanite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TANZANITE).create());
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = conf.getBlockState("alternateTopBlock", this.alternateTopBlock);
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
        
    	if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("tanzanite");}
    	
    	if (!settings.isEnabled(GeneratorType.FOLIAGE)) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        this.topBlock = (noise + rand.nextDouble() * 3.0D > 1.8D) ? this.alternateTopBlock : this.usualTopBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
    
}
