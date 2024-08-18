package mancala;

import java.io.Serializable;

/**
 * Represents a player in the game.
 */
public class Player implements Serializable{
    
    private UserProfile profilePlayer;
    private Store playerStore;
    
    //serial version UID
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for Player.
     * Initializes the user profile and store for the player.
     */
    public Player(){
        profilePlayer = new UserProfile();
        playerStore = new Store();
    }

    /**
     * Sets the name for the player, in the user profile.
     * @param name The name to set for the player.
     */
    public void setName(final String name){
        profilePlayer.setUserName(name);
    }

    
    /**
     * Set the store to the player.
     * @param store The store to set for the player.
     */
    public void setStore(final Store store){
        playerStore = store;
    }

    /**
     * Gets the name of the player.
     * @return The name of the player from their user profile.
     */
    public String getName(){
        return profilePlayer.getUserName();
    }
    
    /**
     * Gets the store associated with the player.
     * @return The store of the player to hold stones.
     */
    public Store getStore(){
        return playerStore;
    }

    /**
     * Gets the store count of the store.
     * @return The store count.
     */
    public int getStoreCount(){
        return playerStore.getStoneCount();
    }

    /**
     * Sets the user profile for the player.
     * @param profile The user profile to set for the player.
     */
    public void setUserProfile(final UserProfile profile){
        profilePlayer = profile;
    }

    /**
     * Gets the user profile of the player.
     * @return The user profile of the player.
     */
    public UserProfile getUserProfile(){
        return profilePlayer;
    }

    /**
     * ToString for the player, using the user profile.
     * @return The string from the user profile toString.
     */
    @Override
    public String toString(){
        return profilePlayer.toString();
    }
}