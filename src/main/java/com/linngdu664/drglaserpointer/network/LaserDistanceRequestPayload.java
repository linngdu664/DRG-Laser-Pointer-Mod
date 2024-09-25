package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.registry.NetworkRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;

public record LaserDistanceRequestPayload(ArrayList<Integer> ids) {
    public static void encoder(LaserDistanceRequestPayload message, FriendlyByteBuf buffer) {
        buffer.writeVarInt(message.ids().size());
        for (int id : message.ids()) {
            buffer.writeVarInt(id);
        }
    }

    public static LaserDistanceRequestPayload decoder(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ids.add(buffer.readVarInt());
        }
        return new LaserDistanceRequestPayload(ids);
    }

    public static void messageConsumer(LaserDistanceRequestPayload message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        ArrayList<Pair<Integer, Short>> arrayList = new ArrayList<>();
        for (int id : message.ids()) {
            Short s = LaserDistanceUpdatePayload.disMap.get(id);
            arrayList.add(new Pair<>(id, Objects.requireNonNullElse(s, (short) 0)));
        }
        NetworkRegister.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(context::getSender), new LaserDistanceResponsePayload(arrayList));
        context.setPacketHandled(true);
    }
}
