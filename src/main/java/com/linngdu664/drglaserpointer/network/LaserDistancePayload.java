package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.component.LaserData;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record LaserDistancePayload(float distance) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserDistancePayload> TYPE = new CustomPacketPayload.Type<>(Main.makeResLoc("laser_distance"));
    public static final StreamCodec<ByteBuf, LaserDistancePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, LaserDistancePayload::distance,
            LaserDistancePayload::new
    );

    public static void handleDataInServer(final LaserDistancePayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ItemStack mainHandItemStack = player.getMainHandItem();
            ItemStack offHandItemStack = player.getOffhandItem();
            var component = DataComponentRegister.LASER_DATA.get();
            if (mainHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                byte colorId = mainHandItemStack.get(component).colorId();
                mainHandItemStack.set(component, new LaserData(payload.distance(), colorId));
            }
            if (offHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                byte colorId = offHandItemStack.get(component).colorId();
                offHandItemStack.set(component, new LaserData(payload.distance(), colorId));
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
