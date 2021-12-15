package com.dm66.leaguecraft.champion;

import com.dm66.leaguecraft.ability.Ability;

public interface Champion
{
    Ability basicAttack = null;
    Ability QAbility = null;
    Ability WAbility = null;
    Ability EAbility = null;
    Ability RAbility = null;

    double baseHealth = 0;
    double baseHealthRegen = 0;

    double baseMana = 0;
    double baseManaRegen = 0;

    double baseAttackDamage = 0;
    double baseAttackSpeed = 0;
    double baseAbilityPower = 0;
    double baseArmor = 0;
    double baseMagicResist = 0;

    double baseMovementSpeed = 0;

    double baseQCoolDown = 0;
    double baseWCoolDown = 0;
    double baseECoolDown = 0;
    double baseRCoolDown = 0;

    double basePassiveCoolDown = 0;
}
