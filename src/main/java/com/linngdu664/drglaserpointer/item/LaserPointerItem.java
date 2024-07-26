package com.linngdu664.drglaserpointer.item;

import com.linngdu664.drglaserpointer.item.component.LaserDistance;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LaserPointerItem extends Item {
    public LaserPointerItem() {
        super(new Properties()
                .stacksTo(1)
                .component(DataComponentRegister.LASER_DISTANCE, new LaserDistance(0))
                .component(DataComponents.BASE_COLOR, DyeColor.ORANGE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        return InteractionResultHolder.pass(itemStack);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !oldStack.getItem().equals(newStack.getItem());
    }
}
