package me.rileykenny.snowballfight.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class Event {

    private static Event event;

    private HashSet<EventPlayer> players;
    private HashSet<EventPlayer> playersEliminated;

    private Location spawn;

    public State state;

    //state of the event
    public enum State{
        QUEUE,
        IDLE,
        SESSION
    }

    //singleton to ensure that there is only
    //one event playing at a time.
    private Event(){
        players = new HashSet<>();

        state = State.IDLE;
    }

    public static Event getInstance(){
        if(event == null){
            event = new Event();
        }
        return event;
    }

    //start the event.
    public void start(){
        state = State.SESSION;

        for(EventPlayer player : players){
            player.getBukkitPlayer().teleport(spawn);
            player.getBukkitPlayer().sendMessage(ChatColor.GREEN + "Teleported to the event!");
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
