package mancala;

import java.io.Serializable;

public class AyoRules extends GameRules implements Serializable{
    private static final long serialVersionUID = 1L;
    //private GameRules rules;
    private MancalaDataStructure playBoard;
    private int playerNumber; // Player number (1 or 2)
    // Index of the first player
    private static final int player1Idx = 1;
    // Index of the second player
    private static final int player2Idx = 2;
    // Index of the first player's store
    private static final int store1Idx = 7;
    // Index of the second player's store
    private static final int store2Idx = 13;
     // Maximum number of iterations for the game loop
    private static final int maxIterations = 50;
    // Limit for resetting the pit position
    private static final int resetLimit = 13;
    // Count of stones to be distributed
    private static final int stoneCount = 1;
   
    
    //private Store storeOne;
    //private Store storeTwo;
    //private int intialPoint;
    public AyoRules(){
        super();
        playBoard = getDataStructure();
        // Check if the board is initialized correctly
        if (playBoard == null) {
            // If the board is not initialized, print an error message
            System.out.println("Error: Board not initialized correctly.");
        } else {
            // If the board is initialized, set up the pits
            playBoard.setUpPits();
        }
    }
    /**
     * Set the Mancala game to the ruleset, to get data.
     * @param game The Mancala Game
     */
    /*@Override
    public void setGame(final MancalaGame game){
        this.game = game;
    }*/
    /**
     * Start of the move stones 
     * @param startPit Starting pit
     * @param playerNum Current player number
     * @return The store count after moving the stones
     * @throws InvalidMoveException If Invalid move is done
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        playBoard = getDataStructure();
        validateMove(startPit, playerNum);

        int initialStoreStones = playBoard.getStoreCount(playerNum);
        playerNumber = playerNum;
        distributeStones(startPit);
        int finalStoreStones = playBoard.getStoreCount(playerNum);

        return finalStoreStones - initialStoreStones;
    }

    /**
     * Validates the move based on the starting pit and player number.
     *
     * @param startPit The pit from which the move starts.
     * @param playerNum The number of the player making the move.
     * @throws InvalidMoveException If the move is not valid. This can happen if the starting pit is empty,
     *                              or if the player chooses a pit outside their range.
     */
    private void validateMove(final int startPit, final int playerNum) throws InvalidMoveException {
        if (playBoard.getNumStones(startPit) == 0) {
            throw new InvalidMoveException("You cannot pick this pit as it is an invalid move.");
        }

        if (playerNum == 1 && (startPit > 6 || startPit < 1)) {
            throw new InvalidMoveException("This is an invalid move.");
        }

        if (playerNum == 2 && (startPit > 12 || startPit < 7)) {
            throw new InvalidMoveException("This is an invalid move.");
        }
    }
    
    /**
     * Check if last stone is on the same side
     * @param pit which pit is need to check
     * @return which side its on
     */
    private boolean sidePitCheck(final int pit){
        return playerNumber == player1Idx && (pit < store1Idx || (pit >= store1Idx && pit < store2Idx));
    }

    /**
     * Iterate through the list
     * @param actual 
     * @return return same index 
     */
    private int loopList(final int actual){
        int updatedIndex = actual + 1;
        if(updatedIndex >= resetLimit){
            updatedIndex = 1;
        }
        return updatedIndex;
    }

    /**
     * Adjust index when the index crosses a store
     * @param actual Actual index
     * @return Adjusted index
     */
    private int checkActualIndexStore(final int actual, final int checkIndex) {
        final boolean isStoreCrossed = actual / 7 != checkIndex / 7;
        final boolean isStoreCheckPassed = playerNumber - 1 != actual / 6;
    
        return (isStoreCrossed && isStoreCheckPassed) ? actual - 1 : actual;
    }
    
