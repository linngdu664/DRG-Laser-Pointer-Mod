package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.item.component.LaserDistance;
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
            var distanceComponent = DataComponentRegister.LASER_DISTANCE.get();
            if (mainHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                mainHandItemStack.set(distanceComponent, new LaserDistance(payload.distance()));
            }
            if (offHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                offHandItemStack.set(distanceComponent, new LaserDistance(payload.distance()));
            }
        });
    }
}
