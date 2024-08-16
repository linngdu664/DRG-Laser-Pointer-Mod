package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import com.linngdu664.drglaserpointer.util.LabelRenderHelper;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.util.List;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderGuiEventHandler {
    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Window window = mc.getWindow();
        if (!mc.options.hideGui) {
            GameRenderer gameRenderer = mc.gameRenderer;
            Camera camera = gameRenderer.getMainCamera();
            Vec3 vec3 = camera.getPosition();
            Matrix4f viewMatrix = new Matrix4f();
            viewMatrix.rotation(camera.rotation().conjugate(new Quaternionf()));
            viewMatrix.translate((float) -vec3.x, (float) -vec3.y, (float) -vec3.z);
            double d0 = gameRenderer.getFov(camera, event.getPartialTick().getGameTimeDeltaPartialTick(true), true);
            Matrix4f projectionMatrix = gameRenderer.getProjectionMatrix(d0);
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
            List<LaserPointerLabelEntity> list = mc.level.getEntitiesOfClass(LaserPointerLabelEntity.class, player.getBoundingBox().inflate(64));
            for (LaserPointerLabelEntity entity : list) {
                LabelRenderHelper helper = new LabelRenderHelper(entity, player, font, event.getPartialTick().getGameTimeDeltaPartialTick(true), viewMatrix, projectionMatrix);
                helper.render(guiGraphics, font, guiWidth, guiHeight);
            }
            poseStack.popPose();


//            poseStack.pushPose();
//            poseStack.scale(0.75F, 0.75F, 0.75F);
            // 渲染分3步（遍历列表三次）
            // 1. 渲染方块/实体名和图标
            // 2. 渲染玩家名和距离
            // 3. 渲染边框

            /*
            Vector4f point = new Vector4f(10, 10, 10, 1);       // 空间中的一个点
            viewMatrix.transform(point);        // 变换到视图坐标系
            projectionMatrix.transform(point);  // 变换到投影坐标系
            point.div(point.w);                 // 得到标准化设备坐标

            // 我们不需要深度测试，只需要判断在摄像机的前面还是后面就行
            if (point.z < 1) {
                float xScreen = (int) ((point.x + 1.0F) * 0.5F * event.getGuiGraphics().guiWidth());
                float yScreen = (int) ((1.0F - point.y) * 0.5F * event.getGuiGraphics().guiHeight());      // 变换到屏幕坐标系
                guiGraphics.drawString(mc.font, "hello", xScreen, yScreen, 0xc0ffffff, true);
            }*/
//            font.split("asdf", 5);
            /*
            level.getEntitiesOfClass(LaserPointerLabelEntity.class, player.getBoundingBox().inflate(64))
                    .stream()
                    .filter(label -> label.getOwnerUUID() != null)
                    .forEach(label -> {
                        // 玩家名\n实体/方块名\n距离
                        Component playerNameComponent = level.getPlayerByUUID(label.getOwnerUUID()).getName();
                        Vector4f labelPos;
                        float distance;
                        Component targetNameComponent;
                        Entity entity = level.getEntity(label.getTargetEntityId());
                        if (entity != null) {
                            if (entity instanceof LivingEntity livingEntity) {
                                labelPos = new Vector4f((float) livingEntity.getX(), (float) livingEntity.getEyeY(), (float) entity.getZ(), 1);
                                distance = livingEntity.distanceTo(player);
                            } else {
                                labelPos = new Vector4f((float) label.getX(), (float) label.getY(), (float) label.getZ(), 1);
                                distance = label.distanceTo(player);
                            }
                            targetNameComponent = entity.getName();
                        } else {
                            labelPos = new Vector4f((float) label.getX(), (float) label.getY(), (float) label.getZ(), 1);
                            distance = label.distanceTo(player);
                            targetNameComponent = level.getBlockState(label.getTargetBlockPos()).getBlock().getName();
                        }
                        Component distanceComponent = MutableComponent.create(new TranslatableContents("tip.drglaserpointer.distance", null, new Object[]{String.format("%.1f", distance)}));
                        viewMatrix.transform(labelPos);         // 变换到视图坐标系
                        projectionMatrix.transform(labelPos);   // 变换到投影坐标系
                        labelPos.div(labelPos.w);               // 得到标准化设备坐标
                        // 出现在最左侧：NDC z < 1 && x < -1 || z >= 1 && x > 0
                        // 出现在最右侧：NDC z < 1 && x > 1 || z >= 1 && x <= 0
                        // 出现在最下侧：NDC z < 1 && y < -1 || z >= 1 && y > 0
                        // 出现在最上侧：NDC z < 1 && y > 1 || z >= 1 && y <= 0

//                        font.width(playerNameComponent);
//                        font.width(targetNameComponent);
//                        font.width(distanceComponent);
                        if (entity != null) {

                        } else {
                            guiGraphics.renderItem(level.getBlockState(label.getTargetBlockPos()).getBlock().asItem().getDefaultInstance(), 10, 10);
                        }
                    });*/


//            PoseStack poseStack = guiGraphics.pose();
//            poseStack.pushPose();
//            poseStack.scale(0.5F, 0.5F, 0.5F);
//            guiGraphics.drawString(font, "悬挂式深色橡木告示牌我测", 28, 10, 0xffcccc66, true);
//            guiGraphics.drawString(font, "悬挂式深色橡木告示牌我测", 28, 19, 0xffcccc66, true);
//            guiGraphics.drawString(font, "Dark Oak Hanging Sign", 28, 14 + 9, 0xffcccc66, true);
//            poseStack.pushPose();
//            poseStack.scale(0.8F, 0.8F, 0.8F);
//            guiGraphics.drawString(font, "我测你们码我测你们码我测你们码我测你们", 13, 14 + 27, 0xffcccc66, true);
//            poseStack.popPose();
//            poseStack.popPose();
//            guiGraphics.drawString(mc.font, "hello", 10, 10, 0x7fffffff, true);
//            System.out.println("gui width: " + guiGraphics.guiWidth() + " height: " + guiGraphics.guiHeight());
//            System.out.println("window width: " + mc.getWindow().getWidth() + " height: " + mc.getWindow().getHeight());
//            System.out.println("gui scale: " + mc.getWindow().getGuiScale());
            // guiscale <= 2 ok
            // >2 scale to 2
        }
    }
}
