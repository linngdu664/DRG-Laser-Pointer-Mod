package com.linngdu664.drglaserpointer.client.util;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

public class GuiUtil {
    public static void fill(PoseStack poseStack, float minX, float minY, float maxX, float maxY, int color) {
        Matrix4f $$7 = poseStack.last().pose();
        float $$9;
        if (minX < maxX) {
            $$9 = minX;
            minX = maxX;
            maxX = $$9;
        }

        if (minY < maxY) {
            $$9 = minY;
            minY = maxY;
            maxY = $$9;
        }

        float $$10 = (float) FastColor.ARGB32.alpha(color) / 255.0F;
        float $$11 = (float) FastColor.ARGB32.red(color) / 255.0F;
        float $$12 = (float) FastColor.ARGB32.green(color) / 255.0F;
        float $$13 = (float) FastColor.ARGB32.blue(color) / 255.0F;
        BufferBuilder $$14 = Tesselator.getInstance().getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        $$14.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        $$14.vertex($$7, minX, minY, 0).color($$11, $$12, $$13, $$10).endVertex();
        $$14.vertex($$7, minX, maxY, 0).color($$11, $$12, $$13, $$10).endVertex();
        $$14.vertex($$7, maxX, maxY, 0).color($$11, $$12, $$13, $$10).endVertex();
        $$14.vertex($$7, maxX, minY, 0).color($$11, $$12, $$13, $$10).endVertex();
        BufferUploader.drawWithShader($$14.end());
        RenderSystem.disableBlend();
    }

    public static void vLine(PoseStack poseStack, float x, float minY, float maxY, int color) {
        if (maxY < minY) {
            float i = minY;
            minY = maxY;
            maxY = i;
        }

        fill(poseStack, x, minY + 1, x + 1, maxY, color);
    }

    public static void hLine(PoseStack poseStack, float minX, float maxX, float y, int color) {
        if (maxX < minX) {
            float i = minX;
            minX = maxX;
            maxX = i;
        }

        fill(poseStack, minX, y, maxX + 1, y + 1, color);
    }

    public static void blit(PoseStack poseStack, ResourceLocation location, float x, float y, float uOffset, float vOffset, float width, float height, float textureWidth, float textureHeight) {
        blit(poseStack, location, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    public static void blit(PoseStack poseStack, ResourceLocation location, float x, float y, float width, float height, float uOffset, float vOffset, float uWidth, float vHeight, float textureWidth, float textureHeight) {
        blit(poseStack, location, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public static void blit(PoseStack poseStack, ResourceLocation location, float x1, float x2, float y1, float y2, float blitOffset, float uWidth, float vHeight, float uOffset, float vOffset, float textureWidth, float textureHeight) {
        innerBlit(poseStack.last().pose(), location, x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / textureWidth, (uOffset + uWidth) / textureWidth, (vOffset + 0.0F) / textureHeight, (vOffset + vHeight) / textureHeight);
    }

    public static void innerBlit(Matrix4f matrix, ResourceLocation location, float x1, float x2, float y1, float y2, float blitOffset, float minU, float maxU, float minV, float maxV) {
        RenderSystem.setShaderTexture(0, location);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder $$10 = Tesselator.getInstance().getBuilder();
        $$10.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        $$10.vertex(matrix, x1, y1, blitOffset).uv(minU, minV).endVertex();
        $$10.vertex(matrix, x1, y2, blitOffset).uv(minU, maxV).endVertex();
        $$10.vertex(matrix, x2, y2, blitOffset).uv(maxU, maxV).endVertex();
        $$10.vertex(matrix, x2, y1, blitOffset).uv(maxU, minV).endVertex();
        BufferUploader.drawWithShader($$10.end());
    }

    public static void renderItem(ItemRenderer itemRenderer, PoseStack poseStack, ItemStack stack, float x, float y) {
        renderItem(itemRenderer, poseStack, stack, x, y, itemRenderer.getModel(stack, null, null, 0));
    }

    public static void renderItem(ItemRenderer itemRenderer, PoseStack pPoseStack, ItemStack stack, float x, float y, BakedModel model) {
        pPoseStack.pushPose();
        pPoseStack.translate(x + 8F, y + 8F, 100.0F);
        pPoseStack.scale(16.0F, -16.0F, 16.0F);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        boolean flag = !model.usesBlockLight();
        if (flag) {
            Lighting.setupForFlatItems();
        }

        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.mulPoseMatrix(pPoseStack.last().pose());
        RenderSystem.applyModelViewMatrix();
        itemRenderer.render(stack, ItemDisplayContext.GUI, false, new PoseStack(), multibuffersource$buffersource, 15728880, OverlayTexture.NO_OVERLAY, model);
        multibuffersource$buffersource.endBatch();
        RenderSystem.enableDepthTest();
        if (flag) {
            Lighting.setupFor3DItems();
        }

        pPoseStack.popPose();
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
    }

    public static void drawCenteredString(PoseStack poseStack, Font font, Component text, float x, float y, int color) {
        FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
        drawString(poseStack, font, formattedcharsequence, x - (float) font.width(formattedcharsequence) / 2, y, color);
    }

    public static void drawString(PoseStack poseStack, Font font, FormattedCharSequence text, float x, float y, int color) {
        drawString(poseStack, font, text, x, y, color, true);
    }

    public static void drawString(PoseStack poseStack, Font font, FormattedCharSequence text, float x, float y, int color, boolean dropShadow) {
        if (dropShadow) {
            font.drawShadow(poseStack, text, x, y, color);
        } else {
            font.draw(poseStack, text, x, y, color);
        }
    }
}
