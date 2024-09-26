package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.HashMap;

public record LaserDistanceUpdatePayload(short distance) implements CustomPacketPayload {
    public static final HashMap<Integer, Short> disMap = new HashMap<>();
    public static final ResourceLocation ID = Main.makeResLoc("laser_distance_update");

    public LaserDistanceUpdatePayload(FriendlyByteBuf buffer) {
        this(buffer.readShort());
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeShort(distance);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleDataInServer(LaserDistanceUpdatePayload message, PlayPayloadContext context) {
        disMap.put(context.player().get().getId(), message.distance);
    }
}
