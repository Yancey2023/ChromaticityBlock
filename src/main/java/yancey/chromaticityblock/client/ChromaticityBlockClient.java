package yancey.chromaticityblock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.BlockChromaticity;
import yancey.chromaticityblock.client.renderer.block.entity.BlockChromaticityRenderer;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

@Environment(EnvType.CLIENT)
public class ChromaticityBlockClient implements ClientModInitializer {

    @Override
    @SuppressWarnings("resource")
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY, ctx -> new BlockChromaticityRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM,
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
                return !(context.world().getBlockState(((BlockHitResult) hitResult).getBlockPos()).getBlock() instanceof BlockChromaticity);
            }
            return true;
        });
    }
}
