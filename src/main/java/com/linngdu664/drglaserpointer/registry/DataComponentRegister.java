package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.component.LaserData;
import com.linngdu664.drglaserpointer.item.component.AudioCooldownData;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegister {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Main.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<LaserData>> LASER_DATA =
            DATA_COMPONENTS.registerComponentType(
                    "laser_data",
                    builder -> builder.persistent(LaserData.CODEC).networkSynchronized(LaserData.STREAM_CODEC)
            );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AudioCooldownData>> AUDIO_COOLDOWN_DATA =
            DATA_COMPONENTS.registerComponentType(
                    "audio_cooldown",
                    builder -> builder.persistent(AudioCooldownData.CODEC).networkSynchronized(AudioCooldownData.STREAM_CODEC)
            );
}
