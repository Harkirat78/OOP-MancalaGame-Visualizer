package mancala;

import java.io.Serializable;

/**
 * Represents a pit in the game of Mancala.
 * A pit contains stones.
 */
public class Pit implements Countable, Serializable {

    private static final long serialVersionUID = 1L;
    
    private int stoneCount;
    
    /**
     * Constructor for Pit.
     * Initializes the pit with no stones.
     */
    public Pit() {
        stoneCount = 0;
    }

    /**
     * Gets the number of stones in the pit.
     * @return The number of stones.
     */
    @Override
    public int getStoneCount() {
        return stoneCount;
    }

    /**
     * Adds a stone to the pit.
     */
    @Override
    public void addStone() {
        stoneCount++;
    }

    /**
     * Adds a specified number of stones to the pit.
     * @param numToAdd The number of stones to add.
     */
    @Override
    public void addStones(int numToAdd) {
        stoneCount += numToAdd; // Add the specified number of stones to the pit
    }

    /**
     * Removes all stones from the pit and returns the number that were removed.
     * @return The number of stones that were removed.
     */
    @Override
    public int removeStones() {
        int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }

    /**
     * Returns a string representation of the pit, including the number of stones.
     * @return A string representation of the pit.
     */
    @Override
    public String toString() {
        return "Pit with " + stoneCount + " stones";
    }
}