package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.GameType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RenderGuiLayerEventHandler {
    @SubscribeEvent
    public static void onRenderGuiLayer(RenderGuiLayerEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Options options = mc.options;
        // "==" is safe here
        if (event.getName() == VanillaGuiLayers.CROSSHAIR && !options.hideGui && options.getCameraType().isFirstPerson() && mc.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            GuiGraphics graphics = event.getGuiGraphics();
            int height = graphics.guiHeight();
            int width = graphics.guiWidth();
            graphics.drawCenteredString(Minecraft.getInstance().font, "hello", width / 2, height / 2, 0xffffffff);
        }
    }
}
