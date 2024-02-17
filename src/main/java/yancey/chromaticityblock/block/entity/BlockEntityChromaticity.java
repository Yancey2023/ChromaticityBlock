package yancey.chromaticityblock.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.registry.Registry;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

public class BlockEntityChromaticity extends BlockEntity {

    public static final String KEY_COLOR = "color";
    private Integer color = null;

    public BlockEntityChromaticity() {
        super(ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (color == null) {
            color = 0xFF00FF00;
        }
        nbt.putInt(KEY_COLOR, color);
        return nbt;
    }

    @Override
    public void fromTag(BlockState state, NbtCompound tag) {
        super.fromTag(state, tag);
        color = ItemBlockChromaticity.getColorFromNBT(tag);
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(int color) {
        if (this.color != color) {
            this.color = color;
            markDirty();
        }
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(getPos(), Registry.BLOCK_ENTITY_TYPE.getRawId(getType()), writeNbt(new NbtCompound()));
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return writeNbt(new NbtCompound());
    }
}
