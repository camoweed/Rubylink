package camoweed.rubylinks.block;

import camoweed.rubylinks.Rubylinks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.sound.BlockSounds;

import static camoweed.rubylinks.Rubylinks.MOD_ID;

public class RubylinksBlocks {

	private RubylinksBlocks(){}

	private static String formatTranslationKey(String key) {
		return String.format("%s.%s", MOD_ID, key);
	}

	private static String formatName(String name) {
		return String.format("%s:block/%s", MOD_ID, name);
	}
	public static final Block<?> RUBYGLASS_CORE =
		Blocks.register(
				formatTranslationKey("rubyglass.core"),
				formatName("rubyglass_core"),
				16238,
				b -> new BlockRubyglassCore(b, Materials.METAL)
			)
			.withHardness(3.5f)
			.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static void init() {
		/* no need */
	}
}

