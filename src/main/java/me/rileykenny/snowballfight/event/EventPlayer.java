package me.rileykenny.snowballfight.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class EventPlayer {

    private EventUtil.Team team;
    private Player player;
    private int points;
    private PlayerInventory oldInventory;

    public EventPlayer(Player player, int points) {
        this.player = player;
        this.points = points;
        this.oldInventory = player.getInventory();
    }

    public void setTeam(EventUtil.Team team) {
        this.team = team;
    }

    public EventUtil.Team getTeam() {
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

    public PlayerInventory getOldInventory() {
        return oldInventory;
    }
}
