package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import com.linngdu664.drglaserpointer.registry.EntityRegister;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record LaserPickEntityPayload(Vec3 location, int entityId, byte color) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserPickEntityPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_pick_entity"));
    public static final StreamCodec<ByteBuf, LaserPickEntityPayload> STREAM_CODEC = StreamCodec.composite(
            CustomStreamCodecs.VEC3_STREAM_CODEC, LaserPickEntityPayload::location,
            ByteBufCodecs.VAR_INT, LaserPickEntityPayload::entityId,
            ByteBufCodecs.BYTE, LaserPickEntityPayload::color,
            LaserPickEntityPayload::new
    );

    public static void handleDataInServer(final LaserPickEntityPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ServerLevel level = (ServerLevel) player.level();
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_MAKE.get(), SoundSource.PLAYERS);
            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof LaserPointerLabelEntity entity1 && entity1.getOwnerUUID().equals(player.getUUID())) {
                    entity1.discard();
                    break;
                }
            }
            level.addFreshEntity(new LaserPointerLabelEntity(EntityRegister.LASER_POINTER_LABEL.get(), level, player, payload.location, payload.entityId, payload.color));
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
