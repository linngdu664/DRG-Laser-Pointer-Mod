package com.linngdu664.drglaserpointer.item;

import com.linngdu664.drglaserpointer.item.component.LaserData;
import com.linngdu664.drglaserpointer.network.LaserPickBlockPayload;
import com.linngdu664.drglaserpointer.network.LaserPickEntityPayload;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.util.LaserPointerHitHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LaserPointerItem extends Item {
    public LaserPointerItem() {
        super(new Properties()
                .stacksTo(1)
                .component(DataComponentRegister.LASER_DATA, LaserData.EMPTY)
                .component(DataComponentRegister.AUDIO_COOLDOWN, 0));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide && (pUsedHand == InteractionHand.MAIN_HAND || !pPlayer.getMainHandItem().is(ItemRegister.LASER_POINTER))) {
            HitResult hitResult = LaserPointerHitHelper.getInstance().getHitResult();
            byte color = itemStack.getOrDefault(DataComponentRegister.LASER_DATA, LaserData.EMPTY).colorId();
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                PacketDistributor.sendToServer(new LaserPickBlockPayload(blockHitResult.getLocation(), blockHitResult.getBlockPos(), color, itemStack.getOrDefault(DataComponentRegister.AUDIO_COOLDOWN, 0) == 0));
            } else if (hitResult.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                PacketDistributor.sendToServer(new LaserPickEntityPayload(entityHitResult.getLocation(), entityHitResult.getEntity().getId(), color));
            }
        }
        return InteractionResultHolder.pass(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        int cd = stack.getOrDefault(DataComponentRegister.AUDIO_COOLDOWN, 0);
        if (cd > 0) {
            stack.set(DataComponentRegister.AUDIO_COOLDOWN, cd - 1);
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !oldStack.getItem().equals(newStack.getItem());
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                return HumanoidModel.ArmPose.valueOf("DRGLASERPOINTER_LASER_POINTER");
            }
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("laser_pointer.tooltip").withStyle(ChatFormatting.DARK_GRAY));
    }
}
