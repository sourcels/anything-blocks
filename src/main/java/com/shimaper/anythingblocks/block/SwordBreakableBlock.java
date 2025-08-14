package com.shimaper.anythingblocks.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class SwordBreakableBlock extends Block {
    public SwordBreakableBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        ItemStack stack = player.getMainHandStack();
        if (stack.isIn(ItemTags.SWORDS)) {
            return super.calcBlockBreakingDelta(state, player, world, pos) * 3.0F;
        }
        return super.calcBlockBreakingDelta(state, player, world, pos);
    }
}