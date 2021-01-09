package me.rileykenny.snowballfight;

import me.rileykenny.snowballfight.event.EventUtil;
import me.rileykenny.snowballfight.event.listeners.SnowballHitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private EventUtil eventUtil;
    private static Core instance;

    @Override
    public void onEnable() {
        eventUtil = new EventUtil();
        instance = this;

        registerListeners();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new SnowballHitListener(), this);
    }

    public EventUtil getEventManager() {
        return eventUtil;
    }

    public static Core getInstance() {
        return instance;
    }
}
