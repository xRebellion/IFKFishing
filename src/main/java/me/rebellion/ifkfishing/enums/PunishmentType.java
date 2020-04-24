package me.rebellion.ifkfishing.enums;

import java.util.SplittableRandom;

public enum PunishmentType {
    LOOK_DOWN, LOOK_UP, FISHERHUNTER, TRUSTYBEE;

    private static final SplittableRandom random = new SplittableRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static PunishmentType getDefault() {
        return TRUSTYBEE;
    }
}