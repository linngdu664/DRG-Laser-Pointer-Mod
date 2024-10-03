package com.linngdu664.drglaserpointer.misc;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabBuilder {
    @SubscribeEvent
    public static void onBuildCreativeModeTabContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(ItemRegister.LASER_POINTER.get());
        }
    }
}
