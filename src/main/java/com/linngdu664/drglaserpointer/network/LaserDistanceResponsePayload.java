package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public record LaserDistanceResponsePayload(ArrayList<Pair<Integer, Float>> disList) implements CustomPacketPayload {
    public static final HashMap<Integer, Float> clientDisMap = new HashMap<>();
    public static final CustomPacketPayload.Type<LaserDistanceResponsePayload> TYPE = new CustomPacketPayload.Type<>(Main.makeResLoc("laser_distance_response"));
    public static final StreamCodec<ByteBuf, LaserDistanceResponsePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.collection(ArrayList::new, CustomStreamCodecs.IF_PAIR_STREAM_CODEC), LaserDistanceResponsePayload::disList,
            LaserDistanceResponsePayload::new
    );

    public static void handleDataInClient(final LaserDistanceResponsePayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            clientDisMap.clear();
            for (Pair<Integer, Float> pair : payload.disList) {
                clientDisMap.put(pair.getA(), pair.getB());
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
