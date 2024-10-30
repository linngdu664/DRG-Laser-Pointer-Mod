package com.linngdu664.drglaserpointer.network;

import com.linngdu664.drglaserpointer.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SwitchInventoryPayload(int slot) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SwitchInventoryPayload> TYPE = new CustomPacketPayload.Type<>(Main.makeResLoc("switch_inventory"));
    public static final StreamCodec<ByteBuf, SwitchInventoryPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, SwitchInventoryPayload::slot,
            SwitchInventoryPayload::new
    );

    public static void handleDataInServer(final SwitchInventoryPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
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
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
