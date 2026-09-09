package camoweed.rubylinks;

import camoweed.rubylinks.item.ItemModelRubyglassGem;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.AtlasStitcher;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.dependency.Key;

import static camoweed.rubylinks.Rubylinks.MOD_ID;

public class RubylinksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//ClientEvents.BEFORE_CLIENT_START.listen(Key.of(MOD_ID), RubylinksClient::beforeClientStart);
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(MOD_ID), RubylinksModels::initItemModels);
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID), RubylinksModels::initBlockModels);
	}

	public static void beforeClientStart() {
		RubylinksClient.registerTextures();
	}

	public static void registerTextures() {
		for (final AtlasStitcher stitcher : TextureRegistry.stitcherMap.values()) {
			TextureHelper.initializeAllFiles(MOD_ID, stitcher, true);
		}
	}
}
