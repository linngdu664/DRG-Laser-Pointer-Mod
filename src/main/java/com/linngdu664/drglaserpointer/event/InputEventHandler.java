package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.network.LaserColorSwitchPayload;
import com.linngdu664.drglaserpointer.network.SwitchInventoryPayload;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.registry.KeyMappingRegister;
import com.linngdu664.drglaserpointer.registry.NetworkRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEventHandler {
    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.is(ItemRegister.LASER_POINTER.get()) && player.isShiftKeyDown()) {
            NetworkRegister.PACKET_HANDLER.sendToServer(new LaserColorSwitchPayload(event.getScrollDelta() > 0));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onInteractionKeyMappingTriggered(InputEvent.InteractionKeyMappingTriggered event) {
        if (Minecraft.getInstance().player.getMainHandItem().is(ItemRegister.LASER_POINTER.get()) && event.isAttack()) {
            event.setCanceled(true);
        }
    }
    private static int oldSlot = -1;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen == null && event.getKey() == KeyMappingRegister.SWITCH_TO_LASER_POINTER.getKey().getValue()) {
            Player player = mc.player;
            if (event.getAction() == GLFW.GLFW_PRESS && !player.getMainHandItem().is(ItemRegister.LASER_POINTER.get()) && !player.getOffhandItem().is(ItemRegister.LASER_POINTER.get())) {
                Inventory inventory = player.getInventory();
                for (int i = 0, k = inventory.getContainerSize(); i < k; i++) {
                    ItemStack stack = inventory.getItem(i);
                    if (stack.is(ItemRegister.LASER_POINTER.get())) {
                        oldSlot = i;
                        NetworkRegister.PACKET_HANDLER.sendToServer(new SwitchInventoryPayload(oldSlot));
                        return;
                    }
                }
            } else if (event.getAction() == GLFW.GLFW_RELEASE && (player.getMainHandItem().is(ItemRegister.LASER_POINTER.get()) || player.getOffhandItem().is(ItemRegister.LASER_POINTER.get()))) {
                NetworkRegister.PACKET_HANDLER.sendToServer(new SwitchInventoryPayload(oldSlot));
            }
        }
    }
}
