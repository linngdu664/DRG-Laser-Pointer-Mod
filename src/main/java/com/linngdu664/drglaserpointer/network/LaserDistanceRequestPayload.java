package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Objects;

public record LaserDistanceRequestPayload(ArrayList<Integer> ids) implements CustomPacketPayload {
    public static final ResourceLocation ID = Main.makeResLoc("laser_distance_request");

    public static LaserDistanceRequestPayload read(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ids.add(buffer.readVarInt());
        }
        return new LaserDistanceRequestPayload(ids);
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeVarInt(ids.size());
        for (int id : ids) {
            buffer.writeVarInt(id);
        }
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleDataInServer(LaserDistanceRequestPayload message, PlayPayloadContext context) {
        ArrayList<Pair<Integer, Short>> arrayList = new ArrayList<>();
        for (int id : message.ids()) {
            Short s = LaserDistanceUpdatePayload.disMap.get(id);
            arrayList.add(new Pair<>(id, Objects.requireNonNullElse(s, (short) 0)));
        }
        PacketDistributor.PLAYER.with((ServerPlayer) context.player().get()).send(new LaserDistanceResponsePayload(arrayList));
    }
}
