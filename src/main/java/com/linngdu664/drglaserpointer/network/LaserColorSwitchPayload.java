package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record LaserColorSwitchPayload(boolean isIncrease) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserColorSwitchPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_color_switch"));
    public static final StreamCodec<ByteBuf, LaserColorSwitchPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, LaserColorSwitchPayload::isIncrease,
            LaserColorSwitchPayload::new
    );

    @Override
    public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
