package mod.lyuxc.ExternalAccessor.block.models;

import mod.lyuxc.ExternalAccessor.ExternalAccessor;
import mod.lyuxc.ExternalAccessor.block.ExternalAccessorBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ExternalAccessorBlockItemModel extends GeoModel<ExternalAccessorBlockItem> {
    @Override
    public ResourceLocation getModelResource(ExternalAccessorBlockItem exposerBlockItem) {
        return ExternalAccessor.rl("geo/external_accessor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ExternalAccessorBlockItem exposerBlockItem) {
        return ExternalAccessor.rl("textures/block/external_accessor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ExternalAccessorBlockItem exposerBlockItem) {
        return ExternalAccessor.rl("animations/external_accessor.animation.json");
    }
}
