package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.config.ClientConfig;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.linngdu664.drglaserpointer.client.util.GuiUtil;
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
    private static final float LABEL_HEIGHT = 36;
    private static final float FRAME_PROTECT = 10;
    private static final float ICON_WIDTH_WITH_MARGIN = 18;
    private static final float MIN_REF_WIDTH = 40;
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
        poseStack.translate(0F, 0F, 4901F);
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
        double markDisplayRangeSq = ClientConfig.MARK_DISPLAY_RANGE.getConfigValue() * ClientConfig.MARK_DISPLAY_RANGE.getConfigValue();
        List<LaserPointerMarkEntity> list = level.getEntitiesOfClass(LaserPointerMarkEntity.class, player.getBoundingBox().inflate(ClientConfig.MARK_DISPLAY_RANGE.getConfigValue()), p -> p.distanceToSqr(player) <= markDisplayRangeSq);
        for (LaserPointerMarkEntity markEntity : list) {
            FormattedText playerText = font.ellipsize(Component.literal(markEntity.getOwnerName()), MAX_PLAYER_NAME_WIDTH);
            FormattedText distanceText;
            List<FormattedCharSequence> targetTextList;
            ItemStack blockItemStack = null;
            ResourceLocation entityIconLocation = null;
            Vector3f labelPos;
            Vector3f realPos;
            Entity entity = level.getEntity(markEntity.getTargetEntityId());
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
                    distanceText = font.ellipsize(Component.translatable("tip.drglaserpointer.distance", String.format("%.1f", markEntity.distanceTo(player))), MAX_PLAYER_NAME_WIDTH);
                    labelPos = new Vector3f((float) (markEntity.getX() - cameraPos.x), (float) (markEntity.getY() + 1.0 - cameraPos.y), (float) (markEntity.getZ() - cameraPos.z));
                    realPos = new Vector3f(labelPos.x, (float) (markEntity.getY() - cameraPos.y), labelPos.z);
                }
            } else {
                Block block = markEntity.getTargetBlockState().getBlock();
                blockItemStack = block.asItem().getDefaultInstance();
                targetTextList = font.split(block.getName(), MAX_TARGET_NAME_WIDTH);
                distanceText = font.ellipsize(Component.translatable("tip.drglaserpointer.distance", String.format("%.1f", markEntity.distanceTo(player))), MAX_PLAYER_NAME_WIDTH);
                labelPos = new Vector3f((float) (markEntity.getX() - cameraPos.x), (float) (markEntity.getY() + 1.0 - cameraPos.y), (float) (markEntity.getZ() - cameraPos.z));
                realPos = new Vector3f(labelPos.x, (float) (markEntity.getY() - cameraPos.y), labelPos.z);
            }
            float mainWidth = ICON_WIDTH_WITH_MARGIN;
            FormattedCharSequence targetTextLine1 = null, targetTextLine2 = null;
            if (targetTextList.size() == 1) {
                targetTextLine1 = targetTextList.getFirst();
                mainWidth += font.width(targetTextLine1);
            } else if (targetTextList.size() >= 2) {
                targetTextLine1 = targetTextList.getFirst();
                targetTextLine2 = targetTextList.get(1);
                mainWidth += Math.max(font.width(targetTextLine1), font.width(targetTextLine2));
            }
            float refWidth = Math.max(MIN_REF_WIDTH, Math.max(Math.round(Math.max(font.width(distanceText), font.width(playerText)) * 0.8F), mainWidth));
            rotMat.transform(labelPos);
            rotMat.transform(realPos);
            // horizontal    tan = x / -z                         +right，-left
            // vertical      tan = y / -z    +up, -down
            float rx = labelPos.x / -labelPos.z / tanHalfFovx;
            float xScreen = labelPos.z >= 0 ? (labelPos.x >= 0 ? guiWidth - (refWidth / 2 + FRAME_PROTECT) : refWidth / 2 + FRAME_PROTECT) : Mth.clamp( guiWidth * 0.5F * (1 + rx), refWidth / 2 + FRAME_PROTECT, guiWidth - (refWidth / 2 + FRAME_PROTECT));
