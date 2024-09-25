package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.criterion_trigger.MarkBlockTrigger;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.linngdu664.drglaserpointer.misc.ModTags;
import com.linngdu664.drglaserpointer.registry.EntityRegister;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record LaserPickBlockPayload(Vec3 location, BlockPos blockPos, byte color, boolean canPlayAudio) {
    public static void encoder(LaserPickBlockPayload message, FriendlyByteBuf buffer) {
        buffer.writeDouble(message.location.x);
        buffer.writeDouble(message.location.y);
        buffer.writeDouble(message.location.z);
        buffer.writeBlockPos(message.blockPos);
        buffer.writeByte(message.color);
        buffer.writeBoolean(message.canPlayAudio);
    }

    public static LaserPickBlockPayload decoder(FriendlyByteBuf buffer) {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new LaserPickBlockPayload(new Vec3(x, y, z), buffer.readBlockPos(), buffer.readByte(), buffer.readBoolean());
    }

    public static void messageConsumer(LaserPickBlockPayload message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = (ServerLevel) player.level();
            if (level.hasChunkAt(message.blockPos)) {
                level.gameEvent(GameEvent.ENTITY_INTERACT, player.position(), GameEvent.Context.of(player));
                BlockState blockState = level.getBlockState(message.blockPos);
                MarkBlockTrigger.INSTANCE.trigger(player, blockState);
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
        context.setPacketHandled(true);
    }
}
