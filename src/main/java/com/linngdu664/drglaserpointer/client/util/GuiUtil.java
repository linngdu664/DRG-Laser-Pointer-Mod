package com.linngdu664.drglaserpointer.client.util;

import com.linngdu664.drglaserpointer.client.renderer.state.gui.FloatBlitRenderState;
import com.linngdu664.drglaserpointer.client.renderer.state.gui.FloatColoredRectangleRenderState;
import com.linngdu664.drglaserpointer.client.renderer.state.gui.GuiFloatItemRenderState;
import com.linngdu664.drglaserpointer.client.renderer.state.gui.GuiFloatTextRenderState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuSampler;
import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.item.TrackingItemStackRenderState;
import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix3x2f;

import javax.annotation.Nullable;

public class GuiUtil {
    public static void fill(GuiGraphicsExtractor guiGraphics, float minX, float minY, float maxX, float maxY, int color) {
        if (minX < maxX) {
            float tmp = minX;
            minX = maxX;
            maxX = tmp;
        }

        if (minY < maxY) {
            float tmp = minY;
            minY = maxY;
            maxY = tmp;
        }

        innerFill(guiGraphics, RenderPipelines.GUI, TextureSetup.noTexture(), minX, minY, maxX, maxY, color, null);
    }

    private static void innerFill(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, TextureSetup textureSetup, float x0, float y0, float x1, float y1, int color1, @Nullable Integer color2) {
        guiGraphics.guiRenderState.addGuiElement(new FloatColoredRectangleRenderState(renderPipeline, textureSetup, new Matrix3x2f(guiGraphics.pose()), x0, y0, x1, y1, color1, color2 != null ? color2 : color1, guiGraphics.scissorStack.peek()));
    }

    public static void vLine(GuiGraphicsExtractor guiGraphics, float x, float minY, float maxY, int color) {
        if (maxY < minY) {
            float i = minY;
            minY = maxY;
            maxY = i;
        }

        fill(guiGraphics, x, minY + 1, x + 1, maxY, color);
    }

    public static void hLine(GuiGraphicsExtractor guiGraphics, float minX, float maxX, float y, int color) {
        if (maxX < minX) {
            float i = minX;
            minX = maxX;
            maxX = i;
        }

        fill(guiGraphics, minX, y, maxX + 1, y + 1, color);
    }


//    public static void blit(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier texture, float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight, int color) {
//        blit(guiGraphics, renderPipeline, texture, x, y, u, v, width, height, width, height, textureWidth, textureHeight, color);
//    }
//
//    public static void blit(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
//        blit(guiGraphics, renderPipeline, texture, x, y, u, v, width, height, width, height, textureWidth, textureHeight);
//    }

//    public static void blit(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int srcWidth, int srcHeight, int textureWidth, int textureHeight) {
//        blit(guiGraphics, renderPipeline, texture, x, y, u, v, width, height, srcWidth, srcHeight, textureWidth, textureHeight, -1);
//    }
//
//    public static void blit(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier texture, float x, float y, float u, float v, float width, float height, float srcWidth, float srcHeight, float textureWidth, float textureHeight, int color) {
//        innerBlit(guiGraphics, renderPipeline, texture, x, x + width, y, y + height, (u + 0.0F) / (float)textureWidth, (u + (float)srcWidth) / (float)textureWidth, (v + 0.0F) / (float)textureHeight, (v + (float)srcHeight) / (float)textureHeight, color);
//    }

    public static void blit(GuiGraphicsExtractor guiGraphics, Identifier location, float x0, float y0, float x1, float y1, float u0, float u1, float v0, float v1) {
        innerBlit(guiGraphics, RenderPipelines.GUI_TEXTURED, location, x0, x1, y0, y1, u0, u1, v0, v1, -1);
    }

//    public static void blit(GuiGraphicsExtractor guiGraphics, GpuTextureView textureView, GpuSampler sampler, int x0, int y0, int x1, int y1, float u0, float u1, float v0, float v1) {
//        innerBlit(guiGraphics, RenderPipelines.GUI_TEXTURED, textureView, sampler, x0, y0, x1, y1, u0, u1, v0, v1, -1);
//    }


    private static void innerBlit(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier location, float x0, float x1, float y0, float y1, float u0, float u1, float v0, float v1, int color) {
        AbstractTexture texture = guiGraphics.minecraft.getTextureManager().getTexture(location);
        innerBlit(guiGraphics, renderPipeline, texture.getTextureView(), texture.getSampler(), x0, y0, x1, y1, u0, u1, v0, v1, color);
    }

