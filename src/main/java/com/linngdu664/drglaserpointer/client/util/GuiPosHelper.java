package com.linngdu664.drglaserpointer.client.util;

import org.joml.Matrix3x2fStack;

public class GuiPosHelper {
    private float x = 0f;
    private float y = 0f;

    public void setPos(float newX, float newY, Matrix3x2fStack poseStack) {
        poseStack.translate(newX - x, newY - y);
        x = newX;
        y = newY;
    }

    public void reset(Matrix3x2fStack poseStack) {
        poseStack.translate(-x, -y);
        x = 0f;
        y = 0f;
    }
}
