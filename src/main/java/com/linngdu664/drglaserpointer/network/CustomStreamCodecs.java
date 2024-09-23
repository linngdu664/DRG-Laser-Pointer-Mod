package com.linngdu664.drglaserpointer.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

public class CustomStreamCodecs {
    public static final StreamCodec<ByteBuf, Vec3> VEC3_STREAM_CODEC = new StreamCodec<>() {
        @Override
        public void encode(@NotNull ByteBuf byteBuf, @NotNull Vec3 vec3) {
            byteBuf.writeDouble(vec3.x);
            byteBuf.writeDouble(vec3.y);
            byteBuf.writeDouble(vec3.z);
        }

        @Override
        public @NotNull Vec3 decode(@NotNull ByteBuf byteBuf) {
            double x = byteBuf.readDouble();
            double y = byteBuf.readDouble();
            double z = byteBuf.readDouble();
            return new Vec3(x, y, z);
        }
    };

    public static final StreamCodec<ByteBuf, Pair<Integer, Float>> IF_PAIR_STREAM_CODEC = new StreamCodec<>() {
        @Override
        public void encode(@NotNull ByteBuf byteBuf, @NotNull Pair<Integer, Float> pair) {
            byteBuf.writeInt(pair.getA());
            byteBuf.writeFloat(pair.getB());
        }

        @Override
        public @NotNull Pair<Integer, Float> decode(@NotNull ByteBuf byteBuf) {
            return new Pair<>(byteBuf.readInt(), byteBuf.readFloat());
        }
    };
}
