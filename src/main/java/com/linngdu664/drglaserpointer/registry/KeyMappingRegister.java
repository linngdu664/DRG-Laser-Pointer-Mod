package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyMappingRegister {
    public static final KeyMapping SWITCH_TO_LASER_POINTER = new KeyMapping("key.laserpointer.switch_to_laser_pointer", GLFW.GLFW_KEY_Z, "key.categories.laserpointer");

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(SWITCH_TO_LASER_POINTER);
    }
}
