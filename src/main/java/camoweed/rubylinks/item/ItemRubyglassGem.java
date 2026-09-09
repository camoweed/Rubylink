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
import net.minecraft.core.world.pos.ChunkPos;
import net.minecraft.core.world.pos.ChunkTilePos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemRubyglassGem extends Item {
	public ItemRubyglassGem(String name, String namespaceId, int id) {
		super(name, namespaceId, id);
	}

	@Override
	public @Nullable ItemStack onUse(@NotNull ItemStack itemstack, @NotNull World world, @NotNull Player entityplayer) {
		if (itemstack.getData().getBoolean("rubylinks$has_location")){
			int destX = itemstack.getData().getInteger("rubylinks$x");
			int destY = itemstack.getData().getInteger("rubylinks$y");
			int destZ = itemstack.getData().getInteger("rubylinks$z");
			int dim = itemstack.getData().getInteger("rubylinks$dimension");
			if (dim != world.dimension.id) {
				entityplayer.sendMessageTranslated("rubylinks.teleport.fail.dimension");
				return itemstack;
			}
			int cost = MathHelper.floor(entityplayer.distanceTo(destX, destY, destZ));
			if (entityplayer.score < cost) {
				entityplayer.sendMessageTranslated("rubylinks.teleport.fail.score");
				return itemstack;
			}
			Rubylinks.forceChunkLoads = true;
			Chunk chunk = world.getChunkProvider().provideChunk(new ChunkPos(destX >> 4, destZ >> 4), true);
			Rubylinks.forceChunkLoads = false;
			ChunkTilePos chunkTilePos = new ChunkTilePos(destX, destY, destZ);
			TileEntity te = chunk.getTileEntity(chunkTilePos);
			if (te instanceof TileEntityRubyglassCore TileEntityRubyglassCore && TileEntityRubyglassCore.canTeleport(itemstack)){
				entityplayer.score -= cost;
				Side side = TileEntityRubyglassCore.side();
				((ITeleporter) entityplayer).rubylinks$teleport(destX + side.offsetX() + 0.5f, destY + side.offsetY(), destZ + side.offsetZ() + 0.5f);
				TileEntityRubyglassCore.setInUse(false);
			} else if (!world.isClientSide) {
				entityplayer.sendMessageTranslated("rubylinks.teleport.fail.missing");
			}
			itemstack.getData().putBoolean("rubylinks$has_location", false);
		}
		return itemstack;
	}
}
