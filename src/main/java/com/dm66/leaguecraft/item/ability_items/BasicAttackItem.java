package com.dm66.leaguecraft.item.ability_items;

import com.dm66.leaguecraft.Summoner;
import com.dm66.leaguecraft.entity.BasicAttackProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BasicAttackItem extends AbilityItem
{
    public BasicAttackItem(Properties p)
    {
        super(p);
    }

    // TODO: For charging abilities check onUsingTick() and onUseRelease()

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        ItemStack is = pPlayer.getItemInHand(pUsedHand);
        if(pLevel.isClientSide())
        {
            Summoner s = Summoner.getSummoner(pPlayer);
            if(s.basicAttackCD > 0) return InteractionResultHolder.fail(is);
            LivingEntity entity = getTargetedEntity(pPlayer, pLevel, 10);
            if(entity != null)
            {
                BasicAttackProjectile proj = new BasicAttackProjectile(pPlayer);
                proj.copyPosition(pPlayer);
                proj.setTarget(entity);
                pLevel.addFreshEntity(proj);
                s.basicAttackCD = (int) Math.round(20.0/s.attackSpeed);
            }
        }
        return InteractionResultHolder.success(is);
    }

}
