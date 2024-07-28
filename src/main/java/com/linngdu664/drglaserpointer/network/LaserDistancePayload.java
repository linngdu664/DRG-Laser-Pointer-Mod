package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record LaserDistancePayload(float distance) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserDistancePayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_distance"));
    public static final StreamCodec<ByteBuf, LaserDistancePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, LaserDistancePayload::distance,
            LaserDistancePayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
