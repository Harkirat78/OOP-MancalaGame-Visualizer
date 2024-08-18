package mancala;

import java.io.Serializable;

public class KalahRules extends GameRules implements Serializable{
    
    //serialVersionUID
    private static final long serialVersionUID = 1L;
    //store2 pits only 
    private static final int store2 = 13;
    //last pit
    private static final int stone = 1;

    //private GameRules rules;
    private static final int player1Idx = 1;
    private static final int player2Idx = 2;

    //initialize data structure
    private MancalaDataStructure board;
    //private MancalaGame game;
    private static final int resetLimit = 14;  //set to 14 as the board is 14 pits

      //initialize players
    private final Player player1;
    private final Player player2;

    //initialize player number
    private int playerNumber;
    
    /**
     * Constructor for Kalah Rules
     */
    public KalahRules(){
        
        // Call the parent constructor
        super();

        // Get the data structure and set up the pits
        board = getDataStructure();
        board.setUpPits();

        // Initialize the players
        player1 = new Player();
        player2 = new Player();

        // Check if players are initialized correctly
        if (player1 == null || player2 == null) {
            // If any player is not initialized, print an error message
            System.out.println("Error: Players not initialized correctly.");
        } else {
            // If both players are initialized, register the players
            registerPlayers(player1, player2);
        }
    }

    /**
     * Distribute stones throughout the board and pits
     * @param startingPoint starting pit or point to distribute
     * @return The total stones distributed
     */
    @Override
    public int distributeStones(final int startPit) {
        board.setIterator(startPit, playerNumber, false);
        int stonesToDistribute = board.getNumStones(startPit), captured = 0, iteratorPos = startPit - 1, actual = startPit;
        board.removeStones(startPit);
        Countable currentPit = null;

        for(int stonesLeft = stonesToDistribute; stonesLeft > 0; stonesLeft--){
            currentPit = board.next();
            iteratorPos = ++iteratorPos == resetLimit ? 0 : iteratorPos;
            actual = ++actual == resetLimit ? 1 : actual;

            if(currentPit instanceof Pit){
                ((Pit) currentPit).addStones(1);
            }else if(currentPit instanceof Store && ((playerNumber == player1Idx && actual != 7) || (playerNumber == player2Idx && actual != 14))){
                stonesLeft++;
                continue;
            }
        }

        if(sameSide(actual) && actual != stone && board.getNumStones(actual) == stone && currentPit instanceof Pit && board.getNumStones(13 - actual) > 0){
            captured = captureStones(actual);
        }

        if(captured == 0) setAlert(false);

        addCaptured(captured);
        return stonesToDistribute + captured;
    }

    /**
     * Moving stones throughout the board
     * @param startPit Starting pit
     * @param playerNum Current player number
     * @return The store count after moving the stones
     * @throws InvalidMoveException 
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        board = getDataStructure();
        int initialStoreStones = board.getStoreCount(playerNum);
        playerNumber = playerNum;

        if (board.getNumStones(startPit) == 0) {
            throw new InvalidMoveException("You cannot pick this pit as it is an invalid move.");
        }

        switch (playerNum) {
            case 1:
                if (startPit < 1 || startPit > 6) {
                    throw new InvalidMoveException("This is an invalid move.");
                }
                break;
            case 2:
                if (startPit < 7 || startPit > 12) {
                    throw new InvalidMoveException("This is an invalid move.");
                }
                break;
            default:
                throw new InvalidMoveException("Invalid player number.");
        }

        distributeStones(startPit);
        int finalStoreStones = board.getStoreCount(playerNum);
        return finalStoreStones - initialStoreStones;
    }

    /**
     * Check last stone on same player side
     * @param pitNum (Pit Number)
     * @return boolean value
     */
    private boolean sameSide(final int pitNum){
        boolean capture = false;

        switch (playerNumber) {
            case player1Idx:
                capture = pitNum < 7;
                break;
            case player2Idx:
                capture = pitNum >= 7;
                break;
        }

        return capture;
    }
   
    /**
     * Capture the stones from the opposite pit from the other players index.
     * @param stoppingPoint The pit where the last stone stopped at
     * @return The stones captured
     */
    @Override
    public int captureStones(final int stoppingPoint) {
        int captured = 0, oppositeIndex;
        if (board.getNumStones(stoppingPoint) != stone) {
            setAlert(true);
            return captured;
        }

        board.removeStones(stoppingPoint);
        captured++;
        oppositeIndex = 13 - stoppingPoint;
        captured += board.getNumStones(oppositeIndex);
        board.removeStones(oppositeIndex);
        setAlert(true);

        return captured;
    }
    /**
     * Adding Captured Stones 
     * @param stonesCaptured 
     */
    private void addCaptured(final int stonesCaptured){
        if (stonesCaptured > 0) {
            board.addToStore(playerNumber, stonesCaptured);
        } else {
            System.out.println("No stones were captured.");
        }
    }
 
}