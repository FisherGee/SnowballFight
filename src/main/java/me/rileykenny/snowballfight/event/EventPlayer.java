package me.rileykenny.snowballfight.event;

import org.bukkit.entity.Player;

public class EventPlayer {

    EventManager.Team team;
    private Player player;
    private int points;

    public EventPlayer(Player player, int points) {
        this.player = player;
        this.points = points;
    }

    public void setTeam(EventManager.Team team) {
        this.team = team;
    }

    public EventManager.Team getTeam() {
        return team;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
