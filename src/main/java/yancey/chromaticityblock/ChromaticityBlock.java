package yancey.chromaticityblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import yancey.chromaticityblock.block.BlockChromaticity;
import yancey.chromaticityblock.block.entity.BlockEntityChromaticity;
import yancey.chromaticityblock.item.ItemBlockChromaticity;

import java.util.Set;

public class ChromaticityBlock implements ModInitializer {

    @SuppressWarnings("SpellCheckingInspection")
    public static final String MOD_ID = "chromaticityblock";
    public static final Identifier ID_CHROMATICITY_BLOCK = Identifier.of(MOD_ID, "chromaticity_block");
    public static final Identifier ID_CHROMATICITY_BLOCK_ENTITY = Identifier.of(MOD_ID, "chromaticity_block_entity");
    public static final Identifier ID_ITEM_GROUP = Identifier.of(MOD_ID, MOD_ID);
    public static final Block CHROMATICITY_BLOCK = new BlockChromaticity(AbstractBlock.Settings.create()
            .strength(-1, 3600000).dropsNothing().nonOpaque().noBlockBreakParticles().pistonBehavior(PistonBehavior.BLOCK));
    public static final Item CHROMATICITY_BLOCK_ITEM = new ItemBlockChromaticity();
    public static BlockEntityType<BlockEntityChromaticity> CHROMATICITY_BLOCK_ENTITY = new BlockEntityType<>(
            BlockEntityChromaticity::new, Set.of(CHROMATICITY_BLOCK), null);
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup." + MOD_ID))
            .icon(() -> new ItemStack(CHROMATICITY_BLOCK_ITEM))
            .entries((displayContext, entries) -> {
                entries.add(ItemBlockChromaticity.createWithColor(0xFF000000));
                entries.add(ItemBlockChromaticity.createWithColor(0xFFFFFFFF));

                entries.add(ItemBlockChromaticity.createWithColor(0xFFFF0000));
                entries.add(ItemBlockChromaticity.createWithColor(0xFF00FF00));
                entries.add(ItemBlockChromaticity.createWithColor(0xFF0000FF));

                entries.add(ItemBlockChromaticity.createWithColor(0xFFFFFF00));
                entries.add(ItemBlockChromaticity.createWithColor(0xFF00FFFF));
                entries.add(ItemBlockChromaticity.createWithColor(0xFFFF00FF));

                entries.add(ItemBlockChromaticity.createWithColor(0xFF8800FF));
                entries.add(ItemBlockChromaticity.createWithColor(0xFF88FFFF));
                entries.add(ItemBlockChromaticity.createWithColor(0xFFFF88FF));

                entries.add(ItemBlockChromaticity.createWithColor(0xFFBA54EF));
            }).build();

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, ID_CHROMATICITY_BLOCK, CHROMATICITY_BLOCK);
        Registry.register(Registries.ITEM, ID_CHROMATICITY_BLOCK, CHROMATICITY_BLOCK_ITEM);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, ID_CHROMATICITY_BLOCK_ENTITY, CHROMATICITY_BLOCK_ENTITY);
        Registry.register(Registries.ITEM_GROUP, ID_ITEM_GROUP, ITEM_GROUP);
    }
}
