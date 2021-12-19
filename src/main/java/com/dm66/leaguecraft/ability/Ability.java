package com.dm66.leaguecraft.ability;

import com.dm66.leaguecraft.Summoner;
import com.dm66.leaguecraft.item.ability_items.AbilityItem;
import net.minecraft.entity.Entity;

public interface Ability
{
    public double coolDown = 0;
    public boolean isUltimate = false;
    public AbilityItem item = null;

    public void cast(Summoner summoner);
    public void onHit(Entity entity);

}
