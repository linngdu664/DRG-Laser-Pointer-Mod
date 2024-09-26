package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.linngdu664.drglaserpointer.misc.ModTags;
import com.linngdu664.drglaserpointer.registry.TriggerTypeRegister;
import com.linngdu664.drglaserpointer.registry.EntityRegister;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record LaserPickBlockPayload(Vec3 location, BlockPos blockPos, byte color, boolean canPlayAudio) implements CustomPacketPayload {
    public static final ResourceLocation ID = Main.makeResLoc("laser_pick_block");

    public LaserPickBlockPayload(FriendlyByteBuf buffer) {
        this(new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble()), buffer.readBlockPos(), buffer.readByte(), buffer.readBoolean());
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeDouble(location.x);
        buffer.writeDouble(location.y);
        buffer.writeDouble(location.z);
        buffer.writeBlockPos(blockPos);
        buffer.writeByte(color);
        buffer.writeBoolean(canPlayAudio);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleDataInServer(LaserPickBlockPayload message, PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
            ServerPlayer player = (ServerPlayer) context.player().get();
            ServerLevel level = (ServerLevel) player.level();
            if (level.hasChunkAt(message.blockPos)) {
                level.gameEvent(GameEvent.ENTITY_INTERACT, player.position(), GameEvent.Context.of(player));
                BlockState blockState = level.getBlockState(message.blockPos);
                TriggerTypeRegister.MARK_BLOCK_TRIGGER.get().trigger(player, blockState);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_MAKE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                if (message.canPlayAudio) {
                    RandomSource random = level.getRandom();
                    if (blockState.is(ModTags.Blocks.RICH_BLOCKS)) {
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.WERE_RICH.get(), SoundSource.PLAYERS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                    } else if (blockState.is(ModTags.Blocks.MUSHROOMS)) {
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), random.nextIntBetweenInclusive(0, 1) == 0 ? SoundRegister.MUSHROOM1.get() : SoundRegister.MUSHROOM2.get(), SoundSource.PLAYERS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                    }
                }
                for (Entity entity : level.getAllEntities()) {
                    if (entity instanceof LaserPointerMarkEntity entity1 && entity1.getOwnerName().equals(player.getName().getString())) {
                        entity1.discard();
                        break;
                    }
                }
                level.addFreshEntity(new LaserPointerMarkEntity(EntityRegister.LASER_POINTER_MARK.get(), level, player, message.location, blockState, message.color));
            }
        });
    }
}
