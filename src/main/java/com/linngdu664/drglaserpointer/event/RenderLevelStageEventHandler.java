package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.LaserPointerItem;
import com.linngdu664.drglaserpointer.network.LaserDistanceResponsePayload;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.client.util.LaserPointerHitHelper;
import com.linngdu664.drglaserpointer.registry.RenderPipelineRegistry;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.util.*;

@EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class RenderLevelStageEventHandler {
    public static final RenderType LASER_RENDER_TYPE = RenderType.create(
            Main.makeMyIdentifier("laser").toString(),
            RenderSetup.builder(RenderPipelineRegistry.LASER_PIPELINE)
                    .bufferSize(4096)
                    .sortOnUpload()
                    .createRenderSetup()
    );
    public static final double LASER_WIDTH = 0.005;

    private static void addLaserQuad(VertexConsumer consumer, Vec3 camPos, Vec3 start, Vec3 end, Vec3 n, int color) {
        int endAlpha = Math.max(0, (int) (255 * (1 - end.distanceTo(start) / LaserPointerHitHelper.LASER_RANGE)));
        Vec3 start1 = start.add(n);
        Vec3 start2 = start.subtract(n);
        Vec3 end1 = end.add(n);
        Vec3 end2 = end.subtract(n);
        consumer.addVertex((float) (start1.x - camPos.x), (float) (start1.y - camPos.y), (float) (start1.z - camPos.z)).setColor(color | (255 << 24));
        consumer.addVertex((float) (start2.x - camPos.x), (float) (start2.y - camPos.y), (float) (start2.z - camPos.z)).setColor(color | (255 << 24));
        consumer.addVertex((float) (end2.x - camPos.x), (float) (end2.y - camPos.y), (float) (end2.z - camPos.z)).setColor(color & 0xffffff | (endAlpha << 24));
        consumer.addVertex((float) (end1.x - camPos.x), (float) (end1.y - camPos.y), (float) (end1.z - camPos.z)).setColor(color & 0xffffff | (endAlpha << 24));
    }

    private static void addLaser(VertexConsumer consumer, Vec3 camPos, Vec3 start, Vec3 end, int color) {
        Vec3 rVec = end.subtract(start);
        Vec3 n1 = rVec.cross(new Vec3(0, 1, 0));
        if (n1.lengthSqr() < 1e-10) {
            n1 = rVec.cross(new Vec3(1, 0, 0));
        }
        n1 = n1.normalize().scale(LASER_WIDTH);
        Vec3 n2 = rVec.cross(n1).normalize().scale(LASER_WIDTH);
        addLaserQuad(consumer, camPos, start, end, n1, color);
        addLaserQuad(consumer, camPos, start, end, n2, color);
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
        Vec3 vec3 = mc.gameRenderer.getMainCamera().getNearPlane(mc.options.fov().get()).getPointOnPlane(isLeftHand ? -0.56F : 0.56F, -0.36F).scale(d4);
        return player.getEyePosition(partialTick).add(vec3);
    }

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent.AfterTranslucentBlocks event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ClientLevel level = mc.level;
        if (player == null || level == null) {
            return;
        }
        ItemStack mainHandItemStack = player.getMainHandItem();
        ItemStack offHandItemStack = player.getOffhandItem();
        Item laserPointer = ItemRegister.LASER_POINTER.get();
        Vec3 camPos = mc.gameRenderer.getMainCamera().position();
        float partialTick = mc.getDeltaTracker().getGameTimeDeltaPartialTick(false);
        MultiBufferSource.BufferSource source = mc.renderBuffers().bufferSource();
        VertexConsumer consumer = source.getBuffer(LASER_RENDER_TYPE);

        if (mc.gameMode.getPlayerMode() != GameType.SPECTATOR && (mainHandItemStack.is(laserPointer) || offHandItemStack.is(laserPointer))) {
            LaserPointerHitHelper helper = LaserPointerHitHelper.getInstance();
            helper.calcHitResult(player, partialTick);
            Vec3 targetPos = helper.getHitResult().getLocation();
            if (mainHandItemStack.is(laserPointer)) {
                int color = LaserPointerItem.getLaserColorARGB(mainHandItemStack.getOrDefault(DataComponentRegister.LASER_COLOR, (byte) 0));
                if (mc.options.getCameraType().isFirstPerson()) {
                    if (mc.gameRenderer.itemInHandRenderer.mainHandHeight == 1 && ClientTickEventHandler.mainHandLaserTick > 6) {
                        Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                        addLaser(consumer, camPos, startPos, targetPos, color);
                    }
                } else {
                    Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                    addLaser(consumer, camPos, startPos, targetPos, color);
                }
                mainHandItemStack.set(DataComponentRegister.SCREEN_COLOR, helper.getScreenColor(player.level()));
            }
            if (offHandItemStack.is(laserPointer)) {
                int color = LaserPointerItem.getLaserColorARGB(offHandItemStack.getOrDefault(DataComponentRegister.LASER_COLOR, (byte) 0));
                if (mc.options.getCameraType().isFirstPerson()) {
                    if (mc.gameRenderer.itemInHandRenderer.offHandHeight == 1 && ClientTickEventHandler.offHandLaserTick > 6) {
                        Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                        addLaser(consumer, camPos, startPos, targetPos, color);
                    }
                } else {
                    Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                    addLaser(consumer, camPos, startPos, targetPos, color);
                }
                offHandItemStack.set(DataComponentRegister.SCREEN_COLOR, helper.getScreenColor(player.level()));
            }
        }

        List<AbstractClientPlayer> playerList = level.players();
        AABB aabb = player.getBoundingBox().inflate(LaserPointerHitHelper.LASER_RANGE);
        for (AbstractClientPlayer p : playerList) {
            if (aabb.contains(p.getPosition(partialTick)) && !p.isSpectator() && (p.getMainHandItem().is(laserPointer) || p.getOffhandItem().is(laserPointer)) && !p.equals(player)) {
                ItemStack mainHandItemStack1 = p.getMainHandItem();
                ItemStack offHandItemStack1 = p.getOffhandItem();
                Vec3 eyePos = p.getEyePosition(partialTick);
                Vec3 viewVec = p.getViewVector(partialTick);
                if (mainHandItemStack1.is(laserPointer)) {
                    Vec3 startPos = getThirdViewPlayerHandPos(p, p.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                    Vec3 targetPos = eyePos.add(viewVec.scale(Objects.requireNonNullElse(LaserDistanceResponsePayload.clientDisMap.get(p.getId()), (short) 0) / 64F));
                    addLaser(consumer, camPos, startPos, targetPos, LaserPointerItem.getLaserColorARGB(mainHandItemStack1.getOrDefault(DataComponentRegister.LASER_COLOR, (byte) 0)));
                }
                if (offHandItemStack1.is(laserPointer)) {
                    Vec3 startPos = getThirdViewPlayerHandPos(p, p.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                    Vec3 targetPos = eyePos.add(viewVec.scale(Objects.requireNonNullElse(LaserDistanceResponsePayload.clientDisMap.get(p.getId()), (short) 0) / 64F));
                    addLaser(consumer, camPos, startPos, targetPos, LaserPointerItem.getLaserColorARGB(offHandItemStack1.getOrDefault(DataComponentRegister.LASER_COLOR, (byte) 0)));
                }
            }
        }

        // todo 记得移除测试代码
        addLaser(consumer, camPos, new Vec3(0.5, 72.5, 0.5), new Vec3(64.5, 72.5, 0.5), 0xff78e0ff);
        addLaser(consumer, camPos, new Vec3(0.5, 72.5, 1.5), new Vec3(64.5, 72.5, 1.5), 0xffff7864);
        addLaser(consumer, camPos, new Vec3(0.5, 72.5, 2.5), new Vec3(64.5, 72.5, 2.5), 0xffffbc4c);
        addLaser(consumer, camPos, new Vec3(0.5, 72.5, 3.5), new Vec3(64.5, 72.5, 3.5), 0xff78ff78);
        source.endBatch(LASER_RENDER_TYPE);
    }
}
