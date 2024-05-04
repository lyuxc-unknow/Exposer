package cn.lyuxc.exprorser.block;

import cn.lyuxc.exprorser.cap.ItemHandlerExposer;
import com.refinedmods.refinedstorage.api.network.INetwork;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;

public class NetworkNodeExposer extends NetworkNode{
    private ItemHandlerExposer itemHandler;
    protected NetworkNodeExposer(Level level, BlockPos pos) {
        super(level, pos);
    }

    @Override
    public int getEnergyUsage() {
        return 1;
    }

    @Override
    public ResourceLocation getId() {
        return new ResourceLocation("exposer");
    }

    @Override
    public void onConnected(INetwork network) {
        super.onConnected(network);
        itemHandler = new ItemHandlerExposer(network);
        network.getItemStorageCache().addListener(itemHandler);
    }

    @Override
    public void onDisconnected(INetwork network) {
        super.onDisconnected(network);
        network.getItemStorageCache().removeListener(itemHandler);
        itemHandler = null;
    }

    public IItemHandler getItemHandler() {
        return itemHandler;
    }
}
