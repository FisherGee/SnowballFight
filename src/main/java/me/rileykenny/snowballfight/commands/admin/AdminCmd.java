package me.rileykenny.snowballfight.commands.admin;

import me.rileykenny.snowballfight.event.Event;
import me.rileykenny.snowballfight.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCmd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if(!(cmd.getName().equalsIgnoreCase("eventa"))){
            return true;
        }

        if(!(sender instanceof Player)){
            sender.sendMessage(MessageUtil.getLocale("COMMAND_ONLY_IN_GAME"));
            return true;
        }

        Player player = (Player) sender;

        if(args.length > 0){
            if(args[0].equalsIgnoreCase("stop")){
                Event.getInstance().stop();
            }
            if(args.length == 3){
                if(args[0].equalsIgnoreCase("arena")){
                    if(args[1].equalsIgnoreCase("setcorner")){
                        if(args[2].equalsIgnoreCase("1")){
                            Event.getInstance().getArena().setCornerOne(player.getLocation().getBlock());
                            player.sendMessage(MessageUtil.getLocale("COMMAND_SET_CORNER_ONE"));
                            return true;
                        }
                        else if(args[2].equalsIgnoreCase("2")){
                            Event.getInstance().getArena().setCornerTwo(player.getLocation().getBlock());
                            player.sendMessage(MessageUtil.getLocale("COMMAND_SET_CORNER_TWO"));
                            return true;
                        }
                    }
                }
            }
            
            sender.sendMessage(ChatColor.RED + " /eventa stop");
            sender.sendMessage(ChatColor.RED + " /eventa arena setcorner 1");
            sender.sendMessage(ChatColor.RED + " /eventa arena setcorner 2");
        }
        return true;
    }
}
