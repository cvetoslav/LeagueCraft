package com.dm66.leaguecraft.entity;

import com.dm66.leaguecraft.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BasicAttackProjectile extends ProjectileItemEntity
{
    public BasicAttackProjectile(EntityType<BasicAttackProjectile> type, World world)
    {
        super(type, world);
    }

    public BasicAttackProjectile(LivingEntity player, World world)
    {
        super(ModEntities.AUTO_ATTACK_PROJECTILE.get(), player, world);
    }

    public BasicAttackProjectile(double x, double y, double z, World world)
    {
        super(ModEntities.AUTO_ATTACK_PROJECTILE.get(), x,y,z, world);
    }

    @Override
    protected Item getDefaultItem()
    {
        return ModItems.AUTO_ATTACK.get().asItem();
    }

    @Override
    public net.minecraft.network.IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onImpact(RayTraceResult res)
    {
        if(res.getType() == RayTraceResult.Type.ENTITY)
        {
            ((EntityRayTraceResult)res).getEntity().setFire(3);
        }
        this.remove();
    }
}
