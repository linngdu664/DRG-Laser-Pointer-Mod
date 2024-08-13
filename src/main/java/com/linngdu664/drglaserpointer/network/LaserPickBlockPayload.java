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

import java.util.Objects;

public class LaserPickBlockPayload implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserPickBlockPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_pick_block"));
    public static final StreamCodec<ByteBuf, LaserPickBlockPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, LaserPickBlockPayload::getLocationX,
            ByteBufCodecs.DOUBLE, LaserPickBlockPayload::getLocationY,
            ByteBufCodecs.DOUBLE, LaserPickBlockPayload::getLocationZ,
            ByteBufCodecs.INT, LaserPickBlockPayload::getBlockPosX,
            ByteBufCodecs.INT, LaserPickBlockPayload::getBlockPosY,
            ByteBufCodecs.INT, LaserPickBlockPayload::getBlockPosZ,
            LaserPickBlockPayload::new
    );

    public static void handleDataInServer(final LaserPickBlockPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ServerLevel level = (ServerLevel) player.level();
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.LASER_MAKE.get(), SoundSource.PLAYERS);
            Block block = level.getBlockState(payload.blockPos).getBlock();
            RandomSource random = level.getRandom();
            if (block == Blocks.GOLD_BLOCK) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.WERE_RICH.get(), SoundSource.PLAYERS,random.nextFloat()*0.4f+0.8f,random.nextFloat()*0.4f+0.8f);
            }else if (block == Blocks.MUSHROOM_STEM || block == Blocks.BROWN_MUSHROOM || block == Blocks.RED_MUSHROOM || block == Blocks.BROWN_MUSHROOM_BLOCK || block == Blocks.RED_MUSHROOM_BLOCK || block == Blocks.POTTED_BROWN_MUSHROOM || block == Blocks.POTTED_RED_MUSHROOM) {
                if(random.nextFloat()>0.5f){
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.MUSHROOM1.get(), SoundSource.PLAYERS,random.nextFloat()*0.4f+0.8f,random.nextFloat()*0.4f+0.8f);
                }else{
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegister.MUSHROOM2.get(), SoundSource.PLAYERS,random.nextFloat()*0.4f+0.8f,random.nextFloat()*0.4f+0.8f);
                }
            }
            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof LaserPointerLabelEntity entity1 && entity1.getOwnerUUID().equals(player.getUUID())) {
                    entity1.discard();
                    break;
                }
            }
            level.addFreshEntity(new LaserPointerLabelEntity(EntityRegister.LASER_POINTER_LABEL.get(), level, player, payload.location, payload.blockPos));
        });
    }

    private final Vec3 location;
    private final BlockPos blockPos;

    public LaserPickBlockPayload(Vec3 location, BlockPos blockPos) {
        this.location = location;
        this.blockPos = blockPos;
    }

    private LaserPickBlockPayload(double locationX, double locationY, double locationZ, int blockPosX, int blockPosY, int blockPosZ) {
        this.location = new Vec3(locationX, locationY, locationZ);
        this.blockPos = new BlockPos(blockPosX, blockPosY, blockPosZ);
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private double getLocationX() {
        return location.x;
    }

    private double getLocationY() {
        return location.y;
    }

    private double getLocationZ() {
        return location.z;
    }

    private int getBlockPosX() {
        return blockPos.getX();
    }

    private int getBlockPosY() {
        return blockPos.getY();
    }

    private int getBlockPosZ() {
        return blockPos.getZ();
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