    private static void innerBlit(GuiGraphicsExtractor guiGraphics, RenderPipeline pipeline, GpuTextureView textureView, GpuSampler sampler, float x0, float y0, float x1, float y1, float u0, float u1, float v0, float v1, int color) {
        guiGraphics.guiRenderState.addGuiElement(new FloatBlitRenderState(pipeline, TextureSetup.singleTexture(textureView, sampler), new Matrix3x2f(guiGraphics.pose()), x0, y0, x1, y1, u0, u1, v0, v1, color, guiGraphics.scissorStack.peek()));
    }


/*
    public static void blit(GuiGraphicsExtractor guiGraphics, Identifier atlasLocation, float x, float y, float uOffset, float vOffset, float width, float height, float textureWidth, float textureHeight) {
        blit(guiGraphics, atlasLocation, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }

    public static void blit(GuiGraphicsExtractor guiGraphics, Identifier atlasLocation, float x, float y, float width, float height, float uOffset, float vOffset, float uWidth, float vHeight, float textureWidth, float textureHeight) {
        blit(guiGraphics, atlasLocation, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    // todo 可疑
    public static void blit(GuiGraphicsExtractor guiGraphics, Identifier atlasLocation, float x1, float x2, float y1, float y2, float blitOffset, float uWidth, float vHeight, float uOffset, float vOffset, float textureWidth, float textureHeight) {
        innerBlit(guiGraphics, RenderPipelines.GUI_TEXTURED, atlasLocation, x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / textureWidth, (uOffset + uWidth) / textureWidth, (vOffset + 0.0F) / textureHeight, (vOffset + vHeight) / textureHeight);
    }

    private static void innerBlit(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier location, float x0, float x1, float y0, float y1, float u0, float u1, float v0, float v1, int color) {
        AbstractTexture texture = guiGraphics.minecraft.getTextureManager().getTexture(location);
        innerBlit(guiGraphics, renderPipeline, texture.getTextureView(), texture.getSampler(), x0, y0, x1, y1, u0, u1, v0, v1, color);
    }

    private static void innerBlit(GuiGraphicsExtractor guiGraphics, RenderPipeline pipeline, GpuTextureView textureView, GpuSampler sampler, float x0, float y0, float x1, float y1, float u0, float u1, float v0, float v1, int color) {
        guiGraphics.guiRenderState.addGuiElement(new FloatBlitRenderState(pipeline, TextureSetup.singleTexture(textureView, sampler), new Matrix3x2f(guiGraphics.pose()), x0, y0, x1, y1, u0, u1, v0, v1, color, guiGraphics.scissorStack.peek()));
    }
*/
    /*
    public static void renderItem(GuiGraphicsExtractor guiGraphics, ItemStack stack, float x, float y) {
        renderItem(guiGraphics, guiGraphics.minecraft.player, guiGraphics.minecraft.level, stack, x, y, 0);
    }

    public static void renderItem(GuiGraphicsExtractor guiGraphics, LivingEntity owner, @Nullable Level level, ItemStack itemStack, float x, float y, int seed) {
        if (!itemStack.isEmpty()) {
            TrackingItemStackRenderState itemStackRenderState = new TrackingItemStackRenderState();
            guiGraphics.minecraft.getItemModelResolver().updateForTopItem(itemStackRenderState, itemStack, ItemDisplayContext.GUI, level, owner, seed);

            try {
                GuiFloatItemRenderState itemState = new GuiFloatItemRenderState(new Matrix3x2f(guiGraphics.pose()), itemStackRenderState, x, y, guiGraphics.scissorStack.peek());
                GuiRenderState guiRenderState = guiGraphics.guiRenderState;
                if (guiRenderState.findAppropriateNode(itemState)) {
                    guiRenderState.itemModelIdentities.add(itemState.itemStackRenderState().getModelIdentity());
                    guiRenderState.current.addItem(itemState);
                    guiRenderState.addDebugRectangleIfEnabled(itemState.bounds());
                }
            } catch (Throwable var11) {
                CrashReport report = CrashReport.forThrowable(var11, "Rendering item");
                CrashReportCategory category = report.addCategory("Item being rendered");
                category.setDetail("Item Type", () -> String.valueOf(itemStack.getItem()));
                category.setDetail("Item Components", () -> String.valueOf(itemStack.getComponents()));
                category.setDetail("Item Foil", () -> String.valueOf(itemStack.hasFoil()));
                throw new ReportedException(report);
            }
        }
    }

    public static void drawCenteredString(GuiGraphicsExtractor guiGraphics, Font font, Component text, float x, float y, int color) {
        FormattedCharSequence toRender = text.getVisualOrderText();
        drawString(guiGraphics, font, toRender, x - (float) font.width(toRender) / 2, y, color);
    }

//    public static void drawString(GuiGraphicsExtractor guiGraphics, Font font, Component str, int x, int y, int color) {
//        drawString(guiGraphics, font, str, x, y, color, true);
//    }

    public static void drawString(GuiGraphicsExtractor guiGraphics, Font font, Component str, float x, float y, int color, boolean dropShadow) {
        drawString(guiGraphics, font, str.getVisualOrderText(), x, y, color, dropShadow);
    }

    public static void drawString(GuiGraphicsExtractor guiGraphics, Font font, FormattedCharSequence str, float x, float y, int color) {
        drawString(guiGraphics, font, str, x, y, color, true);
    }

    public static void drawString(GuiGraphicsExtractor guiGraphics, Font font, FormattedCharSequence str, float x, float y, int color, boolean dropShadow) {
        if (ARGB.alpha(color) != 0) {
            GuiRenderState guiRenderState = guiGraphics.guiRenderState;
            GuiFloatTextRenderState textState = new GuiFloatTextRenderState(font, str, new Matrix3x2f(guiGraphics.pose()), x, y, color, 0, dropShadow, false, guiGraphics.scissorStack.peek());
            if (guiRenderState.findAppropriateNode(textState)) {
                guiRenderState.current.addText(textState);
                guiRenderState.addDebugRectangleIfEnabled(textState.bounds());
            }
        }
    }
*/




//    public static void drawCenteredString(GuiGraphics guiGraphics, Font font, Component text, float x, float y, int color) {
//        FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
//        drawString(guiGraphics, font, formattedcharsequence, x - (float) font.width(formattedcharsequence) / 2, y, color);
//    }
//
//    public static int drawString(GuiGraphics guiGraphics, Font font, FormattedCharSequence text, float x, float y, int color) {
//        return drawString(guiGraphics, font, text, x, y, color, true);
//    }
//
//    public static int drawString(GuiGraphics guiGraphics, Font font, FormattedCharSequence text, float x, float y, int color, boolean dropShadow) {
//        return guiGraphics.drawString(font, text, x, y, color, dropShadow);
//    }
}
