package com.cs222ee.embeddedender.entity;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.entity.custom.EnderEggEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/***
 * Template taken from Kaupenjoe's tutorial
 * Source: https://github.com/Tutorials-By-Kaupenjoe/Minecraft-1.16.5/tree/37-customEntities
 ***/
public class ModEntityTypes {
    // List of Embedded Ender entity types
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, EmbeddedEnder.MOD_ID);

    // Add Ender Egg entity
    public static final RegistryObject<EntityType<EnderEggEntity>> ENDER_EGG =
            ENTITY_TYPES.register("ender_egg",
                    ()-> EntityType.Builder.<EnderEggEntity>create(EnderEggEntity::new, EntityClassification.MISC)
                            .build("ender_egg"));

    // Register list with forge
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
