package me.rileykenny.snowballfight.event;

import de.tr7zw.nbtapi.NBTItem;
import me.rileykenny.snowballfight.Core;
import me.rileykenny.snowballfight.util.EventUtil;
import me.rileykenny.snowballfight.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Event {

    private static Event event;
    private List<EventPlayer> players;
    private List<EventPlayer> playersEliminated;
    private Arena arena;
    private int gameTime;
    public State state;

    //state of the event
    public enum State {
        QUEUE,
        IDLE,
        SESSION,
        TERMINATED
    }

    // Singleton to ensure that there is only
    // One event playing at a time.
    private Event(){
        gameTime = 120;
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

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), () -> {
            int teamA = 0;
            int teamB = 0;

            for(EventPlayer eventPlayer : players){
                if(eventPlayer.getTeam() == EventUtil.Team.A){
                    teamA++;
                }else{
                    teamB++;
                }
            }

            if(teamA == 0 && teamB == 0){
                event.globalEventBroadcast("&b&lTie");
                event.stop();
            }
            else if(teamA == 0 && teamB > 0){
                event.globalEventBroadcast("&b&lTeam A has won");
                event.stop();
            }
            else if(teamA > 0 && teamB == 0){
                event.globalEventBroadcast("&b&lTeam B has won");
                event.stop();
            }
        }, 20, gameTime * 20);

        event.globalEventBroadcast("&c&lEvent has ended");
        event.stop();
    }

    public void stop(){
        state = State.TERMINATED;

        for(EventPlayer player : players){
            player.restoreInventory();
            player.getPlayer().teleport(player.getOldLocation());
            player.getPlayer().sendMessage(MessageUtil.getLocale("EVENT_END_BROADCAST"));
        }

        for(EventPlayer player : playersEliminated){
            player.restoreInventory();
            player.getPlayer().teleport(player.getOldLocation());
            player.getPlayer().sendMessage(MessageUtil.getLocale("EVENT_END_BROADCAST"));

            //clear the event data
            event = null;
        }
    }

    public boolean playerExists(EventPlayer player, Collection<EventPlayer> coll){
        return coll.contains(player);
    }

    public Arena getArena(){
        return arena;
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
        return eventPlayer.getTeam() == eventPlayer2.getTeam();
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

    public void globalEventBroadcast(String message){
        for(EventPlayer player : players){
            player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }

        for(EventPlayer player : playersEliminated){
            player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
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
