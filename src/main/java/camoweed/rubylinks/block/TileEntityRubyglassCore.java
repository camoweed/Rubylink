package camoweed.rubylinks.block;

import camoweed.rubylinks.Rubylinks;
import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.pos.ChunkPos;
import net.minecraft.core.world.pos.ChunkTilePos;
import org.jetbrains.annotations.NotNull;

public class TileEntityRubyglassCore extends TileEntity {
	private boolean inUse = false;
	private long checkCode = 0;
	private Side side = Side.NORTH;

	public Side side(){
		return this.side;
	}

	public boolean inUse(){
		return this.inUse;
	}

	public ItemStack linkStar(ItemStack stack){
		if(this.worldObj == null){
			return stack;
		}
		this.checkCode = this.worldObj.rand.nextLong();
		int data = this.worldObj.getBlockData(this.tilePos);
		this.side = Side.fromId(data);
		if (stack.getData().getBoolean("rubylinks$has_location")){
			int destX = stack.getData().getInteger("rubylinks$x");
			int destY = stack.getData().getInteger("rubylinks$y");
			int destZ = stack.getData().getInteger("rubylinks$z");
			Rubylinks.forceChunkLoads = true;
			Chunk chunk = worldObj.getChunkProvider().provideChunk(new ChunkPos(destX >> 4, destZ >> 4), true);
			Rubylinks.forceChunkLoads = false;
			TileEntity te = chunk.getTileEntity(new ChunkTilePos(destX & 0xF, destY, destZ & 0xF));
			if (te instanceof TileEntityRubyglassCore tileEntityStellarRewinder && tileEntityStellarRewinder.canTeleport(stack)){
				tileEntityStellarRewinder.setInUse(false);
			}
		}
		stack.getData().putBoolean("rubylinks$has_location", true);
		stack.getData().putInt("rubylinks$x", this.tilePos.x() );
		stack.getData().putInt("rubylinks$y", this.tilePos.y() );
		stack.getData().putInt("rubylinks$z", this.tilePos.z() );
		stack.getData().putInt("rubylinks$dimension", this.worldObj.dimension.id);
		stack.getData().putLong("rubylinks$checkcode", this.checkCode);
		setInUse(true);
		return stack;
	}
	public boolean canTeleport(ItemStack stack){
		boolean can = true;
		can &= stack.getData().getInteger("rubylinks$x") == this.tilePos.x();
		can &= stack.getData().getInteger("rubylinks$y") == this.tilePos.y();
		can &= stack.getData().getInteger("rubylinks$z") == this.tilePos.z();
		can &= stack.getData().getLong("rubylinks$checkcode") == this.checkCode;
		return can;
	}
	public void setInUse(boolean flag){
		inUse = flag;
		if (this.worldObj != null) {
			this.worldObj.notifyBlockChange(this.tilePos, this.worldObj.getBlockType(tilePos));
		}
	}

	@Override
	public void readAdditionalData(@NotNull CompoundTag tag) {
		this.inUse = tag.getBoolean("inuse");
		this.checkCode = tag.getLong("checkcode");
		this.side = Side.fromId(tag.getInteger("side"));
	}

	@Override
	public void writeAdditionalData(@NotNull CompoundTag tag) {
		tag.putBoolean("inuse", inUse);
		tag.putLong("checkcode", checkCode);
		tag.putInt("side", side.id);

	}
}
