package com.dm66.leaguecraft;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class Summoner
{
    public static HashMap<PlayerEntity, Summoner> summoners = new HashMap<>();

    public double health;
    public double maxHealth;
    public double healthRegen;
    public double shield;
    public double magicShield;
    public double physicalShield;
    public double mana;
    public double maxMana;
    public double manaRegen;

    // public Champion champion;

    public Summoner(PlayerEntity player)
    {
        health = 900;
        maxHealth = 1000;
        healthRegen = 0.5;
        shield = magicShield = physicalShield = 0;
        mana = 800;
        maxMana = 880;
        manaRegen = 0.2;
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

    public static Summoner getSummoner(PlayerEntity player)
    {
        return summoners.get(player);
    }

    public static void addSummoner(PlayerEntity player)
    {
        summoners.put(player, new Summoner(player));
    }

}
