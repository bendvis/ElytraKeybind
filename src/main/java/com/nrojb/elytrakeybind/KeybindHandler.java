package com.nrojb.elytrakeybind;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class KeybindHandler {

    public static final KeyMapping TOGGLE_ELYTRA =
            new KeyMapping("Toggle Elytra Flight", GLFW.GLFW_KEY_B, "key.elytrakeybind.category");

    private static boolean wasDown = false;

    // This is a MOD BUS event (IModBusEvent) – wired via modEventBus.addListener(...)
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_ELYTRA);
    }

    // This is a GAMEPLAY event – wired via NeoForge.EVENT_BUS.addListener(...)

    public static void onClientTick(ClientTickEvent.Post event) {
        boolean isDown = TOGGLE_ELYTRA.isDown();

        if (isDown && !wasDown) {
            ElytraKeybind.elytraToggleEnabled = !ElytraKeybind.elytraToggleEnabled;

            // Save new value
            ToggleConfig.save(ElytraKeybind.elytraToggleEnabled);

            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && !Config.disableChatMessages.getAsBoolean()) {
                player.sendSystemMessage(
                        Component.literal("Elytra toggle: " + ElytraKeybind.elytraToggleEnabled)
                );
            }
        }

        wasDown = isDown;
    }

}
