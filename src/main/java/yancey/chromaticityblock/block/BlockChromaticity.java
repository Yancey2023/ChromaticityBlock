package yancey.chromaticityblock.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

public class BlockChromaticity extends Block implements BlockEntityProvider {

    public BlockChromaticity(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @Environment(EnvType.CLIENT)
    @SuppressWarnings("deprecation")
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityChromaticity(pos, state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (itemStack.isOf(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityChromaticity blockEntityChromaticity) {
                blockEntityChromaticity.setColor(ItemBlockChromaticity.getColorFromItemStack(itemStack));
            }
        }
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return ItemBlockChromaticity.createWithColor(BlockEntityChromaticity.getColorFromBlockEntity(world.getBlockEntity(pos)));
    }

}
