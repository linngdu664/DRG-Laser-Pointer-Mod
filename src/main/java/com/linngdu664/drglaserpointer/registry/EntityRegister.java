package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModel;
import com.linngdu664.drglaserpointer.client.model.LaserPointerMarkModelBall;
import com.linngdu664.drglaserpointer.client.renderer.entity.LaserPointerMarkRenderer;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Main.MODID);

    public static final RegistryObject<EntityType<?>> LASER_POINTER_MARK =
            ENTITY_TYPES.register("laser_pointer_mark", () -> EntityType.Builder.of(LaserPointerMarkEntity::new, MobCategory.MISC)
                    .sized(0.0625f, 0.0625f).clientTrackingRange(8).fireImmune()
                    .build(Main.makeResLoc("laser_pointer_mark").toString()));


    @Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class RendererRegister {
        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(LASER_POINTER_MARK.get(), LaserPointerMarkRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(LaserPointerMarkModel.LAYER_LOCATION_BLUE, LaserPointerMarkModel::createBodyLayer);
            event.registerLayerDefinition(LaserPointerMarkModelBall.LAYER_LOCATION_RED, LaserPointerMarkModelBall::createBodyLayer);
        }
    }
}
