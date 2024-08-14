package com.linngdu664.drglaserpointer.util;

import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.List;

public class LabelRenderHelper {
    public static final int MAX_TARGET_NAME_WIDTH = 108;
    public static final int MAX_PLAYER_NAME_WIDTH = 153;
    public static final int LABEL_HEIGHT = 36;
    public static final int FRAME_PROTECT = 10;
    public static final int ICON_HORIZONTAL_SPACE = 18;

    private final FormattedText playerText;
    private final FormattedText distanceText;
    private final FormattedCharSequence targetTextLine1;
    private final FormattedCharSequence targetTextLine2;
    private final ItemStack blockItemStack;
    private final ResourceLocation entityIconLocation;
    private final int refWidth;
    private final Vector4f ndcPos;

    public LabelRenderHelper(LaserPointerLabelEntity labelEntity, Player player, Font font, float partialTick, Matrix4f viewMatrix, Matrix4f projectionMatrix) {
        Level level = labelEntity.level();
        playerText = font.ellipsize(level.getPlayerByUUID(labelEntity.getOwnerUUID()).getName(), MAX_PLAYER_NAME_WIDTH);
        List<FormattedCharSequence> targetTextList;
        Entity entity = level.getEntity(labelEntity.getTargetEntityId());
        if (entity != null) {
            if (entity instanceof LivingEntity livingEntity) {
                Vec3 vec3 = livingEntity.getEyePosition(partialTick);
                ndcPos = new Vector4f((float) vec3.x, (float) vec3.y, (float) vec3.z, 1);
                distanceText = font.ellipsize(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", livingEntity.distanceTo(player))})), MAX_PLAYER_NAME_WIDTH);
                if (entity instanceof Player) {
                    entityIconLocation = ResourceLocation.fromNamespaceAndPath("drglaserpointer", "textures/gui/face/unknown.png");
                } else {
                    entityIconLocation = ResourceLocation.fromNamespaceAndPath("drglaserpointer", "textures/gui/face/" + BuiltInRegistries.ENTITY_TYPE.getKey(livingEntity.getType()).getPath() + "_face.png");
                }
            } else {
                ndcPos = new Vector4f((float) labelEntity.getX(), (float) labelEntity.getY(), (float) labelEntity.getZ(), 1);
                distanceText = font.ellipsize(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", labelEntity.distanceTo(player))})), MAX_PLAYER_NAME_WIDTH);
                entityIconLocation = ResourceLocation.fromNamespaceAndPath("drglaserpointer", "textures/gui/face/unknown.png");
            }
            targetTextList = font.split(entity.getName(), MAX_TARGET_NAME_WIDTH);
            blockItemStack = null;
        } else {
            ndcPos = new Vector4f((float) labelEntity.getX(), (float) labelEntity.getY(), (float) labelEntity.getZ(), 1);
            distanceText = font.ellipsize(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", labelEntity.distanceTo(player))})), MAX_PLAYER_NAME_WIDTH);
            Block block = level.getBlockState(labelEntity.getTargetBlockPos()).getBlock();
            targetTextList = font.split(block.getName(), MAX_TARGET_NAME_WIDTH);
            blockItemStack = block.asItem().getDefaultInstance();
            entityIconLocation = null;
        }
        if (targetTextList.size() == 1) {
            targetTextLine1 = targetTextList.getFirst();
            targetTextLine2 = null;
            refWidth = Math.max(Math.round(Math.max(font.width(distanceText), font.width(playerText)) * 0.8F), font.width(targetTextLine1) + ICON_HORIZONTAL_SPACE);
        } else {
            targetTextLine1 = targetTextList.getFirst();
            targetTextLine2 = targetTextList.get(1);
            refWidth = Math.max(Math.round(Math.max(font.width(distanceText), font.width(playerText)) * 0.8F), Math.max(font.width(targetTextLine1), font.width(targetTextLine2)) + ICON_HORIZONTAL_SPACE);
        }
        viewMatrix.transform(ndcPos);         // 变换到视图坐标系
        projectionMatrix.transform(ndcPos);   // 变换到投影坐标系
//        System.out.println(ndcPos);
        ndcPos.div(ndcPos.w);                 // 得到标准化设备坐标
        // 出现在最左侧：NDC z < 1 && x < -1 || z >= 1 && x > 0
        // 出现在最右侧：NDC z < 1 && x > 1 || z >= 1 && x <= 0
        // 出现在最下侧：NDC z < 1 && y < -1 || z >= 1 && y > 0
        // 出现在最上侧：NDC z < 1 && y > 1 || z >= 1 && y <= 0
        // 优先渲染左右箭头，再渲染上下箭头
    }

    public void render(GuiGraphics guiGraphics, Font font, int maxWidth, int maxHeight) {
        if (maxWidth <= refWidth + 2 * FRAME_PROTECT || maxHeight <= LABEL_HEIGHT + 2 * FRAME_PROTECT) {
            return;
        }
        int xScreen, yScreen, xOutFlag, yOutFlag;
        System.out.println(ndcPos);
//        if (ndcPos.z < 1) {
            if (ndcPos.x < -1) {
                xScreen = refWidth / 2 + FRAME_PROTECT;
                xOutFlag = -1;
            } else if (ndcPos.x <= 1) {
                xScreen = Mth.clamp((int) ((ndcPos.x + 1.0F) * 0.5F * maxWidth), refWidth / 2 + FRAME_PROTECT, maxWidth - (refWidth / 2 + FRAME_PROTECT));
                xOutFlag = 0;
            } else {
                xScreen = maxWidth - (refWidth / 2 + FRAME_PROTECT);
                xOutFlag = 1;
            }
            if (ndcPos.y < -1) {
                yScreen = maxHeight - (LABEL_HEIGHT / 2 + FRAME_PROTECT);
                yOutFlag = 1;
            } else if (ndcPos.y <= 1) {
                yScreen = Mth.clamp((int) ((1.0F - ndcPos.y) * 0.5F * maxHeight), LABEL_HEIGHT / 2 + FRAME_PROTECT, maxHeight - (LABEL_HEIGHT / 2 + FRAME_PROTECT));
                yOutFlag = 0;
            } else {
                yScreen = LABEL_HEIGHT / 2 + FRAME_PROTECT;
                yOutFlag = -1;
            }
//        } else {
//            if (ndcPos.x > 0) {
//                xScreen = refWidth / 2 + FRAME_PROTECT;
//                xOutFlag = -1;
//            } else {
//                xScreen = maxWidth - (refWidth / 2 + FRAME_PROTECT);
//                xOutFlag = 1;
//            }
//            if (ndcPos.y > 0) {
//                yScreen = maxHeight - (LABEL_HEIGHT / 2 + FRAME_PROTECT);
//                yOutFlag = 1;
//            } else {
//                yScreen = LABEL_HEIGHT / 2 + FRAME_PROTECT;
//                yOutFlag = -1;
//            }
//        }

        guiGraphics.fill(xScreen - refWidth / 2 - 2, yScreen - 11, xScreen + refWidth / 2 + 2, yScreen + 11, 0x40000000);
        guiGraphics.vLine(xScreen - refWidth / 2 - 3, yScreen - 12, yScreen + 11, 0xffc1bd93);
        guiGraphics.vLine(xScreen + refWidth / 2 + 2, yScreen - 12, yScreen + 11, 0xffc1bd93);
        guiGraphics.hLine(xScreen - refWidth / 2 - 3, xScreen - refWidth / 2 - 1, yScreen - 12, 0xffc1bd93);
        guiGraphics.hLine(xScreen - refWidth / 2 - 3, xScreen - refWidth / 2 - 1, yScreen + 11, 0xffc1bd93);
        guiGraphics.hLine(xScreen + refWidth / 2, xScreen + refWidth / 2 + 2, yScreen - 12, 0xffc1bd93);
        guiGraphics.hLine(xScreen + refWidth / 2, xScreen + refWidth / 2 + 2, yScreen + 11, 0xffc1bd93);
        if (blockItemStack != null) {
            guiGraphics.renderItem(blockItemStack, xScreen - refWidth / 2, yScreen - 8);
        } else {
            // todo
            guiGraphics.blit(entityIconLocation, xScreen - refWidth / 2, yScreen - 8, 0, 0, 16, 16, 16, 16);
        }
        int targetTextCenterX = xScreen + (refWidth - ICON_HORIZONTAL_SPACE) / 2 - (refWidth / 2 - ICON_HORIZONTAL_SPACE);
        if (targetTextLine2 == null) {
            guiGraphics.drawString(font, targetTextLine1, targetTextCenterX - font.width(targetTextLine1) / 2, yScreen - 4, 0xffc1bd93, false);
        } else {
            guiGraphics.drawString(font, targetTextLine1, targetTextCenterX - font.width(targetTextLine1) / 2, yScreen - 9, 0xffc1bd93, false);
            guiGraphics.drawString(font, targetTextLine2, targetTextCenterX - font.width(targetTextLine2) / 2, yScreen, 0xffc1bd93, false);
        }
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.scale(0.8F, 0.8F, 0.8F);
        xScreen = Math.round(xScreen * 1.25F);
        yScreen = Math.round(yScreen * 1.25F);
        guiGraphics.drawCenteredString(font, (Component) playerText, xScreen, yScreen - 24, 0xffc1bd93);
        guiGraphics.drawCenteredString(font, (Component) distanceText, xScreen, yScreen + 17, 0xffc1bd93);
        poseStack.popPose();
    }
}
