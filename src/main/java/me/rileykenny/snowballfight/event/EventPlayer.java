package me.rileykenny.snowballfight.event;

import org.bukkit.entity.Player;

public class EventPlayer {

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
