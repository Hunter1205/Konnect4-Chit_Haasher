package Chit.Haasher.Connect4;

import java.util.*;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * This class creates 2 ArrayLists for data entry and turns.
 * This class also creates the board and display of the board.
 * This class also declares the to chips colors to red and blue.
 * This class also states whether that hole is available or unavailable.
 * This class also check every row, column vertically, horizontally, and diagonally for a win.
 * @author Haasher and Chit
 */

public class BoardData {
private String [][] data;
private String turn; 

/**
 * On the board, this populates the board cells with a "no color"
 */
	public BoardData(){
		this.data = new String[6][7];
		for(int row=0;row<6;row++){
			for(int col=0;col<7;col++){
				data[row][col] = "no color";
			}
		}
	}

/**
 * For the red player, this checks to see if the position is available, if adds the red
 * @param col
 */
	public void AddRed(int col){
		int row = 0;
		while(data[row][col] != "no color"){
			row++;
		}
		data[row][col] = "red";
	} 
	
/**
 * For the blue player, this checks to see if the position is available, if adds the blue
 * @param col
 */
	public void AddBlue(int col){
		int row = 0;
		while(data[row][col] != "no color"){
			row++;
		}
		data[row][col] = "blue";
	}

/**
 * returns the colour at the specified location
 * @param row
 * @param col
 * @return this.data[row][col]
 */
	public String GetColor(int row, int col){
		return this.data[row][col];
	}

/**
 * Checks winner and returns winning color
 * @return winner
 */
	public String isWin(){
		
		/**
		 * vertical check
		 */
		String winner = "";
		for (int col=0;col<7;col++){   
            for (int row=0;row<3;row++){ 
                    if (data[row][col].equals(data[row+1][col]) && data[row][col].equals(data[row+2][col]) && data[row][col].equals(data[row+3][col]) && data[row][col].equals("red")){
                    	winner="red";
                    }
                    if (data[row][col].equals(data[row+1][col]) && data[row][col].equals(data[row+2][col]) && data[row][col].equals(data[row+3][col]) && data[row][col].equals("blue")){
                    	winner="blue";
                    }
            }
		}
		
		/**
		 * horizontal check
		 */
		for (int row=0;row<6;row++){
            for(int col=0;col<4;col++){
                    if (data[row][col].equals(data[row][col+1]) && data[row][col].equals(data[row][col+2]) && data[row][col].equals(data[row][col+3]) && data[row][col].equals("red")){
                    	winner="red";
                    }
                    if (data[row][col].equals(data[row][col+1]) && data[row][col].equals(data[row][col+2]) && data[row][col].equals(data[row][col+3]) && data[row][col].equals("blue")){
                    	winner="blue";
                    }
            }                                      
        }
		
		/**
		 * diagonal check
		 */
		for (int row=0;row<3;row++){ 
            for(int col=0;col<4;col++){   
                    if (data[row][col].equals(data[row+1][col+1]) && data[row][col].equals(data[row+2][col+2]) && data[row][col].equals(data[row+3][col+3]) && data[row][col].equals("red")){
                    	winner="red";
                    }
                    if (data[row][col].equals(data[row+1][col+1]) && data[row][col].equals(data[row+2][col+2]) && data[row][col].equals(data[row+3][col+3]) && data[row][col].equals("blue")){
                    	winner="blue";          
                    }
            }
		}
		
		/**
		 * diagonal check
		 */
		for (int row=3;row<6;row++){ 
            for(int col=0;col<4;col++){   
                    if (data[row][col].equals(data[row-1][col+1]) && data[row][col].equals(data[row-2][col+2]) && data[row][col].equals(data[row-3][col+3]) && data[row][col].equals("red")){
                    	winner="red";
                    }
                    if (data[row][col].equals(data[row-1][col+1]) && data[row][col].equals(data[row-2][col+2]) && data[row][col].equals(data[row-3][col+3]) && data[row][col].equals("blue")){
                    	winner="blue";
                    }
            }
		}
		return winner;
	}

/**
 * Switches between player, turn by turn
 * @return this.turn
 */	
	public String getTurn(){
		return this.turn; 
	}
}