    /**
     * Skip index if starting point has been reached
     * @param actual index
     * @param startingPoint start
     * @return where we are now
     */
    private int ifSkippedStart(final int actual, final int startingPoint){
        return (actual == startingPoint) ? actual + 1 : actual;
    }
    
    /**
     * Distributing stones
     * @param startingpoint starting point
     * @return the number of stones distributed amongst the pits
     */
    @Override
    public int distributeStones(final int startPit) {
        // Flag to track if distribution is still in progress
        boolean isDistributionInProgress = true;

        // Variables to hold the number of remaining stones, total stones distributed, current and previous indices, capture requirement, total stones captured, and loop count
        int remainingStones;
        int totalStonesDistributed = 0;
        int currentIndex = startPit;
        int previousIndex = 0;
        boolean isCaptureRequired;
        int totalStonesCaptured = 0;
        int loopCount = 0;

        // Set the iterator to the starting pit
        playBoard.setIterator(startPit, playerNumber, true);
        
        // Start distributing stones
        do {
            // Reset currentIndex to 1 if it's 0
            currentIndex = (currentIndex == 0) ? 1 : currentIndex;

            // Get the number of stones in the current pit
            remainingStones = playBoard.getNumStones(currentIndex);

            // Add the number of stones in the current pit to the total
            totalStonesDistributed += remainingStones;

            // Remove the stones from the current pit
            playBoard.removeStones(currentIndex);
            
            // Distribute the stones
            while (remainingStones-- > 0) {
                // Get the next pit
                final Countable currentPit = playBoard.next();

                // Update the current index
                currentIndex = loopList(currentIndex);
                currentIndex = ifSkippedStart(currentIndex, startPit);
                
                // Add a stone to the current pit or store
                if (currentPit instanceof Pit || currentPit instanceof Store) {
                    currentPit.addStones(1);
                }
                
                // Check if a capture is required
                if (currentPit.getStoneCount() == 1 && remainingStones == 0) {
                    isCaptureRequired = sidePitCheck(currentIndex);

                    // If a capture is required, capture the stones
                    if (isCaptureRequired) {
                        totalStonesCaptured += captureStones(currentIndex);
                    } else {
                        // If no capture is required, stop distribution
                        isDistributionInProgress = false;
                    }
                    
                    // If no stones were captured, stop distribution and set an alert
                    if (totalStonesCaptured == 0) {
                        setAlert(false);
                        isDistributionInProgress = false;
                    }
                }
            }
            
            // Check if the current index is a store
            currentIndex = checkActualIndexStore(currentIndex, previousIndex);

            // Update the previous index
            previousIndex = currentIndex;

            // Increment the loop count
            loopCount++;
            
            // If the loop count reaches the maximum number of iterations, stop distribution
            if (loopCount == maxIterations) {
                isDistributionInProgress = false;
            }
        } while (isDistributionInProgress);
        
        // Add the captured stones to the total
        addCaptured(totalStonesCaptured);

        // Add the captured stones to the total stones distributed
        totalStonesDistributed += totalStonesCaptured;

        return totalStonesDistributed;
    }

    /**
     * Add the captured stones to  store
     * @param capturedStones 
     */
    private void addCaptured(final int capturedStones) {
        switch (playerNumber) {
            case player1Idx:
            case player2Idx:
                playBoard.addToStore(playerNumber, capturedStones);
                break;
            default:
                break;
        }
    }

    /**
     * Capturing  stones from opposite index in pits.
     * @param stoppingPoint pit where the last stone ended up at
     * @return stones captured
     */
    @Override
    public int captureStones(final int lastStonePosition) {
        int oppositePitIndex = resetLimit - lastStonePosition;
        int capturedStones = (playBoard.getNumStones(lastStonePosition) == stoneCount) ? playBoard.getNumStones(oppositePitIndex) : 0;
    
        if (capturedStones > 0) {
            playBoard.removeStones(oppositePitIndex);
            setAlert(true);
        }
    
        return capturedStones;
    }
}