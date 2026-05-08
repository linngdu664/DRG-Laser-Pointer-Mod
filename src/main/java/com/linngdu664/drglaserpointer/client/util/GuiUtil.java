package com.linngdu664.drglaserpointer.client.util;

import com.linngdu664.drglaserpointer.client.renderer.state.gui.FloatBlitRenderState;
import com.linngdu664.drglaserpointer.client.renderer.state.gui.FloatColoredRectangleRenderState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuSampler;
import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.Identifier;
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

    public static void blitXyuvwh(GuiGraphicsExtractor guiGraphics, Identifier texture, float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        blitXyuvwh(guiGraphics, RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, width, height, width, height, textureWidth, textureHeight);
    }

    public static void blitXyuvwh(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier texture, float x, float y, float u, float v, float width, float height, float srcWidth, float srcHeight, float textureWidth, float textureHeight) {
        blitXyuvwh(guiGraphics, renderPipeline, texture, x, y, u, v, width, height, srcWidth, srcHeight, textureWidth, textureHeight, -1);
    }

    public static void blitXyuvwh(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier texture, float x, float y, float u, float v, float width, float height, float srcWidth, float srcHeight, float textureWidth, float textureHeight, int color) {
        innerBlit(guiGraphics, renderPipeline, texture, x, x + width, y, y + height, u / textureWidth, (u + srcWidth) / textureWidth, v / textureHeight, (v + srcHeight) / textureHeight, color);
    }

    private static void innerBlit(GuiGraphicsExtractor guiGraphics, RenderPipeline renderPipeline, Identifier location, float x0, float x1, float y0, float y1, float u0, float u1, float v0, float v1, int color) {
        AbstractTexture texture = guiGraphics.minecraft.getTextureManager().getTexture(location);
        innerBlit(guiGraphics, renderPipeline, texture.getTextureView(), texture.getSampler(), x0, y0, x1, y1, u0, u1, v0, v1, color);
    }

    private static void innerBlit(GuiGraphicsExtractor guiGraphics, RenderPipeline pipeline, GpuTextureView textureView, GpuSampler sampler, float x0, float y0, float x1, float y1, float u0, float u1, float v0, float v1, int color) {
        guiGraphics.guiRenderState.addGuiElement(new FloatBlitRenderState(pipeline, TextureSetup.singleTexture(textureView, sampler), new Matrix3x2f(guiGraphics.pose()), x0, y0, x1, y1, u0, u1, v0, v1, color, guiGraphics.scissorStack.peek()));
    }
}
