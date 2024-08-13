package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SoundRegister {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Main.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> LASER_OFF = registerHelper("laser_off");
    public static final DeferredHolder<SoundEvent, SoundEvent> LASER_ON = registerHelper("laser_on");
    public static final DeferredHolder<SoundEvent, SoundEvent> LASER_MAKE = registerHelper("laser_make");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSHROOM1 = registerHelper("mushroom1");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSHROOM2 = registerHelper("mushroom2");
    public static final DeferredHolder<SoundEvent, SoundEvent> WERE_RICH = registerHelper("were_rich");

    public static DeferredHolder<SoundEvent, SoundEvent> registerHelper(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Main.MODID, name)));
    }
}
