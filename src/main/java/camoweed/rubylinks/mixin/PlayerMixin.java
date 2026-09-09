package camoweed.rubylinks.mixin;

import camoweed.rubylinks.interfaces.ITeleporter;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

	@Mixin(Player.class)
	public class PlayerMixin extends Mob implements ITeleporter {

		public PlayerMixin(@NotNull World world) {
			super(world);
		}

		@Override
		public void rubylinks$teleport(final double x, final double y, final double z) {
			setPos(x, y + this.bbHeight, z);
		}
}
