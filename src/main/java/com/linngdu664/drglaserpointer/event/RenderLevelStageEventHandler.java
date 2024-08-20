package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.component.LaserData;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.util.LaserPointerHitHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderLevelStageEventHandler {
    public static final double LASER_WIDTH = 0.005;

    private static void addLaserQuad(BufferBuilder bufferBuilder, Vec3 start, Vec3 end, Vec3 n, int color) {
        int endAlpha = Math.max(0, (int) (255 * (1 - end.distanceTo(start) / LaserPointerHitHelper.LASER_MAX_DISTANCE)));
        Vec3 start1 = start.add(n);
        Vec3 start2 = start.subtract(n);
        Vec3 end1 = end.add(n);
        Vec3 end2 = end.subtract(n);
        bufferBuilder.addVertex((float) start1.x, (float) start1.y, (float) start1.z).setColor(color | (255 << 24));
        bufferBuilder.addVertex((float) start2.x, (float) start2.y, (float) start2.z).setColor(color | (255 << 24));
        bufferBuilder.addVertex((float) end2.x, (float) end2.y, (float) end2.z).setColor(color & 0xffffff | (endAlpha << 24));
        bufferBuilder.addVertex((float) end1.x, (float) end1.y, (float) end1.z).setColor(color & 0xffffff | (endAlpha << 24));
    }

    private static void addLaserToBuffer(BufferBuilder bufferBuilder, Vec3 start, Vec3 end, int color) {
        Vec3 rVec = end.subtract(start);
        Vec3 n1 = rVec.cross(new Vec3(0, 1, 0));
        if (n1.lengthSqr() < 1e-10) {
            n1 = rVec.cross(new Vec3(1, 0, 0));
        }
        n1 = n1.normalize().scale(LASER_WIDTH);
        Vec3 n2 = rVec.cross(n1).normalize().scale(LASER_WIDTH);
        addLaserQuad(bufferBuilder, start, end, n1, color);
        addLaserQuad(bufferBuilder, start, end, n2, color);
    }

    /*
    private static Vec3 getThirdViewPlayerHandPos(Player player, boolean isLeftHand, float partialTick) {
        float f = Mth.lerp(partialTick, player.yBodyRotO, player.yBodyRot) * 0.017453292F;
        double d0 = Mth.sin(f);
        double d1 = Mth.cos(f);
        double d4 = player.getScale();
        double d2 = (isLeftHand ? -0.32 : 0.32) * d4;       // 左右，original 0.35
        double d3 = 0.6 * d4;                               // 前后，original 0.8
        double d5 = player.isCrouching() ? -0.1875 : 0.0;
        return player.getEyePosition(partialTick).add(-d1 * d2 - d0 * d3, d5 - 0.12 * d4, -d0 * d2 + d1 * d3);
    }*/

    private static Vec3 getThirdViewPlayerHandPos(Player player, boolean isLeftHand, float partialTick) {
        // x：俯仰，垂直向上为-90
        // y：水平偏转
        float yBodyRot = Mth.lerp(partialTick, player.yBodyRotO, player.yBodyRot) * Mth.DEG_TO_RAD;
        float yRot = (player.getViewYRot(partialTick) + (isLeftHand ? 0.1F : -0.1F)) * Mth.DEG_TO_RAD;
        float xRot = player.getViewXRot(partialTick) * Mth.DEG_TO_RAD;
        Vec3 eyePos = player.getEyePosition(partialTick);
        Vec3 shoulderPos = eyePos.add(Mth.cos(yBodyRot) * (isLeftHand ? 0.28125 : -0.28125), -0.3125, Mth.sin(yBodyRot) * (isLeftHand ? 0.28125 : -0.28125));
        Vec3 handVec = new Vec3(-Mth.sin(yRot) * Mth.cos(xRot), -Mth.sin(xRot), Mth.cos(yRot) * Mth.cos(xRot));
        Vec3 handPos = shoulderPos.add(handVec.scale(0.6));
        Vec3 normal = handVec.cross(handVec.add(Mth.cos(yRot), 0, Mth.sin(yRot))).normalize();
        return handPos.add(normal.scale(player.isCrouching() ? 0.125 : 0.3125));
    }

    // copy mojang's code
    private static Vec3 getFirstViewPlayerHandPos(Player player, boolean isLeftHand, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        double d4 = 960.0 / mc.options.fov().get();
        Vec3 vec3 = mc.gameRenderer.getMainCamera().getNearPlane().getPointOnPlane(isLeftHand ? -0.525F : 0.525F, -0.3F).scale(d4);
        return player.getEyePosition(partialTick).add(vec3);
    }

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
            var bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            float partialTick = event.getPartialTick().getGameTimeDeltaPartialTick(true);
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            ItemStack mainHandItemStack = player.getMainHandItem();
            ItemStack offHandItemStack = player.getOffhandItem();
            Item laserPointer = ItemRegister.LASER_POINTER.get();
            DataComponentType<LaserData> componentType = DataComponentRegister.LASER_DATA.get();
            if (mc.gameMode.getPlayerMode() != GameType.SPECTATOR && (mainHandItemStack.is(laserPointer) || offHandItemStack.is(laserPointer))) {
                LaserPointerHitHelper helper = LaserPointerHitHelper.getInstance();
                helper.calcHitResult(player, partialTick);
                Vec3 targetPos = helper.getHitResult().getLocation();
                if (mainHandItemStack.is(laserPointer)) {
                    int color = mainHandItemStack.getOrDefault(componentType, LaserData.EMPTY).getColorARGB();
                    if (mc.options.getCameraType().isFirstPerson()) {
                        if (ClientTickEventHandler.mainHandLaserTick > 6) {
                            Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                        }
                    } else {
                        Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    }
                }
                if (offHandItemStack.is(laserPointer)) {
                    int color = offHandItemStack.getOrDefault(componentType, LaserData.EMPTY).getColorARGB();
                    if (mc.options.getCameraType().isFirstPerson()) {
                        if (ClientTickEventHandler.offHandLaserTick > 6) {
                            Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                        }
                    } else {
                        Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    }
                }
            }

            mc.level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(LaserPointerHitHelper.LASER_MAX_DISTANCE), p -> !p.equals(player) && !p.isSpectator() && (p.getMainHandItem().is(laserPointer) || p.getOffhandItem().is(laserPointer)))
                    .forEach(p -> {
                        ItemStack mainHandItemStack1 = p.getMainHandItem();
                        ItemStack offHandItemStack1 = p.getOffhandItem();
                        Vec3 eyePos = p.getEyePosition(partialTick);
                        Vec3 viewVec = p.getViewVector(partialTick);
                        if (mainHandItemStack1.is(laserPointer)) {
                            LaserData data = mainHandItemStack1.getOrDefault(componentType, LaserData.EMPTY);
                            Vec3 startPos = getThirdViewPlayerHandPos(p, p.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                            Vec3 targetPos = eyePos.add(viewVec.scale(data.distance()));
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, data.getColorARGB());
                        }
                        if (offHandItemStack1.is(laserPointer)) {
                            LaserData data = offHandItemStack1.getOrDefault(componentType, LaserData.EMPTY);
                            Vec3 startPos = getThirdViewPlayerHandPos(p, p.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                            Vec3 targetPos = eyePos.add(viewVec.scale(data.distance()));
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, data.getColorARGB());
                        }
                    });
