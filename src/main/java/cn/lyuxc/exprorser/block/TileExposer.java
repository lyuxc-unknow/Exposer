package cn.lyuxc.exprorser.block;

import cn.lyuxc.exprorser.Registry;
import com.refinedmods.refinedstorage.blockentity.NetworkNodeBlockEntity;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationSpec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;

public class TileExposer extends NetworkNodeBlockEntity<NetworkNodeExposer> {
    public static BlockEntitySynchronizationSpec SPEC = BlockEntitySynchronizationSpec.builder()
            .addWatchedParameter(REDSTONE_MODE)
            .build();

    public TileExposer(BlockPos pos, BlockState state) {
        super(Registry.EXPOSER.get(), pos, state, SPEC, NetworkNodeExposer.class);
    }

    @Override
    public NetworkNodeExposer createNode(Level level, BlockPos blockPos) {
        return new NetworkNodeExposer(level,blockPos);
    }

    @Override
    public CompoundTag writeUpdate(CompoundTag tag) {
        return super.writeUpdate(tag);
    }
    public IItemHandler getItemHandler() {
        return this.getNode().getItemHandler();
    }

}
