package me.rebellion.ifkfishing.managers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.rebellion.ifkfishing.managers.MobManager.MobType;

public class PunishmentManager {

    public static void punish(Player p, int level) {
        switch (level) {
            case 0:
                disorientPlayer(p);
                break;
            case 1:
                String message = "&7&oSepertinya kamu sedang bermimpi...";
                message = ChatColor.translateAlternateColorCodes('&', message);
                p.sendMessage(message);
                MobManager.spawn(p, MobType.FISHERHUNTER);
                break;
            case 2:
                storeFishingRod(p);
                break;
        }
    }

    private static void disorientPlayer(Player p) {
        // Temporary
        String message = "&7&oKamu tertidur karena memancing...";
        message = ChatColor.translateAlternateColorCodes('&', message);
        p.sendMessage(message);
        Location newLoc = p.getLocation();
        newLoc.setPitch(90);
        p.teleport(newLoc);
    }

    private static void storeFishingRod(Player p) {
        ItemStack handItem = p.getInventory().getItemInMainHand();
        int emptySlot = p.getInventory().firstEmpty();
        if (emptySlot != -1) {
            p.getInventory().setItem(emptySlot, handItem);
            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        } else {
            ItemStack temp = p.getInventory().getItemInMainHand();
            p.getInventory().setItemInMainHand(p.getInventory().getItem(9));
            p.getInventory().setItem(9, temp);
        }
    }
}