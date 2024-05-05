package cn.lyuxc.ExternalAccessor;

import cn.lyuxc.ExternalAccessor.block.render.ExternalAccessorBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod(ExternalAccessor.MOD_ID)
public class ExternalAccessor {
    public static final String MOD_ID = "external_accessor";
    public ExternalAccessor(IEventBus modEventbus) {
        Register.register(modEventbus);
        modEventbus.addListener(this::clientSetup);
        modEventbus.addListener(this::registerCapabilities);
    }
    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, Register.EXPOSER_BLOCK_ENTITY.get(),(o, direction) -> o.getItemHandler());
    }
    public void clientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(Register.EXPOSER_BLOCK_ENTITY.get(), ExternalAccessorBlockRenderer::new);
    }
    public static ResourceLocation rl(String id) {
        return new ResourceLocation(MOD_ID,id);
    }
}