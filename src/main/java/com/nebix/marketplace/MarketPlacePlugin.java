package com.nebix.marketplace;

import com.nebix.marketplace.config.ConfigManager;
import com.nebix.marketplace.utils.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

public final class MarketPlacePlugin extends JavaPlugin {

    private static MarketPlacePlugin instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;

        registerManagers();

        PluginLogger.info("MarketPlace enabled successfully!");

    }

    @Override
    public void onDisable() {
        PluginLogger.info("MarketPlace disabled.");
    }

    private void registerManagers() {
        this.configManager = new ConfigManager(this);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static MarketPlacePlugin getInstance() {
        return instance;
    }
}