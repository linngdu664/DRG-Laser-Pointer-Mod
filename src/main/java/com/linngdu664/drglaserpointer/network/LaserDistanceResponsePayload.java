package com.linngdu664.drglaserpointer.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public record LaserDistanceResponsePayload(ArrayList<Pair<Integer, Short>> disList) {
    public static final HashMap<Integer, Short> clientDisMap = new HashMap<>();

    public static void encoder(LaserDistanceResponsePayload message, FriendlyByteBuf buffer) {
        buffer.writeVarInt(message.disList.size());
        for (Pair<Integer, Short> pair : message.disList) {
            buffer.writeVarInt(pair.getA());
            buffer.writeShort(pair.getB());
        }
    }

    public static LaserDistanceResponsePayload decoder(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        ArrayList<Pair<Integer, Short>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Pair<Integer, Short> pair = new Pair<>(buffer.readVarInt(), buffer.readShort());
            list.add(pair);
        }
        return new LaserDistanceResponsePayload(list);
    }

    public static void messageConsumer(LaserDistanceResponsePayload message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        clientDisMap.clear();
        for (Pair<Integer, Short> pair : message.disList) {
            clientDisMap.put(pair.getA(), pair.getB());
        }
        context.setPacketHandled(true);
    }
}
