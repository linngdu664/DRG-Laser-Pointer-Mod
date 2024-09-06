package com.linngdu664.drglaserpointer.util;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class GuiUtil {
    public static void fill(GuiGraphics guiGraphics, float minX, float minY, float maxX, float maxY, int color) {
        Matrix4f matrix4f = guiGraphics.pose.last().pose();
        float j;
        if (minX < maxX) {
            j = minX;
            minX = maxX;
            maxX = j;
        }

        if (minY < maxY) {
            j = minY;
            minY = maxY;
            maxY = j;
        }
        VertexConsumer vertexconsumer = guiGraphics.bufferSource.getBuffer(RenderType.gui());
        vertexconsumer.addVertex(matrix4f, minX, minY, 0).setColor(color);
        vertexconsumer.addVertex(matrix4f, minX, maxY, 0).setColor(color);
        vertexconsumer.addVertex(matrix4f, maxX, maxY, 0).setColor(color);
        vertexconsumer.addVertex(matrix4f, maxX, minY, 0).setColor(color);
        guiGraphics.flushIfUnmanaged();
    }
    public static void vLine(GuiGraphics guiGraphics,float x, float minY, float maxY, int color) {
        if (maxY < minY) {
            float i = minY;
            minY = maxY;
            maxY = i;
        }

        fill(guiGraphics, x, minY + 1, x + 1, maxY, color);
    }
    public static void hLine(GuiGraphics guiGraphics, float minX, float maxX, float y, int color) {
        if (maxX < minX) {
            float i = minX;
            minX = maxX;
            maxX = i;
        }

        fill(guiGraphics, minX, y, maxX + 1, y + 1, color);
    }
    public static void blit(GuiGraphics guiGraphics,ResourceLocation atlasLocation, float x, float y, float uOffset, float vOffset, float width, float height, float textureWidth, float textureHeight) {
        blit(guiGraphics,atlasLocation, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }
    public static void blit(GuiGraphics guiGraphics,ResourceLocation atlasLocation, float x, float y, float width, float height, float uOffset, float vOffset, float uWidth, float vHeight, float textureWidth, float textureHeight) {
        blit(guiGraphics,atlasLocation, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }
    public static void blit(GuiGraphics guiGraphics,ResourceLocation atlasLocation, float x1, float x2, float y1, float y2, float blitOffset, float uWidth, float vHeight, float uOffset, float vOffset, float textureWidth, float textureHeight) {
        innerBlit(guiGraphics,atlasLocation, x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / textureWidth, (uOffset + uWidth) / textureWidth, (vOffset + 0.0F) / textureHeight, (vOffset + vHeight) / textureHeight);
    }
    public static void innerBlit(GuiGraphics guiGraphics,ResourceLocation atlasLocation, float x1, float x2, float y1, float y2, float blitOffset, float minU, float maxU, float minV, float maxV) {
        RenderSystem.setShaderTexture(0, atlasLocation);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Matrix4f matrix4f = guiGraphics.pose.last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.addVertex(matrix4f, x1, y1, blitOffset).setUv(minU, minV);
        bufferbuilder.addVertex(matrix4f, x1, y2, blitOffset).setUv(minU, maxV);
        bufferbuilder.addVertex(matrix4f, x2, y2, blitOffset).setUv(maxU, maxV);
        bufferbuilder.addVertex(matrix4f, x2, y1, blitOffset).setUv(maxU, minV);
        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
    }
    public static void renderItem(GuiGraphics guiGraphics,ItemStack stack, float x, float y) {
        renderItem(guiGraphics,guiGraphics.minecraft.player, guiGraphics.minecraft.level, stack, x, y, 0);
    }
    public static void renderItem(GuiGraphics guiGraphics,@Nullable LivingEntity entity, @Nullable Level level, ItemStack stack, float x, float y, int seed) {
        renderItem(guiGraphics,entity, level, stack, x, y, seed, 0);
    }
    public static void renderItem(GuiGraphics guiGraphics,@Nullable LivingEntity entity, @Nullable Level level, ItemStack stack, float x, float y, int seed, int guiOffset) {
        if (!stack.isEmpty()) {
            BakedModel bakedmodel = guiGraphics.minecraft.getItemRenderer().getModel(stack, level, entity, seed);
            guiGraphics.pose.pushPose();
            guiGraphics.pose.translate(x + 8, y + 8, (float)(150 + (bakedmodel.isGui3d() ? guiOffset : 0)));

            try {
                guiGraphics.pose.scale(16.0F, -16.0F, 16.0F);
                boolean flag = !bakedmodel.usesBlockLight();
                if (flag) {
                    Lighting.setupForFlatItems();
                }

                guiGraphics.minecraft.getItemRenderer().render(stack, ItemDisplayContext.GUI, false, guiGraphics.pose, guiGraphics.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY, bakedmodel);
                guiGraphics.flush();
                if (flag) {
                    Lighting.setupFor3DItems();
                }
            } catch (Throwable var12) {
                CrashReport crashreport = CrashReport.forThrowable(var12, "Rendering item");
                CrashReportCategory crashreportcategory = crashreport.addCategory("Item being rendered");
                crashreportcategory.setDetail("Item Type", () -> String.valueOf(stack.getItem()));
                crashreportcategory.setDetail("Item Components", () -> String.valueOf(stack.getComponents()));
                crashreportcategory.setDetail("Item Foil", () -> String.valueOf(stack.hasFoil()));
                throw new ReportedException(crashreport);
            }

            guiGraphics.pose.popPose();
        }
    }
    public static void drawCenteredString(GuiGraphics guiGraphics,Font font, Component text, float x, float y, int color) {
        FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
        drawString(guiGraphics,font, formattedcharsequence, x - (float) font.width(formattedcharsequence) / 2, y, color);
    }
    public static int drawString(GuiGraphics guiGraphics,Font font, FormattedCharSequence text, float x, float y, int color) {
        return drawString(guiGraphics,font, text, x, y, color, true);
    }
    public static int drawString(GuiGraphics guiGraphics,Font font, FormattedCharSequence text, float x, float y, int color, boolean dropShadow) {
        return guiGraphics.drawString(font, text, x, y, color, dropShadow);
    }
}
