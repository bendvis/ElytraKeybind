package com.nrojb.elytrakeybind;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ToggleConfig {

    private static final String FILE_NAME = "elytra_toggle.cfg";

    public static void save(boolean value) {
        try {
            File file = new File(Minecraft.getInstance().gameDirectory, FILE_NAME);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(Boolean.toString(value));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean load() {
        try {
            File file = new File(Minecraft.getInstance().gameDirectory, FILE_NAME);
            if (!file.exists()) return true;

            String content = Files.readString(file.toPath()).trim();
            return Boolean.parseBoolean(content);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
