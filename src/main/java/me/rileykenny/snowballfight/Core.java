package me.rileykenny.snowballfight;

import me.rileykenny.snowballfight.event.EventManager;
import me.rileykenny.snowballfight.event.listeners.SnowballHitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private EventManager eventManager;
    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        eventManager = new EventManager();

        registerListeners();
    }

    public void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new SnowballHitListener(), this);
    }

    public EventManager getEventManager(){
        return eventManager;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
