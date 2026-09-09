package camoweed.rubylinks;

import camoweed.rubylinks.block.RubylinksBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.RecipeBuilder;

import static camoweed.rubylinks.Rubylinks.MOD_ID;


public class RubylinksRecipes {

	public static void initializeRecipes() {
		RecipeBuilder.initNameSpace(MOD_ID);
		//shaped
		RecipeBuilder.Shaped(MOD_ID,"GRG","RDR","GRG")
			.addInput('R', Items.RUBYGLASS)
			.addInput('D', Items.DIAMOND)
			.addInput('G', Items.INGOT_GOLD)
			.create("rubyglass_gem_shaped", new ItemStack(RubylinksItems.RUBYGLASS_GEM));
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(RubylinksItems.RUBYGLASS_GEM)
			.create("rubyglass_gem_shapeless", new ItemStack(RubylinksItems.RUBYGLASS_GEM));
	RecipeBuilder.Shaped(MOD_ID,"GRG","RDR","GRG")
		.addInput('R', Blocks.BLOCK_RUBYGLASS)
			.addInput('D', Blocks.BLOCK_DIAMOND)
			.addInput('G', Blocks.OBSIDIAN)
			.create("rubyglass_core_shaped", new ItemStack(RubylinksBlocks.RUBYGLASS_CORE));
	}
	public static void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
	}
}

