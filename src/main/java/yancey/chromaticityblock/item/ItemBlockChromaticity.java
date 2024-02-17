package yancey.chromaticityblock.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;

public class ItemBlockChromaticity extends BlockItem {

    public ItemBlockChromaticity() {
        super(ChromaticityBlock.CHROMATICITY_BLOCK, new FabricItemSettings());
    }

    @Override
    public Text getName(ItemStack stack) {
        int color = getColorFromItemStack(stack);
        return new LiteralText("")
                .append(withColor(super.getName(stack).copy(), 0xFF000000 | color))
                .append(String.format("#%08X", color));
    }

    public static Text withColor(MutableText text, int color) {
        return text.setStyle(text.getStyle().withColor(TextColor.fromRgb(color)));
    }

    public static int getColorFromItemStack(ItemStack stack) {
        return getColorFromNBT(stack.getSubTag("BlockEntityTag"));
    }

    public static int getColorFromNBT(NbtCompound nbt) {
        int color = 0xFF00FF00;
        if (nbt != null && nbt.contains(BlockEntityChromaticity.KEY_COLOR, 3)) {
            color = nbt.getInt(BlockEntityChromaticity.KEY_COLOR);
        }
        return color;
    }

    public static ItemStack createWithColor(int color) {
        ItemStack itemStack = new ItemStack(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM);
        NbtCompound nbtCompound = itemStack.getOrCreateSubTag("BlockEntityTag");
        nbtCompound.putString("id", ChromaticityBlock.ID_CHROMATICITY_BLOCK_ENTITY.toString());
        nbtCompound.putInt(BlockEntityChromaticity.KEY_COLOR, color);
        return itemStack;
    }

}
