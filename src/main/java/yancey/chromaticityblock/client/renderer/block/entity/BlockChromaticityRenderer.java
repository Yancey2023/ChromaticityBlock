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
        render(entity.getColor(), matrices, vertexConsumers);
    }

    public static void render(int color, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        VoxelShapes.fullCube().forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> drawBox(matrices,
                vertexConsumers.getBuffer(RenderLayer.getLeash()),
                (float) minX, (float) minY, (float) minZ, (float) maxX, (float) maxY, (float) maxZ,
                (color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >> 24) & 0xFF));
    }

    public static void drawBox(MatrixStack matrices, VertexConsumer vertexConsumer, float x1, float y1, float z1, float x2, float y2, float z2, int red, int green, int blue, int alpha) {
        Matrix4f matrix4f = matrices.peek().getModel();
        vertexConsumer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x1, y1, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x1, y2, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x1, y2, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();

        vertexConsumer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y1, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x1, y1, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();

        vertexConsumer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x1, y2, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y2, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y1, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();

        vertexConsumer.vertex(matrix4f, x2, y1, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y2, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();

        vertexConsumer.vertex(matrix4f, x1, y2, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x1, y2, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y2, z1).color(red, green, blue, alpha).light(0xF0, 0xF0).next();

        vertexConsumer.vertex(matrix4f, x1, y1, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();
        vertexConsumer.vertex(matrix4f, x1, y2, z2).color(red, green, blue, alpha).light(0xF0, 0xF0).next();

    }

}
