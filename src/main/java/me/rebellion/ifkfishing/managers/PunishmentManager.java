package me.rebellion.ifkfishing.managers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.rebellion.ifkfishing.managers.MobManager.MobType;

public class PunishmentManager {
    public static void punish(Player p, int level) {
        String message = "";
        switch (level) {
            case 0:
                message = "&7&oKamu tertidur karena merasa bosan...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                disorientPlayer(p, 90);
                break;
            case 1:
                message = "&7&oKamu kehilangan fokus dan melihat ke langit karena suasana yang tenang...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                disorientPlayer(p, -90);
                break;
            case 2:
                message = "&7&oSepertinya kamu sedang bermimpi...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                MobManager.spawn(p, MobType.FISHERHUNTER);
                break;
            case 3:
                message = "&7&oSepertinya kamu sedang bermimpi...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                MobManager.spawn(p, MobType.TRUSTYBEE);
                break;
        }
    }

    private static void disorientPlayer(Player p, int pitch) {
        // Temporary
        Location newLoc = p.getLocation();
        newLoc.setPitch(pitch);
        p.teleport(newLoc);
    }

}