package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderLevelStageEventHandler {
    public static final double LASER_MAX_DISTANCE = 64;
    public static final double LASER_MAX_DISTANCE_SQ = 64 * 64;
    public static final double LASER_WIDTH = 0.005;
    public static double laserDistance;

    private static void addLaserQuad(BufferBuilder bufferBuilder, Vec3 start, Vec3 end, Vec3 n, int color) {
        int endAlpha = Math.max(0, (int) (255 * (1 - end.distanceTo(start) / LASER_MAX_DISTANCE)));
        Vec3 start1 = start.add(n);
        Vec3 start2 = start.subtract(n);
        Vec3 end1 = end.add(n);
        Vec3 end2 = end.subtract(n);
        bufferBuilder.addVertex((float) start1.x, (float) start1.y, (float) start1.z).setColor(color | (255 << 24));
        bufferBuilder.addVertex((float) start2.x, (float) start2.y, (float) start2.z).setColor(color | (255 << 24));
        bufferBuilder.addVertex((float) end2.x, (float) end2.y, (float) end2.z).setColor(color | (endAlpha << 24));
        bufferBuilder.addVertex((float) end1.x, (float) end1.y, (float) end1.z).setColor(color | (endAlpha << 24));
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
        float f = Mth.lerp(partialTick, player.yBodyRotO, player.yBodyRot) * 0.017453292F;
        double d0 = Mth.sin(f);
        double d1 = Mth.cos(f);
        double d4 = player.getScale();
        double d2 = (isLeftHand ? -0.35 : 0.35) * d4;
        double d3 = 0.8 * d4;
        double d5 = player.isCrouching() ? -0.1875 : 0.0;
        return player.getEyePosition(partialTick).add(-d1 * d2 - d0 * d3, d5 - 0.45 * d4, -d0 * d2 + d1 * d3);
    }

    private static Vec3 getFirstViewPlayerHandPos(Player player, boolean isLeftHand, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        double d4 = 960.0 / mc.options.fov().get();
//        float para = 1.57f;
//        Vec3 vec3 = mc.gameRenderer.getMainCamera().getNearPlane().getPointOnPlane(isLeftHand ? -0.525F : 0.525F, -0.1F).scale(d4).yRot(para * 0.5F).xRot(-para * 0.7F);
        Vec3 vec3 = mc.gameRenderer.getMainCamera().getNearPlane().getPointOnPlane(isLeftHand ? -0.525F : 0.525F, -0.1F).scale(d4);
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
            if (mainHandItemStack.is(laserPointer) || offHandItemStack.is(laserPointer)) {
                HitResult blockHitResult = player.pick(LASER_MAX_DISTANCE, partialTick, false);
                Vec3 traceBegin = player.getEyePosition(partialTick);
                Vec3 scaledViewVec = player.getViewVector(partialTick).scale(LASER_MAX_DISTANCE);
                Vec3 traceEnd = traceBegin.add(scaledViewVec);
                AABB aabb = player.getBoundingBox().expandTowards(scaledViewVec).inflate(1.0);
                EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(player, traceBegin, traceEnd, aabb, p -> !p.isSpectator() && p.isPickable(), LASER_MAX_DISTANCE_SQ);
                Vec3 targetPos = blockHitResult.getLocation();
                laserDistance = blockHitResult.getLocation().distanceTo(traceBegin);
                if (entityHitResult != null) {
                    double d1 = entityHitResult.getLocation().distanceTo(traceBegin);
                    if (d1 < laserDistance) {
                        targetPos = entityHitResult.getLocation();
                        laserDistance = d1;
                    }
                }

                if (mainHandItemStack.is(laserPointer)) {
                    int color = mainHandItemStack.get(DataComponents.BASE_COLOR).getTextureDiffuseColor();
                    if (mc.options.getCameraType().isFirstPerson()) {
                        Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    } else {
                        Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    }
                }
                if (offHandItemStack.is(laserPointer)) {
                    int color = offHandItemStack.get(DataComponents.BASE_COLOR).getTextureDiffuseColor();
                    if (mc.options.getCameraType().isFirstPerson()) {
                        Vec3 startPos = getFirstViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    } else {
                        Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                        addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    }
                }
            }

            mc.level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(LASER_MAX_DISTANCE), p -> !p.equals(player) && (p.getMainHandItem().is(laserPointer) || p.getOffhandItem().is(laserPointer)))
                    .forEach(p -> {
                        ItemStack mainHandItemStack1 = p.getMainHandItem();
                        ItemStack offHandItemStack1 = p.getOffhandItem();
                        Vec3 eyePos = p.getEyePosition(partialTick);
                        Vec3 viewVec = p.getViewVector(partialTick);
                        if (mainHandItemStack1.is(laserPointer)) {
                            Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), partialTick);
                            Vec3 targetPos = eyePos.add(viewVec.scale(mainHandItemStack1.get(DataComponentRegister.LASER_DISTANCE.get()).distance()));
                            int color = mainHandItemStack1.get(DataComponents.BASE_COLOR).getTextureDiffuseColor();
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                        }
                        if (offHandItemStack1.is(laserPointer)) {
                            Vec3 startPos = getThirdViewPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), partialTick);
                            Vec3 targetPos = eyePos.add(viewVec.scale(offHandItemStack1.get(DataComponentRegister.LASER_DISTANCE.get()).distance()));
                            int color = offHandItemStack1.get(DataComponents.BASE_COLOR).getTextureDiffuseColor();
                            addLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                        }
                    });

            MeshData meshData = bufferBuilder.build();
            if (meshData != null) {
                RenderSystem.depthMask(false);
                RenderSystem.disableCull();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                var vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                vertexBuffer.bind();
                vertexBuffer.upload(meshData);
                var view = event.getCamera().getPosition();
                var translatedModelViewMatrix = new Matrix4f(event.getModelViewMatrix()).translate((float) -view.x, (float) -view.y, (float) -view.z);
                vertexBuffer.drawWithShader(translatedModelViewMatrix, event.getProjectionMatrix(), GameRenderer.getPositionColorShader());
                VertexBuffer.unbind();
                RenderSystem.depthMask(true);
                RenderSystem.enableCull();
                RenderSystem.disableBlend();
            }
        }
    }
}
