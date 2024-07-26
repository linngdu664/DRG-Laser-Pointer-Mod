package com.linngdu664.drglaserpointer.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record LaserDistance(double distance) {
    public static final Codec<LaserDistance> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.DOUBLE.fieldOf("distance").forGetter(LaserDistance::distance)
            ).apply(instance, LaserDistance::new)
    );
    public static final StreamCodec<ByteBuf, LaserDistance> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, LaserDistance::distance,
            LaserDistance::new
    );
}
