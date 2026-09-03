package camoweed.rubylinks;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;

import static camoweed.rubylinks.Rubylinks.MOD_ID;

public class RubylinksItems {
	public static Item RUBYGLASS_GEM;

	public static void aftterItemInit() {
		RUBYGLASS_GEM = new ItemBuilder(MOD_ID)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.WAND_NBT))
			.setStackSize(1)
			.build(new ItemExample("rubyglass.gem","rubylinks:item/rubyglass_gem",1743));
	}
}
