package com.linngdu664.drglaserpointer.item;

import com.linngdu664.drglaserpointer.misc.ModTags;
import com.linngdu664.drglaserpointer.network.LaserPickBlockPayload;
import com.linngdu664.drglaserpointer.network.LaserPickEntityPayload;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.client.util.LaserPointerHitHelper;
import com.linngdu664.drglaserpointer.registry.KeyMappingRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
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
import net.minecraft.world.level.block.state.BlockState;
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
    int audioCooldown = 0;      // client only

    public LaserPointerItem() {
        super(new Properties()
                .stacksTo(1)
                .component(DataComponentRegister.LASER_COLOR, (byte) 0)
                .component(DataComponentRegister.SCREEN_COLOR, (byte) 0));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide && (pUsedHand == InteractionHand.MAIN_HAND || !pPlayer.getMainHandItem().is(ItemRegister.LASER_POINTER))) {
            HitResult hitResult = LaserPointerHitHelper.getInstance().getHitResult();
            byte color = itemStack.getOrDefault(DataComponentRegister.LASER_COLOR, (byte) 0);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                PacketDistributor.sendToServer(new LaserPickBlockPayload(blockHitResult.getLocation(), blockHitResult.getBlockPos(), color, audioCooldown == 0));
                BlockState blockState = pLevel.getBlockState(blockHitResult.getBlockPos());
                if (audioCooldown == 0 && (blockState.is(ModTags.Blocks.RICH_BLOCKS) || blockState.is(ModTags.Blocks.MUSHROOMS))) {
                    audioCooldown = 30;
                }
            } else if (hitResult.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                PacketDistributor.sendToServer(new LaserPickEntityPayload(entityHitResult.getLocation(), entityHitResult.getEntity().getId(), color));
            }
        }
        return InteractionResultHolder.pass(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (level.isClientSide) {
            if (!isSelected && slotId != 40) {
                stack.set(DataComponentRegister.SCREEN_COLOR, (byte) 0);
            }
            if (audioCooldown > 0) {
                audioCooldown--;
            }
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
        tooltipComponents.add(Component.translatable("laser_pointer.tooltip", Minecraft.getInstance().options.keyShift.getTranslatedKeyMessage()).withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("laser_pointer1.tooltip", KeyMappingRegister.SWITCH_TO_LASER_POINTER.getTranslatedKeyMessage()).withStyle(ChatFormatting.GRAY));
    }

    public static int getLaserColorARGB(byte laserColor) {
        return switch (laserColor) {
            case 1 -> 0xffff7864;   // red
            case 2 -> 0xffffbc4c;   // yellow
            case 3 -> 0xff78ff78;   // green
            default -> 0xff78e0ff;  // blue
        };
    }
}
