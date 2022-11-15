package com.dm66.leaguecraft.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BlueCasterMinion extends AmbientCreature implements IAnimatable
{
    private final AnimationFactory factory = new AnimationFactory(this);

    protected BlueCasterMinion(EntityType<? extends AmbientCreature> type, Level worldIn)
    {
        super(type, worldIn);
    }

    public static AttributeSupplier setAttributes()
    {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0d)
                .add(Attributes.ATTACK_DAMAGE, 100.0d)
                .add(Attributes.FOLLOW_RANGE, 100.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.5d).build();
    }


    //TODO

//    @Override
//    protected void registerGoals()
//    {
//        this.goalSelector.addGoal(1, new MoveToBlockGoal(this, 1d, 10) {
//            @Override
//            protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
//                return worldIn.getBlockState(pos).getBlock() instanceof StealthWardBlock;
//            }
//        });
//        this.goalSelector.addGoal(0, new LookAtGoal(this, PlayerEntity.class, 10));
//    }

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
