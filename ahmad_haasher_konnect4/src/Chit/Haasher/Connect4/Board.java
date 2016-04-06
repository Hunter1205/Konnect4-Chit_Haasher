package Chit.Haasher.Connect4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.*;
import java.text.*; 

/**
 * This class controls on which mode the player plays. Player vs computer or Player vs Player.
 * This class also controls on which hole is filled based on which button is clicked.
 * this class also displays the buttons for the player to click on which hole to fill.
 * and on what to do on which result, of a tie, win, or lose.
 * This class also initializes the buttons for the player to use for inputing the chip onto board
 * @author Haasher
 */
	public class Board extends BoardController{

		public static String [] whichModeToPlay = {"Player vs Player", "Player vs Computer"};
		public static String userChoiceTypeOfPlay;
		private static boolean vsComputer; 
		private static BoardController controller= new BoardController(); 
		private static GameAI AI = new GameAI();
		
/**
 * Opens a dialogue box which allows player to select which mode they would like to play
 * @return
 */
		public static String chooseGameMode(){
	userChoiceTypeOfPlay = (String)JOptionPane.showInputDialog(null,"                     Welcome To Connect 4!"+"\n"+
			 "Fill in four cells in all directions continously to win!"+"\n"+"Tip:Look at Status bar for instructions."+"\n"+
					"Caution: Computer is Team Red."+"\n"+"Which game mode would you like to play?","Select Game Mode",JOptionPane.PLAIN_MESSAGE,null, whichModeToPlay,"--------");

			return userChoiceTypeOfPlay;	
		}

/**
 * Declares weather the userChoice was to verse the computer or not
 * @param userChoice
 */
	public static void intialize(String userChoice){
		if(userChoiceTypeOfPlay == whichModeToPlay[1]){
			vsComputer = true;
		} else{
			vsComputer = false;
		}

/**
 * initialization of board components
 */
		BoardGUI theBoard = new BoardGUI();
		theBoard.setTitle("Connect 4");
		theBoard.setVisible(true);
		theBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theBoard.setSize(800, 800);	
	}
	

	public static class BoardGUI  extends JFrame implements ActionListener{
		//the integers are click counters
/**
 * The reason this class implements the ActionListener class
 * is to allow the buttons that add tokens to the board to be clicked
 * more than once.
 */	
		private int Click1 = 0;
		private int Click2 = 0;
		private int Click3 = 0;
		private int Click4 = 0;
		private int Click5 = 0;
		private int Click6 = 0;
		private int Click7 = 0;
		
		private int allButtonDisabled = 0;

/**
 * this object relays messages from the GUI to the BoardController class
 */

		//Buttons for each column
		private JButton Column1 = new JButton("Add");
		private JButton Column2 = new JButton("Add");
		private JButton Column3 = new JButton("Add");
		private JButton Column4 = new JButton("Add");
		private JButton Column5 = new JButton("Add");
		private JButton Column6 = new JButton("Add");
		private JButton Column7 = new JButton("Add");

		private JButton playAgain = new JButton("Play Again");




		//labels
		private JLabel status = new JLabel("Turn:"+controller.getTurn());



		private JLabel [][]  cells = new JLabel [6][7];//array of labels to repersent the board

		// the reason behind this is to at a later point grab the original colour of each created JLabel 
		private Color defaultColour; 



		public BoardGUI(){
/**
 * create and add buttons to a button panel so they appear in the GUI
 */
			JPanel buttonsPanel = new JPanel(new GridLayout(1,1));
			buttonsPanel.add(Column1);
			buttonsPanel.add(Column2);
			buttonsPanel.add(Column3);
			buttonsPanel.add(Column4);
			buttonsPanel.add(Column5);
			buttonsPanel.add(Column6);
			buttonsPanel.add(Column7);



			JPanel boardPanel = new JPanel(new GridLayout(6,7));
			this.updateBoard();
			for(int i = 5; i >=0; i--){

/**
 * This for loop initiates the the board's starting point
 * it creates each label in the array
 * it adds them to boardPanel
 */
				for(int j = 6; j>=0; j--){
					cells[i][j]= new JLabel("");
					cells[i][j].setBackground(Color.yellow);
					cells[i][j].setBorder(BorderFactory.createEtchedBorder());
					
					cells[i][j].setOpaque(true);
					boardPanel.add(cells[i][j]);
					
					
					cells[i][j].setBorder(new LineBorder(Color.BLACK));

				}
			}

			defaultColour = cells[0][0].getBackground(); 

			Container container = getContentPane();
			container.add(boardPanel,BorderLayout.CENTER);
			container.add(buttonsPanel,BorderLayout.NORTH);
			container.add(status,BorderLayout.SOUTH);


			//Add the actionListeners 
			Column1.addActionListener(this);
			Column2.addActionListener(this);
			Column3.addActionListener(this);
			Column4.addActionListener(this);
			Column5.addActionListener(this);
			Column6.addActionListener(this);
			Column7.addActionListener(this);

		}

/**
 * This disables all the button on the board
 */
		public void disableAllButtons(){
			Column1.setEnabled(false);
			Column2.setEnabled(false);
			Column3.setEnabled(false);
			Column4.setEnabled(false);
			Column5.setEnabled(false);
			Column6.setEnabled(false);
			Column7.setEnabled(false);
		}

/**
 * enables all the buttons
 */
		private void buttonEnable(){
			Column1.setEnabled(true);
			Column2.setEnabled(true);
			Column3.setEnabled(true);
			Column4.setEnabled(true);
			Column5.setEnabled(true);
			Column6.setEnabled(true);
			Column7.setEnabled(true);
		}

/** Method updates the color on the cell
 * 1. Retrieves which color from BoardController class
 * 2. Uses the color to place the correct background
 */
		private void updateBoard(){
			for(int i = 0; i<6;i++){
				for(int q = 0;q <7; q++){
					//updates the board with red color
					if(controller.getColour(i,q)=="red"){
						cells[i][q].setBackground(Color.red);
					}
					//updates the board with blue color
					else if(controller.getColour(i,q)=="blue"){
						cells[i][q].setBackground(Color.blue);	
					}
				}
			}
		}

/**
 * This method checks to see if there is winner, if so, it displays who won, and disables the buttons
 * @param winningColour
 */
		public void winner(String winningColour){
			JFrame frame = new JFrame("End Game");
			frame.setSize(500,200);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel();
			frame.add(panel);

			JLabel endGame = new JLabel("The winning colour is: " + winningColour + "\n");
			panel.add(endGame);
	
			JButton button2 = new JButton("Quit.");
			panel.add(button2);
			button2.addActionListener (new QuitListener());

			frame.getContentPane().add(panel);
			frame.pack();
			frame.setVisible(true);
		}


		@Override

/** 
 * 1.Checks to see which button was pressed.	
 * 2.Tells controller to add a chip 
 * 3.Finds out what color placed, relays to user
 * 4.Ensures that not more than 6 chips in one column
 */
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== Column1){
				controller.add(6);
				this.updateBoard();
				Click1++;
				if(Click1==6){		
					Column1.setEnabled(false);
					allButtonDisabled++;
				}
			} else if(e.getSource()== Column2){
				controller.add(5);
				this.updateBoard();
				Click2++;
				if(Click2==6){
					Column2.setEnabled(false);
					allButtonDisabled++;
				}
			} else if(e.getSource()== Column3){
				controller.add(4);
				this.updateBoard();	
				Click3++;
				if(Click3==6){
					Column3.setEnabled(false);
					allButtonDisabled++;
				}
			} else if(e.getSource()== Column4){
				controller.add(3);
				this.updateBoard();
				Click4++;
				if(Click4==6){
					Column4.setEnabled(false);
					allButtonDisabled++;
				}
			} else if(e.getSource()== Column5){
				controller.add(2);
				this.updateBoard();
				Click5++;
				if(Click5==6){
					Column5.setEnabled(false);
					allButtonDisabled++;
				}
			} else if(e.getSource()== Column6){
				controller.add(1);
				this.updateBoard();	
				Click6++;
				if(Click6==6){
					Column6.setEnabled(false);
					allButtonDisabled++;
				}
			} else{
				controller.add(0); 
				this.updateBoard();
				Click7++;
				if(Click7==6){
					Column7.setEnabled(false);
					allButtonDisabled++;
				}
				
			if (allButtonDisabled==7){
				System.out.println("Tie Game");
			}
		}

			if(vsComputer == true){

				AI.setMove();
				controller.add(AI.getMove());
				this.updateBoard();
			}
			status.setText("Turn:"+controller.getTurn()); 

/**
 * this goes and calls the controller to see if there is a winner, and then the board deals with it
 */
			if(controller.winner() != ""){
				disableAllButtons();
				this.winner(controller.winner());
				status.setText("The winner is "+controller.winner()); 
			}
		}
		
		private class QuitListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				System.exit(0);

			}
		}
	}
}


        