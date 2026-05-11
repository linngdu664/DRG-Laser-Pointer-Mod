package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.DrgLaserPointer;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, DrgLaserPointer.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<?>> LASER_POINTER_MARK =
            ENTITY_TYPES.register("laser_pointer_mark", () -> EntityType.Builder.of(LaserPointerMarkEntity::new, MobCategory.MISC)
                    .sized(0.0625f, 0.0625f).clientTrackingRange(8).fireImmune()
                    .build(ResourceKey.create(ENTITY_TYPES.getRegistryKey(), DrgLaserPointer.makeMyIdentifier("laser_pointer_mark"))));
}
