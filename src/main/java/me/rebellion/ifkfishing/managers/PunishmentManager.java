package me.rebellion.ifkfishing.managers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.rebellion.ifkfishing.enums.PunishmentType;
import me.rebellion.ifkfishing.managers.MobManager.MobType;

public class PunishmentManager {

    public static void punish(Player p, PunishmentType type) {
        String message = "";
        switch (type) {
            case LOOK_DOWN:
                message = "&7&oKamu tertidur karena merasa bosan...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                disorientPlayer(p, 90);
                break;
            case LOOK_UP:
                message = "&7&oKamu kehilangan fokus dan melihat ke langit karena suasana yang tenang...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                disorientPlayer(p, -90);
                break;
            case FISHERHUNTER:
                message = "&7&oSepertinya kamu sedang bermimpi...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                MobManager.spawn(p, MobType.FISHERHUNTER);
                break;
            case TRUSTYBEE:
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