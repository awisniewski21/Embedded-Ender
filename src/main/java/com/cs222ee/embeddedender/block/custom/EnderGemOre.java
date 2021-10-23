package com.cs222ee.embeddedender.block.custom;

import com.cs222ee.embeddedender.block.ModBlocks;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class EnderGemOre extends OreBlock {
    public EnderGemOre(OreBlock.Properties properties) {
        super(properties);
    }

    /***
     * Defines how much experience is drop when Ender Gem Ore block is mined
     ***/
    protected int getExperience(Random rand) {
        if (this == ModBlocks.ENDER_GEM_ORE.get()) {
            return MathHelper.nextInt(rand, 5, 10);
        } else {
            return super.getExperience(rand);
        }
    }
}
