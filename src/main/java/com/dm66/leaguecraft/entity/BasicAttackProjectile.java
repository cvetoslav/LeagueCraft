package com.dm66.leaguecraft.entity;

import com.dm66.leaguecraft.Summoner;
import com.dm66.leaguecraft.item.ModItems;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.client.renderer.entity.model.TridentModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.List;

public class BasicAttackProjectile extends ThrowableEntity implements IRendersAsItem
{
    private static final DataParameter<Integer> TARGET = EntityDataManager.createKey(BasicAttackProjectile.class, DataSerializers.VARINT);

    public BasicAttackProjectile(EntityType<BasicAttackProjectile> type, World world)
    {
        super(type, world);
    }

    public BasicAttackProjectile(LivingEntity owner)
    {
        super(ModEntities.AUTO_ATTACK_PROJECTILE.get(), owner, owner.world);
    }

    @Override
    protected void registerData()
    {
        dataManager.register(TARGET, 0);
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setTarget(LivingEntity e)
    {
        dataManager.set(TARGET, e == null ? -1 : e.getEntityId());
    }

    public LivingEntity getTargetEntity()
    {
        int id = dataManager.get(TARGET);
        Entity e = world.getEntityByID(id);
        if (e instanceof LivingEntity)
        {
            return (LivingEntity) e;
        }
        return null;
    }

    @Override
    public void tick()
    {

        super.tick();

        Vector3d thisVec = this.getPositionVec();

        LivingEntity target = getTargetEntity();
        if (target != null)
        {

            Vector3d targetVec = new Vector3d(target.getPosX(), target.getPosY() + 0.5, target.getPosZ());
            Vector3d diffVec = targetVec.subtract(thisVec);
            Vector3d motionVec = diffVec.normalize().scale(0.3);
            setMotion(motionVec);

            /*List<LivingEntity> targetList = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(getPosX() - 0.5, getPosY() - 0.5, getPosZ() - 0.5, getPosX() + 0.5, getPosY() + 0.5, getPosZ() + 0.5));
            if (targetList.contains(target))
            {
                Entity owner = func_234616_v_();
                if (owner instanceof LivingEntity) {
                    PlayerEntity player = owner instanceof PlayerEntity ? (PlayerEntity) owner : null;
                    target.attackEntityFrom(player == null ? DamageSource.causeMobDamage((LivingEntity) owner) : DamageSource.causePlayerDamage(player), evil ? 12 : 7);
                } else {
                    target.attackEntityFrom(DamageSource.GENERIC, evil ? 12 : 7);
                }

                remove();
            }*/
        }
        else remove();

    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result)
    {
        if(result.getEntity() instanceof LivingEntity && result.getEntity() == getTargetEntity())
        {
            result.getEntity().setFire(3);
            remove();
        }
    }

    @Override
    public ItemStack getItem()
    {
        return ModItems.AUTO_ATTACK.get().getDefaultInstance();
    }
}
