package com.dm66.leaguecraft.item.ability_items;

import com.dm66.leaguecraft.Summoner;
import com.dm66.leaguecraft.entity.BasicAttackProjectile;
import com.dm66.leaguecraft.item.ability_items.AbilityItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BasicAttack extends AbilityItem
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
            Summoner s = Summoner.getSummoner(player);
            if(s.basicAttackCD > 0) return ActionResult.resultFail(is);
            LivingEntity entity = getTargetedEntity(player, world, 10);
            if(entity != null)
            {
                BasicAttackProjectile proj = new BasicAttackProjectile(player);
                proj.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
                proj.setTarget(entity);
                world.addEntity(proj);
                s.basicAttackCD = (int) Math.round(20.0/s.attackSpeed);
            }
        }
        return ActionResult.resultSuccess(is);
    }

}
