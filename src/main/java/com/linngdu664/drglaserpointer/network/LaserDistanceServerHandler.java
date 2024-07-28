package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.item.component.LaserData;
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
            var component = DataComponentRegister.LASER_DATA.get();
            if (mainHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                byte colorId = mainHandItemStack.get(component).colorId();
                mainHandItemStack.set(component, new LaserData(payload.distance(), colorId));
            }
            if (offHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                byte colorId = offHandItemStack.get(component).colorId();
                offHandItemStack.set(component, new LaserData(payload.distance(), colorId));
            }
        });
    }
}
