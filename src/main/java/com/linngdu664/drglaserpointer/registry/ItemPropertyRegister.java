package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.client.renderer.item.properties.numeric.LightColor;
import com.linngdu664.drglaserpointer.client.renderer.item.properties.numeric.ScreenColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;

@EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class ItemPropertyRegister {
    @SubscribeEvent
    public static void registerRangeSelectItemModelProperty(RegisterRangeSelectItemModelPropertyEvent event) {
        event.register(Main.makeMyIdentifier("light_color"), LightColor.MAP_CODEC);
        event.register(Main.makeMyIdentifier("screen_color"), ScreenColor.MAP_CODEC);
    }
}
