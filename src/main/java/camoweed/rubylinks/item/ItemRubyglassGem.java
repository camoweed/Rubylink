package camoweed.rubylinks.item;

import camoweed.rubylinks.Rubylinks;
import camoweed.rubylinks.block.TileEntityRubyglassCore;
import camoweed.rubylinks.interfaces.ITeleporter;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;

public class ItemRubyglassGem extends Item {
	public ItemRubyglassGem(String name, String namespaceId, int id) {
		super(name, namespaceId, id);
	}

	@Override
	public ItemStack onUse(ItemStack itemstack, World world, Player entityPlayer) {

		if (itemstack.getData().getBoolean("rubylinks$has_location")){
			int destX = itemstack.getData().getInteger("rubylinks$x");
			int destY = itemstack.getData().getInteger("rubylinks$y");
			int destZ = itemstack.getData().getInteger("rubylinks$z");
			int dim = itemstack.getData().getInteger("rubylinks$dimension");

			if (dim != world.dimension.id) {
				entityPlayer.sendTranslatedChatMessage("rubylinks.teleport.fail.dimension");
				return itemstack;
			}
			int cost = MathHelper.floor(entityPlayer.distanceTo(destX, destY, destZ));
			if (entityPlayer.score < cost) {
				entityPlayer.sendTranslatedChatMessage("moonsteel.teleport.fail.score");
				return itemstack;
			}
			Rubylinks.forceChunkLoads = true;
			Chunk chunk = world.getChunkProvider().provideChunk(destX >> 4, destZ >> 4);
			Rubylinks.forceChunkLoads = false;
			TileEntity te = chunk.getTileEntity(destX &0xF, destY, destZ &0xF);
			if (te instanceof TileEntityRubyglassCore && ((TileEntityRubyglassCore) te).canTeleport(itemstack)){
				entityPlayer.score -= cost;
				Side side = ((TileEntityRubyglassCore) te).side;
				((ITeleporter) entityPlayer).rubylinks$teleport(destX + side.offsetX() + 0.5f, destY + side.offsetY(), destZ + side.offsetZ() + 0.5f);
				((TileEntityRubyglassCore) te).setInUse(false);
				} else if (!world.isClientSide) {
				entityPlayer.sendTranslatedChatMessage("moonsteel.teleport.fail.missing");
			}
			itemstack.getData().putBoolean("moonsteel$has_location", false);
		}
		return itemstack;
	}

}
