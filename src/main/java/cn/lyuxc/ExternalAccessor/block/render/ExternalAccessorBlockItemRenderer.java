package cn.lyuxc.ExternalAccessor.block.render;

import cn.lyuxc.ExternalAccessor.block.ExternalAccessorBlockItem;
import cn.lyuxc.ExternalAccessor.block.models.ExternalAccessorBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ExternalAccessorBlockItemRenderer extends GeoItemRenderer<ExternalAccessorBlockItem> {
    public ExternalAccessorBlockItemRenderer() {
        super(new ExternalAccessorBlockItemModel());
    }
}
