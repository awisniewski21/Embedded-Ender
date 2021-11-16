package com.cs222ee.embeddedender.tileentity;


import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/***
 * Taken from Kaupenjoe's tutorial
 * Source: https://www.youtube.com/watch?v=4YgGqJgz2VY&ab_channel=TutorialsbyKaupenjoe
 ***/
public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EmbeddedEnder.MOD_ID);

    public static RegistryObject<TileEntityType<EmbenderTile>> EMBENDER_TILE =
            TILE_ENTITIES.register("embender_tile", () -> TileEntityType.Builder.create(
                    EmbenderTile::new, ModBlocks.EMBENDER.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
