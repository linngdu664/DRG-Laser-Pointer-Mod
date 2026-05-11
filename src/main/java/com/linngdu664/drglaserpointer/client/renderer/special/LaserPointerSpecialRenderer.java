package com.linngdu664.drglaserpointer.client.renderer.special;

import com.linngdu664.drglaserpointer.client.model.LaserPointerModel;
import com.linngdu664.drglaserpointer.client.util.LaserPointerHitHelper;
import com.linngdu664.drglaserpointer.item.LaserPointerItem;
import com.linngdu664.drglaserpointer.registry.DataComponentRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public record LaserPointerSpecialRenderer(LaserPointerModel model) implements SpecialModelRenderer<ItemStack> {
//    private static final Quaternionf Y_AXIS_180 = new Quaternionf(new AxisAngle4f(Mth.PI, 0, 1, 0));
    private static final Quaternionf X_AXIS_180 = new Quaternionf(new AxisAngle4f(Mth.PI, 1, 0, 0));

    @Override
    public void submit(ItemStack itemStack, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        ClientLevel level = mc.level;
        // 这里就是用 == 来判断跟自己手上拿的是同一个对象
        boolean shouldShowInfo = level != null && player != null && (player.getMainHandItem() == itemStack || player.getOffhandItem() == itemStack);
        int bulbColor = LaserPointerItem.getLaserColorARGB(itemStack.getOrDefault(DataComponentRegistry.LASER_COLOR, (byte) 0));
        int screenColor = LaserPointerItem.getScreenColorARGB(shouldShowInfo ? itemStack.getOrDefault(DataComponentRegistry.SCREEN_COLOR, (byte) 0) : (byte) 0);
        submitNodeCollector.submitModelPart(model.getBulb(), poseStack, RenderTypes.entityCutout(LaserPointerModel.TEXTURE_LOCATION), 15728880, OverlayTexture.NO_OVERLAY, null, bulbColor, null);
        submitNodeCollector.submitModelPart(model.getBody(), poseStack, RenderTypes.entityCutout(LaserPointerModel.TEXTURE_LOCATION), lightCoords, OverlayTexture.NO_OVERLAY, null, 0xffffffff, null);
        submitNodeCollector.submitModelPart(model.getScreen(), poseStack, RenderTypes.entityCutout(LaserPointerModel.TEXTURE_LOCATION), 15728880, OverlayTexture.NO_OVERLAY, null, screenColor, null);

        if (shouldShowInfo) {
            HitResult hitResult = LaserPointerHitHelper.getInstance().getHitResult();
            MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                ItemStack hitItemStack = level.getBlockState(blockHitResult.getBlockPos()).getBlock().asItem().getDefaultInstance();
                ItemModelResolver resolver = mc.getItemModelResolver();
                ItemStackRenderState state = new ItemStackRenderState();
                resolver.updateForTopItem(state, hitItemStack, ItemDisplayContext.GUI, level, null, 0);

                poseStack.pushPose();
                poseStack.translate(-2f, -12.34f, -1.25f);
                poseStack.scale(0.75f, 0.75f, 0.75f);
                poseStack.mulPose(X_AXIS_180);
//                poseStack.mulPose(new Quaternionf(new AxisAngle4f(-0.4f, 0f, 1f, 0f)));
//                poseStack.mulPose(new Quaternionf(new AxisAngle4f(-0.15f, 1f, 0f, 0f)));
                state.submit(poseStack, submitNodeCollector, 15728880, OverlayTexture.NO_OVERLAY, 0);
                poseStack.popPose();

                poseStack.pushPose();
                poseStack.translate(2f, -12.34f, -1.25f);
                poseStack.scale(0.75f, 0.75f, 0.75f);
                poseStack.mulPose(X_AXIS_180);
//                poseStack.mulPose(new Quaternionf(new AxisAngle4f(-0.58f, 0f, 1f, 0f)));
//                poseStack.mulPose(new Quaternionf(new AxisAngle4f(-0.15f, 1f, 0f, 0f)));
                state.submit(poseStack, submitNodeCollector, 15728880, OverlayTexture.NO_OVERLAY, 0);
                poseStack.popPose();
            }

            poseStack.pushPose();
            poseStack.translate(0f, -11.5f, -1.07f);
            poseStack.scale(0.058f, 0.058f, 0.058f);
            Matrix4f matrix = poseStack.last().pose();
            Font font = mc.font;
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
                } else if (hitResult.getType() == HitResult.Type.ENTITY) {
                    EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                    component = entityHitResult.getEntity().getName();
                } else {
                    component = Component.literal("unknown");
                }
                var splitList = font.split(component, 108); // 18 * 6
                if (splitList.size() == 1) {
                    font.drawInBatch(splitList.getFirst(), (float) (-font.width(splitList.getFirst()) / 2), 0, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                } else if (splitList.size() >= 2) {
                    font.drawInBatch(splitList.getFirst(), (float) (-font.width(splitList.getFirst()) / 2), -4.5f, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                    font.drawInBatch(splitList.get(1), (float) (-font.width(splitList.get(1)) / 2), 4.5f, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                    if (splitList.size() >= 3) {
                        font.drawInBatch(splitList.get(2), (float) (-font.width(splitList.get(2)) / 2), 13.5f, 0xffffffff, false, matrix, bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                    }
                }
                poseStack.popPose();
                poseStack.pushPose();
                poseStack.translate(0f, -10f, -1.07f);
                poseStack.scale(0.0464f, 0.0464f, 0.0464f);
                component = MutableComponent.create(new TranslatableContents("tip.drglaserpointer.mark_key", null, new Object[]{mc.options.keyUse.getKey().getDisplayName()}));
                font.drawInBatch(component, (float) (-font.width(component) / 2), 0, 0xffffff00, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, 240);
                poseStack.popPose();
            }
        }
    }

    @Override
    public void getExtents(@NonNull Consumer<Vector3fc> consumer) {

    }

    @Override
    public ItemStack extractArgument(@NonNull ItemStack itemStack) {
        return itemStack;
    }

    public static class Unbaked implements SpecialModelRenderer.Unbaked<ItemStack> {
        public static final MapCodec<LaserPointerSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(Unbaked::new);

        @Override
        public @Nullable SpecialModelRenderer<ItemStack> bake(BakingContext bakingContext) {
            LaserPointerModel model = new LaserPointerModel(bakingContext.entityModelSet().bakeLayer(LaserPointerModel.LAYER_LOCATION));
            return new LaserPointerSpecialRenderer(model);
        }

        @Override
        public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked<ItemStack>> type() {
            return MAP_CODEC;
        }
    }
}
