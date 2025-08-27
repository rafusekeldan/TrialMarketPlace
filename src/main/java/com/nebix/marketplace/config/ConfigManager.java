package com.nebix.marketplace.config;

import com.nebix.marketplace.utils.TextUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin plugin;

    private FileConfiguration config;
    private FileConfiguration menusConfig;
    private FileConfiguration messagesConfig;

    private File menusFile;
    private File messagesFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        setupConfigs();
    }

    private void setupConfigs() {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();

        menusFile = new File(plugin.getDataFolder(), "menus.yml");
        if (!menusFile.exists()) {
            plugin.saveResource("menus.yml", false);
        }
        menusConfig = YamlConfiguration.loadConfiguration(menusFile);

        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);

        TextUtils.debug("Configuration files loaded successfully.");
    }

    public void reloadAll() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
        this.menusConfig = YamlConfiguration.loadConfiguration(menusFile);
        this.messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        TextUtils.debug("All configuration files reloaded.");
    }

    public void saveMenus() {
        try {
            menusConfig.save(menusFile);
        } catch (IOException e) {
            TextUtils.debug("Could not save menus.yml: " + e.getMessage());
        }
    }

    public void saveMessages() {
        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
            TextUtils.debug("Could not save messages.yml: " + e.getMessage());
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public FileConfiguration getMenuConfig() {
        return menusConfig;
    }

    public FileConfiguration getMessageConfig() {
        return messagesConfig;
    }
}
