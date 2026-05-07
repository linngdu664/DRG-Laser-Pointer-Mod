package com.linngdu664.drglaserpointer.client.renderer.item.properties.numeric;

import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class LightColor implements RangeSelectItemModelProperty {
    public static final MapCodec<LightColor> MAP_CODEC = MapCodec.unit(new LightColor());

    @Override
    public float get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
        return itemStack.getOrDefault(DataComponentRegister.LASER_COLOR, (byte) 0);
    }

    @Override
    public @NonNull MapCodec<LightColor> type() {
        return MAP_CODEC;
    }
}
