package cn.lyuxc.exprorser.cap;

import com.refinedmods.refinedstorage.api.network.INetwork;
import com.refinedmods.refinedstorage.api.storage.cache.IStorageCacheListener;
import com.refinedmods.refinedstorage.api.util.Action;
import com.refinedmods.refinedstorage.api.util.IComparer;
import com.refinedmods.refinedstorage.api.util.StackListEntry;
import com.refinedmods.refinedstorage.api.util.StackListResult;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemHandlerExposer implements IItemHandler, IStorageCacheListener<ItemStack> {
    private final INetwork network;
    private List<ItemStack> storageCacheData;

    public ItemHandlerExposer(INetwork network) {
        this.network = network;
        invalidate();
    }
    @Override
    public void onAttached() {
//        invalidate();
    }

    @Override
    public void onInvalidated() {
        invalidate();
    }

    @Override
    public void onChanged(StackListResult<ItemStack> stackListResult) {
        invalidate();
    }

    @Override
    public void onChangedBulk(List<StackListResult<ItemStack>> list) {
        invalidate();
    }

    @Override
    public int getSlots() {
        return storageCacheData.size() + 1;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        if (slot < storageCacheData.size()) {
            return storageCacheData.get(slot);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return network.insertItem(stack, stack.getCount(), simulate ? Action.SIMULATE : Action.PERFORM);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot < storageCacheData.size()) {
            return network.extractItem(storageCacheData.get(slot), amount, IComparer.COMPARE_QUANTITY | IComparer.COMPARE_NBT, simulate ? Action.SIMULATE : Action.PERFORM);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int i) {
        return 64;
    }

    @Override
    public boolean isItemValid(int i, @NotNull ItemStack itemStack) {
        return false;
    }

    private void invalidate() {
        this.storageCacheData = network.getItemStorageCache().getList().getStacks().stream().map(StackListEntry::getStack).toList();
    }
}
