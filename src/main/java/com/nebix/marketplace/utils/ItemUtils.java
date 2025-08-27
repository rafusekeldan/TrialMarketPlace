package com.nebix.marketplace.utils;

import org.bukkit.inventory.ItemStack;

import java.util.Base64;
import java.io.*;

public class ItemUtils {

    public static String itemToBase64(ItemStack item) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream dataOutput = new ObjectOutputStream(outputStream)) {
            dataOutput.writeObject(item);
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            PluginLogger.error("Failed to serialize item: " + e.getMessage());
            return null;
        }
    }

    public static ItemStack itemFromBase64(String base64) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(base64));
             ObjectInputStream dataInput = new ObjectInputStream(inputStream)) {
            return (ItemStack) dataInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            PluginLogger.error("Failed to deserialize item: " + e.getMessage());
            return null;
        }
    }
}