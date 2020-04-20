package me.rebellion.ifkfishing.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AFKFishDetectionManager {
    public enum ViolationThreshold {
        SAFE, ABOVE_THRESHOLD, ABOVE_MAX
    }

    private static Map<String, Location> previousFishSpot = new HashMap<>();
    private static Map<String, Integer> violation = new HashMap<>();
    private static final int VIOLATION_THRESHOLD = 20;
    private static final int MAX_VIOLATION = 25;
    private static final int VIOLATION_POINT = 3;

    public static void saveFishSpot(Player p) {
        previousFishSpot.put(p.getName(), p.getLocation());
    }

    public static void checkAFK(Player p) {
        String name = p.getName();
        Location l = p.getLocation();
        if (previousFishSpot.containsKey(name)) {
            int vl = calculateViolation(previousFishSpot.get(name), l);
            if (vl != -1)
                violation.put(name, violation.getOrDefault(name, 0) + vl);
            else
                violation.put(name, 0);

        }
    }

    private static int calculateViolation(Location prev, Location now) {
        if (prev.getPitch() == now.getPitch() && prev.getYaw() == now.getYaw())
            return VIOLATION_POINT;
        else
            return -1;

    }

    public static ViolationThreshold checkViolation(Player p) {
        if (violation.getOrDefault(p.getName(), 0) < VIOLATION_THRESHOLD)
            return ViolationThreshold.SAFE;
        else if (violation.getOrDefault(p.getName(), 0) < MAX_VIOLATION)
            return ViolationThreshold.ABOVE_THRESHOLD;
        else
            return ViolationThreshold.ABOVE_MAX;

    }

    public static void resetViolation(Player p) {
        violation.put(p.getName(), 0);
    }

}