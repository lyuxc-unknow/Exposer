package cn.lyuxc.ExternalAccessor.block.models;

import cn.lyuxc.ExternalAccessor.ExternalAccessor;
import cn.lyuxc.ExternalAccessor.block.render.tile.ExternalAccessorBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ExternalAccessorBlockModel extends GeoModel<ExternalAccessorBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ExternalAccessorBlockEntity creativeGeneratorBlockEntity) {
        return ExternalAccessor.rl("geo/external_accessor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ExternalAccessorBlockEntity creativeGeneratorBlockEntity) {
        return ExternalAccessor.rl("textures/block/external_accessor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ExternalAccessorBlockEntity creativeGeneratorBlockEntity) {
        return ExternalAccessor.rl("animations/external_accessor.animation.json");
    }
}
