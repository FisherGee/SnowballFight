package me.rileykenny.snowballfight.event;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Arena {

    private Location spawnLocation;
    private Block cornerOne;
    private Block cornerTwo;

    public Arena(Location spawnLocation, Block cornerOne, Block cornerTwo) {
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

    public Block getCornerOne() {
        return cornerOne;
    }

    public void setCornerOne(Block cornerOne) {
        this.cornerOne = cornerOne;
    }

    public Block getCornerTwo() {
        return cornerTwo;
    }

    public void setCornerTwo(Block cornerTwo) {
        this.cornerTwo = cornerTwo;
    }
}
