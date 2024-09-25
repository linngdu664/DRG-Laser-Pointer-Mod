package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegister {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MODID);

    public static final RegistryObject<SoundEvent> LASER_OFF = registerHelper("laser_off");
    public static final RegistryObject<SoundEvent> LASER_ON = registerHelper("laser_on");
    public static final RegistryObject<SoundEvent> LASER_MAKE = registerHelper("laser_make");
    public static final RegistryObject<SoundEvent> MUSHROOM1 = registerHelper("mushroom1");
    public static final RegistryObject<SoundEvent> MUSHROOM2 = registerHelper("mushroom2");
    public static final RegistryObject<SoundEvent> WERE_RICH = registerHelper("were_rich");

    public static RegistryObject<SoundEvent> registerHelper(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Main.makeResLoc(name)));
    }
}
