package yancey.chromaticityblock.mixin;

import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yancey.chromaticityblock.ChromaticityBlock;

@Mixin(BlockDustParticle.Factory.class)
public class BlockDustParticleFactoryMixin {

    @Inject(method = "createParticle*", at = @At("HEAD"), cancellable = true)
    public void createParticle(BlockStateParticleEffect blockStateParticleEffect, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, CallbackInfoReturnable<Particle> cir) {
        if (blockStateParticleEffect.getBlockState().isOf(ChromaticityBlock.CHROMATICITY_BLOCK)) {
            cir.setReturnValue(null);
        }
    }

}
