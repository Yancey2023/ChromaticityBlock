package yancey.chromaticityblock.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;

public class ItemBlockChromaticity extends BlockItem {

    public ItemBlockChromaticity() {
        super(ChromaticityBlock.CHROMATICITY_BLOCK, new FabricItemSettings());
    }

    @Override
    public Text getName(ItemStack stack) {
        int color = getColorFromItemStack(stack);
        return Text.empty()
                .append(super.getName(stack).copy().withColor(0xFF000000 | color))
                .append(Text.literal(" #" + Integer.toHexString(color).toUpperCase()));
    }

    public static int getColorFromItemStack(ItemStack stack) {
        return getColorFromNBT(BlockItem.getBlockEntityNbt(stack));
    }

    public static int getColorFromNBT(NbtCompound nbt) {
        int color = 0xFF00FF00;
        if (nbt != null && nbt.contains(BlockEntityChromaticity.KEY_COLOR, NbtElement.INT_TYPE)) {
            color = nbt.getInt(BlockEntityChromaticity.KEY_COLOR);
        }
        return color;
    }
}
