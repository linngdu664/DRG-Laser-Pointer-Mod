package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.linngdu664.drglaserpointer.registry.EntityRegister;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record LaserPickEntityPayload(Vec3 location, int entityId, byte color) implements CustomPacketPayload {
    public static final ResourceLocation ID = Main.makeResLoc("laser_pick_entity");

    public LaserPickEntityPayload(FriendlyByteBuf buffer) {
        this(new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble()), buffer.readVarInt(), buffer.readByte());
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeDouble(location.x);
        buffer.writeDouble(location.y);
        buffer.writeDouble(location.z);
        buffer.writeVarInt(entityId);
        buffer.writeByte(color);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleDataInServer(LaserPickEntityPayload message, PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
            ServerPlayer player = (ServerPlayer) context.player().get();
            ServerLevel level = (ServerLevel) player.level();
            level.gameEvent(GameEvent.ENTITY_INTERACT, player.position(), GameEvent.Context.of(player));
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_MAKE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            level.getAllEntities().forEach(p -> {
                if (p instanceof LaserPointerMarkEntity entity1 && (entity1.getOwnerName().equals(player.getName().getString()) || level.getEntity(message.entityId) instanceof LivingEntity && entity1.getTargetEntityId() == message.entityId)) {
                    p.discard();
                }
            });
            level.addFreshEntity(new LaserPointerMarkEntity(EntityRegister.LASER_POINTER_MARK.get(), level, player, message.location, message.entityId, message.color));
        });
    }
}
