package yancey.chromaticityblock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.BlockChromaticity;
import yancey.chromaticityblock.client.renderer.block.entity.BlockChromaticityRenderer;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

import java.io.IOException;

public class ChromaticityBlockClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY, BlockChromaticityRenderer::new);
        BuiltinItemRendererRegistry.INSTANCE.register(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM,
                (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                    matrices.push();
                    if (mode != ModelTransformation.Mode.GUI) {
                        matrices.translate(0.5, 0.5, 0.5);
                        matrices.scale(0.3F, 0.3F, 0.3F);
                    }
                    BlockChromaticityRenderer.render(ItemBlockChromaticity.getColorFromItemStack(stack), matrices, vertexConsumers);
                    matrices.pop();
                });
        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((context, hitResult) -> {
            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = ((BlockHitResult) hitResult).getBlockPos();
                try (ClientWorld world = context.world()) {
                    return !(world.getBlockState(blockPos).getBlock() instanceof BlockChromaticity);
                } catch (IOException e) {
                    return true;
                }
            }
            return true;
        });
    }
}
