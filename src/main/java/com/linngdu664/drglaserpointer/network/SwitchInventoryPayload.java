package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SwitchInventoryPayload(int slot) implements CustomPacketPayload {
    public static final ResourceLocation ID = Main.makeResLoc("switch_inventory");
    public SwitchInventoryPayload(FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }


    public static void handleDataInServer(final SwitchInventoryPayload payload, final IPayloadContext context) {
        context.workHandler().submitAsync(() -> {
            Player player = context.player().get();
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

    @Override
    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.readInt();
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
