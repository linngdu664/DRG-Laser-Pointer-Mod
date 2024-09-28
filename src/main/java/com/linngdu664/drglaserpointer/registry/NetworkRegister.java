package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.network.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkRegister {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(Main.MODID);
        registrar.play(LaserColorSwitchPayload.ID, LaserColorSwitchPayload::new, handler -> handler.server(LaserColorSwitchPayload::handleDataInServer));
        registrar.play(LaserDistanceRequestPayload.ID, LaserDistanceRequestPayload::read, handler -> handler.server(LaserDistanceRequestPayload::handleDataInServer));
        registrar.play(LaserDistanceResponsePayload.ID, LaserDistanceResponsePayload::read, handler -> handler.client(LaserDistanceResponsePayload::handleDataInClient));
        registrar.play(LaserDistanceUpdatePayload.ID, LaserDistanceUpdatePayload::new, handler -> handler.server(LaserDistanceUpdatePayload::handleDataInServer));
        registrar.play(LaserPickBlockPayload.ID, LaserPickBlockPayload::new, handler -> handler.server(LaserPickBlockPayload::handleDataInServer));
        registrar.play(LaserPickEntityPayload.ID, LaserPickEntityPayload::new, handler -> handler.server(LaserPickEntityPayload::handleDataInServer));
        registrar.play(LaserPlaySoundPayload.ID, LaserPlaySoundPayload::new, handler -> handler.server(LaserPlaySoundPayload::handleDataInServer));
        registrar.play(SwitchInventoryPayload.ID, SwitchInventoryPayload::new, SwitchInventoryPayload::handleDataInServer);
    }
}
