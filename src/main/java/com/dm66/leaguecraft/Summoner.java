package com.dm66.leaguecraft;

import com.dm66.leaguecraft.champion.Champion;
import com.dm66.leaguecraft.item.ability_items.BasicAttackItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class Summoner
{
    public static HashMap<Player, Summoner> summoners = new HashMap<>();

    public Player player;

    public ActivityStatus status;

    public enum ActivityStatus
    {
        INGAME, CHAMPSELECT, QUEUE, LOBBY, ONLINE, OFFLINE
    }

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

    public int basicAttackCD = 0;
    public int QAbilityCD = 0;
    public int WAbilityCD = 0;
    public int EAbilityCD = 0;
    public int RAbilityCD = 0;

    // TODO: add champion specific base stats
    public Champion champion;

    public Summoner(Player player)
    {
        this.player = player;
        status = ActivityStatus.OFFLINE;
        health = 900;
        maxHealth = 1000;
        healthRegen = 0.5;
        shield = magicShield = physicalShield = 0;
        mana = 800;
        maxMana = 880;
        manaRegen = 0.2;
        attackDamage = 69;
        attackSpeed = 2.5;
        abilityPower = 42;
        armor = 15;
        magicResist = 13;
        movementSpeed = 300;
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END || !event.player.level.isClientSide()) return;
        Summoner s = Summoner.getSummoner(event.player);
        s.updateCoolDowns();
        s.health += s.healthRegen / 20;
        if(s.health > s.maxHealth) s.health = s.maxHealth;
        s.mana += s.manaRegen / 20;
        if(s.mana > s.maxMana) s.mana = s.maxMana;
    }

    public void updateCoolDowns()
    {
        if(basicAttackCD > 0) basicAttackCD--;
        for(int i=0;i<36;i++)
        {
            ItemStack is = player.getInventory().getItem(i);
            if(is.getItem() instanceof BasicAttackItem)
            {
                is.getItem().setDamage(is, (int) Math.round(10.0*basicAttackCD/Math.round(20.0/attackSpeed)));
                break;
            }
        }
        if(QAbilityCD > 0) QAbilityCD--;
        if(WAbilityCD > 0) WAbilityCD--;
        if(EAbilityCD > 0) EAbilityCD--;
        if(RAbilityCD > 0) RAbilityCD--;
    }

    public void addEffect(MobEffect effect, int duration)
    {
        if(!player.hasEffect(effect) && status == ActivityStatus.INGAME)
        {
            player.addEffect(new MobEffectInstance(effect, duration));
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

    public static Summoner getSummoner(Player player)
    {
        return summoners.get(player);
    }

    public static void addSummoner(Player player)
    {
        summoners.put(player, new Summoner(player));
    }

}
