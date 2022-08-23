package com.dm66.leaguecraft.entity;

import com.dm66.leaguecraft.block.StealthWardBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BlueCasterMinion extends CreatureEntity implements IAnimatable
{
    private AnimationFactory factory = new AnimationFactory(this);

    protected BlueCasterMinion(EntityType<? extends CreatureEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0d)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 100.0d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 100.0d)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5d);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new MoveToBlockGoal(this, 1d, 10) {
            @Override
            protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
                return worldIn.getBlockState(pos).getBlock() instanceof StealthWardBlock;
            }
        });
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.25d));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.blue_caster_minion.walk", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
