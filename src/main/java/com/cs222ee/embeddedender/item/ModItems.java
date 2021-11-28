package com.cs222ee.embeddedender.item;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.entity.ModEntityTypes;
import com.cs222ee.embeddedender.item.custom.EnderEgg;
import com.cs222ee.embeddedender.item.custom.ModSpawnEggItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
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

    public static final RegistryObject<Item> ENDER_EGG = ITEMS.register("ender_egg",
            () -> new EnderEgg(new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> INFUSED_ENDER_GEM = ITEMS.register("infused_ender_gem",
            () -> new Item(new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM = ITEMS.register("ender_gem",
            () -> new Item(new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_SWORD = ITEMS.register("ender_gem_sword",
            () -> new SwordItem(ModItemTier.ENDER_GEM, 4, -2.2f,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_PICKAXE = ITEMS.register("ender_gem_pickaxe",
            () -> new PickaxeItem(ModItemTier.ENDER_GEM, 2, -2.4f,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_SHOVEL = ITEMS.register("ender_gem_shovel",
            () -> new ShovelItem(ModItemTier.ENDER_GEM, 1.7F, -2.7f,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_AXE = ITEMS.register("ender_gem_axe",
            () -> new AxeItem(ModItemTier.ENDER_GEM, 5.7F, -2.7f,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_HOE = ITEMS.register("ender_gem_hoe",
            () -> new HoeItem(ModItemTier.ENDER_GEM, -3, 0f,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_BOOTS = ITEMS.register("ender_gem_boots",
            () -> new ArmorItem(ModArmorMaterial.ENDER_GEM, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_CHESTPLATE = ITEMS.register("ender_gem_chestplate",
            () -> new ArmorItem(ModArmorMaterial.ENDER_GEM, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_LEGGINGS = ITEMS.register("ender_gem_leggings",
            () -> new ArmorItem(ModArmorMaterial.ENDER_GEM, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    public static final RegistryObject<Item> ENDER_GEM_HELMET = ITEMS.register("ender_gem_helmet",
            () -> new ArmorItem(ModArmorMaterial.ENDER_GEM, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));


    // 0x231336 - Primary spawn egg color is dark purple
    // 0xfd0bfd - Secondary spawn egg color is bright pink/purple
    public static final RegistryObject<ModSpawnEggItem> ENDER_CHICKEN_SPAWN_EGG =
            ITEMS.register("ender_chicken_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.ENDER_CHICKEN,
                    0x231336, 0xfd0bfd,
                    new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    // 0x000000 - Primary spawn egg color is black
    // 0x160016 - Secondary spawn egg color is dark purple
    public static final RegistryObject<ModSpawnEggItem> EMBEDDED_ENDERMAN_SPAWN_EGG =
            ITEMS.register("embedded_enderman_spawn_egg",
                    () -> new ModSpawnEggItem(ModEntityTypes.EMBEDDED_ENDERMAN,
                            0x000000, 0x160016,
                            new Item.Properties().group(ModItemGroup.EMBEDDED_ENDER_GROUP)));

    // Register list with (Mod) event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
