package com.linngdu664.drglaserpointer.util;

import com.linngdu664.drglaserpointer.Main;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.List;

/**
 * <b>Important: This class will be deleted later!!!</b>
 */
public class LabelRenderHelper {
    public static final int MAX_TARGET_NAME_WIDTH = 108;
    public static final int MAX_PLAYER_NAME_WIDTH = 153;
    public static final int LABEL_HEIGHT = 36;
    public static final int FRAME_PROTECT = 10;
    public static final int ICON_WIDTH_WITH_MARGIN = 18;
    public static final int MIN_REF_WIDTH = 40;

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
        Entity entity = level.getEntity(labelEntity.getTargetEntityId());
        List<FormattedCharSequence> targetTextList;
        float distance;
        if (entity != null) {
            if (entity instanceof LivingEntity) {
                ResourceLocation resourceLocation = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                blockItemStack = null;
                if (!(entity instanceof Player) && resourceLocation.getNamespace().equals("minecraft")) {
                    entityIconLocation = ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/gui/face/" + resourceLocation.getPath() + "_face.png");
                } else {
                    entityIconLocation = ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/gui/face/unknown.png");
                }
                targetTextList = font.split(entity.getName(), MAX_TARGET_NAME_WIDTH);
                AABB aabb = entity.getBoundingBox();
                Vec3 vec3 = entity.getPosition(partialTick);
                ndcPos = new Vector4f((float) vec3.x, (float) (vec3.y + aabb.maxY - aabb.minY + 0.5), (float) vec3.z, 1.0F);
                distance = entity.distanceTo(player);
            } else {
                if (entity instanceof ItemEntity itemEntity) {
                    blockItemStack = itemEntity.getItem();
                    entityIconLocation = null;
                    targetTextList = font.split(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.item_entity_name", null, new Object[]{entity.getName()})), MAX_TARGET_NAME_WIDTH);
                } else {
                    blockItemStack = null;
                    entityIconLocation = ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/gui/face/unknown.png");
                    targetTextList = font.split(entity.getName(), MAX_TARGET_NAME_WIDTH);
                }
                ndcPos = new Vector4f((float) labelEntity.getX(), (float) labelEntity.getY() + 1.0F, (float) labelEntity.getZ(), 1.0F);
                distance = labelEntity.distanceTo(player);
            }
        } else {
            Block block = labelEntity.getTargetBlockState().getBlock();
            blockItemStack = block.asItem().getDefaultInstance();
            entityIconLocation = null;
            targetTextList = font.split(block.getName(), MAX_TARGET_NAME_WIDTH);
            ndcPos = new Vector4f((float) labelEntity.getX(), (float) labelEntity.getY() + 1.0F, (float) labelEntity.getZ(), 1.0F);
            distance = labelEntity.distanceTo(player);
        }
        distanceText = font.ellipsize(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", distance)})), MAX_PLAYER_NAME_WIDTH);
        int mainWidth;
        if (targetTextList.size() == 1) {
            targetTextLine1 = targetTextList.getFirst();
            targetTextLine2 = null;
            mainWidth = font.width(targetTextLine1) + ICON_WIDTH_WITH_MARGIN;
        } else {
            targetTextLine1 = targetTextList.getFirst();
            targetTextLine2 = targetTextList.get(1);
            mainWidth = Math.max(font.width(targetTextLine1), font.width(targetTextLine2)) + ICON_WIDTH_WITH_MARGIN;
        }
        refWidth = Math.max(MIN_REF_WIDTH, Math.max(Math.round(Math.max(font.width(distanceText), font.width(playerText)) * 0.8F), mainWidth));
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
        // todo: fix behind bug
        int xScreen, yScreen, xOutFlag, yOutFlag;
//        System.out.println(ndcPos);
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
        if (blockItemStack == null) {
            guiGraphics.blit(entityIconLocation, xScreen - refWidth / 2, yScreen - 8, 0, 0, 16, 16, 16, 16);
        } else {
            guiGraphics.renderItem(blockItemStack, xScreen - refWidth / 2, yScreen - 8);
        }
        int targetTextCenterX = xScreen + (refWidth - ICON_WIDTH_WITH_MARGIN) / 2 - (refWidth / 2 - ICON_WIDTH_WITH_MARGIN);
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
