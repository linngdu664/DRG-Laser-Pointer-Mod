package com.linngdu664.drglaserpointer.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SwitchInventoryPayload(int slot) {
    public static void encoder(SwitchInventoryPayload message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.slot);
    }
    public static SwitchInventoryPayload decoder(FriendlyByteBuf buffer) {
        return new SwitchInventoryPayload(buffer.readInt());
    }


    public static void messageConsumer(SwitchInventoryPayload payload, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            Inventory inventory = player.getInventory();
            if (payload.slot < 0 || payload.slot >= inventory.getContainerSize()) {
                return;
            }
            ItemStack itemStack1 = inventory.getSelected();
            ItemStack itemStack2 = inventory.getItem(payload.slot);
            inventory.setItem(inventory.selected, itemStack2);
            inventory.setItem(payload.slot, itemStack1);
        });
    }
}
