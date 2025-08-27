package com.nebix.marketplace.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class TextUtils {

    private static final String PREFIX = color("&7[&bMarketPlace&7] ");
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,###.##");

    public static String color(String input) {
        if (input == null) return "";
        return ChatColor.translateAlternateColorCodes('&', input.replace("§", "&"));
    }

    public static String format(String message) {
        return PREFIX + color(message);
    }

    public static void send(CommandSender sender, String message) {
        if (message != null && !message.isEmpty()) {
            sender.sendMessage(format(message));
        }
    }

    public static void sendRaw(CommandSender sender, String message) {
        if (message != null && !message.isEmpty()) {
            sender.sendMessage(color(message));
        }
    }

    public static String formatPrice(double value) {
        return PRICE_FORMAT.format(value);
    }

    public static String replacePlaceholders(String message, Player player, double price) {
        return color(message
                .replace("%player%", player.getName())
                .replace("%uuid%", player.getUniqueId().toString())
                .replace("%price%", formatPrice(price))
        );
    }

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return java.util.Arrays.stream(input.split(" "))
                .map(word -> word.length() > 1
                        ? Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase()
                        : word.toUpperCase())
                .collect(Collectors.joining(" "));
    }

    public static List<String> formatList(List<String> lines) {
        return lines.stream().map(TextUtils::color).collect(Collectors.toList());
    }

    public static String join(List<String> items) {
        return color(String.join(", ", items));
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(color(title), color(subtitle), fadeIn, stay, fadeOut);
    }

    public static String center(String msg) {
        int centerPx = 154;
        int messagePxSize = getMessagePxSize(msg);
        int spaces = (centerPx - messagePxSize / 2) / 4;
        return " ".repeat(Math.max(0, spaces)) + color(msg);
    }

    private static int getMessagePxSize(String msg) {
        int px = 0;
        boolean bold = false;
        for (char c : ChatColor.stripColor(color(msg)).toCharArray()) {
            if (c == ' ') {
                px += 4;
            } else {
                px += bold ? 6 : 5;
            }
        }
        return px;
    }

    public static void debug(String message) {
        if (Bukkit.getPluginManager().isPluginEnabled("MarketPlace")) {
            Bukkit.getConsoleSender().sendMessage(color("&8[&3DEBUG&8] &7" + message));
        }
    }
}
