package com.dm66.leaguecraft.item;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.Summoner;
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

public class BasicAttack extends Item
{
    public BasicAttack(Properties p)
    {
        super(p);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        ItemStack is = player.getHeldItem(hand);
        if(!world.isRemote)
        {
            LivingEntity entity = getLookingEntity(player, world);
            //Summoner.getSummoner(player).addEffect(ModEffects.STASIS.get(), 100);
            if(entity!=null)
            {
                BasicAttackProjectile proj = new BasicAttackProjectile(player);
                proj.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
                proj.setTarget(entity);
                world.addEntity(proj);
            }
        }
        return ActionResult.resultSuccess(is);
    }

    private LivingEntity getLookingEntity(PlayerEntity player, World world)
    {
        AxisAlignedBB aabb = new AxisAlignedBB(player.getPosX()-15, player.getPosY()-15, player.getPosZ()-15, player.getPosX()+15, player.getPosY()+15, player.getPosZ()+15);
        List<Entity> es = world.getEntitiesWithinAABB(Entity.class, aabb);
        Vector3d v = player.getLookVec();
        double min_ang = 1000000;
        LivingEntity ret = null;
        for(Entity e : es)
        {
            if(e instanceof LivingEntity)
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

}
