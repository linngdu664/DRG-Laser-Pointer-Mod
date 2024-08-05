package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.network.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkRegister {
    @SubscribeEvent
    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(LaserDistancePayload.TYPE, LaserDistancePayload.STREAM_CODEC, LaserDistancePayload::handleDataInServer);
        registrar.playToServer(LaserColorSwitchPayload.TYPE, LaserColorSwitchPayload.STREAM_CODEC, LaserColorSwitchPayload::handleDataInServer);
        registrar.playToServer(LaserPlaySoundPayload.TYPE, LaserPlaySoundPayload.STREAM_CODEC, LaserPlaySoundPayload::handleDataInServer);
        registrar.playToServer(LaserPickEntityPayload.TYPE, LaserPickEntityPayload.STREAM_CODEC, LaserPickEntityPayload::handleDataInServer);
        registrar.playToServer(LaserPickBlockPayload.TYPE, LaserPickBlockPayload.STREAM_CODEC, LaserPickBlockPayload::handleDataInServer);
    }
}
