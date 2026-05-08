package com.linngdu664.drglaserpointer.client.renderer.state.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.state.gui.ScreenArea;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix3x2fc;
import org.jspecify.annotations.Nullable;

public final class GuiFloatTextRenderState implements ScreenArea {
    public final Font font;
    public final FormattedCharSequence text;
    public final Matrix3x2fc pose;
    public final float x;
    public final float y;
    public final int color;
    public final int backgroundColor;
    public final boolean dropShadow;
    final boolean includeEmpty;
    public final @Nullable ScreenRectangle scissor;
    private Font.@Nullable PreparedText preparedText;
    private @Nullable ScreenRectangle bounds;

    public GuiFloatTextRenderState(Font font, FormattedCharSequence text, Matrix3x2fc pose, float x, float y, int color, int backgroundColor, boolean dropShadow, boolean includeEmpty, @Nullable ScreenRectangle scissor) {
        this.font = font;
        this.text = text;
        this.pose = pose;
        this.x = x;
        this.y = y;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.dropShadow = dropShadow;
        this.includeEmpty = includeEmpty;
        this.scissor = scissor;
    }

    public Font.PreparedText ensurePrepared() {
        if (this.preparedText == null) {
            this.preparedText = this.font.prepareText(this.text, this.x, this.y, this.color, this.dropShadow, this.includeEmpty, this.backgroundColor);
            ScreenRectangle bounds = this.preparedText.bounds();
            if (bounds != null) {
                bounds = bounds.transformMaxBounds(this.pose);
                this.bounds = this.scissor != null ? this.scissor.intersection(bounds) : bounds;
            }
        }

        return this.preparedText;
    }

    public @Nullable ScreenRectangle bounds() {
        this.ensurePrepared();
        return this.bounds;
    }
}

