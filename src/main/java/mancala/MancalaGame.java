package mancala;

import java.io.Serializable;
import java.util.ArrayList;


public class MancalaGame implements Serializable {
    private static final long serialVersionUID = 1L;
    /*Instance Varaibles*/
    
    //players arraylist
    private final ArrayList<Player> players;
    //game rules
    private GameRules board;
    //kalah rules
    private final KalahRules kalah;
    //ayo rules
    private final AyoRules ayo;

    //player one and two
    private Player player1;
    private Player player2;

    //implement data strucuture methods and current player
    private final MancalaDataStructure dataStruct;
    private Player currentPlayer;
    //check current players number
    private int currentPlayerNum;

    private final int player1Idx = 1;
    private final int player2Idx = 1;
    /**
     * Constructor for MancalaGame.
     * Initializes the players, game rules, data structure, and sets the current player.
     */
    public MancalaGame() {
        // Initialize the list of players
        players = new ArrayList<>();
        // Initialize the rules for Kalah game
        kalah = new KalahRules();
        // Initialize the rules for Ayo game
        ayo = new AyoRules();
        // Initialize the data structure for the game
        dataStruct = new MancalaDataStructure();
        // Set the current player number
        currentPlayerNum = 1;
    }

    /**
     * Setting the players
     * @param onePlayer (Player one)
     * @param twoPlayer (Player Two)
     */
    public void setPlayers(final Player onePlayer, final Player twoPlayer){
        // Set the first player
        player1 = onePlayer;
        // Set the second player
        player2 = twoPlayer;
        // Add the first player to the list of players
        players.add(player1);
        // Add the second player to the list of players
        players.add(player2);
    }

    /**
     * Get Player One
     * @return Player One
     */
    public Player getPlayerOne(){
        return player1; //return that player
    }
    /**
     * Get Player Two
     * @return Player Two
     */
    public Player getPlayerTwo(){
        return player2; //return that player
    }

    /**
     * Get the arraylist players
     * @return Arraylist of the players (not really needed)
     */
    public ArrayList<Player> getPlayers(){
        return players; //get the players
    }

    /**
     * Get current player
     * @return Current Player
     */
    public Player getCurrentPlayer(){
        // If there is no current player, return null
        if (currentPlayer == null) {
            return null;
        }

        // If the current player is player1, set currentPlayerNum to 1
        if(currentPlayer.equals(player1)){
            currentPlayerNum = 1;
        } else {
            // Otherwise, set currentPlayerNum to 2
            currentPlayerNum = 2;
        }

        // Return the current player
        return currentPlayer;
    }
    /**
     * Set Current Player
     * @param player The player that needs to be set
     */
    public void setCurrentPlayer(final Player player){
        currentPlayer = player; //set the current player
    }
    /**
     * Set current player
     * @param num (the current player number)
     */
    public void setCurrentPlayerNum(final int num){
        currentPlayerNum = num; //set the current player number
    }
    /**
     * Get current player number
     * @return The current player number
     */
    public int getCurrentPlayerNum(){
        return currentPlayerNum; //get the current player number
    }
    /**
     * Set the board
     * @param theBoard (GameRules board)
     */
    public void setBoard(final GameRules theBoard){
        board = theBoard; //set the board
    }
    /**
     * Get Board
     * @return GameRules board
     */
    public GameRules getBoard(){
        return board;
    }
    /**
     * Get a kalah rules set
     * @return a kalah rules set
     */
    public KalahRules getKalah(){
        return kalah;
    }
    /**
     * Get ayo ruleset
     * @return A ayo ruleset
     */
    public AyoRules getAyo(){
        return ayo;
    }
    /**
     * Get Mancala Data Struct
     * @return The data structure
     */
    public MancalaDataStructure getMancalaData(){
        return dataStruct;
    }
    /**
     * Get number of stones
     * @param pitNum pit amount of stone
     * @return number stones
     */
    public int getNumStones(final int pitNum)/*throws PitNotFoundException*/{

        //final Logger logger = Logger.getLogger(MancalaGame.class.getName());
        if (board == null) {
            return 0;
        }
        //get that number of stones in a pit
        int stonesInAPit = 0;
        stonesInAPit = board.getNumStones(pitNum);
        return stonesInAPit;
    }
    
