package com.cs222ee.embeddedender.events;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.entity.ModEntityTypes;
import com.cs222ee.embeddedender.entity.custom.EnderChickenEntity;
import com.cs222ee.embeddedender.item.custom.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/***
 * Template taken from Kaupenjoe's tutorial
 * Source: https://github.com/Tutorials-By-Kaupenjoe/Minecraft-1.16.5/tree/37-customEntities
 ***/
@Mod.EventBusSubscriber(modid = EmbeddedEnder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.ENDER_CHICKEN.get(), EnderChickenEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }
}
