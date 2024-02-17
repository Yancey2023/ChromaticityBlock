package yancey.chromaticityblock.client.renderer.block.entity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;

public class BlockChromaticityRenderer implements BlockEntityRenderer<BlockEntityChromaticity> {

    public BlockChromaticityRenderer() {

    }

    @Override
    public void render(BlockEntityChromaticity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Integer color = entity.getColor();
        if (color != null) {
            render(color, matrices, vertexConsumers);
        }
    }

    public static void render(int color, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        VoxelShapes.fullCube().forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> WorldRenderer.renderFilledBox(matrices,
                vertexConsumers.getBuffer(RenderLayer.getDebugFilledBox()),
                minX, minY, minZ, maxX, maxY, maxZ,
                ((color >> 16) & 0xFF) / 255F, ((color >> 8) & 0xFF) / 255F, (color & 0xFF) / 255F, ((color >> 24) & 0xFF) / 255F));
    }

    @Override
    public boolean isInRenderDistance(BlockEntityChromaticity blockEntity, Vec3d pos) {
        return true;
    }
}
