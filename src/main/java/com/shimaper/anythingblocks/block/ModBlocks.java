package com.shimaper.anythingblocks.block;

import com.shimaper.anythingblocks.AnythingBlocks;
import com.shimaper.anythingblocks.item.GlowingBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
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

    public static final Block APPLE_BLOCK = register(
            "apple_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .strength(1.2f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    public static final Block GOLDEN_APPLE_BLOCK = register(
            "golden_apple_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .sounds(BlockSoundGroup.METAL)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresTool()
                    .burnable()
                    .strength(3.0f, 6.0f)
                    .pistonBehavior(PistonBehavior.IGNORE),
            true,
            false
    );

    public static final Block ENCHANTED_GOLDEN_APPLE_BLOCK = register(
            "enchanted_golden_apple_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .sounds(BlockSoundGroup.METAL)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresTool()
                    .burnable()
                    .strength(3.0f, 6.0f)
                    .pistonBehavior(PistonBehavior.IGNORE)
                    .luminance((state) -> 8),
            true,
            true
    );

    public static final Block SWEET_BERRIES_BLOCK = register(
            "sweet_berries_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_GREEN)
                    .sounds(BlockSoundGroup.GRASS)
                    .burnable()
                    .strength(1.5f, 1.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    public static final Block GLOW_BERRIES_BLOCK = register(
            "glow_berries_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.CAVE_VINES)
                    .burnable()
                    .strength(1.5f, 1.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    public static final Block GOLDEN_CARROT_BLOCK = register(
            "golden_carrot_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .sounds(BlockSoundGroup.METAL)
                    .requiresTool()
                    .burnable()
                    .strength(3.0f, 5.5f)
                    .pistonBehavior(PistonBehavior.IGNORE),
            true,
            false
    );

    public static final Block POTATO_BLOCK = register(
            "potato_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
                    .strength(2.5f, 2.5f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    public static final Block PORK_BLOCK = register(
            "pork_block",
            SwordBreakableBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED)
                    .sounds(BlockSoundGroup.RESIN)
                    .burnable()
                    .strength(2.2f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    public static final Block COOKED_PORK_BLOCK = register(
            "cooked_pork_block",
            SwordBreakableBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .sounds(BlockSoundGroup.RESIN)
                    .burnable()
                    .strength(2.2f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    public static final Block BREAD_BLOCK = register(
            "bread_block",
            SwordBreakableBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .strength(2.2f, 2.2f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory,
                                  AbstractBlock.Settings settings, boolean shouldRegisterItem, boolean isGlowingItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        RegistryKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem;
        if (shouldRegisterItem) {
            if (isGlowingItem) {
                blockItem = new GlowingBlockItem(block, new Item.Settings().registryKey(itemKey));
            }
            else {
                blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            }
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
