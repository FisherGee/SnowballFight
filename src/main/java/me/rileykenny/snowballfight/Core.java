package me.rileykenny.snowballfight;

import me.rileykenny.snowballfight.commands.EventStart;
import me.rileykenny.snowballfight.event.listeners.PlayerInventory;
import me.rileykenny.snowballfight.event.listeners.SnowballHitListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Core extends JavaPlugin {

    private static Core instance;
    private FileConfiguration messageConfig;

    @Override
    public void onEnable() {
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
        this.saveResource("messages.yml", false);
        this.messageConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "messages.yml"));
        this.messageConfig.options().copyDefaults(true);
    }

    public FileConfiguration getMessageConfig() {
        return messageConfig;
    }

    public static Core getInstance() {
        return instance;
    }
}
