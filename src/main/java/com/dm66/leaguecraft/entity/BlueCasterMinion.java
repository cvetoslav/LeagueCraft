package com.dm66.leaguecraft.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public class BlueCasterMinion extends MobEntity
{
    protected BlueCasterMinion(EntityType<? extends MobEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 100.0d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 100.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5d);
    }
}
