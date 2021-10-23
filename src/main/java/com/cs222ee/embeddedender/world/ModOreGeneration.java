package com.cs222ee.embeddedender.world;

import com.cs222ee.embeddedender.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.Random;

/***
 * Template taken from TurtyWurty's tutorial
 * Source: https://github.com/DaRealTurtyWurty/1.16-Tutorial-Mod
 ***/
public class ModOreGeneration {

    // Configure Ender Gem Ore generation rules
    public static void generateOres(final BiomeLoadingEvent event) {
        if (event.getCategory().equals(Biome.Category.THEEND)) {
            generateOre(event.getGeneration(), ENDER_GEM_ORE_FEATURE, new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.ENDER_GEM_ORE.get().getDefaultState(), 15, 1, 20, 3);
        }
    }

    /***
     * Set Ender Gem Ore generation rules
     *      1. Ender Gem Ore spawns on the bottom of islands in the End
     *      2. At least one ore block must be exposed to air
     ***/
    private static final Feature<OreFeatureConfig> ENDER_GEM_ORE_FEATURE = new OreFeature(OreFeatureConfig.CODEC) {
        @Override
        public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
            // Only generate Ender Gem Ore in The End and at least one block must be exposed to air
            if (world.getWorld().getDimensionKey() == World.THE_END && isExposedToAir(world, pos)) {
                return super.generate(world, generator, rand, pos, config);
            } else {
                return false;
            }
        }
    };

    /***
     * Set ore generation settings
     *      settings - Biome Generation Settings
     *      oreFeature - Ore feature codec
     *      fillerType - what blocks to replace with ore block
     *      state - block state of ore block (ie. solid, liquid, air)
     *      veinSize - average number of ore blocks in a group
     *      minHeight - minimum height to spawn ore blocks at
     *      maxHeight - maximum height to spawn ore blocks at
     *      avgGroupsPerChunk - average amount of ore groups per chunk
     ***/
    private static void generateOre(BiomeGenerationSettingsBuilder settings, Feature<OreFeatureConfig> oreFeature,
                                    RuleTest fillerType, BlockState state,
                                    int veinSize, int minHeight, int maxHeight, int avgGroupsPerChunk) {
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                oreFeature.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))
                                .square().count(avgGroupsPerChunk)));
    }

    /***
     * Check if a block has at least one side that is exposed to air
     * Args:
     *      world - the current world based on the seed
     *      pos - position that the block to be checked is at
     ***/
    private static boolean isExposedToAir(ISeedReader world, BlockPos pos) {
        return world.isAirBlock(pos.up()) || world.isAirBlock(pos.north()) || world.isAirBlock(pos.east()) || world.isAirBlock(pos.south()) || world.isAirBlock(pos.west()) || world.isAirBlock(pos.down());
    }

    /***
     * Register ore generation listener with the (forge) event bus
     ***/
    public static void register(IEventBus eventBus) {
        eventBus.addListener(EventPriority.HIGH, ModOreGeneration::generateOres);
    }
}
