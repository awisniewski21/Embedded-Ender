package com.cs222ee.embeddedender.entity.model;

import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

;

@OnlyIn(Dist.CLIENT)
public class EmbeddedEndermanModel<T extends LivingEntity> extends EndermanModel<T> {
    public EmbeddedEndermanModel(float scale) {
        super(scale);
    }
}