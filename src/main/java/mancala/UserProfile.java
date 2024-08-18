package mancala;

import java.io.Serializable;

/**
 * UserProfile class represents user information and game statistics.
 * This class is serializable for saving and loading.
 */
public class UserProfile implements Serializable {
    // User's name and game statistics
    private String userName;
    private int numberOfKalahGamesPlayed;
    private int numberOfAyoGamesPlayed;
    private int numberOfKalahWins;
    private int numberOfAyoWins;

    /**
     * Constructs a new UserProfile with a default username.
     */
    public UserProfile() {
        this.userName = "Default";
    }

    /**
     * Gets the username of the UserProfile.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the number of Kalah games played by the user.
     *
     * @return The number of Kalah games played.
     */
    public int getNumberOfKalahGamesPlayed() {
        return numberOfKalahGamesPlayed;
    }

    /**
     * Gets the number of Ayoayo games played by the user.
     *
     * @return The number of Ayoayo games played.
     */
    public int getNumberOfAyoGamesPlayed() {
        return numberOfAyoGamesPlayed;
    }

    /**
     * Gets the number of Kalah games won by the user.
     *
     * @return The number of Kalah games won.
     */
    public int getNumberOfKalahWins() {
        return numberOfKalahWins;
    }

    /**
     * Sets the number of Ayoayo games played by the user.
     *
     * @param numberOfAyoGamesPlayed The new number of Ayoayo games played.
     */
    public void setNumberOfAyoGamesPlayed(int numberOfAyoGamesPlayed) {
        this.numberOfAyoGamesPlayed = numberOfAyoGamesPlayed;
    }

    /**
     * Sets the number of Kalah games played by the user.
     *
     * @param numberOfKalahGamesPlayed The new number of Kalah games played.
     */
    public void setNumberOfKalahGamesPlayed(int numberOfKalahGamesPlayed) {
        this.numberOfKalahGamesPlayed = numberOfKalahGamesPlayed;
    }

    /**
     * Sets the username of the UserProfile.
     *
     * @param userName The new username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Sets the number of Kalah games won by the user.
     *
     * @param numberOfKalahWins The new number of Kalah games won.
     */
    public void setNumberOfKalahWins(int numberOfKalahWins) {
        this.numberOfKalahWins = numberOfKalahWins;
    }

    /**
     * Sets the number of Ayoayo games won by the user.
     *
     * @param numberOfAyoWins The new number of Ayoayo games won.
     */
    public void setNumberOfAyoWins(int numberOfAyoWins) {
        this.numberOfAyoWins = numberOfAyoWins;
    }

    /**
     * Gets the number of Ayoayo games won by the user.
     *
     * @return The number of Ayoayo games won.
     */
    public int getNumberOfAyoWins() {
        return numberOfAyoWins;
    }
}
