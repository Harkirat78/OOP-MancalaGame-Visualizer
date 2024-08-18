package mancala;

import java.io.Serializable;
import java.util.stream.IntStream;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private MancalaDataStructure gameMancala;
    private int currentPlayer; // Player number (1 or 2)
    private int bonusValue;
    private boolean alert;
    private MancalaGame game;
    private static final int store1 = 7;
    private static final int lastPit1 = 6;

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        //new object from mancala data structure
        gameMancala = new MancalaDataStructure();
    }

    /**
     * Get the number of stones in a store
     * 
     * @param playerNum
     * @return The number of stones in a specified store (p1 or p2)
     */
    public int getStoreCount(final int playerNum){
        return gameMancala.getStoreCount(playerNum);
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(int pitNum) {
        //return the number of stones in the pit
        return gameMancala.getNumStones(pitNum);
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(int pitNum) {
        int startPit = (pitNum - 1) * 6;
        return IntStream.range(startPit, startPit + 6).noneMatch(i -> gameMancala.getNumStones(i) > 0);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        return gameMancala;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    public abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(Player one, Player two) {
       
        //set the owner of the store
        Store store1 = new Store();
        store1.setOwner(one);
        Store store2 = new Store();
        store2.setOwner(two);

        //set the owner of the pits
        gameMancala.setStore(store1, 1);
        gameMancala.setStore(store2, 2);
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameMancala.setUpPits();
        gameMancala.emptyStores();
    }

    /**
     * Sets an alert for the capture
     *
     * @param alert Boolean value indicating whether to trigger the alert for a capture.
     */
    public void setAlert(final boolean alert){
        this.alert = alert;
    }
    /**
     * Sending alert to mancala game
     * @return alert if needed boolean value
     */
    public Boolean sendAlert(){
        return alert;
    }

    /**
     * End game by distributing final stones & getting store count values
     */
    public void endGame() {
        int sum1 = 0, sum2 = 0;
        for (int index = 1; index < 13; index++) {
            if (index < 7) {
                sum1 += game.getNumStones(index);
                gameMancala.removeStones(index);
            } else {
                sum2 += game.getNumStones(index);
                gameMancala.removeStones(index);
            }
        }
        gameMancala.addToStore(1, sum1);
        gameMancala.addToStore(2, sum2);
    }

    @Override
    public String toString() {
        return "GameRules: Player " + currentPlayer + " is currently playing.";
    }
}
