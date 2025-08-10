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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final List<Item> MOD_ITEMS = new ArrayList<>();

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of(AnythingBlocks.MOD_ID, "item_group")
    );

    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.PORK_BLOCK.asItem()))
            .displayName(Text.translatable("itemGroup." + AnythingBlocks.MOD_ID))
            .build();

    //public static final Item NEW_ITEM = register(
    //        "new_item",
    //        Item::new,
    //        new Item.Settings()
    //);

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AnythingBlocks.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        MOD_ITEMS.add(item);

        return item;
    }

    public static void initialize() {
        AnythingBlocks.LOGGER.info("Registering item groups for " + AnythingBlocks.MOD_ID);

        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            for (Block block : ModBlocks.MOD_BLOCKS) {
                itemGroup.add(block.asItem());
            }
            for (Item item : MOD_ITEMS) {
                itemGroup.add(item);
            }
        });
    }
}