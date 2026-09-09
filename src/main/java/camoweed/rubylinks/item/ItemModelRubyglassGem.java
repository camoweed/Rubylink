package camoweed.rubylinks.item;

import camoweed.rubylinks.Rubylinks;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemModelRubyglassGem extends ItemModelStandard {
	private static final IconCoordinate RUBGLASS_GEM = TextureRegistry.getTexture(Rubylinks.MOD_ID + ":item/rubyglass_gem");

	//TODO remove the namespace constructor with namespace, it still need for some textures so I leave it for later
	public ItemModelRubyglassGem(final Item item, final String namespace) {
		super(item, namespace);
	}

	@NotNull
	@Override
	public IconCoordinate getIcon(@Nullable final Entity entity, final ItemStack itemStack) {
		if (itemStack.getData().getBoolean("rubylinks$has_location")) {
			return RUBGLASS_GEM;
		}
		return super.getIcon(entity, itemStack);
	}
}
