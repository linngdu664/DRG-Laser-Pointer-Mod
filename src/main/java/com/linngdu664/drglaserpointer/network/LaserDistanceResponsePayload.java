package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public record LaserDistanceResponsePayload(ArrayList<Pair<Integer, Short>> disList) implements CustomPacketPayload {
    public static final HashMap<Integer, Short> clientDisMap = new HashMap<>();
    public static final ResourceLocation ID = Main.makeResLoc("laser_distance_response");

    public static LaserDistanceResponsePayload read(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        ArrayList<Pair<Integer, Short>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Pair<Integer, Short> pair = new Pair<>(buffer.readVarInt(), buffer.readShort());
            list.add(pair);
        }
        return new LaserDistanceResponsePayload(list);
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeVarInt(disList.size());
        for (Pair<Integer, Short> pair : disList) {
            buffer.writeVarInt(pair.getA());
            buffer.writeShort(pair.getB());
        }
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleDataInClient(LaserDistanceResponsePayload message, PlayPayloadContext context) {
        clientDisMap.clear();
        for (Pair<Integer, Short> pair : message.disList) {
            clientDisMap.put(pair.getA(), pair.getB());
        }
    }
}
