package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.item.component.LaserPointerData;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class LaserDistanceServerHandler {
    public static void handleData(final LaserDistancePayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ItemStack mainHandItemStack = player.getMainHandItem();
            ItemStack offHandItemStack = player.getOffhandItem();
            var componentType = DataComponentRegister.LASER_POINTER_DATA.get();
            if (payload.isOffHand() && offHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                LaserPointerData data = offHandItemStack.get(componentType);
                offHandItemStack.set(componentType, new LaserPointerData(payload.distance(), data.colorId()));
            } else if (!payload.isOffHand() && mainHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                LaserPointerData data = mainHandItemStack.get(componentType);
                mainHandItemStack.set(componentType, new LaserPointerData(payload.distance(), data.colorId()));
            }
        });
    }
}
