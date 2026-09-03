package camoweed.rubylinks;

import net.fabricmc.api.ModInitializer;
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

		CommonEvents.AFTER_ITEM_INIT.listen(Key.of(MOD_ID), RubylinksItems::aftterItemInit);
		//recipes too
		//CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), RubylinksRecipes::initializeRecipes);

		LOGGER.info("☻");
	}

	public void beforeGameStart() {

	}

	public void afterGameStart() {

	}
}
