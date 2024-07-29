package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.registry.SoundRegister;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class LaserPlaySoundServerHandler {
    public static void handleData(final LaserPlaySoundPayload payload, final IPayloadContext context) {
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
}
