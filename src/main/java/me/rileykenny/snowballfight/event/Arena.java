package me.rileykenny.snowballfight.event;

import org.bukkit.Location;

public class Arena {

    private Location spawnLocation;
    private Location cornerOne;
    private Location cornerTwo;

    public Arena(Location spawnLocation, Location cornerOne, Location cornerTwo) {
        this.spawnLocation = spawnLocation;
        this.cornerOne = cornerOne;
        this.cornerTwo = cornerTwo;
    }

    public void inArena(Location location) {
        // TODO
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public Location getCornerOne() {
        return cornerOne;
    }

    public void setCornerOne(Location cornerOne) {
        this.cornerOne = cornerOne;
    }

    public Location getCornerTwo() {
        return cornerTwo;
    }

    public void setCornerTwo(Location cornerTwo) {
        this.cornerTwo = cornerTwo;
    }
}
