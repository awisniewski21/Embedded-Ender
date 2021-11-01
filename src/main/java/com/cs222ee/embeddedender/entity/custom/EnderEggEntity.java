package com.cs222ee.embeddedender.entity.custom;

import com.cs222ee.embeddedender.entity.ModEntityTypes;
import com.cs222ee.embeddedender.item.ModItems;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

// Based off EggEntity.Java
public class EnderEggEntity extends ProjectileItemEntity {
    public static final EntityType ENDER_EGG = (EntityType.Builder.<EnderEggEntity>create(EnderEggEntity::new, EntityClassification.MISC)
            .size(0.25f, 0.25f)
            .trackingRange(4)
            .updateInterval(10)
            .build("ender_egg"));

    public EnderEggEntity(EntityType<? extends EnderEggEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EnderEggEntity(World worldIn, LivingEntity throwerIn) {
        super(ModEntityTypes.ENDER_EGG.get(), throwerIn, worldIn);
    }

    public EnderEggEntity(World worldIn, double x, double y, double z) {
        super(ModEntityTypes.ENDER_EGG.get(), x, y, z, worldIn);
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            double d0 = 0.08D;

            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItem()), this.getPosX(), this.getPosY(), this.getPosZ(), ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }

    /***
     * Called when projectile hits an entity
     ***/
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        result.getEntity().attackEntityFrom(DamageSource.causeThrownDamage(this, this.getShooter()), 0.0F);
    }

    /***
     * Called when projectile hits a block or entity.
     ***/
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            if (this.rand.nextInt(8) == 0) {
                int i = 1;
                if (this.rand.nextInt(32) == 0) {
                    i = 4;
                }

                for (int j = 0; j < i; ++j) {
                    EnderChickenEntity enderChickenEntity = ModEntityTypes.ENDER_CHICKEN.get().create(this.world);
                    assert enderChickenEntity != null;
                    enderChickenEntity.setGrowingAge(-24000);
                    enderChickenEntity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, 0.0F);
                    this.world.addEntity(enderChickenEntity);
                }
            }

            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ENDER_EGG.get().asItem();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}