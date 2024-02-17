package yancey.chromaticityblock.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yancey.chromaticityblock.ChromaticityBlock;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {

    @Shadow
    protected ClientWorld world;

    @Inject(method = "addBlockBreakParticles", at = @At(value = "HEAD"), cancellable = true)
    public void injectAddBlockBreakParticles(BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.isOf(ChromaticityBlock.CHROMATICITY_BLOCK)) {
            ci.cancel();
        }
    }

    @Inject(method = "addBlockBreakingParticles", at = @At(value = "HEAD"), cancellable = true)
    public void injectAddBlockBreakingParticles(BlockPos pos, Direction direction, CallbackInfo ci) {
        if (world.getBlockState(pos).isOf(ChromaticityBlock.CHROMATICITY_BLOCK)) {
            ci.cancel();
        }
    }

}
