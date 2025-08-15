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
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.function.Function;

public class ModBlocks {
    public enum BlockGroup {
        FOOD("food"),
        MATERIAL("material"),
        MISC("misc");

        private final String name;

        BlockGroup(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static final Map<BlockGroup, List<Block>> BLOCK_GROUPS = new EnumMap<>(BlockGroup.class);

    static {
        for (BlockGroup group : BlockGroup.values()) {
            BLOCK_GROUPS.put(group, new ArrayList<>());
        }
    }

    public static final Block APPLE_BLOCK = register(
            "apple_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .strength(1.2f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
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
                    .strength(3.0f, 6.0f)
                    .pistonBehavior(PistonBehavior.IGNORE),
            BlockGroup.FOOD,
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
                    .strength(3.0f, 6.0f)
                    .pistonBehavior(PistonBehavior.IGNORE)
                    .luminance((state) -> 8),
            BlockGroup.FOOD,
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
            BlockGroup.FOOD,
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
            BlockGroup.FOOD,
            false
    );

    public static final Block CHORUS_FRUIT_BLOCK = register(
            "chorus_fruit_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .strength(1.75f, 1.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block CARROT_BLOCK = register(
            "carrot_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
                    .strength(2.0f, 1f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block GOLDEN_CARROT_BLOCK = register(
            "golden_carrot_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .sounds(BlockSoundGroup.METAL)
                    .requiresTool()
                    .strength(3.0f, 5.5f)
                    .pistonBehavior(PistonBehavior.IGNORE),
            BlockGroup.FOOD,
            false
    );

    public static final Block POTATO_BLOCK = register(
            "potato_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY)
                    .sounds(BlockSoundGroup.BAMBOO_WOOD)
                    .burnable()
                    .strength(2.5f, 2f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block BAKED_POTATO_BLOCK = register(
            "baked_potato_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY)
                    .sounds(BlockSoundGroup.NETHERRACK)
                    .burnable()
                    .strength(2.5f, 1.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block POISONOUS_POTATO_BLOCK = register(
            "poisonous_potato_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.LICHEN_GREEN)
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .burnable()
                    .strength(2.0f, 1.5f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block BEETROOT_BLOCK = register(
            "beetroot_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_RED)
                    .sounds(BlockSoundGroup.GRASS)
                    .burnable()
                    .strength(2.0f, 1.5f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block BEEF_BLOCK = register(
            "beef_block",
            SwordBreakableBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED)
                    .sounds(BlockSoundGroup.RESIN)
                    .burnable()
                    .strength(2.2f, 2.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block COOKED_BEEF_BLOCK = register(
            "cooked_beef_block",
            SwordBreakableBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .sounds(BlockSoundGroup.RESIN)
                    .burnable()
                    .strength(2.2f, 2.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block PORK_BLOCK = register(
            "pork_block",
            SwordBreakableBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED)
                    .sounds(BlockSoundGroup.RESIN)
                    .burnable()
                    .strength(2.2f, 2.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
            false
    );

    public static final Block COOKED_PORK_BLOCK = register(
            "cooked_pork_block",
            SwordBreakableBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .sounds(BlockSoundGroup.RESIN)
                    .burnable()
                    .strength(2.2f, 2.0f)
                    .pistonBehavior(PistonBehavior.DESTROY),
            BlockGroup.FOOD,
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
            BlockGroup.FOOD,
            false
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory,
                                  AbstractBlock.Settings settings, BlockGroup group, boolean isGlowingItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        RegistryKey<Item> itemKey = keyOfItem(name);

        BlockItem blockItem;
        if (isGlowingItem) {
            blockItem = new GlowingBlockItem(block, new Item.Settings().registryKey(itemKey));
        } else {
            blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
        }

        Registry.register(Registries.ITEM, itemKey, blockItem);
        BLOCK_GROUPS.get(group).add(block);

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AnythingBlocks.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnythingBlocks.MOD_ID, name));
    }

    public static List<Block> getBlocksInGroup(BlockGroup group) {
        return Collections.unmodifiableList(BLOCK_GROUPS.get(group));
    }

    public static Map<BlockGroup, List<Block>> getAllBlockGroups() {
        Map<BlockGroup, List<Block>> result = new EnumMap<>(BlockGroup.class);
        for (Map.Entry<BlockGroup, List<Block>> entry : BLOCK_GROUPS.entrySet()) {
            result.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return result;
    }

    public static List<Block> getModBlocksFood() {
        return getBlocksInGroup(BlockGroup.FOOD);
    }

    public static void initialize() {
        AnythingBlocks.LOGGER.info("Registering blocks for " + AnythingBlocks.MOD_ID);

        for (BlockGroup group : BlockGroup.values()) {
            int count = BLOCK_GROUPS.get(group).size();
            AnythingBlocks.LOGGER.info("Registered " + count + " blocks in group: " + group.getName());
        }
    }
}