package com.dm66.leaguecraft.block;

import com.dm66.leaguecraft.rendering.client.LeagueClientGUI;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LeagueClientBlock extends Block
{
    public LeagueClientBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if(pLevel.isClientSide()) LeagueClientGUI.open();
        return InteractionResult.SUCCESS;
    }

}
