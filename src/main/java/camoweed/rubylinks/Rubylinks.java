package camoweed.rubylinks;

import camoweed.rubylinks.block.RubylinksBlocks;
import camoweed.rubylinks.block.TileEntityRubyglassCore;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.entity.TileEntityDispatcher;
import net.minecraft.core.util.collection.NamespaceID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class Rubylinks implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("rubylinks", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean forceChunkLoads = false;

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), RubylinksRecipes::initializeRecipes);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), RubylinksRecipes::initNamespaces);
		LOGGER.info("Rubylinks active.");
	}

	public void beforeGameStart() {
		TileEntityDispatcher.addMapping(
			TileEntityRubyglassCore.class,
			NamespaceID.fromPool(MOD_ID, "rubylinks$rubyglass_core")
		);
		RubylinksItems.init();
		RubylinksBlocks.init();
	}

	public void afterGameStart() {

	}
}
