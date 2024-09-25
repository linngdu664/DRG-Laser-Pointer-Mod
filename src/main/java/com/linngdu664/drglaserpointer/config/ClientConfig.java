package com.linngdu664.drglaserpointer.config;

import com.linngdu664.drglaserpointer.Main;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ClientConfig extends Config {
    public static ConfigValueHolder<Boolean> CUBE_MARK_MODEL = new ConfigValueHolder(Main.MODID, "client/cube_mark_model", (builder) ->
            builder.comment("Set to true to enable cube mark model.")
                    .define("cubeMarkModel", false));
    public static ConfigValueHolder<Integer> MARK_DISPLAY_RANGE = new ConfigValueHolder(Main.MODID, "client/mark_display_range", (builder) ->
            builder.comment("The display range of marks. Default value: 96")
                    .defineInRange("markDisplayRange", 96, 0, 511));

    public static final ClientConfig INSTANCE;
    public static final ForgeConfigSpec SPEC;

    public ClientConfig(ForgeConfigSpec.Builder builder) {
        super(Main.MODID, "client", builder);
    }

    static {
        Pair<ClientConfig, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(ClientConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }
}