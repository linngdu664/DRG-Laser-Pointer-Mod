package com.linngdu664.drglaserpointer.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class LaserPointerLabelRenderer<T extends Entity> extends EntityRenderer<T>  {
    public LaserPointerLabelRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T t) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    public void render(@NotNull T p_entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        String displayName = "hello";
        poseStack.pushPose();
        poseStack.translate(0, 2, 0);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.scale(0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = poseStack.last().pose();
        float f = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
        int j = (int)(f * 255.0F) << 24;
        Font font = this.getFont();
        float f1 = (float)(-font.width(displayName) / 2);
        font.drawInBatch(displayName, f1, 0, 0xffffffff, false, matrix4f, bufferSource, Font.DisplayMode.SEE_THROUGH, j, 240);
        poseStack.popPose();
    }
}