    /**
     * Moves stones in game based on choosen starting pit.
     * @param startPit pit from which the player starts the move.
     * @return value from moveStones method in GameRules
     * @throws InvalidMoveException If move is invalid based 
     */
    public int move(final int startPit) throws InvalidMoveException {
        final MancalaDataStructure gameBoard = board.getDataStructure();
    
        validateMove(startPit, gameBoard);
    
        currentPlayer = getCurrentPlayer();
        return board.moveStones(startPit, currentPlayerNum);
    }
    /*
     * Helper function for move method.
     */
    private void validateMove(final int startPit, final MancalaDataStructure gameBoard) throws InvalidMoveException {
        if (gameBoard.getNumStones(startPit) == 0) {
            throw new InvalidMoveException("This is an invalid move. Stone Count of Pit is 0.");
        }
    
        boolean isPlayerOne = currentPlayer.equals(player1);
        boolean isPlayerTwo = currentPlayer.equals(player2);
    
        if ((isPlayerOne && (startPit > 6 || startPit < 1)) || (isPlayerTwo && (startPit > 12 || startPit < 7))) {
            throw new InvalidMoveException("This is an invalid move. Wrong pit number picked.");
        }
    
        if (startPit > 12 || startPit < 1) {
            throw new InvalidMoveException("This is an invalid move.");
        }
    }
     /**
     * Retrieves the stone count in the store of a specified player.
     * @param player Tplayer whose store count is going to be counted.
     * @return store count
     */
    public int getStoreCount(final Player player) /*throws NoSuchPlayerException*/{
        
        // Initialize the player number
        int playerNum;

        // Determine the player number based on the player
        if (player.equals(player1)) {
            playerNum = 1;
        } else if (player.equals(player2)) {
            playerNum = 2;
        } else {
            // If the player is not player1 or player2, return -1
            return -1;
        }

        // Return the stone count of the player's store
        return board.getStoreCount(playerNum);
    }
    /**
     * Checks if a alert for a capture should be made for the game.
     * @return Boolean value.
     */
    public boolean getAlert(){
        return board.sendAlert();
    }
    /**
     * Retrieve the winner of the game
     * @return winning player or NULL if  tie.
     */
    public Player getWinner() /*throws GameNotOverException*/{
        
       // Get the stone count for each player's store
        int player1StoneCount = board.getStoreCount(player1Idx);
        int player2StoneCount = board.getStoreCount(player2Idx);

        // If the stone counts are equal, it's a tie
        if (player1StoneCount == player2StoneCount) {
            return null;
        }
        // If player 1 has more stones, they win. Otherwise, player 2 wins.
        return player1StoneCount > player2StoneCount ? players.get(0) : players.get(1);
    }

    /**
     * Checks if the game is over by checking if any side is empty.
     * @return Boolean value on game status.
     */

    public boolean isGameOver(){
        boolean gameStatus;
        //final Logger logger = Logger.getLogger(MancalaGame.class.getName());
        if((board.isSideEmpty(1)) || (board.isSideEmpty(7))){
            gameStatus = true;
        }else{
            gameStatus = false;
        }

        return gameStatus;
        
    }

    /**
     * last board of the gamege retreived from GameRules.
     */
    public void lastBoard(){
        // Check if the board is null
        if (board == null) {
            // If the board is null, print an error message
            System.out.println("Error: Board not initialized correctly.");
        } else {
            // If the board is not null, end the game
            board.endGame();
        }
    }
    
    /**
     * Starts new game.
     */
    public void startNewGame(){
        board.resetBoard();
        currentPlayer = players.get(0);
        players.get(0).getStore().removeStones();
        players.get(1).getStore().removeStones();
    }

    /**
     * ToString for Mancala.
     * @return string containing names of Player 1 & Player 2.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game: Mancala | ");
        sb.append("Player 1: ").append(player1.getName()).append(" | ");
        sb.append("Player 2: ").append(player2.getName());
        return sb.toString();
    }
}