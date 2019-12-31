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
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BrambleBlock extends SixWayBlock
{
    public BrambleBlock(Block.Properties builder)
    {
        super(0.25F, builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.makeConnections(context.getLevel(), context.getClickedPos());
    }

    public BlockState makeConnections(IBlockReader reader, BlockPos pos)
    {
        BlockState block = reader.getBlockState(pos.below());
        BlockState block1 = reader.getBlockState(pos.above());
        BlockState block2 = reader.getBlockState(pos.north());
        BlockState block3 = reader.getBlockState(pos.east());
        BlockState block4 = reader.getBlockState(pos.south());
        BlockState block5 = reader.getBlockState(pos.west());
        return this.defaultBlockState()
        		.setValue(DOWN, Boolean.valueOf(block.getBlock() == this || Block.isShapeFullBlock(block.getCollisionShape(reader, pos.below()))))
        		.setValue(UP, Boolean.valueOf(block1.getBlock() == this || Block.isShapeFullBlock(block1.getCollisionShape(reader, pos.above()))))
        		.setValue(NORTH, Boolean.valueOf(block2.getBlock() == this || Block.isShapeFullBlock(block2.getCollisionShape(reader, pos.north()))))
        		.setValue(EAST, Boolean.valueOf(block3.getBlock() == this || Block.isShapeFullBlock(block3.getCollisionShape(reader, pos.east()))))
        		.setValue(SOUTH, Boolean.valueOf(block4.getBlock() == this || Block.isShapeFullBlock(block4.getCollisionShape(reader, pos.south()))))
        		.setValue(WEST, Boolean.valueOf(block5.getBlock() == this || Block.isShapeFullBlock(block5.getCollisionShape(reader, pos.west()))));
    }

     @Override
     public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
     {
    	Block block = facingState.getBlock();
     	boolean flag = block == this || Block.isShapeFullBlock(facingState.getCollisionShape(worldIn, facingPos));
     	return stateIn.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(flag));
     }

     @Override
     public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
     {
         if (entityIn instanceof PlayerEntity)
         {
             PlayerEntity playerEntity = (PlayerEntity) entityIn;
             playerEntity.hurt(DamageSource.CACTUS, 1.0F);
         }
      }

     @Override
     protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
     {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
     }

     @Override
     public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
     {
        return false;
     }
}
