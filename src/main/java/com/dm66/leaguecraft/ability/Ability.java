package com.dm66.leaguecraft.ability;

import com.dm66.leaguecraft.Summoner;

public interface Ability
{
    public double coolDown = 0;
    public boolean isUltimate = false;

    public void cast(Summoner summoner);

}
