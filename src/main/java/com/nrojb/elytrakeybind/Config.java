package com.nrojb.elytrakeybind;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue disableChatMessages = BUILDER
            .comment("Toggle the \"Elytra Toggle: true\" messages")
            .define("disableChatMessages", false);

    static final ModConfigSpec SPEC = BUILDER.build();
}
