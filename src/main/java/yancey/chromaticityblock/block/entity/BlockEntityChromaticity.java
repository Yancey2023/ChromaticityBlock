package yancey.chromaticityblock.block.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

public class BlockEntityChromaticity extends BlockEntity implements BlockEntityClientSerializable {

    public static final String KEY_COLOR = "color";
    private int color = 0xFF00FF00;

    public BlockEntityChromaticity() {
        super(ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt(KEY_COLOR, color);
        return nbt;
    }

    @Override
    public void fromTag(BlockState state, NbtCompound tag) {
        super.fromTag(state, tag);
        color = ItemBlockChromaticity.getColorFromNBT(tag);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (this.color != color) {
            this.color = color;
            markDirty();
        }
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return writeNbt(new NbtCompound());
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        fromTag(getCachedState(), tag);
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        return toInitialChunkDataNbt();
    }

    public static int getColorFromBlockEntity(BlockEntity blockEntity) {
        int color = 0xFF00FF00;
        if (blockEntity instanceof BlockEntityChromaticity) {
            color = ((BlockEntityChromaticity) blockEntity).getColor();
        }
        return color;
    }

}
