package com.cs222ee.embeddedender.container;

import com.cs222ee.embeddedender.EmbeddedEnder;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/***
 * Taken from KaupenJoes's tutorial
 * Source: https://www.youtube.com/watch?v=4YgGqJgz2VY&ab_channel=TutorialsbyKaupenjoe
 ***/

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, EmbeddedEnder.MOD_ID);

    public static final RegistryObject<ContainerType<EmbenderContainer>> EMBENDER_CONTAINER
            = CONTAINERS.register("embender_container",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new EmbenderContainer(windowId, world, pos, inv, inv.player);
            }));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
