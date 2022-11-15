package com.dm66.leaguecraft.item.ability_items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public abstract class AbilityItem extends Item
{
    public AbilityItem(Properties p)
    {
        super(p);
    }

    protected LivingEntity getTargetedEntity(Player player, Level world, double range)
    {
        AABB aabb = new AABB(player.getX() - range, player.getY() - range, player.getZ() - range, player.getX() + range, player.getY() + range, player.getZ() + range);
        List<Entity> es = world.getEntities(null, aabb);
        Vec3 v = player.getLookAngle();
        // TODO: adjust the max targeting lock angle ( FOV ? )
        double min_ang = 1000000;
        LivingEntity ret = null;
        for(Entity e : es)
        {
            // TODO: add target filtering (ally, enemy, summoner, minion, ...)
            if(e instanceof LivingEntity && player.distanceTo(e) <= range)
            {
                Vec3 v1 = e.position().subtract(player.position());
                double ang = Math.acos(v.dot(v1) / v.length() / v1.length());
                if (ang < min_ang && e != player)
                {
                    min_ang = ang;
                    ret = (LivingEntity) e;
                }
            }
        }
        return ret;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

}
