package me.rileykenny.snowballfight.event;

import me.rileykenny.snowballfight.Core;
import me.rileykenny.snowballfight.util.EventUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.*;

public class Event {

    private static Event event;
    private List<EventPlayer> players;
    private List<EventPlayer> playersEliminated;
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
    private Event(){
    }

    public static Event getInstance() {
        if (event == null) {
            event = new Event();
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

    public boolean playerExists(EventPlayer player, Collection coll){
        if(coll.contains(player))
            return true;

        return false;
    }

    public void addEventPlayer(EventPlayer eventPlayer) {
        if(playerExists(eventPlayer, players)){
            return;
        }
        players.add(eventPlayer);
    }

    public void addEliminatedEventPlayer(EventPlayer eventPlayer) {
        if(playerExists(eventPlayer, playersEliminated)){
            return;
        }
        playersEliminated.add(eventPlayer);
    }

    public boolean isPlayerEvent(Player player) {
        for (EventPlayer eventPlayer : players) {
            if (eventPlayer.getPlayer() == player) {
                return true;
            }
        }

        return false;
    }


    public EventPlayer getEventPlayer(Player player) {
        for (EventPlayer eventPlayer : players) {
            if (eventPlayer.getPlayer() == player) {
                return eventPlayer;
            }
        }
        return null;
    }

    public State getState() {
        return state;
    }

    public void setArena(Arena arena){
        this.arena = arena;
    }

    //flat surface only -- randomly spawn players in arena
    public void spawnPlayers(Arena arena){
        int arenaLength, arenaWidth, arenaYFloor;

        arenaLength = arena.getCornerOne().getX() - arena.getCornerTwo().getX();
        arenaWidth = arena.getCornerOne().getZ() - arena.getCornerTwo().getZ();
        arenaYFloor = arena.getCornerOne().getY() + 1;

        List<Block> spawnPositions = new ArrayList<>();
        Random randomGen = new Random();

        int locationsNeeded = players.size();

        for(int i = 1; i < arenaLength; i++){
            for(int j = 1; j < arenaWidth && j < locationsNeeded; j++){

                int numWidth = randomGen.nextInt(arenaWidth - 1) + 1;
                int numLength = randomGen.nextInt(arenaWidth - 1) + 1;
                Block spawnBlock = Bukkit.getWorld("world").getBlockAt(numWidth, numLength,arenaYFloor);

                for(int a = 0; a < spawnPositions.size(); a++){
                    if(spawnPositions.get(a).equals(spawnBlock)){
                        a--;
                        numWidth = randomGen.nextInt(arenaWidth - 1) + 1;
                        numLength = randomGen.nextInt(arenaWidth - 1) + 1;
                        spawnBlock = Bukkit.getWorld("world").getBlockAt(numWidth, numLength,arenaYFloor);
                        continue;
                    }
                }
            }
        }

        for(int i = 0; i < players.size(); i++){
            players.get(i).getPlayer().teleport(spawnPositions.get(i).getLocation());
        }
    }

}
