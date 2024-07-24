package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameEvents {
    public static final double LASER_MAX_DISTANCE = 100;

    private static void addLaserQuad(BufferBuilder bufferBuilder, Vec3 start, Vec3 end, Vec3 n, int r, int g, int b) {
        int endAlpha = Math.max(0, (int) (255 * (1 - end.distanceTo(start) / LASER_MAX_DISTANCE)));
        Vec3 start1 = start.add(n);
        Vec3 start2 = start.subtract(n);
        Vec3 end1 = end.add(n);
        Vec3 end2 = end.subtract(n);
        bufferBuilder.addVertex((float) start1.x, (float) start1.y, (float) start1.z).setColor(r, g, b, 255);
        bufferBuilder.addVertex((float) start2.x, (float) start2.y, (float) start2.z).setColor(r, g, b, 255);
        bufferBuilder.addVertex((float) end2.x, (float) end2.y, (float) end2.z).setColor(r, g, b, endAlpha);
        bufferBuilder.addVertex((float) end1.x, (float) end1.y, (float) end1.z).setColor(r, g, b, endAlpha);
    }

    private static void renderLaser(RenderLevelStageEvent event, Vec3 start, Vec3 end, int r, int g, int b) {
        Vec3 rVec = end.subtract(start);
        Vec3 n1 = rVec.cross(new Vec3(0, 1, 0));
        if (n1.lengthSqr() < 1e-10) {
            n1 = rVec.cross(new Vec3(1, 0, 0));
        }
        n1 = n1.normalize().scale(0.01);
        Vec3 n2 = rVec.cross(n1).normalize().scale(0.01);

        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        var tesselator = Tesselator.getInstance();
        var bufferBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        addLaserQuad(bufferBuilder, start, end, n1, r, g, b);
        addLaserQuad(bufferBuilder, start, end, n2, r, g, b);
        var vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        vertexBuffer.bind();
        vertexBuffer.upload(bufferBuilder.build());
        var view = event.getCamera().getPosition();
        var translatedModelViewMatrix = event.getModelViewMatrix().translate((float) -view.x, (float) -view.y, (float) -view.z);
        vertexBuffer.drawWithShader(translatedModelViewMatrix, event.getProjectionMatrix(), GameRenderer.getPositionColorShader());
        VertexBuffer.unbind();

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
            renderLaser(event, new Vec3(0, 0, 0), new Vec3(40, 0, 40), 255, 165, 0);
        }
    }
}
