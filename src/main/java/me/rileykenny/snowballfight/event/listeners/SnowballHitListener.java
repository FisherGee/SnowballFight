package me.rileykenny.snowballfight.event.listeners;

import me.rileykenny.snowballfight.event.Event;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowballHitListener implements Listener{

    @EventHandler
    public void onSnowballHitEvent(ProjectileHitEvent e){

        if(e.getEntity() instanceof Player && e.getHitEntity() instanceof Player){
            Player thrower = (Player) e.getEntity();
            Player hit = (Player) e.getHitEntity();

            if(Event.getInstance().getState() == Event.State.SESSION){
                if(Event.getInstance().isPlayerEvent(thrower) && Event.getInstance().isPlayerEvent(hit)){
                    Event.getInstance().addEliminatedEventPlayer(Event.getInstance().getEventPlayer(hit));

                    thrower.playSound(thrower.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    thrower.sendMessage(ChatColor.GREEN + "Killed player " + hit.getDisplayName());
                    hit.sendMessage(ChatColor.RED + "You have been eliminated by " + thrower.getDisplayName());
                    hit.playSound(thrower.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 10);
                }
            }


        }

    }
}