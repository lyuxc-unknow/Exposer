package cn.lyuxc.ExternalAccessor.block.render;

import cn.lyuxc.ExternalAccessor.block.models.ExternalAccessorBlockModel;
import cn.lyuxc.ExternalAccessor.block.render.tile.ExternalAccessorBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ExternalAccessorBlockRenderer extends GeoBlockRenderer<ExternalAccessorBlockEntity> {
    public ExternalAccessorBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new ExternalAccessorBlockModel());
    }
}
