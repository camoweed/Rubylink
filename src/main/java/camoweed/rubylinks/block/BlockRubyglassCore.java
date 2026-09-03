package camoweed.rubylinks.block;

import camoweed.rubylinks.RubylinksItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockRubyglassCore extends BlockLogicRotatable {
	public BlockRubyglassCore(Block<?> block, Material material) {
		super(block, material);
		block.withEntity(TileEntityRubyglassCore::new);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
		ItemStack heldItem = player.getHeldItem();
		// deprecated method
		TileEntityRubyglassCore rubycore = (TileEntityRubyglassCore) world.getTileEntity(x,y,z);
		if (heldItem != null && heldItem.getItem() == RubylinksItems.RUBYGLASS_GEM){
			rubycore.linkGem(heldItem);
			return true;
		}
		return false;
	}


}
