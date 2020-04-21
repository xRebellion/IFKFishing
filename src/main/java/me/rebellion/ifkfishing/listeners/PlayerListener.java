package me.rebellion.ifkfishing.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import me.rebellion.ifkfishing.managers.AFKFishDetectionManager;

public class PlayerListener implements Listener {

    @EventHandler
    public void onFishCatch(PlayerFishEvent e) {
        if (e.getState() != State.CAUGHT_FISH)
            return;
        Player p = e.getPlayer();
        AFKFishDetectionManager.checkAFK(p);
        AFKFishDetectionManager.saveFishSpot(p);
        AFKFishDetectionManager.executePunishment(p);

    }

}