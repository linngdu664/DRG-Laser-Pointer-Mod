package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.component.LaserPointerData;
import com.linngdu664.drglaserpointer.network.LaserDistancePayload;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.DyeColor;
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
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Matrix4f;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameEvents {
    public static final double LASER_MAX_DISTANCE = 64;
    public static final double LASER_MAX_DISTANCE_SQ = 64 * 64;
    public static final double LASER_WIDTH = 0.01;

    private static void addLaserQuad(BufferBuilder bufferBuilder, Vec3 start, Vec3 end, Vec3 n, int color) {
        color &= 0xffffff;
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

    private static void renderLaserToBuffer(BufferBuilder bufferBuilder, Vec3 start, Vec3 end, int color) {
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

    private static Vec3 getPlayerHandPos(Player player, boolean isLeftHand, float p_340872_, float partialTick) {
        int i = isLeftHand ? -1 : 1;
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.getCameraType().isFirstPerson() && player == mc.player) {
            double d4 = 960.0 / (double) Minecraft.getInstance().options.fov().get();
            Vec3 vec3 = mc.gameRenderer.getMainCamera().getNearPlane().getPointOnPlane((float)i * 0.525F, -0.1F).scale(d4).yRot(p_340872_ * 0.5F).xRot(-p_340872_ * 0.7F);
            return player.getEyePosition(partialTick).add(vec3);
        } else {
            float f = Mth.lerp(partialTick, player.yBodyRotO, player.yBodyRot) * 0.017453292F;
            double d0 = Mth.sin(f);
            double d1 = Mth.cos(f);
            float f1 = player.getScale();
            double d2 = i * 0.35 * f1;
            double d3 = 0.8 * f1;
            float f2 = player.isCrouching() ? -0.1875F : 0.0F;
            return player.getEyePosition(partialTick).add(-d1 * d2 - d0 * d3, (double)f2 - 0.45 * (double)f1, -d0 * d2 + d1 * d3);
        }
    }

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        // todo fix third person bug
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
            var bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            float partialTick = event.getPartialTick().getGameTimeDeltaPartialTick(true);
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            ItemStack mainHandItemStack = player.getMainHandItem();
            ItemStack offHandItemStack = player.getOffhandItem();
            Item laserPointer = ItemRegister.LASER_POINTER.get();
            DataComponentType<LaserPointerData> componentType = DataComponentRegister.LASER_POINTER_DATA.get();

            if (mainHandItemStack.is(laserPointer) || offHandItemStack.is(laserPointer)) {
                HitResult blockHitResult = player.pick(LASER_MAX_DISTANCE, partialTick, false);
                Vec3 traceBegin = player.getEyePosition(partialTick);
                Vec3 scaledViewVec = player.getViewVector(partialTick).scale(LASER_MAX_DISTANCE);
                Vec3 traceEnd = traceBegin.add(scaledViewVec);
                AABB aabb = player.getBoundingBox().expandTowards(scaledViewVec).inflate(1.0);
                EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(player, traceBegin, traceEnd, aabb, p -> !p.isSpectator() && p.isPickable(), LASER_MAX_DISTANCE_SQ);
                Vec3 targetPos = blockHitResult.getLocation();
                double laserDistance = blockHitResult.getLocation().distanceTo(traceBegin);
                if (entityHitResult != null) {
//                    System.out.println(entityHitResult.getEntity());
                    double d1 = entityHitResult.getLocation().distanceTo(traceBegin);
                    if (d1 < laserDistance) {
                        targetPos = entityHitResult.getLocation();
                        laserDistance = d1;
                    }
                }
                if (mainHandItemStack.is(laserPointer)) {
                    Vec3 startPos = getPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), 0, partialTick);
                    int color = DyeColor.byId(mainHandItemStack.get(componentType).colorId()).getTextureDiffuseColor();
                    renderLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    if (partialTick == 0) {
                        PacketDistributor.sendToServer(new LaserDistancePayload(laserDistance, false));
                    }
                }
                if (offHandItemStack.is(laserPointer)) {
                    Vec3 startPos = getPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), 0, partialTick);
                    int color = DyeColor.byId(offHandItemStack.get(componentType).colorId()).getTextureDiffuseColor();
                    renderLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                    if (partialTick == 0) {
                        PacketDistributor.sendToServer(new LaserDistancePayload(laserDistance, true));
                    }
                }
            }

            mc.level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(LASER_MAX_DISTANCE), p -> !p.equals(player) && (p.getMainHandItem().is(laserPointer) || p.getOffhandItem().is(laserPointer)))
                    .forEach(p -> {
                        ItemStack mainHandItemStack1 = p.getMainHandItem();
                        ItemStack offHandItemStack1 = p.getOffhandItem();
                        Vec3 eyePos = p.getEyePosition(partialTick);
                        if (mainHandItemStack1.is(laserPointer)) {
                            LaserPointerData data = mainHandItemStack1.get(componentType);
                            int color = DyeColor.byId(data.colorId()).getTextureDiffuseColor();
                            Vec3 targetPos = eyePos.add(p.getViewVector(partialTick).scale(data.distance()));
                            Vec3 startPos = getPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.LEFT), 0, partialTick);
                            renderLaserToBuffer(bufferBuilder, startPos, targetPos, color);
                        }
                        if (offHandItemStack1.is(laserPointer)) {
                            LaserPointerData data = offHandItemStack1.get(componentType);
                            int color = DyeColor.byId(data.colorId()).getTextureDiffuseColor();
                            Vec3 targetPos = eyePos.add(p.getViewVector(partialTick).scale(data.distance()));
                            Vec3 startPos = getPlayerHandPos(player, player.getMainArm().equals(HumanoidArm.RIGHT), 0, partialTick);
                            renderLaserToBuffer(bufferBuilder, startPos, targetPos, color);
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
