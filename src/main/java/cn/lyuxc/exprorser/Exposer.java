package cn.lyuxc.exprorser;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod("exposer")
public class Exposer {
    public Exposer(IEventBus modEventbus) {
        Registry.register(modEventbus);
        modEventbus.addListener(this::RegisterCap);
    }
    private void RegisterCap(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK,Registry.EXPOSER.get(),(o,direction) -> o.getItemHandler());
    }
}