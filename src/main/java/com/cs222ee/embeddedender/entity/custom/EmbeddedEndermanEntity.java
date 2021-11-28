package com.cs222ee.embeddedender.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.world.World;

public class EmbeddedEndermanEntity extends EndermanEntity {

    public EmbeddedEndermanEntity(EntityType<? extends EndermanEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return func_234287_m_();
    }
}
