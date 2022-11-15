package com.dm66.leaguecraft.effect;

import com.dm66.leaguecraft.Summoner;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class StasisEffect extends MobEffect
{
    public StasisEffect()
    {
        this(MobEffectCategory.NEUTRAL, 0xf5ef42);
    }

    public StasisEffect(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amp)
    {
        if(entity instanceof Player)
        {
            // TODO
            /*Summoner s = Summoner.getSummoner((Player) entity);
            if(s == null) return ;
            s.health -= 10;*/
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
