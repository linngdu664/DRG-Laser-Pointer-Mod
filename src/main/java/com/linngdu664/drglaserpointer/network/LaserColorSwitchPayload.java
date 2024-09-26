package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record LaserColorSwitchPayload(boolean isIncrease) implements CustomPacketPayload {
    public static final ResourceLocation ID = Main.makeResLoc("laser_color_switch");

    public LaserColorSwitchPayload(final FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(isIncrease);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleDataInServer(LaserColorSwitchPayload message, PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
            Player player = context.player().get();
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
    }
}
