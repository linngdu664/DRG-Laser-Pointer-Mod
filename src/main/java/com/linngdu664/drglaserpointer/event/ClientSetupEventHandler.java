package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEventHandler {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ItemRegister.LASER_POINTER.get(), Main.makeResLoc("light_color"), (itemStack, level, livingEntity, num) -> itemStack.getOrDefault(DataComponentRegister.LASER_COLOR, (byte) 0));
            ItemProperties.register(ItemRegister.LASER_POINTER.get(), Main.makeResLoc("screen_color"), (itemStack, level, livingEntity, num) -> {
                if (livingEntity == null) {
                    return 0;
                }
                return itemStack.getOrDefault(DataComponentRegister.SCREEN_COLOR, (byte) 0);
            });
        });
    }
}
