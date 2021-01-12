package me.rileykenny.snowballfight.event.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.rileykenny.snowballfight.event.Event;
import me.rileykenny.snowballfight.event.EventPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class SnowballHitListener implements Listener{

    @EventHandler
    public void onSnowballHitEvent(ProjectileHitEvent e){

        if(e.getEntity() instanceof Player && e.getHitEntity() instanceof Player){
            Player thrower = (Player) e.getEntity();
            Player hit = (Player) e.getHitEntity();

            NBTItem snowBall = new NBTItem(new ItemStack(Material.SNOW_BLOCK));
            snowBall.setString("id", "snowballfight");
            thrower.getPlayer().getInventory().addItem(snowBall.getItem());

            if(Event.getInstance().getState() == Event.State.SESSION){

                if(Event.getInstance().isPlayerEvent(thrower) && Event.getInstance().isPlayerEvent(hit)){
                    EventPlayer hitEventPlayer = Event.getInstance().getEventPlayer(hit);
                    EventPlayer throwEventPlayer = Event.getInstance().getEventPlayer(thrower);

                    if(Event.getInstance().isSameTeam(hitEventPlayer, throwEventPlayer)){
                        thrower.playSound(thrower.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 10);
                        return;
                    }

                    Event.getInstance().addEliminatedEventPlayer(hitEventPlayer);

                    thrower.playSound(thrower.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    thrower.sendMessage(ChatColor.GREEN + "Killed player " + hit.getDisplayName());
                    hit.sendMessage(ChatColor.RED + "You have been eliminated by " + thrower.getDisplayName());
                    hit.playSound(thrower.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 10);
                }
            }


        }

    }
}