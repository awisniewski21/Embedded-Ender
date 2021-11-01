package com.cs222ee.embeddedender.item;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.entity.ModEntityTypes;
import com.cs222ee.embeddedender.item.custom.EnderEgg;
import com.cs222ee.embeddedender.item.custom.ModSpawnEggItem;
import net.minecraft.item.Item;
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
            () -> new EnderEgg(new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    // Add Ender Chicken spawn egg
    // 0x231336 - Primary spawn egg color is dark purple
    // 0xfd0bfd - Secondary spawn egg color is bright pink/purple
    public static final RegistryObject<ModSpawnEggItem> ENDER_CHICKEN_SPAWN_EGG =
            ITEMS.register("ender_chicken_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.ENDER_CHICKEN,
                    0x231336, 0xfd0bfd,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    // Register list with (Mod) event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
