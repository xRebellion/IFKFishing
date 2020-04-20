package me.rebellion.ifkfishing;

import org.bukkit.plugin.java.JavaPlugin;

import me.rebellion.ifkfishing.listeners.PlayerListener;

public class IFKFishingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

}
