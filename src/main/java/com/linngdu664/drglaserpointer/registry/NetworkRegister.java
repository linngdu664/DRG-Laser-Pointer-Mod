package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.network.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkRegister {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(Main.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID++, messageType, encoder, decoder, messageConsumer);
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        NetworkRegister.addNetworkMessage(LaserColorSwitchPayload.class, LaserColorSwitchPayload::encoder, LaserColorSwitchPayload::decoder, LaserColorSwitchPayload::messageConsumer);
        NetworkRegister.addNetworkMessage(LaserDistanceRequestPayload.class, LaserDistanceRequestPayload::encoder, LaserDistanceRequestPayload::decoder, LaserDistanceRequestPayload::messageConsumer);
        NetworkRegister.addNetworkMessage(LaserDistanceResponsePayload.class, LaserDistanceResponsePayload::encoder, LaserDistanceResponsePayload::decoder, LaserDistanceResponsePayload::messageConsumer);
        NetworkRegister.addNetworkMessage(LaserDistanceUpdatePayload.class, LaserDistanceUpdatePayload::encoder, LaserDistanceUpdatePayload::decoder, LaserDistanceUpdatePayload::messageConsumer);
        NetworkRegister.addNetworkMessage(LaserPickBlockPayload.class, LaserPickBlockPayload::encoder, LaserPickBlockPayload::decoder, LaserPickBlockPayload::messageConsumer);
        NetworkRegister.addNetworkMessage(LaserPickEntityPayload.class, LaserPickEntityPayload::encoder, LaserPickEntityPayload::decoder, LaserPickEntityPayload::messageConsumer);
        NetworkRegister.addNetworkMessage(LaserPlaySoundPayload.class, LaserPlaySoundPayload::encoder, LaserPlaySoundPayload::decoder, LaserPlaySoundPayload::messageConsumer);
        NetworkRegister.addNetworkMessage(SwitchInventoryPayload.class, SwitchInventoryPayload::encoder, SwitchInventoryPayload::decoder,SwitchInventoryPayload::messageConsumer);
    }
}
