package me.rileykenny.snowballfight.event.listeners;

import me.rileykenny.snowballfight.event.Event;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PlayerInventory implements Listener {

    @EventHandler
    public void pickUpEvent(EntityPickupItemEvent event) {
        cancelEventDuringSession(event);
    }

    @EventHandler
    public void dropItemEvent(EntityDropItemEvent event) {
        cancelEventDuringSession(event);
    }

    public void cancelEventDuringSession(EntityEvent event) {
        Event snowBallEvent = Event.getInstance();
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;
        if (player == null) return;

        if (snowBallEvent.getState().equals(Event.State.SESSION)) {
            if (snowBallEvent.isPlayerEvent(player)) {
                if (event instanceof Cancellable) {
                    Cancellable cancellableEvent = (Cancellable) event;
                    cancellableEvent.setCancelled(true);
                }
            }
        }
    }

}
