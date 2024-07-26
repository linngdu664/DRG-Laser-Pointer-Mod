package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.component.LaserDistance;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegister {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Main.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<LaserDistance>> LASER_DISTANCE =
            DATA_COMPONENTS.registerComponentType(
                    "laser_distance",
                    builder -> builder.persistent(LaserDistance.CODEC).networkSynchronized(LaserDistance.STREAM_CODEC)
            );
}
