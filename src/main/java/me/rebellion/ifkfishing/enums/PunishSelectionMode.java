package me.rebellion.ifkfishing.enums;

public enum PunishSelectionMode {
    STREAK, RANDOM, NONE;

    public static PunishSelectionMode getDefault() {
        return RANDOM;
    }

}