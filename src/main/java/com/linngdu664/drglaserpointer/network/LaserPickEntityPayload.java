package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.linngdu664.drglaserpointer.registry.EntityRegister;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record LaserPickEntityPayload(Vec3 location, int entityId, byte color) {
    public static void encoder(LaserPickEntityPayload message, FriendlyByteBuf buffer) {
        buffer.writeDouble(message.location.x);
        buffer.writeDouble(message.location.y);
        buffer.writeDouble(message.location.z);
        buffer.writeVarInt(message.entityId);
        buffer.writeByte(message.color);
    }

    public static LaserPickEntityPayload decoder(FriendlyByteBuf buffer) {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new LaserPickEntityPayload(new Vec3(x, y, z), buffer.readVarInt(), buffer.readByte());
    }

    public static void messageConsumer(LaserPickEntityPayload message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = (ServerLevel) player.level;
            level.gameEvent(GameEvent.ENTITY_INTERACT, player.position(), GameEvent.Context.of(player));
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_MAKE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            level.getAllEntities().forEach(p -> {
                if (p instanceof LaserPointerMarkEntity entity1 && (entity1.getOwnerName().equals(player.getName().getString()) || level.getEntity(message.entityId) instanceof LivingEntity && entity1.getTargetEntityId() == message.entityId)) {
                    p.discard();
                }
            });
            level.addFreshEntity(new LaserPointerMarkEntity(EntityRegister.LASER_POINTER_MARK.get(), level, player, message.location, message.entityId, message.color));

        });
        context.setPacketHandled(true);
    }
}
