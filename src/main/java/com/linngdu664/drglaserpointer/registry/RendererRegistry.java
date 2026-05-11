package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.DrgLaserPointer;
import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModel;
import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModelBall;
import com.linngdu664.drglaserpointer.client.model.LaserPointerModel;
import com.linngdu664.drglaserpointer.client.renderer.entity.LaserPointerMarkRenderer;
import com.linngdu664.drglaserpointer.client.renderer.special.LaserPointerSpecialRenderer;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

import static com.linngdu664.drglaserpointer.registry.EntityRegistry.LASER_POINTER_MARK;

@EventBusSubscriber(modid = DrgLaserPointer.MODID, value = Dist.CLIENT)
public class RendererRegistry {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LaserPointerMarkModel.LAYER_LOCATION_BLUE, LaserPointerMarkModel::createBodyLayer);
        event.registerLayerDefinition(LaserPointerMarkModelBall.LAYER_LOCATION_RED, LaserPointerMarkModelBall::createBodyLayer);
        event.registerLayerDefinition(LaserPointerModel.LAYER_LOCATION, LaserPointerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer((EntityType<LaserPointerMarkEntity>) LASER_POINTER_MARK.get(), LaserPointerMarkRenderer::new);
    }

    @SubscribeEvent
    public static void registerSpecialRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(DrgLaserPointer.makeMyIdentifier("laser_pointer_special"), LaserPointerSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
