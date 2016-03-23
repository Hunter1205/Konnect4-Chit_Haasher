package ahmad.haasher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
/**
 * ConnectFour: Two-player console, graphics.
 */
 
public class mainDriver extends JFrame{
 
    public static final int EMPTY = 0;  
    public static final int PLAYER1 = 1;  
    public static final int PLAYER2 = 2;  
    public static final int PLAYING = 0;  
       
    private int currentState = 1;  // the current state of the game  
    private int currentPlayer; // the current player (PLAYER1 or PLAYER2)  
    private int Rows = 5;
    private int Columns = 6;
    private int AmountToWin = 4;  
    private int[][] board = new int[Rows][Columns];  
    private Scanner input = new Scanner(System.in); // the input Scanner
    MouseAdapter me;
    final ArcsPanel[][] arcs;

    public static void main(String[] args) {  
        new mainDriver();
    }

   /* contructor */
    public mainDriver(){  
       Game();
       arcs = new ArcsPanel[Rows][Columns];
       me = new MouseAdapter() {
          public void mouseClicked(MouseEvent me) {
             ArcsPanel panel = (ArcsPanel) me.getSource();
             int y = panel.column;
             int x = availableRow(y);
             if (x == -1){ 
            	 return;
             }
           	  board[x][y] = currentPlayer;
           	  if(currentPlayer == PLAYER1){
           		  currentPlayer = PLAYER2;
           	  }else{ 
           	   currentPlayer = PLAYER1;
           	   arcs[x][y].repaint();
           	   checkForResult(x, y);
           	  }
          } 
       };
       JPanel p1 = new JPanel();  
       p1.setLayout(new GridLayout(Rows, Columns)); 
   
       for(int i = 0; i < Rows; i++){ 
           for(int j = 0; j < Columns; j++){
              arcs[i][j] = new ArcsPanel(i, j);
              p1.add(arcs[i][j]);  
              arcs[i][j].addMouseListener(me);
           }
       }  
       add(p1, BorderLayout.CENTER);  
       this.pack();
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
       this.setVisible(true);
    } 
    
    private void checkForResult(int row, int col) {
       // check here if the game is finished or not
      }

    public void Game(){       
        for (int row = 0; row < Rows; ++row) {  
            for (int col = 0; col < Columns; ++col) {  
                board[row][col] = EMPTY;  // all cells empty  
            }  
        }  
        currentState = PLAYING; 
        currentPlayer = PLAYER1;  // player 1 plays first  
    }  

   /**finds the first empty space in a column starting at the bottom.*/ 
    public int availableRow(int col){   
        for(int row = Rows -1; row >= 0; row--){  
            if(board[row][col] == EMPTY){  
                return row;              
            }  
        }
        return -1;    
    }  
    
    public class ArcsPanel extends JPanel {   
        int row, column;
        public ArcsPanel(int r, int c) {
           row = r;
           column = c;
           this.setBackground(Color.BLACK);
           this.setPreferredSize(new Dimension(100, 100));
        }
        
        public void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            Color c = board[row][column] == EMPTY? Color.WHITE:
                board[row][column] == PLAYER1? Color.RED: Color.GREEN;
            g.setColor(c);
            Graphics2D holeGraphics = (Graphics2D) g.create();
            holeGraphics.fillOval(10, 10, 90, 90); 
        }  
    }
}
