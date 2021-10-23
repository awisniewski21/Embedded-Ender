package com.cs222ee.embeddedender.block;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.block.custom.EnderGemOre;
import com.cs222ee.embeddedender.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/***
 * Template taken from TurtyWurty's tutorial
 * Source: https://tutorialsbykaupenjoe.net/minecraft-forge-modding-116/add-custom-block-in-minecraft-1-16-5-with-forge/
 ***/
public class ModBlocks {

    // List of Embedded Ender blocks
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, EmbeddedEnder.MOD_ID);

    // Add Ender Gem Ore
    public static final RegistryObject<Block> ENDER_GEM_ORE = registerBlock("ender_gem_ore",
            () -> new EnderGemOre(Block.Properties.create(Material.ROCK)
                    .sound(SoundType.STONE)
                    .hardnessAndResistance(10f, 15f)
                    .setLightLevel(s -> 0)
                    .harvestLevel(4) // Requires netherite
                    .harvestTool(ToolType.PICKAXE)
                    .setRequiresTool()));

    // Register block as well as the item associated with the block
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Register the item associated with the block
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ItemGroup.MATERIALS)));
    }

    // Register list with Forge
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
