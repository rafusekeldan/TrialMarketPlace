package com.nebix.marketplace.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscordWebhook {

    public static void sendWebhook(String webhookUrl, String content) {
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String payload = String.format("{\"content\":\"%s\"}", content.replace("\"", "\\\""));

            try (OutputStream os = connection.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != 204 && responseCode != 200) {
                PluginLogger.warn("Failed to send webhook: HTTP " + responseCode);
            }
        } catch (Exception e) {
            PluginLogger.error("Webhook error: " + e.getMessage());
        }
    }
}
