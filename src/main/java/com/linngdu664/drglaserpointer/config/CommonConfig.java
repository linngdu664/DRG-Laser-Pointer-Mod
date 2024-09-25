package com.linngdu664.drglaserpointer.config;

import com.linngdu664.drglaserpointer.Main;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig extends Config {
    public static ConfigValueHolder<Integer> LASER_RANGE = new ConfigValueHolder(Main.MODID, "common/laser_range", (builder) ->
            builder.comment("The range of laser. Default value: 64")
                    .defineInRange("laserRange", 64, 0, 511));

    public static final CommonConfig INSTANCE;
    public static final ForgeConfigSpec SPEC;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        super(Main.MODID, "common", builder);
    }

    static {
        Pair<CommonConfig, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(CommonConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }
}
