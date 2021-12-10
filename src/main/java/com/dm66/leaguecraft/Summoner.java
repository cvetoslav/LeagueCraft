package com.dm66.leaguecraft;

import com.dm66.leaguecraft.effect.ModEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class Summoner
{
    public static HashMap<PlayerEntity, Summoner> summoners = new HashMap<>();

    public PlayerEntity player;
    public boolean inGame;

    public double health;
    public double maxHealth;
    public double healthRegen;

    public double shield;
    public double magicShield;
    public double physicalShield;

    public double mana;
    public double maxMana;
    public double manaRegen;

    public double attackDamage;
    public double attackSpeed;
    public double abilityPower;
    public double armor;
    public double magicResist;

    public double movementSpeed;

    // TODO: add champion specific base stats
    // public Champion champion;

    public Summoner(PlayerEntity player)
    {
        this.player = player;
        inGame = true;
        health = 900;
        maxHealth = 1000;
        healthRegen = 0.5;
        shield = magicShield = physicalShield = 0;
        mana = 800;
        maxMana = 880;
        manaRegen = 0.2;
        attackDamage = 69;
        attackSpeed = 0.7;
        abilityPower = 42;
        armor = 15;
        magicResist = 13;
        movementSpeed = 300;
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event)
    {
        Summoner s = Summoner.getSummoner(event.player);
        s.health += s.healthRegen / 20;
        if(s.health > s.maxHealth) s.health = s.maxHealth;
        s.mana += s.manaRegen / 20;
        if(s.mana > s.maxMana) s.mana = s.maxMana;
    }

    public void addEffect(Effect effect, int duration)
    {
        if(player.getActivePotionEffect(effect) == null && inGame)
        {
            player.addPotionEffect(new EffectInstance(effect, duration));
        }
    }

    public void dealDamage(double physicalDamage, double magicDamage, double trueDamage)
    {
        shield -= trueDamage;

        double dmgMult;
        if(armor >= 0) dmgMult = 100.0 / (100.0 + armor);
        else dmgMult = 2.0 - 100.0 / (100.0 - armor);
        physicalShield -= physicalDamage * dmgMult;
        if(physicalShield < 0)
        {
            shield += physicalShield;
            physicalShield = 0;
        }

        if(magicResist >= 0) dmgMult = 100.0 / (100.0 + magicResist);
        else dmgMult = 2.0 - 100.0 / (100.0 - magicResist);
        magicShield -= magicDamage * dmgMult;
        if(magicShield < 0)
        {
            shield += magicShield;
            magicShield = 0;
        }

        if(shield < 0) health += shield;
    }

    public static Summoner getSummoner(PlayerEntity player)
    {
        return summoners.get(player);
    }

    public static void addSummoner(PlayerEntity player)
    {
        summoners.put(player, new Summoner(player));
    }

}
