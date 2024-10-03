package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record LaserColorSwitchPayload(boolean isIncrease) {
    public static void encoder(LaserColorSwitchPayload message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.isIncrease);
    }

    public static LaserColorSwitchPayload decoder(FriendlyByteBuf buffer) {
        return new LaserColorSwitchPayload(buffer.readBoolean());
    }

    public static void messageConsumer(LaserColorSwitchPayload message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            ItemStack mainHandItemStack = player.getMainHandItem();
            if (mainHandItemStack.is(ItemRegister.LASER_POINTER.get())) {
                int colorId = mainHandItemStack.getOrCreateTag().getByte("LaserColor");
                if (message.isIncrease()) {
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
                mainHandItemStack.getOrCreateTag().putByte("LaserColor", (byte) colorId);
            }
        });
        context.setPacketHandled(true);
    }
}
