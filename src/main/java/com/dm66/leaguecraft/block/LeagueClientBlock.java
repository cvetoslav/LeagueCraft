package com.dm66.leaguecraft.block;

import com.dm66.leaguecraft.rendering.client.LeagueClientGUI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class LeagueClientBlock extends Block
{
    public LeagueClientBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if(!worldIn.isRemote())
        {
            LeagueClientGUI.open();
        }
        return ActionResultType.SUCCESS;
   }
}
