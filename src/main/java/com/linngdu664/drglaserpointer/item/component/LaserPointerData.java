package com.linngdu664.drglaserpointer.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record LaserPointerData(double distance, int colorId) {
    public static final Codec<LaserPointerData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.DOUBLE.fieldOf("distance").forGetter(LaserPointerData::distance),
                    Codec.INT.fieldOf("colorId").forGetter(LaserPointerData::colorId)
            ).apply(instance, LaserPointerData::new)
    );
    public static final StreamCodec<ByteBuf, LaserPointerData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, LaserPointerData::distance,
            ByteBufCodecs.INT, LaserPointerData::colorId,
            LaserPointerData::new
    );
}
