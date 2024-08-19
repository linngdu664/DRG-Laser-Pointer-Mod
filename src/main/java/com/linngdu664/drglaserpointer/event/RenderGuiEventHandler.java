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
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
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
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector4f;

import java.util.List;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderGuiEventHandler {
    public static final int MAX_TARGET_NAME_WIDTH = 108;
    public static final int MAX_PLAYER_NAME_WIDTH = 153;
    public static final int LABEL_HEIGHT = 36;
    public static final int FRAME_PROTECT = 10;
    public static final int ICON_WIDTH_WITH_MARGIN = 18;
    public static final int MIN_REF_WIDTH = 40;

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui) {
            return;
        }
        GameRenderer gameRenderer = mc.gameRenderer;
        Camera camera = gameRenderer.getMainCamera();
        Vec3 cameraPos = camera.getPosition();
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.rotation(camera.rotation().conjugate(new Quaternionf()));
        viewMatrix.translate((float) -cameraPos.x, (float) -cameraPos.y, (float) -cameraPos.z);
        double d0 = gameRenderer.getFov(camera, event.getPartialTick().getGameTimeDeltaPartialTick(true), true);
        Matrix4f projectionMatrix = gameRenderer.getProjectionMatrix(d0);
        GuiGraphics guiGraphics = event.getGuiGraphics();
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        int guiWidth, guiHeight;
        Window window = mc.getWindow();
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
        List<LaserPointerLabelEntity> list = level.getEntitiesOfClass(LaserPointerLabelEntity.class, player.getBoundingBox().inflate(96), p -> p.distanceToSqr(player) <= 96 * 96 && p.getOwnerUUID() != null);
        for (LaserPointerLabelEntity labelEntity : list) {
            FormattedText playerText = font.ellipsize(level.getPlayerByUUID(labelEntity.getOwnerUUID()).getName(), MAX_PLAYER_NAME_WIDTH);
            FormattedText distanceText;
            List<FormattedCharSequence> targetTextList;
            ItemStack blockItemStack = null;
            ResourceLocation entityIconLocation = null;
            Vector4f ndcPos;
            Entity entity = level.getEntity(labelEntity.getTargetEntityId());
            if (entity != null) {
                if (entity instanceof LivingEntity) {
                    ResourceLocation resourceLocation = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
                    if (resourceLocation.getNamespace().equals("minecraft") && !(entity instanceof Player) && !(entity instanceof ArmorStand)) {
                        entityIconLocation = ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/gui/face/" + resourceLocation.getPath() + "_face.png");
                    } else {
                        entityIconLocation = ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/gui/face/unknown.png");
                    }
                    targetTextList = font.split(entity.getName(), MAX_TARGET_NAME_WIDTH);
                    distanceText = font.ellipsize(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", entity.distanceTo(player))})), MAX_PLAYER_NAME_WIDTH);
                    AABB aabb = entity.getBoundingBox();
                    Vec3 vec3 = entity.getPosition(event.getPartialTick().getGameTimeDeltaPartialTick(true));
                    ndcPos = new Vector4f((float) vec3.x, (float) (vec3.y + aabb.maxY - aabb.minY + 0.5), (float) vec3.z, 1.0F);
                } else {
                    if (entity instanceof ItemEntity itemEntity) {
                        blockItemStack = itemEntity.getItem();
                        targetTextList = font.split(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.item_entity_name", null, new Object[]{entity.getName()})), MAX_TARGET_NAME_WIDTH);
                    } else {
                        entityIconLocation = ResourceLocation.fromNamespaceAndPath(Main.MODID, "textures/gui/face/unknown.png");
                        targetTextList = font.split(entity.getName(), MAX_TARGET_NAME_WIDTH);
                    }
                    distanceText = font.ellipsize(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", labelEntity.distanceTo(player))})), MAX_PLAYER_NAME_WIDTH);
                    ndcPos = new Vector4f((float) labelEntity.getX(), (float) labelEntity.getY() + 1.0F, (float) labelEntity.getZ(), 1.0F);
                }
            } else {
                Block block = labelEntity.getTargetBlockState().getBlock();
                blockItemStack = block.asItem().getDefaultInstance();
                targetTextList = font.split(block.getName(), MAX_TARGET_NAME_WIDTH);
                distanceText = font.ellipsize(MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", labelEntity.distanceTo(player))})), MAX_PLAYER_NAME_WIDTH);
                ndcPos = new Vector4f((float) labelEntity.getX(), (float) labelEntity.getY() + 1.0F, (float) labelEntity.getZ(), 1.0F);
            }
            int mainWidth;
            FormattedCharSequence targetTextLine1, targetTextLine2 = null;
            if (targetTextList.size() == 1) {
                targetTextLine1 = targetTextList.getFirst();
                mainWidth = font.width(targetTextLine1) + ICON_WIDTH_WITH_MARGIN;
            } else {
                targetTextLine1 = targetTextList.getFirst();
                targetTextLine2 = targetTextList.get(1);
                mainWidth = Math.max(font.width(targetTextLine1), font.width(targetTextLine2)) + ICON_WIDTH_WITH_MARGIN;
            }
            int refWidth = Math.max(MIN_REF_WIDTH, Math.max(Math.round(Math.max(font.width(distanceText), font.width(playerText)) * 0.8F), mainWidth));
            viewMatrix.transform(ndcPos);         // 变换到视图坐标系
            projectionMatrix.transform(ndcPos);   // 变换到投影坐标系
            ndcPos.div(ndcPos.w);                 // 得到标准化设备坐标

            // todo: fix behind bug
            int xScreen, yScreen, xOutFlag, yOutFlag;
//          System.out.println(ndcPos);
            if (ndcPos.x < -1) {
                xScreen = refWidth / 2 + FRAME_PROTECT;
                xOutFlag = -1;
            } else if (ndcPos.x <= 1) {
                xScreen = Mth.clamp((int) ((ndcPos.x + 1.0F) * 0.5F * guiWidth), refWidth / 2 + FRAME_PROTECT, guiWidth - (refWidth / 2 + FRAME_PROTECT));
                xOutFlag = 0;
            } else {
                xScreen = guiWidth - (refWidth / 2 + FRAME_PROTECT);
                xOutFlag = 1;
            }
            if (ndcPos.y < -1) {
                yScreen = guiHeight - (LABEL_HEIGHT / 2 + FRAME_PROTECT);
                yOutFlag = 1;
            } else if (ndcPos.y <= 1) {
                yScreen = Mth.clamp((int) ((1.0F - ndcPos.y) * 0.5F * guiHeight), LABEL_HEIGHT / 2 + FRAME_PROTECT, guiHeight - (LABEL_HEIGHT / 2 + FRAME_PROTECT));
                yOutFlag = 0;
            } else {
                yScreen = LABEL_HEIGHT / 2 + FRAME_PROTECT;
                yOutFlag = -1;
            }
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
