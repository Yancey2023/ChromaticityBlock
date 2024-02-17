package yancey.chromaticityblock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.impl.client.rendering.BuiltinItemRendererRegistryImpl;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.json.ModelTransformationMode;
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
        BlockEntityRendererRegistry.register(ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY, ctx -> new BlockChromaticityRenderer());
        BuiltinItemRendererRegistryImpl.INSTANCE.register(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM,
                (stack, mode, matrices, vertexConsumers, light, overlay) -> {
                    matrices.push();
                    if (mode != ModelTransformationMode.GUI) {
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
                    BlockState blockState = world.getBlockState(blockPos);
                    return !(blockState.getBlock() instanceof BlockChromaticity);
                } catch (IOException e) {
                    return true;
                }
            }
            return true;
        });
    }
}
