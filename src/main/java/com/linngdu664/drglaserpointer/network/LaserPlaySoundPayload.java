package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record LaserPlaySoundPayload(boolean isOn) implements CustomPacketPayload {
    public static final ResourceLocation ID = Main.makeResLoc("laser_play_sound");

    public LaserPlaySoundPayload(FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isOn);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleDataInServer(LaserPlaySoundPayload message, PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
            Player player = context.player().get();
            Level level = player.level();
            if (message.isOn) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_ON.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            } else {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_OFF.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        });
    }
}
