package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegister {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Main.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Byte>> LASER_COLOR =
            DATA_COMPONENTS.registerComponentType(
                    "laser_color",
                    builder -> builder.persistent(Codec.BYTE).networkSynchronized(ByteBufCodecs.BYTE)
            );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Byte>> SCREEN_COLOR =
            DATA_COMPONENTS.registerComponentType(
                    "screen_color",
                    builder -> builder.persistent(Codec.BYTE).networkSynchronized(ByteBufCodecs.BYTE)
            );
}
