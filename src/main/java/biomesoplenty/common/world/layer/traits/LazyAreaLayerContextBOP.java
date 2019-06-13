/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.LazyArea;

public class LazyAreaLayerContextBOP extends LazyAreaLayerContext implements IBOPContextExtended<LazyArea>
{
    private long worldSeed;

    public LazyAreaLayerContextBOP(int maxCacheSize, long seed, long seedModifier)
    {
        super(maxCacheSize, seed, seedModifier);
        this.worldSeed = seed;
    }

    @Override
    public long getWorldSeed()
    {
        return this.worldSeed;
    }
}
