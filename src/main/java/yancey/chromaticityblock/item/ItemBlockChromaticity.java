package yancey.chromaticityblock.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import yancey.chromaticityblock.ChromaticityBlock;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;

public class ItemBlockChromaticity extends BlockItem {

    public ItemBlockChromaticity() {
        super(ChromaticityBlock.CHROMATICITY_BLOCK, new Item.Settings());
    }

    @Override
    public Text getName(ItemStack stack) {
        int color = getColorFromItemStack(stack);
        return Text.empty()
                .append(super.getName(stack).copy().withColor(0xFF000000 | color))
                .append(String.format("#%08X", color));
    }

    public static int getColorFromItemStack(ItemStack stack) {
        return getColorFromNBT(stack.getOrDefault(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.DEFAULT).getNbt());
    }

    public static int getColorFromNBT(NbtCompound nbt) {
        int color = 0xFF00FF00;
        if (nbt != null && nbt.contains(BlockEntityChromaticity.KEY_COLOR, NbtElement.INT_TYPE)) {
            color = nbt.getInt(BlockEntityChromaticity.KEY_COLOR);
        }
        return color;
    }

    public static ItemStack createWithColor(int color) {
        ItemStack itemStack = new ItemStack(ChromaticityBlock.CHROMATICITY_BLOCK_ITEM);
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putInt(BlockEntityChromaticity.KEY_COLOR, color);
        BlockItem.setBlockEntityData(itemStack, ChromaticityBlock.CHROMATICITY_BLOCK_ENTITY, nbtCompound);
        return itemStack;
    }

}
