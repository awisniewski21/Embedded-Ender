package com.cs222ee.embeddedender;

import com.cs222ee.embeddedender.block.ModBlocks;
import com.cs222ee.embeddedender.container.ModContainers;
import com.cs222ee.embeddedender.entity.ModEntityTypes;
import com.cs222ee.embeddedender.entity.render.EmbeddedEndermanRenderer;
import com.cs222ee.embeddedender.entity.render.EnderChickenRenderer;
import com.cs222ee.embeddedender.item.ModItems;
import com.cs222ee.embeddedender.screen.EmbenderScreen;
import com.cs222ee.embeddedender.tileentity.ModTileEntities;
import com.cs222ee.embeddedender.world.ModOreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EmbeddedEnder.MOD_ID)
public class EmbeddedEnder {

    // Mod ID
    public static final String MOD_ID = "embeddedender";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public EmbeddedEnder() {
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Forge event bus
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        // Register Embedded Ender items, blocks, entities, and features (ore generation)
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModTileEntities.register(modEventBus);
        ModContainers.register(modEventBus);
        ModOreGeneration.register(forgeEventBus);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);
        modEventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Do something that can only be done on the client
        registerEntityModels(event.getMinecraftSupplier());

        ScreenManager.registerFactory(ModContainers.EMBENDER_CONTAINER.get(),
                EmbenderScreen::new);
    }

    // Register entity models on the client
    private void registerEntityModels(Supplier<Minecraft> minecraft) {
        ItemRenderer renderer = minecraft.get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ENDER_EGG.get(),
                (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ENDER_CHICKEN.get(),
                EnderChickenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.EMBEDDED_ENDERMAN.get(),
                EmbeddedEndermanRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("embeddedender", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        //LOGGER.info("Got IMC {}", event.getIMCStream().
                //map(m -> m.getMessageSupplier().get()).
                //collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
