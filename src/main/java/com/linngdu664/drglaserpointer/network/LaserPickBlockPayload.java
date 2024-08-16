package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import com.linngdu664.drglaserpointer.registry.EntityRegister;
import com.linngdu664.drglaserpointer.registry.SoundRegister;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Objects;

public class LaserPickBlockPayload implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserPickBlockPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_pick_block"));

    public static final StreamCodec<ByteBuf, LaserPickBlockPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BYTE_ARRAY, LaserPickBlockPayload::toByteArray,
            LaserPickBlockPayload::new
    );

    public static void handleDataInServer(final LaserPickBlockPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (payload.blockPos != null && payload.location != null) {
                Player player = context.player();
                ServerLevel level = (ServerLevel) player.level();
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_MAKE.get(), SoundSource.PLAYERS);
                Block block = level.getBlockState(payload.blockPos).getBlock();
                RandomSource random = level.getRandom();
                if (block == Blocks.GOLD_BLOCK) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.WERE_RICH.get(), SoundSource.PLAYERS, random.nextFloat() * 0.4f + 0.8f, random.nextFloat() * 0.4f + 0.8f);
                } else if (block == Blocks.MUSHROOM_STEM || block == Blocks.BROWN_MUSHROOM || block == Blocks.RED_MUSHROOM || block == Blocks.BROWN_MUSHROOM_BLOCK || block == Blocks.RED_MUSHROOM_BLOCK || block == Blocks.POTTED_BROWN_MUSHROOM || block == Blocks.POTTED_RED_MUSHROOM) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), random.nextFloat() > 0.5F ? SoundRegister.MUSHROOM1.get() : SoundRegister.MUSHROOM2.get(), SoundSource.PLAYERS, random.nextFloat() * 0.4f + 0.8f, random.nextFloat() * 0.4f + 0.8f);
                }
                for (Entity entity : level.getAllEntities()) {
                    if (entity instanceof LaserPointerLabelEntity entity1 && entity1.getOwnerUUID().equals(player.getUUID())) {
                        entity1.discard();
                        break;
                    }
                }
                level.addFreshEntity(new LaserPointerLabelEntity(EntityRegister.LASER_POINTER_LABEL.get(), level, player, payload.location, payload.blockPos, payload.color));
            }
        });
    }

    private final Vec3 location;
    private final BlockPos blockPos;
    private final byte color;

    public LaserPickBlockPayload(Vec3 location, BlockPos blockPos, byte color) {
        this.location = location;
        this.blockPos = blockPos;
        this.color = color;
    }

    private LaserPickBlockPayload(byte[] arr) {
        ByteArrayInputStream bais = new ByteArrayInputStream(arr);
        DataInputStream dis = new DataInputStream(bais);
        Vec3 location = null;
        BlockPos blockPos = null;
        byte color = 0;
        try {
            location = new Vec3(dis.readDouble(), dis.readDouble(), dis.readDouble());
            blockPos = new BlockPos(dis.readInt(), dis.readInt(), dis.readInt());
            color = dis.readByte();
        } catch (IOException ignored) {}
        this.location = location;
        this.blockPos = blockPos;
        this.color = color;
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private byte[] toByteArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(baos);
        try {
            dataOutputStream.writeDouble(location.x);
            dataOutputStream.writeDouble(location.y);
            dataOutputStream.writeDouble(location.z);
            dataOutputStream.writeInt(blockPos.getX());
            dataOutputStream.writeInt(blockPos.getY());
            dataOutputStream.writeInt(blockPos.getZ());
            dataOutputStream.writeByte(color);
        } catch (IOException ignored) {}
        return baos.toByteArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaserPickBlockPayload that = (LaserPickBlockPayload) o;
        return Objects.equals(location, that.location) && Objects.equals(blockPos, that.blockPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, blockPos);
    }
}
