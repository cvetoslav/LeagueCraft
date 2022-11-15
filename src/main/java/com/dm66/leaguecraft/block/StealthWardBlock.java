package com.dm66.leaguecraft.block;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class StealthWardBlock extends HorizontalDirectionalBlock
{
    public StealthWardBlock(BlockBehaviour.Properties p)
    {
        super(p);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        pBuilder.add(FACING);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    private static final VoxelShape SHAPE_NS = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 2, 7, 9, 10, 9),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 10, 7, 9, 13, 9),
            Block.box(6, 11, 6, 10, 12, 10),
            Block.box(2, 9, 7, 6, 10, 9),
            Block.box(3, 8, 7, 7, 9, 9),
            Block.box(4, 7, 7, 7, 8, 9),
            Block.box(5, 6, 7, 7, 7, 9),
            Block.box(10, 9, 7, 14, 10, 9),
            Block.box(9, 8, 7, 13, 9, 9),
            Block.box(9, 7, 7, 12, 8, 9),
            Block.box(9, 6, 7, 11, 7, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_EW = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 2, 7, 9, 10, 9),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 10, 7, 9, 13, 9),
            Block.box(6, 11, 6, 10, 12, 10),
            Block.box(7, 9, 10, 9, 10, 14),
            Block.box(7, 8, 9, 9, 9, 13),
            Block.box(7, 7, 9, 9, 8, 12),
            Block.box(7, 6, 9, 9, 7, 11),
            Block.box(7, 9, 2, 9, 10, 6),
            Block.box(7, 8, 3, 9, 9, 7),
            Block.box(7, 7, 4, 9, 8, 7),
            Block.box(7, 6, 5, 9, 7, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static VoxelShape getShapeEw() {
        return SHAPE_EW;
    }

    public static VoxelShape getShapeNs() {
        return SHAPE_NS;
    }

}
