package me.rileykenny.snowballfight;

import me.rileykenny.snowballfight.event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private EventManager eventManager;
    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        eventManager = new EventManager();
    }

    public EventManager getEventManager(){
        return eventManager;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
