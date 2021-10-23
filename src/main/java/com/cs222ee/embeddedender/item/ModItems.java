package com.cs222ee.embeddedender.item;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.item.custom.EnderEgg;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/***
 * Template taken from Kaupenjoe's tutorial
 * Source: https://tutorialsbykaupenjoe.net/minecraft-forge-modding-116/adding-a-custom-item-in-minecraft-1-16-5-with-forge/
 ***/
public class ModItems {

    // List of Embedded Ender items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EmbeddedEnder.MOD_ID);

    // Add Ender Egg
    public static final RegistryObject<Item> ENDER_EGG = ITEMS.register("ender_egg",
            () -> new EnderEgg(new Item.Properties().group(ItemGroup.MISC)));

    // Register list with Forge
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
