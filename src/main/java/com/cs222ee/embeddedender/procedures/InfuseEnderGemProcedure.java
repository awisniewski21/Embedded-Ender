package com.cs222ee.embeddedender.procedures;

import com.cs222ee.embeddedender.entity.ModEntityTypes;
import com.cs222ee.embeddedender.entity.custom.EmbeddedEndermanEntity;
import com.cs222ee.embeddedender.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashMap;
import java.util.Map;

public class InfuseEnderGemProcedure {

    @Mod.EventBusSubscriber
    private static class GlobalTrigger {
        @SubscribeEvent
        public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
            Entity entity = event.getTarget();
            PlayerEntity sourceentity = event.getPlayer();
            if (event.getHand() != sourceentity.getActiveHand()) {
                return;
            }

            IWorld world = event.getWorld();
            Map<String, Object> dependencies = new HashMap<>();
            dependencies.put("x", entity.getPosX());
            dependencies.put("y", entity.getPosY());
            dependencies.put("z", entity.getPosZ());
            dependencies.put("world", world);
            dependencies.put("entity", entity);
            dependencies.put("sourceentity", sourceentity);
            dependencies.put("event", event);
            executeProcedure(dependencies);
        }
    }
    public static void executeProcedure(Map<String, Object> dependencies) {
        Entity entity = (Entity) dependencies.get("entity");
        Entity sourceentity = (Entity) dependencies.get("sourceentity");

        if (sourceentity instanceof PlayerEntity &&
                entity instanceof EndermanEntity &&
                !(entity instanceof EmbeddedEndermanEntity) &&
                ((PlayerEntity) sourceentity).getHeldItemMainhand().getItem() == ModItems.ENDER_GEM.get()) {
            if (!entity.world.isRemote() &&
                    entity.world.getServer() != null) {
                PlayerEntity player = (PlayerEntity) sourceentity;
                EndermanEntity enderman = (EndermanEntity) entity;
                ItemStack helditem = (ItemStack) player.getHeldItemMainhand();
                World world = (World) dependencies.get("world");

                // Remove enderman
                enderman.setInvisible(true);
                enderman.remove();

                // Spawn embedded enderman
                EmbeddedEndermanEntity embedded_enderman = new EmbeddedEndermanEntity(ModEntityTypes.EMBEDDED_ENDERMAN.get(), world);
                embedded_enderman.setLocationAndAngles(enderman.getPosX(), enderman.getPosY(), enderman.getPosZ(), enderman.rotationYaw, enderman.rotationPitch);
                embedded_enderman.setRotationYawHead(enderman.rotationYawHead);
                world.addEntity(embedded_enderman);

                // Remove ender gem
                player.addStat(Stats.ITEM_USED.get(helditem.getItem()));
                if (!player.abilities.isCreativeMode) {
                    helditem.shrink(1);
                }

                // Give infused ender gem
                ItemStack infused_ender_gem = new ItemStack(ModItems.INFUSED_ENDER_GEM.get());
                infused_ender_gem.setCount(1);
                ItemHandlerHelper.giveItemToPlayer(player, infused_ender_gem);
            }
        }
    }
}
