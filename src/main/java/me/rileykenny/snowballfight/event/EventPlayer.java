package me.rileykenny.snowballfight.event;

import org.bukkit.entity.Player;

public class EventPlayer {

    EventManager.Team team;

    //required to do bukkit stuff like teleport
    Player bukkitPlayer;

    public Player getBukkitPlayer(){
        return bukkitPlayer;
    }

    public void setTeam(EventManager.Team team){
        this.team = team;
    }
}
