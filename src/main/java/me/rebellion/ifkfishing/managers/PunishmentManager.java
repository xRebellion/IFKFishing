package me.rebellion.ifkfishing.managers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PunishmentManager {

    public static void disorientPlayer(Player p) {
        // Temporary
        Location newLoc = p.getLocation();
        newLoc.setPitch(90);
        p.teleport(newLoc);
    }
}