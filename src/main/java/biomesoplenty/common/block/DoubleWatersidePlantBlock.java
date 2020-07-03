/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.PlantType;

import java.util.Iterator;

public class DoubleWatersidePlantBlock extends DoublePlantBlockBOP
{
    public DoubleWatersidePlantBlock(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
    	return PlantType.BEACH;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldReader, BlockPos pos)
    {
        if (state.getBlock() != this) return super.canSurvive(state, worldReader, pos);
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER)
        {
            BlockState soil = worldReader.getBlockState(pos.below());
            if (soil.canSustainPlant(worldReader, pos.below(), Direction.UP, this))
            {
                BlockPos blockpos = pos.below();
                Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

                BlockState BlockState;
                FluidState ifluidstate;
                do {
                    if (!var7.hasNext()) {
                        return false;
                    }

                    Direction dir = (Direction)var7.next();
                    BlockState = worldReader.getBlockState(blockpos.relative(dir));
                    ifluidstate = worldReader.getFluidState(blockpos.relative(dir));
                } while(!ifluidstate.is(FluidTags.WATER) && BlockState.getBlock() != Blocks.FROSTED_ICE);

                return true;
            }
        }
        else
        {
           BlockState below = worldReader.getBlockState(pos.below());
           return below.getBlock() == this && below.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
        
        return false;
    }
}
