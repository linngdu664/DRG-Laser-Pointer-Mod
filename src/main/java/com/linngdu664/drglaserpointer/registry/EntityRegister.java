package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.client.model.LaserPointerLabelModel;
import com.linngdu664.drglaserpointer.client.renderer.entity.LaserPointerLabelRenderer;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.io.function.Uncheck;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Main.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Entity>> LASER_POINTER_LABEL =
            ENTITY_TYPES.register("laser_pointer_label", () -> EntityType.Builder.of(LaserPointerLabelEntity::new, MobCategory.MISC)
                    .sized(0.0625f, 0.0625f).clientTrackingRange(8).fireImmune()
                    .build(ResourceLocation.fromNamespaceAndPath(Main.MODID, "laser_pointer_label").toString()));


    @EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class RendererRegister {
        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(LASER_POINTER_LABEL.get(), LaserPointerLabelRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(LaserPointerLabelModel.LAYER_LOCATION_BLUE, LaserPointerLabelModel::createBodyLayer);
        }
    }
}
