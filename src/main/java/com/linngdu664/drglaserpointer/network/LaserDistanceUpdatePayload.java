package com.linngdu664.drglaserpointer.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

public record LaserDistanceUpdatePayload(short distance) {
    public static final HashMap<Integer, Short> disMap = new HashMap<>();

    public static void encoder(LaserDistanceUpdatePayload message, FriendlyByteBuf buffer) {
        buffer.writeShort(message.distance);
    }

    public static LaserDistanceUpdatePayload decoder(FriendlyByteBuf buffer) {
        return new LaserDistanceUpdatePayload(buffer.readShort());
    }

    public static void messageConsumer(LaserDistanceUpdatePayload message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        disMap.put(context.getSender().getId(), message.distance);
        context.setPacketHandled(true);
    }
}
