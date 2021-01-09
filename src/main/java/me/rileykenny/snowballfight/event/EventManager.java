package me.rileykenny.snowballfight.event;

import java.util.Collection;

public class EventManager {

    public enum Team {
        A, B
    }

    public EventManager() {

    }

    //split into two teams
    public void setTeams(Collection<EventPlayer> eventPlayers) {
        EventPlayer[] eventPlayerList = (EventPlayer[]) eventPlayers.toArray();
        for(int i = 0; i < eventPlayerList.length; i++){

            if(i < eventPlayerList.length / 2)
                eventPlayerList[i].setTeam(Team.A);
            else{
                eventPlayerList[i].setTeam(Team.B);
            }
        }
    }
}
