package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import com.linngdu664.drglaserpointer.misc.ModTags;
import com.linngdu664.drglaserpointer.registry.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record LaserPickBlockPayload(Vec3 location, BlockPos blockPos, byte color, boolean canPlayAudio) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserPickBlockPayload> TYPE = new CustomPacketPayload.Type<>(Main.makeResLoc("laser_pick_block"));
    public static final StreamCodec<ByteBuf, LaserPickBlockPayload> STREAM_CODEC = StreamCodec.composite(
            CustomStreamCodecs.VEC3_STREAM_CODEC, LaserPickBlockPayload::location,
            BlockPos.STREAM_CODEC, LaserPickBlockPayload::blockPos,
            ByteBufCodecs.BYTE, LaserPickBlockPayload::color,
            ByteBufCodecs.BOOL, LaserPickBlockPayload::canPlayAudio,
            LaserPickBlockPayload::new
    );

    private static void setAudioCooldown(Player player) {
        ItemStack mainHandStack = player.getMainHandItem();
        ItemStack offHandStack = player.getOffhandItem();
        if (mainHandStack.is(ItemRegister.LASER_POINTER)) {
            mainHandStack.set(DataComponentRegister.AUDIO_COOLDOWN, 20);
        }
        if (offHandStack.is(ItemRegister.LASER_POINTER)) {
            offHandStack.set(DataComponentRegister.AUDIO_COOLDOWN, 20);
        }
    }

    public static void handleDataInServer(final LaserPickBlockPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ServerLevel level = (ServerLevel) player.level();
            level.gameEvent(GameEvent.ENTITY_ACTION, player.position(), GameEvent.Context.of(player));
            BlockState blockState = level.getBlockState(payload.blockPos);
            TriggerTypeRegister.MARK_BLOCK_TRIGGER.get().trigger((ServerPlayer) player, blockState);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_MAKE.get(), SoundSource.PLAYERS);
            if (payload.canPlayAudio) {
                RandomSource random = level.getRandom();
                if (blockState.is(ModTags.Blocks.RICH_BLOCKS)) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.WERE_RICH, SoundSource.PLAYERS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                    setAudioCooldown(player);
                } else if (blockState.is(ModTags.Blocks.MUSHROOMS)) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), random.nextFloat() > 0.5F ? SoundRegister.MUSHROOM1 : SoundRegister.MUSHROOM2, SoundSource.PLAYERS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                    setAudioCooldown(player);
                }
            }
            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof LaserPointerLabelEntity entity1 && entity1.getOwnerName().equals(player.getName().getString())) {
                    entity1.discard();
                    break;
                }
            }
            level.addFreshEntity(new LaserPointerLabelEntity(EntityRegister.LASER_POINTER_LABEL.get(), level, player, payload.location, blockState, payload.color));
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
