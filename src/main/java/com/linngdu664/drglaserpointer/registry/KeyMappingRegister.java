package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class KeyMappingRegister {
    public static final KeyMapping.Category LASER_POINTER_CATEGORY = new KeyMapping.Category(Main.makeMyIdentifier("laserpointer"));
    public static final KeyMapping SWITCH_TO_LASER_POINTER = new KeyMapping("key.laserpointer.switch_to_laser_pointer", GLFW.GLFW_KEY_Z, LASER_POINTER_CATEGORY);

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.registerCategory(LASER_POINTER_CATEGORY);
        event.register(SWITCH_TO_LASER_POINTER);
    }
}
