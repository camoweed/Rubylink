package camoweed.rubylinks.block;

import camoweed.rubylinks.RubylinksItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockRubyglassCore extends BlockLogicRotatable {
	//Uses BlockTileEntityRotatable for its rotation properties not because its a tileEntity
	public BlockRubyglassCore(Block<?> block, Material material) {
		super(block, material);
		block.withEntity(TileEntityRubyglassCore::new);
	}

	@Override
	public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
		ItemStack heldItem = player.getHeldItem();
		TileEntityRubyglassCore rewinder = (TileEntityRubyglassCore) world.getTileEntity(tilePos);
		if (heldItem != null && heldItem.getItem() == RubylinksItems.RUBYGLASS_GEM && rewinder != null){
			rewinder.linkStar(heldItem);
			return true;
		}
		return false;
	}

	@Override
	public int getPistonPushReaction(@NotNull World world, @NotNull TilePosc tilePos) {
		return 2;
	}
}
