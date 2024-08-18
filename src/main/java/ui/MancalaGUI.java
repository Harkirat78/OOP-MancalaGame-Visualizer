package ui;

// importaing the necessary packages

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;

import mancala.AyoRules;
import mancala.GameRules;
import mancala.KalahRules;
import mancala.Saver;
import mancala.UserProfile;

/**
 * This class represents the GUI for the Mancala game.
 * It includes methods for starting a new game, quitting a game, saving and loading a game.
 */
public class MancalaGUI extends JFrame {
    private PositionAwareButton[][] buttons = new PositionAwareButton[2][6];
    private String[] games = {"Kalah", "Ayo"};
    private String selectedGame;
    private int currentPlayer;
    private GameRules currentGame;

    // UserProfile instance for storing user statistics
    private UserProfile userProfile;

    /**
     * Constructor for MancalaGUI. Initializes the game board, control panel, and menu bar.
     */
    public MancalaGUI() {
        setTitle("Mancala Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize UserProfile (replace "Player1" with the actual username)
        userProfile = new UserProfile();

        // Create game board
        JPanel gameBoard = makeButtonGrid(6, 2);  // Using makeButtonGrid to create the game board
        gameBoard.setBackground(new Color(173, 216, 230)); // Light blue color

        // Create control panel
        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("New Game");
        JButton quitGameButton = new JButton("Quit Game");
        JComboBox<String> gameSelector = new JComboBox<>(games);
        gameSelector.addActionListener(e -> {
            selectedGame = (String) gameSelector.getSelectedItem();
            // Initialize the selected game based on the choice
            if ("Kalah".equals(selectedGame)) {
                currentGame = new KalahRules();
            } else if ("Ayo".equals(selectedGame)) {
                currentGame = new AyoRules();
            }
        });
        controlPanel.add(newGameButton);
        controlPanel.add(quitGameButton);
        controlPanel.add(gameSelector);

        // Create menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);

        // Set up ActionListener for save and load menu items
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });

        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });

        setJMenuBar(menuBar);  // Set the menu bar for the JFrame

        // Add game board and control panel to the window
        add(gameBoard, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to start a new game
                startNewGame();
            }
        });

        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to quit the current game
                quitGame();
            }
        });
    }

     /**
     * Starts a new game based on the selected game type.
     */
    void startNewGame() {
        // Check if a game is already in progress
        if (currentGame != null) {
            quitGame();
        }

        // Initialize a new game based on the selected game type
        if ("Kalah".equals(selectedGame)) {
            currentGame = new KalahRules();
        } else if ("Ayo".equals(selectedGame)) {
            currentGame = new AyoRules();
        }

        // Initialize the game board based on the current game state
        updateGameBoard();

        // Additional setup for the new game
        currentPlayer = 1; // Assuming two players, set the starting player

        // You can also update the UI to indicate the current player, etc.
    }

    /**
     * Updates the game board based on the current game state.
     */
    private void updateGameBoard() {
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 6; x++) {
                int pitNum = y * 6 + x;
                if (pitNum >= 0 && pitNum < 12) { // Assuming there are 12 pits
                    buttons[y][x].setText(String.valueOf(currentGame.getNumStones(pitNum)));
                }
            }
        }
    }
    
    /**
     * Quits the current game and saves the user profile.
     */
    void quitGame() {
        // Implement logic to quit the current game
        // Stop the current game and return to the game selection screen...

        // Save the user profile after quitting the game
        saveProfile();
    }

    /**
     * Saves the user profile to a file.
     */
    void saveProfile() {
        File directory = new File("src/main/java/assets/");
        if (! directory.exists()){
            directory.mkdir();
        }
        try {
            Saver.saveObject(userProfile, "src/main/java/assets/userProfile.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current game state to a file.
     */
    void saveGame() {
        try {
            Saver.saveObject(currentGame, "savedGame.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a saved game from a file and updates the game board.
     */
    void loadGame() {
        try {
            currentGame = (GameRules) Saver.loadObject("savedGame.ser");
            updateGameBoard();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a button grid for the game board.
     * @param tall The number of rows in the grid.
     * @param wide The number of columns in the grid.
     * @return The JPanel containing the button grid.
     */
    private JPanel makeButtonGrid(int tall, int wide) {
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[wide][tall]; // Fix the array dimensions
        panel.setLayout(new GridLayout(wide, tall));
        for (int y = 0; y < wide; y++) {
            for (int x = 0; x < tall; x++) {
                // Create buttons and link each button back to a coordinate on the grid
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x + 1); // 1 based buttons
                buttons[y][x].setDown(y + 1);
                buttons[y][x].addActionListener(e -> {
                    someEventHandlerMethodsHere();
                });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }
    
   /**
     * Handles events for the game board buttons.
     */
    private void someEventHandlerMethodsHere() {
        //need to complete
    }
    
    /**
     * Switches the current player.
     */
    private void switchPlayer() {
        //need to complete
    }

    /**
     * The main method that creates and displays the MancalaGUI.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        MancalaGUI gui = new MancalaGUI();
        gui.setVisible(true);
    }
}
