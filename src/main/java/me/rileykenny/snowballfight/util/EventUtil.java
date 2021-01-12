package me.rileykenny.snowballfight.util;

import me.rileykenny.snowballfight.event.EventPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public class EventUtil {

    public enum Team {
        A, B
    }

    public static void setTeams(Collection<EventPlayer> eventPlayers) {
        EventPlayer[] eventPlayerList = (EventPlayer[]) eventPlayers.toArray();
        for (int i = 0; i < eventPlayerList.length; i++) {
            if (i < eventPlayerList.length / 2) {
                eventPlayerList[i].setTeam(Team.A);
            } else {
                eventPlayerList[i].setTeam(Team.B);
            }
        }
    }

}
