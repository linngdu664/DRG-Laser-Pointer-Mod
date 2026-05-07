package com.linngdu664.drglaserpointer.client.renderer.item.properties.numeric;

import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ScreenColor implements RangeSelectItemModelProperty {
    public static final MapCodec<ScreenColor> MAP_CODEC = MapCodec.unit(new ScreenColor());

    @Override
    public float get(@NonNull ItemStack itemStack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
        if (owner == null) {
            return 0;
        }
        return itemStack.getOrDefault(DataComponentRegister.SCREEN_COLOR, (byte) 0);
    }

    @Override
    public @NonNull MapCodec<ScreenColor> type() {
        return MAP_CODEC;
    }
}
