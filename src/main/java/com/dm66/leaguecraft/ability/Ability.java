package com.dm66.leaguecraft.ability;

import com.dm66.leaguecraft.item.BasicAttack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public abstract class Ability
{
    public double coolDown = 0;
    public boolean isUltimate = false;
    public Item abilityItem = null;

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event)
    {
        // TODO: register this, implement CD timer as ItemStack damage bar
        PlayerEntity player = event.player;
        for(int i=27;i<36;i++)
        {
            ItemStack is = player.inventory.mainInventory.get(i);
            if(is.getItem() instanceof BasicAttack)
            {
                is.getItem().setDamage(is, 5);
                break;
            }
        }
    }

}
