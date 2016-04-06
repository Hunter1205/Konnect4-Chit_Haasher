package Chit.Haasher.Connect4;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main driver of Entire Game. This class allows the board to be enabled 
 * and for every method and conditions to run. It allows the player to choose
 * which mode to play and initialize the entire game.
 * This also when run, gives the player the rules and what to do.
 * @author Haasher
 */

public class Driver{	
	
public static void main(String[] args){
	
	String userChoice = Board.chooseGameMode();
	Board.intialize(userChoice);
	
	}
}