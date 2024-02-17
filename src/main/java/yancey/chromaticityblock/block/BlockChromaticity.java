package yancey.chromaticityblock.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;

public class BlockChromaticity extends Block implements BlockEntityProvider {

    public BlockChromaticity(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityChromaticity(pos, state);
    }

    public static ItemStack createItemStackWithColor(int color) {
        ItemStack itemStack = new ItemStack(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM);
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putInt(BlockEntityChromaticity.KEY_COLOR, color);
        BlockItem.setBlockEntityNbt(itemStack, ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY, nbtCompound);
        return itemStack;
    }

    @Override
    protected void spawnBreakParticles(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, pos, getRawIdFromState(state));
    }

}
