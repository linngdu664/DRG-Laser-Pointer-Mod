package com.linngdu664.drglaserpointer.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record AudioCooldownData(int cd) {
    public static final Codec<AudioCooldownData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("cd").forGetter(AudioCooldownData::cd)
            ).apply(instance, AudioCooldownData::new)
    );
    public static final StreamCodec<ByteBuf, AudioCooldownData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, AudioCooldownData::cd,
            AudioCooldownData::new
    );
    public static final AudioCooldownData EMPTY = new AudioCooldownData(0);
}
