package com.shimaper.anythingblocks.item;

import com.shimaper.anythingblocks.AnythingBlocks;
import com.shimaper.anythingblocks.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.Function;

public class ModItems {
    private static final Map<ModBlocks.BlockGroup, List<Item>> ITEM_GROUPS = new EnumMap<>(ModBlocks.BlockGroup.class);

    public static final RegistryKey<ItemGroup> FOOD_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of(AnythingBlocks.MOD_ID, "food")
    );

    public static final RegistryKey<ItemGroup> MATERIAL_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of(AnythingBlocks.MOD_ID, "material")
    );

    public static final RegistryKey<ItemGroup> MISC_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of(AnythingBlocks.MOD_ID, "misc")
    );

    // Item groups with proper localization paths
    public static final ItemGroup FOOD_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.GOLDEN_APPLE_BLOCK.asItem()))
            .displayName(Text.translatable("itemGroup.anything-blocks.food"))
            .build();

    public static final ItemGroup MATERIAL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.GOLDEN_APPLE_BLOCK.asItem()))
            .displayName(Text.translatable("itemGroup.anything-blocks.material"))
            .build();

    public static final ItemGroup MISC_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.GOLDEN_APPLE_BLOCK.asItem()))
            .displayName(Text.translatable("itemGroup.anything-blocks.misc"))
            .build();

    static {
        for (ModBlocks.BlockGroup group : ModBlocks.BlockGroup.values()) {
            ITEM_GROUPS.put(group, new ArrayList<>());
        }
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory,
                                Item.Settings settings, ModBlocks.BlockGroup group) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnythingBlocks.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        ITEM_GROUPS.get(group).add(item);
        return item;
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        return register(name, itemFactory, settings, ModBlocks.BlockGroup.MISC);
    }

    public static List<Item> getItemsInGroup(ModBlocks.BlockGroup group) {
        return Collections.unmodifiableList(ITEM_GROUPS.get(group));
    }

    public static Map<ModBlocks.BlockGroup, List<Item>> getAllItemGroups() {
        Map<ModBlocks.BlockGroup, List<Item>> result = new EnumMap<>(ModBlocks.BlockGroup.class);
        for (Map.Entry<ModBlocks.BlockGroup, List<Item>> entry : ITEM_GROUPS.entrySet()) {
            result.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return result;
    }

    public static void initialize() {
        AnythingBlocks.LOGGER.info("Registering item groups for " + AnythingBlocks.MOD_ID);

        Registry.register(Registries.ITEM_GROUP, FOOD_GROUP_KEY, FOOD_GROUP);
        Registry.register(Registries.ITEM_GROUP, MATERIAL_GROUP_KEY, MATERIAL_GROUP);
        Registry.register(Registries.ITEM_GROUP, MISC_GROUP_KEY, MISC_GROUP);

        ItemGroupEvents.modifyEntriesEvent(FOOD_GROUP_KEY).register((itemGroup) -> {
            for (Block block : ModBlocks.getBlocksInGroup(ModBlocks.BlockGroup.FOOD)) {
                itemGroup.add(block.asItem());
            }
            for (Item item : ITEM_GROUPS.get(ModBlocks.BlockGroup.FOOD)) {
                itemGroup.add(item);
            }
        });

        ItemGroupEvents.modifyEntriesEvent(MATERIAL_GROUP_KEY).register((itemGroup) -> {
            for (Block block : ModBlocks.getBlocksInGroup(ModBlocks.BlockGroup.MATERIAL)) {
                itemGroup.add(block.asItem());
            }
            for (Item item : ITEM_GROUPS.get(ModBlocks.BlockGroup.MATERIAL)) {
                itemGroup.add(item);
            }
        });

        ItemGroupEvents.modifyEntriesEvent(MISC_GROUP_KEY).register((itemGroup) -> {
            for (Block block : ModBlocks.getBlocksInGroup(ModBlocks.BlockGroup.MISC)) {
                itemGroup.add(block.asItem());
            }
            for (Item item : ITEM_GROUPS.get(ModBlocks.BlockGroup.MISC)) {
                itemGroup.add(item);
            }
        });
        for (ModBlocks.BlockGroup group : ModBlocks.BlockGroup.values()) {
            int itemCount = ITEM_GROUPS.get(group).size();
            int blockCount = ModBlocks.getBlocksInGroup(group).size();
            AnythingBlocks.LOGGER.info("Group " + group.getName() + ": " + itemCount + " items, " + blockCount + " blocks");
        }
    }
}