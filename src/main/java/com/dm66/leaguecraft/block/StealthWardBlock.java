package com.dm66.leaguecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class StealthWardBlock extends HorizontalBlock
{
    public StealthWardBlock(Properties p)
    {
        super(p);
    }

    private static final VoxelShape SHAPE_NS = Stream.of(
            Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
            Block.makeCuboidShape(7, 2, 7, 9, 10, 9),
            Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
            Block.makeCuboidShape(7, 10, 7, 9, 13, 9),
            Block.makeCuboidShape(6, 11, 6, 10, 12, 10),
            Block.makeCuboidShape(2, 9, 7, 6, 10, 9),
            Block.makeCuboidShape(3, 8, 7, 7, 9, 9),
            Block.makeCuboidShape(4, 7, 7, 7, 8, 9),
            Block.makeCuboidShape(5, 6, 7, 7, 7, 9),
            Block.makeCuboidShape(10, 9, 7, 14, 10, 9),
            Block.makeCuboidShape(9, 8, 7, 13, 9, 9),
            Block.makeCuboidShape(9, 7, 7, 12, 8, 9),
            Block.makeCuboidShape(9, 6, 7, 11, 7, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_EW = Stream.of(
            Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
            Block.makeCuboidShape(7, 2, 7, 9, 10, 9),
            Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
            Block.makeCuboidShape(7, 10, 7, 9, 13, 9),
            Block.makeCuboidShape(6, 11, 6, 10, 12, 10),
            Block.makeCuboidShape(7, 9, 10, 9, 10, 14),
            Block.makeCuboidShape(7, 8, 9, 9, 9, 13),
            Block.makeCuboidShape(7, 7, 9, 9, 8, 12),
            Block.makeCuboidShape(7, 6, 9, 9, 7, 11),
            Block.makeCuboidShape(7, 9, 2, 9, 10, 6),
            Block.makeCuboidShape(7, 8, 3, 9, 9, 7),
            Block.makeCuboidShape(7, 7, 4, 9, 8, 7),
            Block.makeCuboidShape(7, 6, 5, 9, 7, 7)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_)
    {
        switch (state.get(HORIZONTAL_FACING))
        {
            case NORTH:
            case SOUTH:
                return SHAPE_NS;
            case EAST:
            case WEST:
                return SHAPE_EW;
        }
        return SHAPE_NS;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx)
    {
        return this.getDefaultState().with(HORIZONTAL_FACING, ctx.getPlacementHorizontalFacing().getOpposite());
    }
}
