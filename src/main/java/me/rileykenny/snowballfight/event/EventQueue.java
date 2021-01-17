package me.rileykenny.snowballfight.event;

import me.rileykenny.snowballfight.Core;
import me.rileykenny.snowballfight.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EventQueue {

    private int seconds = 0;

    public EventQueue(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        Bukkit.getScheduler().runTaskTimer(Core.getInstance(), () -> {
            if (seconds != 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(MessageUtil.getLocale("EVENT_COUNTDOWN", Integer.toString(seconds)));
                }

                seconds--;
            }
        }, 0L, 20L);
    }

}
