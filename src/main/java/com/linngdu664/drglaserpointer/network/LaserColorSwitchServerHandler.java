package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.item.component.LaserData;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class LaserColorSwitchServerHandler {
    public static void handleData(final LaserColorSwitchPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ItemStack mainHandItemStack = player.getMainHandItem();
            if (mainHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                var data = mainHandItemStack.get(DataComponentRegister.LASER_DATA.get());
                int colorId = data.colorId();
                if (payload.isIncrease()) {
                    colorId++;
                    if (colorId > 3) {
                        colorId = 0;
                    }
                } else {
                    colorId--;
                    if (colorId < 0) {
                        colorId = 3;
                    }
                }
                mainHandItemStack.set(DataComponentRegister.LASER_DATA.get(), new LaserData(data.distance(), (byte) colorId));
            }
        });
    }
}
