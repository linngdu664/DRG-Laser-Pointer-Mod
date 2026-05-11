package com.linngdu664.drglaserpointer.client.renderer.rendertype;

import com.linngdu664.drglaserpointer.DrgLaserPointer;
import com.linngdu664.drglaserpointer.registry.RenderPipelineRegistry;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;

public class MyRenderTypes {
    public static final RenderType LASER_RENDER_TYPE = RenderType.create(
            DrgLaserPointer.makeMyIdentifier("laser").toString(),
            RenderSetup.builder(RenderPipelineRegistry.LASER_PIPELINE)
                    .sortOnUpload()
                    .createRenderSetup()
    );
    /*
    private static final Function<Identifier, RenderType> ITEM_TRANSLUCENT_NO_DEPTH;

    public static RenderType itemTranslucentNoDepth(Identifier texture) {
        return ITEM_TRANSLUCENT_NO_DEPTH.apply(texture);
    }

    static {
        ITEM_TRANSLUCENT_NO_DEPTH = Util.memoize((texture) -> {
            RenderSetup state = RenderSetup.builder(RenderPipelineRegistry.ITEM_TRANSLUCENT_NO_DEPTH_PIPELINE)
                    .withTexture("Sampler0", texture)
                    .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                    .useLightmap()
                    .affectsCrumbling()
                    .sortOnUpload()
                    .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                    .createRenderSetup();
            return RenderType.create(Main.makeMyIdentifier("item_translucent_no_depth").toString(), state);
        });
    }*/
}
