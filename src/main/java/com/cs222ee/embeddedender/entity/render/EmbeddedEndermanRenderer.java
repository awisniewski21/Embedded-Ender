package com.cs222ee.embeddedender.entity.render;

import com.cs222ee.embeddedender.EmbeddedEnder;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;

public class EmbeddedEndermanRenderer extends EndermanRenderer {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(EmbeddedEnder.MOD_ID, "textures/entity/embedded_enderman.png");

    public EmbeddedEndermanRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getEntityTexture(EndermanEntity entity) {
        return TEXTURE;
    }
}
