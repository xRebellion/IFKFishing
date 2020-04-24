package me.rebellion.ifkfishing.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.rebellion.ifkfishing.enums.PunishSelectionMode;
import me.rebellion.ifkfishing.enums.PunishmentType;

public class AFKFishDetectionManager {

    private static Map<String, Location> previousFishSpot = new HashMap<>();
    private static Map<String, Integer> violation = new HashMap<>();
    private static Map<String, Integer> streak = new HashMap<>();
    private static final int VIOLATION_THRESHOLD = 20;
    private static final int MAX_VIOLATION = 23;
    private static final int VIOLATION_POINT = 4;
    private static final int MAX_STREAK = 4;

    private static PunishmentType punishmentType = PunishmentType.getDefault();
    private static PunishSelectionMode scoringMode = PunishSelectionMode.getDefault();

    public static void saveFishSpot(Player p) {
        previousFishSpot.put(p.getName(), p.getLocation());
    }

    public static void checkAFK(Player p) {
        String name = p.getName();
        Location l = p.getLocation();
        if (previousFishSpot.containsKey(name)) {
            int vl = calculateViolation(previousFishSpot.get(name), l);
            int totalvl = (violation.getOrDefault(name, 0) + vl + streak.getOrDefault(name, 0));
            totalvl = totalvl > 0 ? totalvl : 0;
            violation.put(name, totalvl);

        }
    }

    private static int calculateViolation(Location prev, Location now) {
        if (prev.getPitch() == now.getPitch() && prev.getYaw() == now.getYaw())
            return VIOLATION_POINT;
        else
            return -VIOLATION_POINT * 2;

    }

    private static void resetViolation(Player p) {
        violation.put(p.getName(), 0);
    }

    private static void streakPunish(Player p) {
        int numStreak = streak.get(p.getName());
        streak.putIfAbsent(p.getName(), 0);
        streak.put(p.getName(), numStreak + 1);
        switch (numStreak) {
            case 1:
                PunishmentManager.punish(p, PunishmentType.LOOK_DOWN);
                break;
            case 2:
                PunishmentManager.punish(p, PunishmentType.LOOK_UP);
                break;
            case 3:
                PunishmentManager.punish(p, PunishmentType.TRUSTYBEE);
                break;
            case 4:
                PunishmentManager.punish(p, PunishmentType.FISHERHUNTER);
                break;
        }
        resetViolation(p);
        if ((numStreak) >= MAX_STREAK) {
            streak.put(p.getName(), 0);
        }
    }

    public static void executePunishment(Player p) {
        if (violation.getOrDefault(p.getName(), 0) < VIOLATION_THRESHOLD)
            return;
        else if (violation.getOrDefault(p.getName(), 0) < MAX_VIOLATION) {
            String message = "&7&oRasanya ada yang aneh...";
            message = ChatColor.translateAlternateColorCodes('&', message);
            p.sendMessage(message);
        } else {
            if (scoringMode == PunishSelectionMode.STREAK) {
                streakPunish(p);
            } else if (scoringMode == PunishSelectionMode.RANDOM) {
                PunishmentType value = PunishmentType.randomEnum(PunishmentType.class);
                PunishmentManager.punish(p, value);
            } else {
                PunishmentManager.punish(p, punishmentType);
            }
            NotificationManager.notifyStaff(p, streak.getOrDefault(p.getName(), 0));

        }

    }
}