package com.linngdu664.drglaserpointer.client.renderer.entity;

import com.linngdu664.drglaserpointer.client.model.LaserPointerLabelModel;
import com.linngdu664.drglaserpointer.client.model.LaserPointerLabelModelBall;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
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

public class LaserPointerLabelRenderer extends EntityRenderer<Entity> {
    private final LaserPointerLabelModel model;
    private final LaserPointerLabelModelBall modelBall;

    private final RenderType renderType = RenderType.entityTranslucentCull(LaserPointerLabelModel.LAYER_LOCATION_BLUE.getModel());

    public LaserPointerLabelRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.1F;
        ModelPart modelpart = context.bakeLayer(LaserPointerLabelModel.LAYER_LOCATION_BLUE);
        model = new LaserPointerLabelModel<>(modelpart);
        ModelPart modelpartBall = context.bakeLayer(LaserPointerLabelModelBall.LAYER_LOCATION_RED);
        modelBall = new LaserPointerLabelModelBall<>(modelpartBall);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Entity entity) {
        return switch (((LaserPointerLabelEntity) entity).getColor()) {
            default -> LaserPointerLabelModel.LAYER_LOCATION_EMPTY.getModel();
            case 0 -> LaserPointerLabelModel.LAYER_LOCATION_BLUE.getModel();
            case 1 -> LaserPointerLabelModel.LAYER_LOCATION_RED.getModel();
            case 2 -> LaserPointerLabelModel.LAYER_LOCATION_YELLOW.getModel();
            case 3 -> LaserPointerLabelModel.LAYER_LOCATION_GREEN.getModel();
        };
    }

    @Override
    public void render(@NotNull Entity p_entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(p_entity)));
        int i = OverlayTexture.NO_OVERLAY;
        model.getBody().render(poseStack, vertexconsumer, 15728672, i);
        modelBall.getBody().render(poseStack, vertexconsumer, 15728672, i);
    }
}
