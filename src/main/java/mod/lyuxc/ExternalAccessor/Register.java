package mod.lyuxc.ExternalAccessor;

import mod.lyuxc.ExternalAccessor.block.ExternalAccessorBlock;
import mod.lyuxc.ExternalAccessor.block.ExternalAccessorBlockItem;
import mod.lyuxc.ExternalAccessor.blockentity.ExternalAccessorBlockEntity;
import com.mojang.datafixers.DSL;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Register {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExternalAccessor.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXPOSER_TAB = CREATIVE_MODE_TABS.register("external_accessor_creative_tab",() -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.external_accessor"))
            .icon(() -> Register.EXPOSER_BLOCK_ITEM.get().getDefaultInstance())
            .build());
    private static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks("external_accessor");
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems("external_accessor");
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "external_accessor");

    public static final DeferredBlock<ExternalAccessorBlock> EXPOSER_BLOCK =
            BLOCKS.register("external_accessor", () -> new ExternalAccessorBlock(BlockBehaviour.Properties.of().noOcclusion()));

    @SuppressWarnings("unused")
    public static final DeferredItem<Item> EXPOSER_BLOCK_ITEM =
                ITEMS.register("external_accessor", () -> new ExternalAccessorBlockItem(EXPOSER_BLOCK.get(), new Item.Properties()));

    public static final Supplier<BlockEntityType<ExternalAccessorBlockEntity>> EXPOSER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("external_accessor", () -> BlockEntityType.Builder.of(ExternalAccessorBlockEntity::new, Register.EXPOSER_BLOCK.get()).build(DSL.emptyPartType()));
    public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == EXPOSER_TAB.value()) {
            ITEMS.getEntries().stream().map(DeferredHolder::get).forEach(event::accept);
        }
    }
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(Register::addCreativeTab);
    }
}
