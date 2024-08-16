package com.linngdu664.drglaserpointer.client.renderer.entity;

import com.linngdu664.drglaserpointer.client.model.LaserPointerLabelModel;
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
    private final RenderType renderType = RenderType.entityTranslucentCull(LaserPointerLabelModel.LAYER_LOCATION_BLUE.getModel());

    public LaserPointerLabelRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.1F;
        ModelPart modelpart = context.bakeLayer(LaserPointerLabelModel.LAYER_LOCATION_BLUE);
        model = new LaserPointerLabelModel<>(modelpart);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Entity entity) {
        switch (((LaserPointerLabelEntity) entity).getColor()) {
            case -1 ->{
                return LaserPointerLabelModel.LAYER_LOCATION_EMPTY.getModel();
            }
            case 1 ->{
                return LaserPointerLabelModel.LAYER_LOCATION_RED.getModel();
            }
            case 2 ->{
                return LaserPointerLabelModel.LAYER_LOCATION_YELLOW.getModel();
            }
            case 3 ->{
                return LaserPointerLabelModel.LAYER_LOCATION_GREEN.getModel();
            }
            default -> {
                return LaserPointerLabelModel.LAYER_LOCATION_BLUE.getModel();
            }
        }
    }

    @Override
    public void render(@NotNull Entity p_entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(p_entity)));
        int i = OverlayTexture.NO_OVERLAY;
        poseStack.pushPose();
        model.getBody().render(poseStack, vertexconsumer, 15728672, i);
        poseStack.popPose();
    }
}
