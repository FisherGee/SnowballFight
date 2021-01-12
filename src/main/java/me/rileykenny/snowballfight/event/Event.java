package me.rileykenny.snowballfight.event;

import de.tr7zw.nbtapi.NBTItem;
import me.rileykenny.snowballfight.Core;
import me.rileykenny.snowballfight.util.EventUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
            NBTItem snowBall = new NBTItem(new ItemStack(Material.SNOW_BLOCK, 2304));
            snowBall.setString("id", "snowballfight");
            player.getPlayer().getInventory().addItem(snowBall.getItem());
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

    public boolean isSameTeam(EventPlayer eventPlayer, EventPlayer eventPlayer2){
        if(eventPlayer.getTeam() == eventPlayer2.getTeam())
            return true;
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

    //randomly spawn players in arena
    public void spawnPlayers(Arena arena){
        int arenaLength, arenaWidth;

        arenaLength = arena.getCornerTwo().getX();
        arenaWidth = arena.getCornerTwo().getZ();

        List<Block> spawnPositions = new ArrayList<>();
        Random randomGen = new Random();

        int locationsNeeded = players.size();

        for(int i = 0; i != locationsNeeded ; i++){
            int numWidth = randomGen.nextInt(arenaWidth - arena.getCornerOne().getX()) + arena.getCornerOne().getX();
            int numLength = randomGen.nextInt(arenaLength - arena.getCornerOne().getZ()) + arena.getCornerOne().getZ();

            Block spawnBlock = Bukkit.getWorld("world").getHighestBlockAt(numWidth, numLength);

            if(spawnPositions.contains(spawnBlock)){
                i--;
                continue;
            }
        }

        for(int i = 0; i < players.size(); i++){
            players.get(i).getPlayer().teleport(spawnPositions.get(i).getLocation());
        }
    }

}
