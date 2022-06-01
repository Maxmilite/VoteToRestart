package io.github.maxmilite.votetorestart.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private static FileConfiguration configuration;
    private static File file;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("VoteToRestart").getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfiguration() {
        return configuration;
    }

    public static void saveConfig() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDefaults() {
        configuration.addDefault("vote.timer", 120);
        configuration.addDefault("vote.requirement", 0.5);
        configuration.addDefault("vote.cooldown", 300);
    }
}
