package com.linngdu664.drglaserpointer.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record LaserData(float distance, byte colorId) {
    public static final Codec<LaserData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("distance").forGetter(LaserData::distance),
                    Codec.BYTE.fieldOf("colorId").forGetter(LaserData::colorId)
            ).apply(instance, LaserData::new)
    );
    public static final StreamCodec<ByteBuf, LaserData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, LaserData::distance,
            ByteBufCodecs.BYTE, LaserData::colorId,
            LaserData::new
    );

    public int getColorARGB() {
        return switch (colorId) {
            default -> 0xff78e0ff;
            case 1 -> 0xffff7864;
            case 2 -> 0xffffbc4c;
            case 3 -> 0xff78ff78;
        };
    }
}
