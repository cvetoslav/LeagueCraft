package com.dm66.leaguecraft.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.ModelDataManager;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.List;

public class BasicAttackProjectile extends Projectile implements IAnimatable
{
    private AnimationFactory factory = new AnimationFactory(this);

    private static final EntityDataAccessor<Integer> TARGET = SynchedEntityData.defineId(BasicAttackProjectile.class, EntityDataSerializers.INT);

    public BasicAttackProjectile(EntityType<? extends BasicAttackProjectile> type, Level world)
    {
        super(type, world);
    }

    public BasicAttackProjectile(LivingEntity owner)
    {
        super(ModEntities.AUTO_ATTACK_PROJECTILE.get(), owner.level);
    }

    public void setTarget(LivingEntity e)
    {
        this.entityData.set(TARGET, e == null ? -1 : e.getId());
    }

    public LivingEntity getTargetEntity()
    {
        int id = this.entityData.get(TARGET);
        Entity e = level.getEntity(id);
        if (e instanceof LivingEntity)
        {
            return (LivingEntity) e;
        }
        return null;
    }

    @Override
    protected void defineSynchedData()
    {
        this.entityData.define(TARGET, -1);
    }

    @Override
    public void tick()
    {
        super.tick();

        Vec3 thisVec = this.position();

        LivingEntity target = getTargetEntity();
        if (target != null)
        {
            Vec3 targetVec = new Vec3(target.getX(), target.getY() + 0.5, target.getZ());
            Vec3 diffVec = targetVec.subtract(thisVec);
            Vec3 motionVec = diffVec.normalize().scale(0.3);
            moveTo(thisVec.add(motionVec));
        }
        else remove(RemovalReason.DISCARDED);

    }

    @Override
    protected void onHitEntity(EntityHitResult pResult)
    {
        if(pResult.getEntity() instanceof LivingEntity && pResult.getEntity() == getTargetEntity())
        {
            pResult.getEntity().setSecondsOnFire(3);
            remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
