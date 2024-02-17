package yancey.chromaticityblock.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

public class BlockEntityChromaticity extends BlockEntity {

    public static final String KEY_COLOR = "color";
    private Integer color = null;

    public BlockEntityChromaticity(BlockPos pos, BlockState state) {
        super(ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        if (color == null) {
            color = 0xFF00FF00;
        }
        nbt.putInt(KEY_COLOR, color);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        color = ItemBlockChromaticity.getColorFromNBT(nbt);
        super.readNbt(nbt);
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

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
