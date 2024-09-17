package com.linngdu664.drglaserpointer.client.renderer.entity;

import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModel;
import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModelBall;
import com.linngdu664.drglaserpointer.config.ClientConfig;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class LaserPointerMarkRenderer extends EntityRenderer<Entity> {
    private final LaserPointerMarkModel model;
    private final LaserPointerMarkModelBall modelBall;

    private final RenderType renderType = RenderType.entityTranslucentCull(LaserPointerMarkModel.LAYER_LOCATION_BLUE.getModel());

    public LaserPointerMarkRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.1F;
        ModelPart modelpart = context.bakeLayer(LaserPointerMarkModel.LAYER_LOCATION_BLUE);
        model = new LaserPointerMarkModel<>(modelpart);
        ModelPart modelpartBall = context.bakeLayer(LaserPointerMarkModelBall.LAYER_LOCATION_RED);
        modelBall = new LaserPointerMarkModelBall<>(modelpartBall);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Entity entity) {
        return switch (((LaserPointerMarkEntity) entity).getColor()) {
            default -> LaserPointerMarkModel.LAYER_LOCATION_EMPTY.getModel();
            case 0 -> LaserPointerMarkModel.LAYER_LOCATION_BLUE.getModel();
            case 1 -> LaserPointerMarkModel.LAYER_LOCATION_RED.getModel();
            case 2 -> LaserPointerMarkModel.LAYER_LOCATION_YELLOW.getModel();
            case 3 -> LaserPointerMarkModel.LAYER_LOCATION_GREEN.getModel();
        };
    }

    @Override
    public void render(@NotNull Entity p_entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(p_entity)));
        int i = OverlayTexture.NO_OVERLAY;
        if (ClientConfig.CUBE_MARK_MODEL.getConfigValue()) {
            poseStack.pushPose();
            poseStack.scale(0.75F, 0.75F, 0.75F);
            model.getBody().render(poseStack, vertexconsumer, 15728672, i);
            poseStack.popPose();
        } else {
            modelBall.getBody().render(poseStack, vertexconsumer, 15728672, i);
        }
    }
}