//            float ry1 = realPos.y / Mth.sqrt(realPos.x * realPos.x + realPos.z * realPos.z) / tanHalfFovy;
//            float ry2 = labelPos.y / Mth.sqrt(labelPos.x * labelPos.x + labelPos.z * labelPos.z) / tanHalfFovy;
            float ry1 = realPos.y / -realPos.z / tanHalfFovy;
            float ry2 = labelPos.y / -labelPos.z / tanHalfFovy;
            float yScreen = Mth.clamp( guiHeight * 0.5F * (1 - ry2), LABEL_HEIGHT / 2 + FRAME_PROTECT, guiHeight - (LABEL_HEIGHT / 2 + FRAME_PROTECT));
            GuiUtil.fill(guiGraphics,xScreen - refWidth / 2 - 2, yScreen - 11, xScreen + refWidth / 2 + 2, yScreen + 11, 0x40000000);
            GuiUtil.vLine(guiGraphics,xScreen - refWidth / 2 - 3, yScreen - 12, yScreen + 11, 0xffc1bd93);
            GuiUtil.vLine(guiGraphics,xScreen + refWidth / 2 + 2, yScreen - 12, yScreen + 11, 0xffc1bd93);
            GuiUtil.hLine(guiGraphics,xScreen - refWidth / 2 - 3, xScreen - refWidth / 2 - 1, yScreen - 12, 0xffc1bd93);
            GuiUtil.hLine(guiGraphics,xScreen - refWidth / 2 - 3, xScreen - refWidth / 2 - 1, yScreen + 11, 0xffc1bd93);
            GuiUtil.hLine(guiGraphics,xScreen + refWidth / 2, xScreen + refWidth / 2 + 2, yScreen - 12, 0xffc1bd93);
            GuiUtil.hLine(guiGraphics,xScreen + refWidth / 2, xScreen + refWidth / 2 + 2, yScreen + 11, 0xffc1bd93);
            if (labelPos.z >= 0 && labelPos.x < 0 || labelPos.z < 0 && rx < -1) {
                GuiUtil.blit(guiGraphics,LEFT_ICON, xScreen - refWidth / 2 - 9, yScreen - 2, 0, 0, 4, 4, 4, 4);
            } else if (labelPos.z >= 0 && labelPos.x >= 0 || labelPos.z < 0 && rx > 1) {
                GuiUtil.blit(guiGraphics,RIGHT_ICON, xScreen + refWidth / 2 + 5, yScreen - 2, 0, 0,  4, 4, 4, 4);
            } else if (ry1 > 1) {
                GuiUtil.blit(guiGraphics,UP_ICON, xScreen - 2, yScreen - LABEL_HEIGHT / 2 - 6, 0, 0, 4, 4, 4, 4);
            } else if (ry1 < -1) {
                GuiUtil.blit(guiGraphics,DOWN_ICON, xScreen - 2, yScreen + LABEL_HEIGHT / 2 + 4, 0, 0, 4, 4, 4, 4);
            }
            if (blockItemStack == null) {
                GuiUtil.blit(guiGraphics,entityIconLocation, xScreen - refWidth / 2, yScreen - 8, 0, 0, 16, 16, 16, 16);
            } else {
                GuiUtil.renderItem(guiGraphics,blockItemStack, xScreen - refWidth / 2, yScreen - 8);
            }
            float targetTextCenterX = xScreen + (refWidth - ICON_WIDTH_WITH_MARGIN) / 2 - (refWidth / 2 - ICON_WIDTH_WITH_MARGIN);
            if (targetTextLine1 != null) {
                if (targetTextLine2 != null) {
                    guiGraphics.drawString(font, targetTextLine1, targetTextCenterX - font.width(targetTextLine1) * 0.5F, yScreen - 9, 0xffc1bd93, false);
                    guiGraphics.drawString(font, targetTextLine2, targetTextCenterX - font.width(targetTextLine2) * 0.5F, yScreen, 0xffc1bd93, false);
                } else {
                    guiGraphics.drawString(font, targetTextLine1, targetTextCenterX - font.width(targetTextLine1) * 0.5F, yScreen - 4, 0xffc1bd93, false);
                }
            }
            poseStack.pushPose();
            poseStack.scale(0.8F, 0.8F, 0.8F);
            xScreen = xScreen * 1.25F;
            yScreen = yScreen * 1.25F;
            GuiUtil.drawCenteredString(guiGraphics,font, (Component) playerText, xScreen, yScreen - 24, 0xffc1bd93);
            GuiUtil.drawCenteredString(guiGraphics,font, (Component) distanceText, xScreen, yScreen + 17, 0xffc1bd93);
            poseStack.popPose();
        }
        poseStack.popPose();
    }
}
