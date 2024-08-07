package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.network.LaserDistancePayload;
import com.linngdu664.drglaserpointer.network.LaserPlaySoundPayload;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientTickEventHandler {
    private static Item lastMainHandItem = Items.AIR;
    private static Item lastOffHandItem = Items.AIR;
    public static int mainHandLaserTick;
    public static int offHandLaserTick;

    @SubscribeEvent
    public static void onTick(ClientTickEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            Item laserPointerItem = ItemRegister.LASER_POINTER.get();
            ItemStack mainHandStack = player.getMainHandItem();
            ItemStack offHandStack = player.getOffhandItem();
            if (mainHandStack.is(laserPointerItem) || offHandStack.is(laserPointerItem)) {
                PacketDistributor.sendToServer(new LaserDistancePayload(RenderLevelStageEventHandler.laserDistance));
            }
            if (mainHandStack.is(laserPointerItem)) {
                mainHandLaserTick++;
            } else {
                mainHandLaserTick = 0;
            }
            if (offHandStack.is(laserPointerItem)) {
                offHandLaserTick++;
            } else {
                offHandLaserTick = 0;
            }
            // == is safe here
            if (mainHandStack.is(laserPointerItem) && lastMainHandItem != laserPointerItem || offHandStack.is(laserPointerItem) && lastOffHandItem != laserPointerItem) {
                PacketDistributor.sendToServer(new LaserPlaySoundPayload(true));
            } else if (!mainHandStack.is(laserPointerItem) && lastMainHandItem == laserPointerItem || !offHandStack.is(laserPointerItem) && lastOffHandItem == laserPointerItem) {
                PacketDistributor.sendToServer(new LaserPlaySoundPayload(false));
            }
            lastMainHandItem = mainHandStack.getItem();
            lastOffHandItem = offHandStack.getItem();
        }
    }
}
