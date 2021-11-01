package com.cs222ee.embeddedender.entity.render;

import com.cs222ee.embeddedender.EmbeddedEnder;
import com.cs222ee.embeddedender.entity.custom.EnderChickenEntity;
import com.cs222ee.embeddedender.entity.model.EnderChickenModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

// Based off ChickenRenderer.Java
public class EnderChickenRenderer extends MobRenderer<EnderChickenEntity, EnderChickenModel<EnderChickenEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(EmbeddedEnder.MOD_ID, "textures/entity/ender_chicken.png");

    public EnderChickenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EnderChickenModel<>(), 0.3F);
    }

    // Returns the location of an entity's texture.
    @Override
    public ResourceLocation getEntityTexture(EnderChickenEntity entity) {
        return TEXTURE;
    }

    // Defines what float the third param in setRotationAngles of ModelBase is
    protected float handleRotationFloat(ChickenEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}
