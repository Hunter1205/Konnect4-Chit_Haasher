package Chit.Haasher.Connect4;

/**
 *Driver initializes the game and gets user input for choice of game mode
 *Passes the choice of game mode to initialize the board
 * @author Haasher and Chit
 * @version 15.1998
 */

public class Driver{	
	
public static void main(String[] args){
	
	String userChoice = Board.chooseGameMode();
	Board.intialize(userChoice);
	
	}
}
