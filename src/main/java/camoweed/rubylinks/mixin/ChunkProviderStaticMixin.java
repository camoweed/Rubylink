package camoweed.rubylinks.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.world.chunk.provider.ChunkProviderStatic;
import net.minecraft.core.world.pos.ChunkPosc;
import org.spongepowered.asm.mixin.Mixin;

import camoweed.rubylinks.Rubylinks;

	@Mixin(value = ChunkProviderStatic.class, remap = false)
	public class ChunkProviderStaticMixin {

		@WrapMethod(method = "canChunkExist")
		private boolean overrideChunk(ChunkPosc chunkPos, Operation<Boolean> original){
			return Rubylinks.forceChunkLoads;
		}
	}
