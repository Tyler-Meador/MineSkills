package com.groovy.mineskills.blocks;

import com.groovy.mineskills.registry.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class BuddingOreNode extends Block {
    public static int chance;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingOreNode(AbstractBlock.Settings settings, int chance) {
        super(settings);
        BuddingOreNode.chance = chance;
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(chance) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = ModBlocks.SMALL_ORACHALCITE_BUD;
            } else if (blockState.isOf(ModBlocks.SMALL_ORACHALCITE_BUD) && blockState.get(OreClusterBlock.FACING) == direction) {
                block = ModBlocks.MEDIUM_ORACHALCITE_BUD;
            } else if (blockState.isOf(ModBlocks.MEDIUM_ORACHALCITE_BUD) && blockState.get(OreClusterBlock.FACING) == direction) {
                block = ModBlocks.LARGE_ORACHALCITE_BUD;
            } else if (blockState.isOf(ModBlocks.LARGE_ORACHALCITE_BUD) && blockState.get(OreClusterBlock.FACING) == direction) {
                block = ModBlocks.ORACHALCITE_CLUSTER;
            }

            if (block != null) {
                BlockState blockState2 = (BlockState)((BlockState)block.getDefaultState().with(OreClusterBlock.FACING, direction)).with(OreClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }

        }
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
