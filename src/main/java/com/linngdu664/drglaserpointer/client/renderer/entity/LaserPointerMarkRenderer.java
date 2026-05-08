package com.linngdu664.drglaserpointer.client.renderer.entity;

import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModel;
import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModelBall;
import com.linngdu664.drglaserpointer.client.renderer.entity.state.LaserPointerMarkRenderState;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.BlockPos;

public class LaserPointerMarkRenderer extends EntityRenderer<LaserPointerMarkEntity, LaserPointerMarkRenderState> {
    private final LaserPointerMarkModel model;
    private final LaserPointerMarkModelBall modelBall;

//    private final RenderType renderType = RenderTypes.entityTranslucentCullItemTarget(LaserPointerMarkModel.LAYER_LOCATION_BLUE.model());

    public LaserPointerMarkRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.1F;
        ModelPart modelpart = context.bakeLayer(LaserPointerMarkModel.LAYER_LOCATION_BLUE);
        model = new LaserPointerMarkModel(modelpart);
        ModelPart modelpartBall = context.bakeLayer(LaserPointerMarkModelBall.LAYER_LOCATION_RED);
        modelBall = new LaserPointerMarkModelBall(modelpartBall);
    }
/*
    @Override
    public @NotNull Identifier getTextureLocation(@NotNull Entity entity) {
        return switch (((LaserPointerMarkEntity) entity).getColor()) {
            default -> LaserPointerMarkModel.LAYER_LOCATION_EMPTY.getModel();
            case 0 -> LaserPointerMarkModel.LAYER_LOCATION_BLUE.getModel();
            case 1 -> LaserPointerMarkModel.LAYER_LOCATION_RED.getModel();
            case 2 -> LaserPointerMarkModel.LAYER_LOCATION_YELLOW.getModel();
            case 3 -> LaserPointerMarkModel.LAYER_LOCATION_GREEN.getModel();
        };
    }*/

//    @Override
//    public Identifier getTextureLocation(LaserPointerMarkRenderState state) {
//        return switch (state.color) {
//            default -> LaserPointerMarkModel.LAYER_LOCATION_EMPTY.model();
//            case 0 -> LaserPointerMarkModel.LAYER_LOCATION_BLUE.model();
//            case 1 -> LaserPointerMarkModel.LAYER_LOCATION_RED.model();
//            case 2 -> LaserPointerMarkModel.LAYER_LOCATION_YELLOW.model();
//            case 3 -> LaserPointerMarkModel.LAYER_LOCATION_GREEN.model();
//        };
//
//    }




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
        RenderType a = RenderTypes.entityTranslucentCullItemTarget(LaserPointerMarkModel.LAYER_LOCATION_BLUE.model());
        submitNodeCollector.submitModel(modelBall, state, poseStack, a, state.lightCoords, 0, state.outlineColor, null);
    }
/*
    @Override
    public void render(@NotNull Entity p_entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(p_entity)));
        int i = OverlayTexture.NO_OVERLAY;
        if (ClientConfig.CUBE_MARK_MODEL.getConfigValue()) {
            poseStack.pushPose();
            poseStack.scale(0.75F, 0.75F, 0.75F);
            model.getBody().render(poseStack, vertexconsumer, packedLight, i);
            poseStack.popPose();
        } else {
            modelBall.getBody().render(poseStack, vertexconsumer, packedLight, i);
        }
    }*/
}
