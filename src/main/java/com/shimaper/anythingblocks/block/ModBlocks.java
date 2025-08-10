package com.shimaper.anythingblocks.block;

import com.shimaper.anythingblocks.AnythingBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModBlocks {
    public static final List<Block> MOD_BLOCKS = new ArrayList<>();

    public static final Block PORK_BLOCK = register(
            "pork_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.RESIN)
                    .burnable()
                    .strength(1.2f)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final Block COOKED_PORK_BLOCK = register(
            "cooked_pork_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.RESIN)
                    .strength(2f)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
        return register(name, blockFactory, settings, true);
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory,
                                  AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);

            MOD_BLOCKS.add(block);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AnythingBlocks.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnythingBlocks.MOD_ID, name));
    }

    public static void initialize() {
        AnythingBlocks.LOGGER.info("Registering blocks for " + AnythingBlocks.MOD_ID);
    }
}
