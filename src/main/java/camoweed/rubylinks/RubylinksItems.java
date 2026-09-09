package camoweed.rubylinks;

import camoweed.rubylinks.item.ItemRubyglassGem;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;

import static camoweed.rubylinks.Rubylinks.MOD_ID;

public class RubylinksItems {
	RubylinksItems(){}
	public static final Item RUBYGLASS_GEM = new ItemBuilder(MOD_ID)
		.setStackSize(1)
		.build(new ItemRubyglassGem("rubyglass.gem", MOD_ID + ":item/rubyglass_gem", 1629));
	public static void init() {

	}
}
