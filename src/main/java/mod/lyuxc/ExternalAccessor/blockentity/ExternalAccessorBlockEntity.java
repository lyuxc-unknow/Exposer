package mod.lyuxc.ExternalAccessor.blockentity;

import mod.lyuxc.ExternalAccessor.Register;
import mod.lyuxc.ExternalAccessor.node.ExternalAccessorNode;
import com.refinedmods.refinedstorage.blockentity.NetworkNodeBlockEntity;
import com.refinedmods.refinedstorage.blockentity.data.BlockEntitySynchronizationSpec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class ExternalAccessorBlockEntity extends NetworkNodeBlockEntity<ExternalAccessorNode> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static BlockEntitySynchronizationSpec SPEC = BlockEntitySynchronizationSpec.builder()
            .addWatchedParameter(REDSTONE_MODE)
            .build();

    public ExternalAccessorBlockEntity(BlockPos pos, BlockState state) {
        super(Register.EXPOSER_BLOCK_ENTITY.get(), pos, state, SPEC, ExternalAccessorNode.class);
    }

    @Override
    public ExternalAccessorNode createNode(Level level, BlockPos blockPos) {
        return new ExternalAccessorNode(level,blockPos);
    }
    public IItemHandler getItemHandler() {
        return this.getNode().getItemHandler();
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }
}
