package com.shimaper.anythingblocks;

import com.shimaper.anythingblocks.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnythingBlocks implements ModInitializer {
	public static final String MOD_ID = "anything-blocks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Anything Blocks loaded.");
        ModBlocks.initialize();
	}
}