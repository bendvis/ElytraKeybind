package com.nrojb.elytrakeybind;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = ElytraKeybind.MODID, dist = Dist.CLIENT)
public class ElytraKeybind {
    public static final String MODID = "elytrakeybind";

    public static boolean elytraToggleEnabled;

    public ElytraKeybind(IEventBus modEventBus, ModContainer container) {

        // Load previous value
        elytraToggleEnabled = ToggleConfig.load();

        container.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(KeybindHandler::registerBindings);
        NeoForge.EVENT_BUS.addListener(KeybindHandler::onClientTick);
    }
}