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
        registrar.playToServer(LaserColorSwitchPayload.TYPE, LaserColorSwitchPayload.STREAM_CODEC, LaserColorSwitchPayload::handleDataInServer);
        registrar.playToServer(LaserPlaySoundPayload.TYPE, LaserPlaySoundPayload.STREAM_CODEC, LaserPlaySoundPayload::handleDataInServer);
        registrar.playToServer(LaserPickEntityPayload.TYPE, LaserPickEntityPayload.STREAM_CODEC, LaserPickEntityPayload::handleDataInServer);
        registrar.playToServer(LaserPickBlockPayload.TYPE, LaserPickBlockPayload.STREAM_CODEC, LaserPickBlockPayload::handleDataInServer);
        registrar.playToServer(LaserDistanceUpdatePayload.TYPE, LaserDistanceUpdatePayload.STREAM_CODEC, LaserDistanceUpdatePayload::handleDataInServer);
        registrar.playToServer(LaserDistanceRequestPayload.TYPE, LaserDistanceRequestPayload.STREAM_CODEC, LaserDistanceRequestPayload::handleDataInServer);
        registrar.playToClient(LaserDistanceResponsePayload.TYPE, LaserDistanceResponsePayload.STREAM_CODEC, LaserDistanceResponsePayload::handleDataInClient);
        registrar.playToServer(SwitchInventoryPayload.TYPE, SwitchInventoryPayload.STREAM_CODEC, SwitchInventoryPayload::handleDataInServer);
    }
}
