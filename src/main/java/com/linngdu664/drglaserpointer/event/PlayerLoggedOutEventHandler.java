package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.DrgLaserPointer;
import com.linngdu664.drglaserpointer.network.LaserDistanceUpdatePayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = DrgLaserPointer.MODID)
public class PlayerLoggedOutEventHandler {
    @SubscribeEvent
    public static void onPlayerLoggedOut(final PlayerEvent.PlayerLoggedOutEvent event) {
        LaserDistanceUpdatePayload.disMap.remove(event.getEntity().getId());
    }
}
