package me.rileykenny.snowballfight.event;

import org.bukkit.entity.Player;

public class EventPlayer {

    EventManager.Team team;

    //required to do bukkit stuff like teleport
    Player bukkitPlayer;

    public Player getBukkitPlayer(){
        return bukkitPlayer;
    }

    public void setTeam(EventManager.Team team) {
        this.team = team;
    }
    private Player player;
    private int points;

    public EventPlayer(Player player, int points) {
        this.player = player;
        this.points = points;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