//            addLaserToBuffer(bufferBuilder, new Vec3(0, 72, 0), new Vec3(64, 72, 0), 0xff78e0ff);
//            addLaserToBuffer(bufferBuilder, new Vec3(0, 72, 1), new Vec3(64, 72, 1), 0xffff7864);
//            addLaserToBuffer(bufferBuilder, new Vec3(0, 72, 2), new Vec3(64, 72, 2), 0xffffbc4c);
//            addLaserToBuffer(bufferBuilder, new Vec3(0, 72, 3), new Vec3(64, 72, 3), 0xff78ff78);
            MeshData meshData = bufferBuilder.build();
            if (meshData != null) {
                RenderSystem.disableCull();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                VertexBuffer vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                vertexBuffer.bind();
                vertexBuffer.upload(meshData);
                Vec3 view = event.getCamera().getPosition();
                Matrix4f translatedModelViewMatrix = new Matrix4f(event.getModelViewMatrix()).translate((float) -view.x, (float) -view.y, (float) -view.z);
                vertexBuffer.drawWithShader(translatedModelViewMatrix, event.getProjectionMatrix(), GameRenderer.getPositionColorShader());
                VertexBuffer.unbind();
                RenderSystem.enableCull();
                RenderSystem.disableBlend();
            }
        }
    }
}
