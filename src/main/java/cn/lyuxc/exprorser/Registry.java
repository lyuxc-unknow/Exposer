package cn.lyuxc.exprorser;

import cn.lyuxc.exprorser.block.ExposerBlock;
import cn.lyuxc.exprorser.block.TileExposer;
import com.mojang.datafixers.DSL;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
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

public class Registry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "exposer");
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXPOSER_TAB = CREATIVE_MODE_TABS.register("exposer_creative_tab",() -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.exposer"))
            .icon(() -> Registry.EXPOSER_BLOCK_ITEM.get().getDefaultInstance())
            .build());
    private static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks("exposer");

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems("exposer");
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "exposer");

    public static final DeferredBlock<ExposerBlock> EXPOSER_BLOCK =
            BLOCKS.register("exposer", () -> new ExposerBlock(BlockBehaviour.Properties.of()));

    @SuppressWarnings("unused")
    public static final DeferredItem<Item> EXPOSER_BLOCK_ITEM =
            ITEMS.register("exposer", () -> new BlockItem(EXPOSER_BLOCK.get(), new Item.Properties()));

    public static final Supplier<BlockEntityType<TileExposer>> EXPOSER =
            BLOCK_ENTITIES.register("exposer", () -> BlockEntityType.Builder.of(TileExposer::new, Registry.EXPOSER_BLOCK.get()).build(DSL.emptyPartType()));
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
        modEventBus.addListener(Registry::addCreativeTab);
    }
}
