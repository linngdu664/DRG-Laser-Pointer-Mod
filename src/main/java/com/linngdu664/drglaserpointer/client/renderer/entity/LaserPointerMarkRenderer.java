package com.linngdu664.drglaserpointer.client.renderer.entity;

import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModel;
import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModelBall;
import com.linngdu664.drglaserpointer.client.renderer.entity.state.LaserPointerMarkRenderState;
import com.linngdu664.drglaserpointer.config.ClientConfig;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public class LaserPointerMarkRenderer extends EntityRenderer<LaserPointerMarkEntity, LaserPointerMarkRenderState> {
    private final LaserPointerMarkModel model;
    private final LaserPointerMarkModelBall modelBall;

    public LaserPointerMarkRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.1F;
        ModelPart modelpart = context.bakeLayer(LaserPointerMarkModel.LAYER_LOCATION_BLUE);
        model = new LaserPointerMarkModel(modelpart);
        ModelPart modelpartBall = context.bakeLayer(LaserPointerMarkModelBall.LAYER_LOCATION_RED);
        modelBall = new LaserPointerMarkModelBall(modelpartBall);
    }

    @Override
    protected int getBlockLightLevel(LaserPointerMarkEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public LaserPointerMarkRenderState createRenderState() {
        return new LaserPointerMarkRenderState();
    }

    @Override
    public void extractRenderState(LaserPointerMarkEntity entity, LaserPointerMarkRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.color = entity.getColor();
    }

    @Override
    public void submit(LaserPointerMarkRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        Identifier texture = switch (state.color) {
            case 0 -> LaserPointerMarkModel.LAYER_LOCATION_BLUE.model();
            case 1 -> LaserPointerMarkModel.LAYER_LOCATION_RED.model();
            case 2 -> LaserPointerMarkModel.LAYER_LOCATION_YELLOW.model();
            case 3 -> LaserPointerMarkModel.LAYER_LOCATION_GREEN.model();
            default -> LaserPointerMarkModel.LAYER_LOCATION_EMPTY.model();
        };
        RenderType renderType = RenderTypes.entityTranslucentCullItemTarget(texture);
        if (ClientConfig.CUBE_MARK_MODEL.getConfigValue()) {
            poseStack.pushPose();
            poseStack.scale(0.75F, 0.75F, 0.75F);
            submitNodeCollector.submitModel(model, state, poseStack, renderType, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
            poseStack.popPose();
        } else {
            submitNodeCollector.submitModel(modelBall, state, poseStack, renderType, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        }
    }
}
