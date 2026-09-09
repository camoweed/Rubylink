package camoweed.rubylinks;

import camoweed.rubylinks.block.BlockModelRubyglassCore;
import camoweed.rubylinks.block.RubylinksBlocks;
import camoweed.rubylinks.item.ItemModelRubyglassGem;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;
import org.jetbrains.annotations.NotNull;

import static camoweed.rubylinks.Rubylinks.MOD_ID;
import static net.minecraft.client.render.item.model.ItemModelDispatcher.*;

public class RubylinksModels {
	private RubylinksModels(){}
	public static void initBlockModels(final BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(new BlockModelRubyglassCore<>(RubylinksBlocks.RUBYGLASS_CORE, MOD_ID + ":block/rubyglass_core"));
	}
	public static void initItemModels(final ItemModelDispatcher dispatcher) {
		dispatcher.addDispatch(setIcon(new ItemModelRubyglassGem(RubylinksItems.RUBYGLASS_GEM, null), MOD_ID + ":item/rubyglass_gem_off").setFullBright());
	}
	/*
	public static @NotNull ItemModelStandard makeModel(@NotNull final Item item, @NotNull final String textureValue) {
		return setIcon(new ItemModelStandard(item, true), NamespaceID.fromPool(MOD_ID, "item/" + textureValue));
	}
	public static @NotNull ItemModelStandard makeHoldModel(@NotNull final Item item, @NotNull final String textureValue) {
		return setIcon(new ItemModelStandard(item, true), NamespaceID.fromPool(MOD_ID, "item/" + textureValue))
			.setDisplayPos("firstperson_righthand", HANDHELD_FIRST_PERSON_RIGHT_HAND)
			.setDisplayPos("firstperson_lefthand", HANDHELD_FIRST_PERSON_LEFT_HAND)
			.setDisplayPos("thirdperson_righthand", HANDHELD_THIRD_PERSON_RIGHT_HAND)
			.setDisplayPos("thirdperson_lefthand", HANDHELD_THIRD_PERSON_LEFT_HAND);
	}
	*/

	public static <T extends ItemModelStandard> @NotNull T setIcon(@NotNull final T model, @NotNull final String texture) {
		model.icon = TextureRegistry.getTexture(texture);
		return model;
	}

	public static <T extends ItemModelStandard> @NotNull T setIcon(@NotNull final T model, @NotNull final NamespaceID texture) {
		model.icon = TextureRegistry.getTexture(texture);
		return model;
	}
}
