package Chit.Haasher.Connect4;

/**
 * This class switches back and forth from player to player
 * and player to computer.
 * This class also declares the winner between blue and red.
 * @author Haasher and Chit
 */

public class BoardController {
	private String turn = this.whichPlayerToStart(); //Communicates whose turn it is
	private BoardData board = new BoardData(); 

	
/**
 * this determines who goes first randomly, by using build in Math random function
 * sets the turn to blue or red
 * @return this.turn
 */
	private String whichPlayerToStart(){
		double whichPlayerToCommence = Math.random();
		double randomDecider = Math.random();
		if(whichPlayerToCommence < randomDecider){ 
		this.turn  = "blue";
		return this.turn;
		} else{
		this.turn  = "red";
		return this.turn;
		} 
	}

/**
 * returns Turn
 * @return this.turn
 */
	public String getTurn(){
		return this.turn; 
	}
	
/**
 * Method used to switch turns between players
 */
	public void changeTurn(){
		if (this.turn =="blue"){
			this.turn = "red";	
		} else{
			this.turn ="blue";
		}
	}
	
/**
 * used to communicate the colour in the array to the board
 * @param row
 * @param column
 * @return board.GetColor(row,column)
 */
	public String getColour(int row, int column){	
		return board.GetColor(row, column); 
	}
	
/**
 * tells the model to reset its data
 * @param column
 */
	public void add(int column){
		if(turn == "blue"){
			board.AddBlue(column);
			
		} else{
			board.AddRed(column);
		}
		this.changeTurn();
	}
	
/**
 * Determines the winner and returns it
 * @return winner
 */
	public String winner(){
		String winner ="";
		if(board.isWin() == "blue"){
			winner= "blue";
			} else if(board.isWin()=="red"){
			winner= "red";
			}
		return winner; 
	}
}
