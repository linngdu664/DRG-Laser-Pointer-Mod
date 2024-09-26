package com.linngdu664.drglaserpointer.config;

import com.linngdu664.drglaserpointer.Main;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig extends Config {
    public static ConfigValueHolder<Integer> LASER_RANGE = new ConfigValueHolder(Main.MODID, "common/laser_range", (builder) ->
            builder.comment("The range of laser. Default value: 64")
                    .defineInRange("laserRange", 64, 0, 511));

    public static final CommonConfig INSTANCE;
    public static final ModConfigSpec SPEC;

    public CommonConfig(ModConfigSpec.Builder builder) {
        super(Main.MODID, "common", builder);
    }

    static {
        Pair<CommonConfig, ModConfigSpec> specPair = (new ModConfigSpec.Builder()).configure(CommonConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }
}
