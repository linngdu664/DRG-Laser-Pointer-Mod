package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.LaserPointerItem;
import com.linngdu664.drglaserpointer.network.LaserDistanceResponsePayload;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.client.util.LaserPointerHitHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderLevelStageEventHandler {
    public static final double LASER_WIDTH = 0.005;

    private static void addLaserQuad(BufferBuilder bufferBuilder, Vec3 start, Vec3 end, Vec3 n, int color) {
        int endAlpha = Math.max(0, (int) (255 * (1 - end.distanceTo(start) / LaserPointerHitHelper.LASER_RANGE)));
        Vec3 start1 = start.add(n);
        Vec3 start2 = start.subtract(n);
        Vec3 end1 = end.add(n);
        Vec3 end2 = end.subtract(n);
        bufferBuilder.vertex((float) start1.x, (float) start1.y, (float) start1.z).color(color | (255 << 24)).endVertex();
        bufferBuilder.vertex((float) start2.x, (float) start2.y, (float) start2.z).color(color | (255 << 24)).endVertex();
        bufferBuilder.vertex((float) end2.x, (float) end2.y, (float) end2.z).color(color & 0xffffff | (endAlpha << 24)).endVertex();
        bufferBuilder.vertex((float) end1.x, (float) end1.y, (float) end1.z).color(color & 0xffffff | (endAlpha << 24)).endVertex();
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
            BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            float partialTick = event.getPartialTick();
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            ItemStack mainHandItemStack = player.getMainHandItem();
            ItemStack offHandItemStack = player.getOffhandItem();
            Item laserPointer = ItemRegister.LASER_POINTER.get();
            if (mc.gameMode.getPlayerMode() != GameType.SPECTATOR && (mainHandItemStack.is(laserPointer) || offHandItemStack.is(laserPointer))) {
                LaserPointerHitHelper helper = LaserPointerHitHelper.getInstance();
                helper.calcHitResult(player, partialTick);
                Vec3 targetPos = helper.getHitResult().getLocation();
                if (mainHandItemStack.is(laserPointer)) {
                    int color = LaserPointerItem.getLaserColorARGB(mainHandItemStack.getOrCreateTag().getByte("LaserColor"));
                    if (mc.options.getCameraType().isFirstPerson()) {
                        if (mc.gameRenderer.itemInHandRenderer.mainHandHeight == 1 && ClientTickEventHandler.mainHandLaserTick > 6) {
                            Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                        }
                    } else {
                        Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    }
                    mainHandItemStack.getOrCreateTag().putByte("ScreenColor", helper.getScreenColor(player.level()));
                }
                if (offHandItemStack.is(laserPointer)) {
                    int color = LaserPointerItem.getLaserColorARGB(offHandItemStack.getOrCreateTag().getByte("LaserColor"));
                    if (mc.options.getCameraType().isFirstPerson()) {
                        if (mc.gameRenderer.itemInHandRenderer.offHandHeight == 1 && ClientTickEventHandler.offHandLaserTick > 6) {
                            Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                        }
                    } else {
                        Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    }
                    offHandItemStack.getOrCreateTag().putByte("ScreenColor", helper.getScreenColor(player.level()));
                }
            }
            List<AbstractClientPlayer> playerList = mc.level.players();
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
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, LaserPointerItem.getLaserColorARGB(mainHandItemStack.getOrCreateTag().getByte("LaserColor")));
                    }
                    if (offHandItemStack1.is(laserPointer)) {
                        Vec3 startPos = getThirdViewPlayerHandPos(p, p.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                        Vec3 targetPos = eyePos.add(viewVec.scale(Objects.requireNonNullElse(LaserDistanceResponsePayload.clientDisMap.get(p.getId()), (short) 0) / 64F));
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, LaserPointerItem.getLaserColorARGB(mainHandItemStack.getOrCreateTag().getByte("LaserColor")));
                    }
                }
            }
            BufferBuilder.RenderedBuffer renderedBuffer = bufferBuilder.endOrDiscardIfEmpty();
            if (renderedBuffer != null) {
                RenderSystem.disableCull();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                VertexBuffer vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                vertexBuffer.bind();
                vertexBuffer.upload(renderedBuffer);
                Vec3 view = event.getCamera().getPosition();
                PoseStack poseStack = event.getPoseStack();
                poseStack.pushPose();
                poseStack.translate(-view.x, -view.y, -view.z);
                vertexBuffer.drawWithShader(poseStack.last().pose(), event.getProjectionMatrix(), GameRenderer.getPositionColorShader());
                poseStack.popPose();
                VertexBuffer.unbind();
                RenderSystem.enableCull();
                RenderSystem.disableBlend();
            }
        }
    }
}
