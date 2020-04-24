package me.rebellion.ifkfishing.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.rebellion.ifkfishing.enums.PunishSelectionMode;

public class NotificationManager {
    private static final String NOTIFICATION_MESSAGE = "&aAFK-Fishing detected for %PLAYER% on X: %X% Y: %Y% Z: %Z%. %STREAK%";
    private static final PunishSelectionMode mode = PunishSelectionMode.getDefault();

    public static void notifyStaff(Player p, int streak) {
        String message = NOTIFICATION_MESSAGE.replace("%PLAYER%", p.getName())
                .replace("%X%", Integer.toString(p.getLocation().getBlockX()))
                .replace("%Y%", Integer.toString(p.getLocation().getBlockY()))
                .replace("%Z%", Integer.toString(p.getLocation().getBlockZ()));

        if (mode == PunishSelectionMode.STREAK) {
            message = message.replace("%STREAK%", "Streaks: " + Integer.toString(streak));
        } else if (mode == PunishSelectionMode.RANDOM || mode == PunishSelectionMode.NONE) {
            message = message.replace("%STREAK%", "");
        }

        message = ChatColor.translateAlternateColorCodes('&', message);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("ifkfishing.notification")) {
                player.sendMessage(message);
            }
        }
    }
}