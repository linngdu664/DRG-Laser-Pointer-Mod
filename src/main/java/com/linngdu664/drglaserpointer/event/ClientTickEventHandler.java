package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.network.LaserDistancePayload;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientTickEventHandler {
    @SubscribeEvent
    public static void onTick(ClientTickEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Item laserPointerItem = ItemRegister.LASER_POINTER.get();
            if (player.getMainHandItem().is(laserPointerItem) || player.getOffhandItem().is(laserPointerItem)) {
                PacketDistributor.sendToServer(new LaserDistancePayload(RenderLevelStageEventHandler.laserDistance));
            }
        }
    }
}
