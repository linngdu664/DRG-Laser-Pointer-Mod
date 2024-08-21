package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record LaserPlaySoundPayload(boolean isOn) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserPlaySoundPayload> TYPE = new CustomPacketPayload.Type<>(Main.makeResLoc("laser_play_sound"));
    public static final StreamCodec<ByteBuf, LaserPlaySoundPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, LaserPlaySoundPayload::isOn,
            LaserPlaySoundPayload::new
    );

    public static void handleDataInServer(final LaserPlaySoundPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Level level = player.level();
            if (payload.isOn()) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_ON.get(), SoundSource.PLAYERS);
            } else {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_OFF.get(), SoundSource.PLAYERS);
            }
        });
    }

    @Override
    public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
