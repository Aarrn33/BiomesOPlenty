/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.core;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;

import java.awt.*;
import java.util.Calendar;

public class ClientProxy extends CommonProxy
{
    public static boolean isAprilFools = false;

    public ClientProxy()
    {

    }

    @Override
    public void init()
    {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 4 && calendar.get(5) == 1) { isAprilFools = true; }

        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        //Grass Coloring
        blockColors.register((state, world, pos, tintIndex) ->
            world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D),
            BOPBlocks.sprout, BOPBlocks.watergrass, BOPBlocks.potted_sprout);
        
        //Foliage Coloring
        blockColors.register((state, world, pos, tintIndex) ->
	        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
	        BOPBlocks.bush, BOPBlocks.flowering_oak_leaves, BOPBlocks.mahogany_leaves, BOPBlocks.palm_leaves,
	        BOPBlocks.willow_leaves, BOPBlocks.willow_vine);

        //Rainbow Birch Leaf Coloring
        blockColors.register((state, world, pos, tintIndex) ->
            world != null && pos != null ? getRainbowBirchColor(world, pos) : FoliageColors.getDefaultColor(),
            BOPBlocks.rainbow_birch_leaves);
        
        //Item Coloring
        itemColors.register((stack, tintIndex) -> {
            BlockState BlockState = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(BlockState, null, null, tintIndex); }, 
        	BOPBlocks.sprout, BOPBlocks.bush, BOPBlocks.flowering_oak_leaves, BOPBlocks.mahogany_leaves,
        	BOPBlocks.palm_leaves, BOPBlocks.willow_leaves, BOPBlocks.willow_vine);
    }

    public static int getRainbowBirchColor(IBlockDisplayReader world, BlockPos pos)
    {
        Color foliage = Color.getHSBColor((((float)pos.getX() + MathHelper.sin(((float)pos.getZ() + (float)pos.getX()) / 35) * 35) % 150) / 150, 0.6F, 1.0F);
        if (isAprilFools) { foliage = Color.WHITE; }

        return foliage.getRGB();
    }
}
