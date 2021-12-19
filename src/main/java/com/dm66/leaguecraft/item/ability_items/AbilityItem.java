package com.dm66.leaguecraft.item.ability_items;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.Summoner;
import com.dm66.leaguecraft.ability.Ability;
import com.dm66.leaguecraft.effect.ModEffects;
import com.dm66.leaguecraft.effect.StasisEffect;
import com.dm66.leaguecraft.entity.BasicAttackProjectile;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Random;

public abstract class AbilityItem extends Item
{
    public AbilityItem(Properties p)
    {
        super(p);
    }

    protected LivingEntity getTargetedEntity(PlayerEntity player, World world, double range)
    {
        AxisAlignedBB aabb = new AxisAlignedBB(player.getPosX() - range, player.getPosY() - range, player.getPosZ() - range, player.getPosX() + range, player.getPosY() + range, player.getPosZ() + range);
        List<Entity> es = world.getEntitiesWithinAABB(Entity.class, aabb);
        Vector3d v = player.getLookVec();
        // TODO: adjust the max targeting lock angle ( FOV ? )
        double min_ang = 1000000;
        LivingEntity ret = null;
        for(Entity e : es)
        {
            // TODO: add target filtering (ally, enemy, summoner, minion, ...)
            if(e instanceof LivingEntity && player.getDistance(e) <= range)
            {
                Vector3d v1 = e.getPositionVec().subtract(player.getPositionVec());
                double ang = Math.acos(v.dotProduct(v1) / v.length() / v1.length());
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
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return slotChanged;
    }

}
