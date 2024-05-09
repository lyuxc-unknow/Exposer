package mod.lyuxc.ExternalAccessor.block.render;

import mod.lyuxc.ExternalAccessor.block.ExternalAccessorBlockItem;
import mod.lyuxc.ExternalAccessor.block.models.ExternalAccessorBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ExternalAccessorBlockItemRenderer extends GeoItemRenderer<ExternalAccessorBlockItem> {
    public ExternalAccessorBlockItemRenderer() {
        super(new ExternalAccessorBlockItemModel());
    }
}
