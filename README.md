# Mancala Game GUI

## Description
Mancala is a two-player strategy board game that is played with small stones, beans, or seeds and a board with 12 small pits and two large pits. The objective of the game is to capture more stones than the opponent.

The enhanced version of the Mancala game! This assignment focuses on adding a graphical user interface (GUI) to the existing Mancala game, providing options to play two different variants: Kalah and Ayoayo. Additionally, features such as game state persistence, user profiles, and refactoring of the code for better design have been introduced.

The game starts with four stones in each of the 12 small pits. Each player takes turns picking up all the stones in one of their small pits and distributing them in a counter-clockwise direction, one stone per pit. If the last stone lands in the player's large pit, they get to take another turn. If the last stone lands in an empty small pit on the player's side, they capture that stone and any stones in the opposite small pit, placing them in their large pit. The game ends when one player has no more stones on their side of the board.

Game Variants
- Kalah: A classic two-player game where each player aims to capture more stones than their opponent. The game involves strategic moves, and the winner is determined by the player with the most stones in their store when one side of the board is empty.
- Ayoayo: A simplified version of Mancala with unique rules. The game starts similarly to Kalah, but differences arise in stone distribution and capturing mechanisms. The objective remains to capture more stones, and the game ends when one side of the board is empty.

## Getting Started
Make sure docker desktop is setup and the optimal compiling enviroment is used. Make sure that gradle works correctly and that you are in the correct repository/directory when using gradle build command. Be sure to be in the GP4 directory

### Dependencies
* Java JDK
* Git
* Gradle

### Executing program

* This program can be run in the shell by finding your way within the GP4 repository to the TaskOne folder Executing Program
To run the program, follow these steps:

* Navigate to the GP4 repository by using ```cd``` into coursework then ```cd``` into GP4.
* Build the project using Gradle: ```gradle build```.
* Run the program from jar use the the following command:```java -jar build/libs/ui.jar``` and make sure you are not using a headless server/interface
This will compile the Java files, build the project, and execute the program using the specified command

## Limitations
For the context of the Mancala game project, all requirements have been completed and followed. Since there are no user inputs and the main class is hard coded, there is not much room for an error to occur due to invalid input within the methods.

* The game may not be fully functional as some parts of the GUI need further development. 

A few hard-coded inputs that could cause errors and that any programmer of this program should be aware of are:
* Incorrect Method Usage:
If you misuse the methods (e.g., calling moveStones with an invalid pit number), this could lead to unexpected behavior or incorrect program output.
* Incorrect Use of Player Turn:
Incorrectly managing the player turn (e.g., allowing a player to make a move when it's not their turn) could lead to inconsistent or incorrect results.
* Improper Initialization:
If you forget to initialize the board or the player scores before using them, this could cause null pointer exceptions or incorrect program behavior.
* Invalid Input Data:
Passing unexpected data types or incorrect data when calling methods could lead to unexpected behavior or errors.

## Author Information
Name: Harkirat Soomal
Student ID: 1232181
Email: hsoomal@uoguelph.ca

## Development History

* 0.3
    * Improved game mechanics with proper encapsulation and better reusability of methods
    * Enhanced user experience with a better visual interface and improved usability
    * See [commit change](https://gitlab.socs.uoguelph.ca/2430F23/hsoomal/GP4/-/commits/master) or See [release history]()
* 0.2
    * Various bug fixes and optimizations
    * See [commit change](https://gitlab.socs.uoguelph.ca/2430F23/hsoomal/GP4/-/commits/master/) or See [release history]()
* 0.1
    * Initial Release


## Acknowledgments
Inspiration for this README:
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
