package yancey.chromaticityblock;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import yancey.chromaticityblock.block.BlockChromaticity;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

public class ChromaticityBlock implements ModInitializer {

    public static final String MOD_ID = "chromaticityblock";
    public static final Identifier ID_CHROMATICITY_BLOCK = new Identifier(MOD_ID, "chromaticity_block");
    public static final Identifier ID_CHROMATICITY_BLOCK_ENTITY = new Identifier(MOD_ID, "chromaticity_block_entity");
    public static final Identifier ID_ITEM_GROUP = new Identifier(MOD_ID, MOD_ID);
    public static final Block CHROMATICITY_BLOCK = new BlockChromaticity(FabricBlockSettings.of(Material.METAL)
            .strength(-1, 3600000).dropsNothing().nonOpaque());
    public static final Item CHROMATICITY_BLOCK_ITEM = new ItemBlockChromaticity();
    public static BlockEntityType<BlockEntityChromaticity> CHROMATICITY_BLOCK_ENTITY = new BlockEntityType<>(
            BlockEntityChromaticity::new, ImmutableSet.of(CHROMATICITY_BLOCK),
            Util.getChoiceType(TypeReferences.BLOCK_ENTITY, "chromaticity_block"));

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(ID_ITEM_GROUP)
            .icon(() -> new ItemStack(CHROMATICITY_BLOCK_ITEM))
            .appendItems(itemStacks -> {
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFF000000));
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFFFFFFFF));

                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFFFF0000));
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFF00FF00));
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFF0000FF));

                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFFFFFF00));
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFF00FFFF));
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFFFF00FF));

                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFF8800FF));
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFF88FFFF));
                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFFFF88FF));

                itemStacks.add(BlockChromaticity.createItemStackWithColor(0xFFBA54EF));
            }).build();

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, ID_CHROMATICITY_BLOCK, CHROMATICITY_BLOCK);
        Registry.register(Registry.ITEM, ID_CHROMATICITY_BLOCK, CHROMATICITY_BLOCK_ITEM);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, ID_CHROMATICITY_BLOCK_ENTITY, CHROMATICITY_BLOCK_ENTITY);
        ((ItemGroupExtensions) ITEM_GROUP).fabric_expandArray();
        ItemGroup.GROUPS[ItemGroup.GROUPS.length - 1] = ITEM_GROUP;
    }
}
