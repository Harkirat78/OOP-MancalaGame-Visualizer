package mancala;

import java.io.Serializable;

/**
 * Represents a store in the game of Mancala.
 * A store is owned by a player and contains stones.
 */
public class Store implements Countable, Serializable {

    private static final long serialVersionUID = 1L;
    
    private int totalStones;
    private Player owner;

    /**
     * Constructor for Store.
     * Initializes the store with no stones and no owner.
     */
    public Store() {
        totalStones = 0;
        owner = null;
    }

    /**
     * Sets the owner of the store.
     * @param player The player to set as the owner.
     */
    void setOwner(Player player) {
        owner = player;
    }

    /**
     * Gets the owner of the store.
     * @return The owner of the store.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Empties the store and returns the number of stones that were in it.
     * @return The number of stones that were removed.
     */
    int emptyStore() {
        int removedStones = totalStones;
        totalStones = 0;
        return removedStones;
    }

    /**
     * Gets the total number of stones in the store.
     * @return The total number of stones.
     */
    public int getTotalStones() {
        return totalStones;
    }

    /**
     * Adds a stone to the store.
     */
    @Override
    public void addStone() {
        totalStones++;
    }

    /**
     * Adds a specified number of stones to the store.
     * @param amount The number of stones to add.
     */
    @Override
    public void addStones(int amount) {
        totalStones += amount;
    }

    /**
     * Gets the number of stones in the store.
     * @return The number of stones.
     */
    @Override
    public int getStoneCount() {
        return totalStones;
    }

    /**
     * Removes all stones from the store and returns the number that were removed.
     * @return The number of stones that were removed.
     */
    @Override
    public int removeStones() {
        int removedStones = totalStones;
        totalStones = 0;
        return removedStones;
    }

    /**
     * Returns a string representation of the store, including the owner's name and the number of stones.
     * @return A string representation of the store.
     */
    @Override
    public String toString() {
        if (owner != null) {
            return "Store owned by " + owner.getName() + " with " + totalStones + " stones";
        } else {
            return "Store with " + totalStones + " stones and no owner";
        }
    }
}