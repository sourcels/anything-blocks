package com.shimaper.anythingblocks.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class GlowingBlockItem extends BlockItem {
    public GlowingBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}