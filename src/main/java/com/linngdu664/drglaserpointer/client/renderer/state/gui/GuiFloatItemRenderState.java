package com.linngdu664.drglaserpointer.client.renderer.state.gui;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.item.TrackingItemStackRenderState;
import net.minecraft.client.renderer.state.gui.ScreenArea;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix3x2f;
import org.jspecify.annotations.Nullable;

public final class GuiFloatItemRenderState implements ScreenArea {
    private final Matrix3x2f pose;
    private final TrackingItemStackRenderState itemStackRenderState;
    private final float x;
    private final float y;
    private final @Nullable ScreenRectangle scissorArea;
    private final @Nullable ScreenRectangle oversizedItemBounds;
    private final @Nullable ScreenRectangle bounds;

    public GuiFloatItemRenderState(Matrix3x2f pose, TrackingItemStackRenderState itemStackRenderState, float x, float y, @Nullable ScreenRectangle scissorArea) {
        this.pose = pose;
        this.itemStackRenderState = itemStackRenderState;
        this.x = x;
        this.y = y;
        this.scissorArea = scissorArea;
        this.oversizedItemBounds = this.itemStackRenderState().isOversizedInGui() ? this.calculateOversizedItemBounds() : null;
        this.bounds = this.calculateBounds(this.oversizedItemBounds != null ? this.oversizedItemBounds : new ScreenRectangle((int) this.x, (int) this.y, 16, 16));
    }

    private @Nullable ScreenRectangle calculateOversizedItemBounds() {
        AABB aabb = this.itemStackRenderState.getModelBoundingBox();
        int actualXSize = Mth.ceil(aabb.getXsize() * (double) 16.0F);
        int actualYSize = Mth.ceil(aabb.getYsize() * (double) 16.0F);
        if (actualXSize <= 16 && actualYSize <= 16) {
            return null;
        } else {
            float xOffset = (float) (aabb.minX * (double) 16.0F);
            float yOffset = (float) (aabb.maxY * (double) 16.0F);
            int actualX = (int) (this.x + xOffset + 8);
            int actualY = (int) (this.y - yOffset + 8);
            return new ScreenRectangle(actualX, actualY, actualXSize, actualYSize);
        }
    }

    private @Nullable ScreenRectangle calculateBounds(ScreenRectangle itemBounds) {
        ScreenRectangle bounds = itemBounds.transformMaxBounds(this.pose);
        return this.scissorArea != null ? this.scissorArea.intersection(bounds) : bounds;
    }

    public Matrix3x2f pose() {
        return this.pose;
    }

    public TrackingItemStackRenderState itemStackRenderState() {
        return this.itemStackRenderState;
    }

    public float x() {
        return this.x;
    }

    public float y() {
        return this.y;
    }

    public @Nullable ScreenRectangle scissorArea() {
        return this.scissorArea;
    }

    public @Nullable ScreenRectangle oversizedItemBounds() {
        return this.oversizedItemBounds;
    }

    public @Nullable ScreenRectangle bounds() {
        return this.bounds;
    }
}
