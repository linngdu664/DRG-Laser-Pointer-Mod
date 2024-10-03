package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.registry.SoundRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record LaserPlaySoundPayload(boolean isOn) {
    public static void encoder(LaserPlaySoundPayload message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.isOn);
    }

    public static LaserPlaySoundPayload decoder(FriendlyByteBuf buffer) {
        return new LaserPlaySoundPayload(buffer.readBoolean());
    }

    public static void messageConsumer(LaserPlaySoundPayload message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            Level level = player.level;
            if (message.isOn) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_ON.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            } else {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_OFF.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        });
        context.setPacketHandled(true);
    }
}
