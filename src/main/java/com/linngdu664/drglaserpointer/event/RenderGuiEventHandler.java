package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.joml.*;

import java.lang.Math;
import java.util.List;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderGuiEventHandler {
    private static final int MAX_TARGET_NAME_WIDTH = 108;
    private static final int MAX_PLAYER_NAME_WIDTH = 153;
    private static final int LABEL_HEIGHT = 36;
    private static final int FRAME_PROTECT = 10;
    private static final int ICON_WIDTH_WITH_MARGIN = 18;
    private static final int MIN_REF_WIDTH = 40;
    private static final ResourceLocation UP_ICON = Main.makeResLoc("textures/gui/arrow/up.png");
    private static final ResourceLocation DOWN_ICON = Main.makeResLoc("textures/gui/arrow/down.png");
    private static final ResourceLocation LEFT_ICON = Main.makeResLoc("textures/gui/arrow/left.png");
    private static final ResourceLocation RIGHT_ICON = Main.makeResLoc("textures/gui/arrow/right.png");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui) {
            return;
        }
        GameRenderer gameRenderer = mc.gameRenderer;
        Camera camera = gameRenderer.getMainCamera();
        Vec3 cameraPos = camera.getPosition();
        Matrix3f rotMat = new Matrix3f().rotation(camera.rotation().conjugate(new Quaternionf()));      // make rot mat
        Window window = mc.getWindow();
        float fovy = (float) gameRenderer.getFov(camera, event.getPartialTick().getGameTimeDeltaPartialTick(true), true) * Mth.DEG_TO_RAD;
        float tanHalfFovy = Mth.sin(fovy * 0.5F) / Mth.cos(fovy * 0.5F);
        float tanHalfFovx = tanHalfFovy * (float) window.getWidth() / (float) window.getHeight();
        GuiGraphics guiGraphics = event.getGuiGraphics();
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        int guiWidth, guiHeight;
        float guiScale = (float) window.getGuiScale();
        if (guiScale > 3F) {
            float factor = 3F / guiScale;
            poseStack.scale(factor, factor, factor);
            guiWidth = Math.round((float) window.getWidth() / 3F);
            guiHeight = Math.round((float) window.getHeight() / 3F);
        } else {
            guiWidth = guiGraphics.guiWidth();
            guiHeight = guiGraphics.guiHeight();
        }
        Player player = mc.player;
        Font font = mc.font;
        Level level = mc.level;
        List<LaserPointerLabelEntity> list = level.getEntitiesOfClass(LaserPointerLabelEntity.class, player.getBoundingBox().inflate(96), p -> p.distanceToSqr(player) <= 96 * 96);
        for (LaserPointerLabelEntity labelEntity : list) {
            FormattedText playerText = font.ellipsize(Component.literal(labelEntity.getOwnerName()), MAX_PLAYER_NAME_WIDTH);
            FormattedText distanceText;
            List<FormattedCharSequence> targetTextList;
            ItemStack blockItemStack = null;
            ResourceLocation entityIconLocation = null;
            Vector3f labelPos;
            Vector3f realPos;
            Entity entity = level.getEntity(labelEntity.getTargetEntityId());
            if (entity != null) {
                if (entity instanceof LivingEntity) {
                    ResourceLocation resourceLocation = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                    if (resourceLocation.getNamespace().equals("minecraft") && !(entity instanceof Player) && !(entity instanceof ArmorStand)) {
                        entityIconLocation = Main.makeResLoc("textures/gui/face/" + resourceLocation.getPath() + "_face.png");
                    } else {
                        entityIconLocation = Main.makeResLoc("textures/gui/face/unknown.png");
                    }
                    targetTextList = font.split(entity.getName(), MAX_TARGET_NAME_WIDTH);
                    distanceText = font.ellipsize(Component.translatable("tip.drglaserpointer.distance", String.format("%.1f", entity.distanceTo(player))), MAX_PLAYER_NAME_WIDTH);
                    AABB aabb = entity.getBoundingBox();
                    Vec3 vec3 = entity.getPosition(event.getPartialTick().getGameTimeDeltaPartialTick(true));
                    labelPos = new Vector3f((float) (vec3.x - cameraPos.x), (float) (vec3.y + aabb.getYsize() + 0.5 - cameraPos.y), (float) (vec3.z - cameraPos.z));
                    realPos = new Vector3f(labelPos.x, (float) (vec3.y + aabb.getYsize() * 0.5 - cameraPos.y), labelPos.z);
                } else {
                    if (entity instanceof ItemEntity itemEntity) {
                        blockItemStack = itemEntity.getItem();
                        targetTextList = font.split(Component.translatable("tip.drglaserpointer.item_entity_name", entity.getName()), MAX_TARGET_NAME_WIDTH);
                    } else {
                        entityIconLocation = Main.makeResLoc("textures/gui/face/unknown.png");
                        targetTextList = font.split(entity.getName(), MAX_TARGET_NAME_WIDTH);
                    }
                    distanceText = font.ellipsize(Component.translatable("tip.drglaserpointer.distance", String.format("%.1f", labelEntity.distanceTo(player))), MAX_PLAYER_NAME_WIDTH);
                    labelPos = new Vector3f((float) (labelEntity.getX() - cameraPos.x), (float) (labelEntity.getY() + 1.0 - cameraPos.y), (float) (labelEntity.getZ() - cameraPos.z));
                    realPos = new Vector3f(labelPos.x, (float) (labelEntity.getY() - cameraPos.y), labelPos.z);
                }
            } else {
                Block block = labelEntity.getTargetBlockState().getBlock();
                blockItemStack = block.asItem().getDefaultInstance();
                targetTextList = font.split(block.getName(), MAX_TARGET_NAME_WIDTH);
                distanceText = font.ellipsize(Component.translatable("tip.drglaserpointer.distance", String.format("%.1f", labelEntity.distanceTo(player))), MAX_PLAYER_NAME_WIDTH);
                labelPos = new Vector3f((float) (labelEntity.getX() - cameraPos.x), (float) (labelEntity.getY() + 1.0 - cameraPos.y), (float) (labelEntity.getZ() - cameraPos.z));
                realPos = new Vector3f(labelPos.x, (float) (labelEntity.getY() - cameraPos.y), labelPos.z);
            }
            int mainWidth = ICON_WIDTH_WITH_MARGIN;
            FormattedCharSequence targetTextLine1 = null, targetTextLine2 = null;
            if (targetTextList.size() == 1) {
                targetTextLine1 = targetTextList.getFirst();
                mainWidth += font.width(targetTextLine1);
            } else if (targetTextList.size() >= 2) {
                targetTextLine1 = targetTextList.getFirst();
                targetTextLine2 = targetTextList.get(1);
                mainWidth += Math.max(font.width(targetTextLine1), font.width(targetTextLine2));
            }
            int refWidth = Math.max(MIN_REF_WIDTH, Math.max(Math.round(Math.max(font.width(distanceText), font.width(playerText)) * 0.8F), mainWidth));
            rotMat.transform(labelPos);
            rotMat.transform(realPos);
            // horizontal    tan = x / -z                         +right，-left
            // vertical      tan = y / Mth.sqrt(x * x + z * z)    +up, -down
            float rx = labelPos.x / -labelPos.z / tanHalfFovx;
            int xScreen = labelPos.z >= 0 ? (labelPos.x >= 0 ? guiWidth - (refWidth / 2 + FRAME_PROTECT) : refWidth / 2 + FRAME_PROTECT) : Mth.clamp((int) (guiWidth * 0.5F * (1 + rx)), refWidth / 2 + FRAME_PROTECT, guiWidth - (refWidth / 2 + FRAME_PROTECT));
            float ry1 = realPos.y / Mth.sqrt(realPos.x * realPos.x + realPos.z * realPos.z) / tanHalfFovy;
            float ry2 = labelPos.y / Mth.sqrt(labelPos.x * labelPos.x + labelPos.z * labelPos.z) / tanHalfFovy;
            int yScreen = Mth.clamp((int) (guiHeight * 0.5F * (1 - ry2)), LABEL_HEIGHT / 2 + FRAME_PROTECT, guiHeight - (LABEL_HEIGHT / 2 + FRAME_PROTECT));
            guiGraphics.fill(xScreen - refWidth / 2 - 2, yScreen - 11, xScreen + refWidth / 2 + 2, yScreen + 11, 0x40000000);
            guiGraphics.vLine(xScreen - refWidth / 2 - 3, yScreen - 12, yScreen + 11, 0xffc1bd93);
            guiGraphics.vLine(xScreen + refWidth / 2 + 2, yScreen - 12, yScreen + 11, 0xffc1bd93);
            guiGraphics.hLine(xScreen - refWidth / 2 - 3, xScreen - refWidth / 2 - 1, yScreen - 12, 0xffc1bd93);
            guiGraphics.hLine(xScreen - refWidth / 2 - 3, xScreen - refWidth / 2 - 1, yScreen + 11, 0xffc1bd93);
            guiGraphics.hLine(xScreen + refWidth / 2, xScreen + refWidth / 2 + 2, yScreen - 12, 0xffc1bd93);
            guiGraphics.hLine(xScreen + refWidth / 2, xScreen + refWidth / 2 + 2, yScreen + 11, 0xffc1bd93);
            if (labelPos.z >= 0 && labelPos.x < 0 || labelPos.z < 0 && rx < -1) {
                guiGraphics.blit(LEFT_ICON, xScreen - refWidth / 2 - 9, yScreen - 2, 0, 0, 4, 4, 4, 4);
            } else if (labelPos.z >= 0 && labelPos.x >= 0 || labelPos.z < 0 && rx > 1) {
                guiGraphics.blit(RIGHT_ICON, xScreen + refWidth / 2 + 5, yScreen - 2, 0, 0,  4, 4, 4, 4);
            } else if (ry1 > 1) {
                guiGraphics.blit(UP_ICON, xScreen - 2, yScreen - LABEL_HEIGHT / 2 - 6, 0, 0, 4, 4, 4, 4);
            } else if (ry1 < -1) {
                guiGraphics.blit(DOWN_ICON, xScreen - 2, yScreen + LABEL_HEIGHT / 2 + 4, 0, 0, 4, 4, 4, 4);
            }
            if (blockItemStack == null) {
                guiGraphics.blit(entityIconLocation, xScreen - refWidth / 2, yScreen - 8, 0, 0, 16, 16, 16, 16);
            } else {
                guiGraphics.renderItem(blockItemStack, xScreen - refWidth / 2, yScreen - 8);
            }
            int targetTextCenterX = xScreen + (refWidth - ICON_WIDTH_WITH_MARGIN) / 2 - (refWidth / 2 - ICON_WIDTH_WITH_MARGIN);
            if (targetTextLine1 != null) {
                if (targetTextLine2 != null) {
                    guiGraphics.drawString(font, targetTextLine1, targetTextCenterX - font.width(targetTextLine1) / 2, yScreen - 9, 0xffc1bd93, false);
                    guiGraphics.drawString(font, targetTextLine2, targetTextCenterX - font.width(targetTextLine2) / 2, yScreen, 0xffc1bd93, false);
                } else {
                    guiGraphics.drawString(font, targetTextLine1, targetTextCenterX - font.width(targetTextLine1) / 2, yScreen - 4, 0xffc1bd93, false);
                }
            }
            poseStack.pushPose();
            poseStack.scale(0.8F, 0.8F, 0.8F);
            xScreen = Math.round(xScreen * 1.25F);
            yScreen = Math.round(yScreen * 1.25F);
            guiGraphics.drawCenteredString(font, (Component) playerText, xScreen, yScreen - 24, 0xffc1bd93);
            guiGraphics.drawCenteredString(font, (Component) distanceText, xScreen, yScreen + 17, 0xffc1bd93);
            poseStack.popPose();
        }
        poseStack.popPose();
    }
}
