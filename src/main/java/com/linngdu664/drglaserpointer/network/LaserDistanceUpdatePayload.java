package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

public record LaserDistanceUpdatePayload(short distance) implements CustomPacketPayload {
    public static final HashMap<Integer, Short> disMap = new HashMap<>();
    public static final CustomPacketPayload.Type<LaserDistanceUpdatePayload> TYPE = new CustomPacketPayload.Type<>(Main.makeResLoc("laser_distance_update"));
    public static final StreamCodec<ByteBuf, LaserDistanceUpdatePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.SHORT, LaserDistanceUpdatePayload::distance,
            LaserDistanceUpdatePayload::new
    );

    public static void handleDataInServer(final LaserDistanceUpdatePayload payload, final IPayloadContext context) {
        disMap.put(context.player().getId(), payload.distance);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
