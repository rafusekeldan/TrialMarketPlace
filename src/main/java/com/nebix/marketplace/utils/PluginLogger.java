package com.nebix.marketplace.utils;

import org.bukkit.Bukkit;

import static com.nebix.marketplace.utils.TextUtils.color;

public class PluginLogger {
    private static final String PREFIX = color("&7[&bMarketPlace&7] ");

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(color(PREFIX + message));
    }

    public static void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(color(PREFIX + "&eWARNING: " + message));
    }

    public static void error(String message) {
        Bukkit.getConsoleSender().sendMessage(color(PREFIX + "&cERROR: " + message));
    }
}