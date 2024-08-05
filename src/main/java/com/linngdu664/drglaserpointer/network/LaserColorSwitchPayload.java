package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.component.LaserData;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record LaserColorSwitchPayload(boolean isIncrease) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LaserColorSwitchPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_color_switch"));
    public static final StreamCodec<ByteBuf, LaserColorSwitchPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, LaserColorSwitchPayload::isIncrease,
            LaserColorSwitchPayload::new
    );

    public static void handleDataInServer(final LaserColorSwitchPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ItemStack mainHandItemStack = player.getMainHandItem();
            if (mainHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                var data = mainHandItemStack.get(DataComponentRegister.LASER_DATA.get());
                int colorId = data.colorId();
                if (payload.isIncrease()) {
                    colorId++;
                    if (colorId > 3) {
                        colorId = 0;
                    }
                } else {
                    colorId--;
                    if (colorId < 0) {
                        colorId = 3;
                    }
                }
                mainHandItemStack.set(DataComponentRegister.LASER_DATA.get(), new LaserData(data.distance(), (byte) colorId));
            }
        });
    }

    @Override
    public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
