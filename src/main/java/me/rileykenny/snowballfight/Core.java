package me.rileykenny.snowballfight;

import me.rileykenny.snowballfight.commands.EventStart;
import me.rileykenny.snowballfight.util.EventUtil;
import me.rileykenny.snowballfight.event.listeners.PlayerInventory;
import me.rileykenny.snowballfight.event.listeners.SnowballHitListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Core extends JavaPlugin {

    private EventUtil eventUtil;
    private static Core instance;
    private FileConfiguration messageConfig;

    @Override
    public void onEnable() {
        eventUtil = new EventUtil();
        instance = this;

        registerListeners();
        registerCommands();
        loadConfig();
    }

    public void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new SnowballHitListener(), this);
        pluginManager.registerEvents(new PlayerInventory(), this);
    }

    public void registerCommands() {
        this.getCommand("event").setExecutor(new EventStart());
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.messageConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "messages.yml"));
        this.messageConfig.options().copyDefaults(true);
    }

    public EventUtil getEventUtil() {
        return eventUtil;
    }

    public static Core getInstance() {
        return instance;
    }
}
