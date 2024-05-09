package mod.lyuxc.ExternalAccessor.block.render;

import mod.lyuxc.ExternalAccessor.block.models.ExternalAccessorBlockModel;
import mod.lyuxc.ExternalAccessor.blockentity.ExternalAccessorBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ExternalAccessorBlockRenderer extends GeoBlockRenderer<ExternalAccessorBlockEntity> {
    public ExternalAccessorBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new ExternalAccessorBlockModel());
    }
}
