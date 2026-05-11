package com.linngdu664.drglaserpointer;

import com.linngdu664.drglaserpointer.config.ClientConfig;
import com.linngdu664.drglaserpointer.config.CommonConfig;
import com.linngdu664.drglaserpointer.registry.*;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DrgLaserPointer.MODID)
public class DrgLaserPointer {
    public static final String MODID = "drglaserpointer";

    public static Identifier makeMyIdentifier(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }

    public DrgLaserPointer(IEventBus modEventBus, ModContainer modContainer) {
        DataComponentRegistry.DATA_COMPONENTS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        EntityRegistry.ENTITY_TYPES.register(modEventBus);
        SoundRegistry.SOUNDS.register(modEventBus);
        TriggerTypeRegistry.TRIGGER_TYPES.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }
}
