package com.dm66.leaguecraft.effect;

import com.dm66.leaguecraft.Summoner;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectUtils;

public class StasisEffect extends Effect
{
    public StasisEffect()
    {
        this(EffectType.NEUTRAL, 0xf5ef42);
    }

    public StasisEffect(EffectType effectType, int color)
    {
        super(effectType, color);
    }

    @Override
    public void performEffect(LivingEntity entity, int amp)
    {
        if(entity instanceof PlayerEntity)
        {
            Summoner s = Summoner.getSummoner((PlayerEntity) entity);
            if(s == null) return ;
            s.health -= 10;
        }
    }

    @Override
    public boolean isReady(int duration, int amp)
    {
        if(duration % 20 == 0) return true;
        return false;
    }
}
