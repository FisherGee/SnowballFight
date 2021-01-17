package me.rileykenny.snowballfight.event;

import me.rileykenny.snowballfight.Core;
import me.rileykenny.snowballfight.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class EventQueue {

    private int seconds = 0;

    public EventQueue(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(MessageUtil.getLocale("EVENT_COUNTDOWN", Integer.toString(seconds)));
                if (--seconds == 0) this.cancel();
            }
        }.runTaskTimer(Core.getInstance(), 0, 20);
    }

}
