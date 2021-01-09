package me.rileykenny.snowballfight.event;

import me.rileykenny.snowballfight.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EventQueue {

    private int seconds = 0;

    public EventQueue(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), () -> {
            while (seconds != 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(ChatColor.YELLOW + Integer.toString(seconds) + " remaining!");
                }
            }
        }, 0L, 20L);
    }

}
