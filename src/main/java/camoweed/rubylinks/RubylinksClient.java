package camoweed.rubylinks;

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
		ClientEvents.BEFORE_CLIENT_START.listen(Key.of(MOD_ID), RubylinksClient::beforeClientStart);
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(MOD_ID), RubylinksClient::initItemModels);
	}

	public static void beforeClientStart() {
		RubylinksClient.registerTextures();
	}

	public static void registerTextures() {
		for (final AtlasStitcher stitcher : TextureRegistry.stitcherMap.values()) {
			TextureHelper.initializeAllFiles(MOD_ID, stitcher, true);
		}
	}

	public static void initItemModels(ItemModelDispatcher dispatcher) {
		dispatcher.addDispatch(new ItemModelStandard(RubylinksItems.RUBYGLASS_GEM)
			//give it a texture
			.setIcon("rubylinks:item/rubyglass_gem")
			//make it display like a paintbrush
			.setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND)
			.setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND)
			.setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND)
			.setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
	}

}
