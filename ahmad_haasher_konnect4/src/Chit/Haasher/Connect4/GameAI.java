package Chit.Haasher.Connect4;
import java.util.Random;

/**
 * AI class implements a weak AI, it just picks a number between 1 and 6 (column number)
 * @author Haasher and Chit
 */

public class GameAI {
	private int move;
	
/**
 * Used to send out AI move
 * @return move
 */
	public int getMove()
	{
		return move;
	}
	 
/**
 * set Method to prompt the AI move	
 */
	public void setMove()
	{
		move();
	}
	
/**
 * Generate move of AI	
 */
	private void move()
	{
		Random rand = new Random();
		this.move = rand.nextInt(6)+1;  
	}
	
}