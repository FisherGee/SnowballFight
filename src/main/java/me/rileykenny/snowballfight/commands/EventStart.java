package me.rileykenny.snowballfight.commands;

import me.rileykenny.snowballfight.Core;
import me.rileykenny.snowballfight.event.Event;
import me.rileykenny.snowballfight.event.EventQueue;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class EventStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;
        if (player == null) return false;
        if (!label.equals("event")) return false;

        if (args[0].equals("start")) {
            new EventQueue(60).start();
            Event.getInstance().start();
        }

        return true;


    }
}
