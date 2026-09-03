package camoweed.rubylinks.block;

import camoweed.rubylinks.Rubylinks;
import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.pos.TilePos;
import org.jetbrains.annotations.NotNull;

public class TileEntityRubyglassCore extends TileEntity {
	public boolean inUse = false;
	public long checkCode = 0;
	public Side side = Side.NORTH;

	TilePos tilePos = new TilePos();
	int x = tilePos.x();
	int y = tilePos.y();
	int z = tilePos.z();

	public ItemStack linkGem(ItemStack stack) {
		checkCode = worldObj.rand.nextLong();
		side = Side.fromId(worldObj.getBlockMetadata(x, y, z));
		if (stack.getData().getBoolean("rubylinks$has_location")){
			int destX = stack.getData().getInteger("rubylinks$x");
			int destY = stack.getData().getInteger("rubylinks$y");
			int destZ = stack.getData().getInteger("rubylinks$z");
			Rubylinks.forceChunkLoads = true;
			Chunk chunk = worldObj.getChunkProvider().provideChunk(destX >> 4, destZ >> 4);
			Rubylinks.forceChunkLoads = false;
			TileEntity te = chunk.getTileEntity(destX &0xF, destY, destZ &0xF);
			if (te instanceof TileEntityRubyglassCore && ((TileEntityRubyglassCore) te).canTeleport(stack)){
				((TileEntityRubyglassCore) te).setInUse(false);
			}
		}
		stack.getData().putBoolean("rubylinks$has_location", true);
		stack.getData().putInt("rubylinks$x", x );
		stack.getData().putInt("rubylinks$y", y );
		stack.getData().putInt("rubylinks$z", z );
		stack.getData().putInt("rubylinks$dimension", worldObj.dimension.id);
		stack.getData().putLong("rubylinks$checkcode", checkCode);
		setInUse(true);
		return stack;
	}

	public boolean canTeleport(ItemStack stack) {
		boolean can = true;
		can &= stack.getData().getInteger("rubylinks$x") == x;
		can &= stack.getData().getInteger("rubylinks$y") == y;
		can &= stack.getData().getInteger("rubylinks$z") == z;
		can &= stack.getData().getLong("rubylinks$checkcode") == checkCode;
		return can;
	}
	public void setInUse(boolean flag) {
	inUse = flag;
	worldObj.notifyBlockChange(x,y,z,worldObj.getBlockId(x,y,z));
	}

	@Override
	public void readAdditionalData(@NotNull CompoundTag tag) {
		super.readFromNBT(tag);
		this.inUse = tag.getBoolean("inuse");
		this.checkCode = tag.getLong("checkcode");
		this.side = Side.fromId(tag.getInteger("side"));
	}

	@Override
	public void writeAdditionalData(@NotNull CompoundTag tag) {
		super.writeToNBT(tag);
		tag.putBoolean("inuse", inUse);
		tag.putLong("checkcode", checkCode);
		tag.putInt("side", side.fromId());
	}
}
