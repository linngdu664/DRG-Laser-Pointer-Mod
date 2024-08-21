package com.linngdu664.drglaserpointer;

import com.linngdu664.drglaserpointer.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Main.MODID)
public class Main {
    public static final String MODID = "drglaserpointer";

    public static ResourceLocation makeResLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public Main(IEventBus modEventBus, ModContainer modContainer) {
        DataComponentRegister.DATA_COMPONENTS.register(modEventBus);
        ItemRegister.ITEMS.register(modEventBus);
        EntityRegister.ENTITY_TYPES.register(modEventBus);
        SoundRegister.SOUNDS.register(modEventBus);
        TriggerTypeRegister.TRIGGER_TYPES.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
//        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
