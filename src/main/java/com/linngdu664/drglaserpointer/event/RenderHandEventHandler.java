package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.client.util.LaserPointerHitHelper;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderHandEventHandler {
    public static Quaternionf Y_AXIS_180 = new Quaternionf(new AxisAngle4f(Mth.PI, 0, 1, 0));

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        if (event.getItemStack().is(ItemRegister.LASER_POINTER.get()) && event.getEquipProgress() == 0 && event.getSwingProgress() == 0) {
            Minecraft mc = Minecraft.getInstance();
            Level level = mc.level;
            Player player = mc.player;
            Font font = mc.font;
            PoseStack poseStack = event.getPoseStack();
            MultiBufferSource bufferSource = event.getMultiBufferSource();
            boolean isLeftHand = event.getHand() == InteractionHand.MAIN_HAND && player.getMainArm() == HumanoidArm.LEFT
                    || event.getHand() == InteractionHand.OFF_HAND && player.getMainArm() == HumanoidArm.RIGHT;
            HitResult hitResult = LaserPointerHitHelper.getInstance().getHitResult();
            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                ItemStack itemStack = level.getBlockState(blockHitResult.getBlockPos()).getBlock().asItem().getDefaultInstance();
                ItemRenderer itemRenderer = mc.getItemRenderer();
                poseStack.pushPose();
                poseStack.pushPose();
                poseStack.translate(isLeftHand ? -0.437f : 0.437f, -0.155f, -0.74f);
                poseStack.scale(0.05f, 0.05f, 0.05f);
                itemRenderer.renderStatic(itemStack, ItemDisplayContext.GUI, 15728880, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, level, 0);
                poseStack.popPose();
                poseStack.translate(isLeftHand ? -0.67f : 0.67f, -0.155f, -0.74f);
                poseStack.scale(0.05f, 0.05f, 0.05f);
                itemRenderer.renderStatic(itemStack, ItemDisplayContext.GUI, 15728880, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, level, 0);
                poseStack.popPose();
            }
            poseStack.pushPose();
            poseStack.translate(isLeftHand ? -0.56f : 0.56f, -0.205f, -0.75f);
            poseStack.scale(-0.0035f, -0.0035f, -0.0035f);
            poseStack.mulPose(Y_AXIS_180);
            Matrix4f matrix = poseStack.last().pose();
            Component component;
            if (hitResult == null || hitResult.getType() == HitResult.Type.MISS) {
                component = MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{"??.?"}));
                font.drawInBatch(component, (float) (-font.width(component) / 2), -25, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                poseStack.popPose();
            } else {
                component = MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", LaserPointerHitHelper.getInstance().getLaserDistance())}));
                font.drawInBatch(component, (float) (-font.width(component) / 2), -25, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                    component = level.getBlockState(blockHitResult.getBlockPos()).getBlock().getName();
                } else {
                    EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                    component = entityHitResult.getEntity().getName();
                }
                var splitList = font.split(component, 108);
                if (splitList.size() == 1) {
                    font.drawInBatch(splitList.get(0), (float) (-font.width(splitList.get(0)) / 2), 0, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                } else if (splitList.size() >= 2) {
                    font.drawInBatch(splitList.get(0), (float) (-font.width(splitList.get(0)) / 2), -5, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                    font.drawInBatch(splitList.get(1), (float) (-font.width(splitList.get(1)) / 2), 4, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                    if (splitList.size() >= 3) {
                        font.drawInBatch(splitList.get(2), (float) (-font.width(splitList.get(2)) / 2), 13, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                    }
                }
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(isLeftHand ? -0.56f : 0.56f, -0.29f, -0.75f);
                poseStack.scale(-0.0028f, -0.0028f, -0.0028f);
                poseStack.mulPose(Y_AXIS_180);
                component = MutableComponent.create(new TranslatableContents("tip.drglaserpointer.mark_key", null, new Object[]{mc.options.keyUse.getKey().getDisplayName()}));
                font.drawInBatch(component, (float) (-font.width(component) / 2), 0, 0xffffff00, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                poseStack.popPose();
            }
        }
    }
}
