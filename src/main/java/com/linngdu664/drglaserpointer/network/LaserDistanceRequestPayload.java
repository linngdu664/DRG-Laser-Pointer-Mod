package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Objects;

public record LaserDistanceRequestPayload(ArrayList<Integer> ids) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserDistanceRequestPayload> TYPE = new CustomPacketPayload.Type<>(Main.makeResLoc("laser_distance_request"));
    public static final StreamCodec<ByteBuf, LaserDistanceRequestPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.collection(ArrayList::new, ByteBufCodecs.VAR_INT), LaserDistanceRequestPayload::ids,
            LaserDistanceRequestPayload::new
    );

    public static void handleDataInServer(final LaserDistanceRequestPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ArrayList<Pair<Integer, Float>> arrayList = new ArrayList<>();
            for (int id : payload.ids()) {
                Float f = LaserDistanceUpdatePayload.disMap.get(id);
                arrayList.add(new Pair<>(id, Objects.requireNonNullElse(f, 0F)));
            }
            PacketDistributor.sendToPlayer((ServerPlayer) context.player(), new LaserDistanceResponsePayload(arrayList));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
