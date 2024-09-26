package com.linngdu664.drglaserpointer;

import com.linngdu664.drglaserpointer.config.ClientConfig;
import com.linngdu664.drglaserpointer.config.CommonConfig;
import com.linngdu664.drglaserpointer.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Main.MODID)
public class Main {
    public static final String MODID = "drglaserpointer";

    public static ResourceLocation makeResLoc(String path) {
        return new ResourceLocation(MODID, path);
    }

    public Main(IEventBus modEventBus) {
        ItemRegister.ITEMS.register(modEventBus);
        EntityRegister.ENTITY_TYPES.register(modEventBus);
        SoundRegister.SOUNDS.register(modEventBus);
        TriggerTypeRegister.TRIGGER_TYPES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }
}
