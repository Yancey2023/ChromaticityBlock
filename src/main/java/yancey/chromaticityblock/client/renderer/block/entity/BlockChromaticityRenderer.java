package yancey.chromaticityblock.client.renderer.block.entity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.shape.VoxelShapes;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;

public class BlockChromaticityRenderer extends BlockEntityRenderer<BlockEntityChromaticity> {

    public BlockChromaticityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(BlockEntityChromaticity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Integer color = entity.getColor();
        if (color != null) {
            render(color, matrices, vertexConsumers);
        }
    }

    public static void render(int color, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
//        DEBUG_FILLED_BOX = of("debug_filled_box", VertexFormats.POSITION_COLOR, DrawMode.TRIANGLE_STRIP, 1536, false, true, RenderLayer.MultiPhaseParameters.builder().program(COLOR_PROGRAM).layering(VIEW_OFFSET_Z_LAYERING).transparency(TRANSLUCENT_TRANSPARENCY).build(false));
        VoxelShapes.fullCube().forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> renderFilledBox(matrices,
                vertexConsumers.getBuffer(RenderLayer.getWaterMask()),
                (float) minX, (float) minY, (float) minZ, (float) maxX, (float) maxY, (float) maxZ,
                ((color >> 16) & 0xFF) / 255F, ((color >> 8) & 0xFF) / 255F, (color & 0xFF) / 255F, ((color >> 24) & 0xFF) / 255F));
    }

    public static void renderFilledBox(MatrixStack matrices, VertexConsumer vertexConsumer, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float red, float green, float blue, float alpha) {
        Matrix4f matrix4f = matrices.peek().getModel();
        vertexConsumer.vertex(matrix4f, minX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, minY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, maxY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, maxY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, maxY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, minY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, minY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, minY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, maxY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, minY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, minY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, minY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, minY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, maxY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, maxY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, minX, maxY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, minZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, maxZ).color(red, green, blue, alpha).next();
        vertexConsumer.vertex(matrix4f, maxX, maxY, maxZ).color(red, green, blue, alpha).next();
    }

}
