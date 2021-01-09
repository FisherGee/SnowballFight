package me.rileykenny.snowballfight.event;

import me.rileykenny.snowballfight.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class Event {

    private static Event event;
    private HashSet<EventPlayer> players;
    private HashSet<EventPlayer> playersEliminated;
    private Arena arena;
    public State state;

    //state of the event
    public enum State {
        QUEUE,
        IDLE,
        SESSION
    }

    // Singleton to ensure that there is only
    // One event playing at a time.
    private Event(Arena arena) {
        this.players = new HashSet<>();
        this.state = State.IDLE;
        this.arena = arena;
    }

    public static Event getInstance() {
        if (event == null) {

            World world = Core.getInstance().getServer().getWorld("world");

            event = new Event(new Arena(
                        new Location(world, 0.0, 0.0, 0.0),
                        new Location(world, -10.0, 0.0, -10.0),
                        new Location(world, 10.0, 0.0, 10.0)
                ));
        }

        return event;
    }

    //start the event.
    public void start() {
        state = State.SESSION;
        EventUtil.setTeams(players);

        for (EventPlayer player : players) {
            player.getPlayer().teleport(arena.getSpawnLocation());
            player.getPlayer().sendMessage(ChatColor.GREEN + "Teleported to the event!");
            addEventPlayer(player);
            player.getPlayer().getInventory().clear();
        }


    }

    public void addEventPlayer(EventPlayer eventPlayer){
        players.add(eventPlayer);
    }

    public void addEliminatedEventPlayer(EventPlayer eventPlayer){
        playersEliminated.add(eventPlayer);
    }

    public State getState(){
        return state;
    }

    public boolean isPlayerEvent(Player player){
        for(EventPlayer eventPlayer : players){
            if(eventPlayer.getPlayer() == player){
                return true;
            }
        }
        return false;
    }


    public EventPlayer getEventPlayer(Player player){
        for(EventPlayer eventPlayer : players){
            if(eventPlayer.getPlayer() == player){
                return eventPlayer;
            }
        }
        return null;
    }


}
