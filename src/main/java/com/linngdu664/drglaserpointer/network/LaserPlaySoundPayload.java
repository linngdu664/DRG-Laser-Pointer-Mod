package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record LaserPlaySoundPayload(boolean isOn) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserPlaySoundPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_play_sound"));
    public static final StreamCodec<ByteBuf, LaserPlaySoundPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, LaserPlaySoundPayload::isOn,
            LaserPlaySoundPayload::new
    );

    @Override
    public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
