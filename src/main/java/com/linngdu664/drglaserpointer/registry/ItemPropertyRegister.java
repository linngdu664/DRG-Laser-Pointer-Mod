package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemPropertyRegister {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ItemRegister.LASER_POINTER.get(), Main.makeResLoc("light_color"), (itemStack, level, livingEntity, num) -> itemStack.getOrCreateTag().getByte("LaserColor"));
            ItemProperties.register(ItemRegister.LASER_POINTER.get(), Main.makeResLoc("screen_color"), (itemStack, level, livingEntity, num) -> {
                if (livingEntity == null) {
                    return 0;
                }
                return itemStack.getOrCreateTag().getByte("ScreenColor");
            });
        });
    }
}
