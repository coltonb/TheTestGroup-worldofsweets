package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 750;

	Player[] players = new Player[4];	//0-3, holds the corresponding amount of Player Objects
	private int numPlayers = 0;		//1-4, effectively size of players[]
	private int currentPlayer = 0;	//0-3, which player's turn it is, corresponds with players[]
	
    private JFrame frame = new JFrame("World of Sweets");

    private BoardPanel boardPanel;
    private CardPanel cardPanel;

    public static enum Cards {
        RED, YELLOW, BLUE, GREEN, ORANGE, DOUBLERED, DOUBLEYELLOW, DOUBLEBLUE,
        DOUBLEGREEN, DOUBLEORANGE;
    }

    public GameFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numPlr = getNumPlayers();
        getPlayerNames(numPlr);
        printTitle();

        boardPanel = new BoardPanel(this, numPlr, playerNames);
        cardPanel = new CardPanel(this, boardPanel);

        add(boardPanel, BorderLayout.PAGE_START);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private int getNumPlayers() {
        int numPlr = 0;
        Object[] options = {1, 2, 3, 4};
        numPlr = (int) JOptionPane.showInputDialog(
            this,
            "How many people are playing?",
            "Welcome!",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            1);
		
		//create the players
		players[0] = new Player();
		players[0].setName("Player 1");
		
		players[1] = new Player();
		players[1].setName("Player 2");
		
		players[2] = new Player();
		players[2].setName("Player 3");
		
		players[3] = new Player();
		players[3].setName("Player 4");
		
        return numPlayers;
    }
	
	//This method will be called by CardPanel
	//and passed the last card drawn by a player,
	//then update the current player to the next one
	public void makeMove(Card cardDrawn){
      //move player
      //TODO

      //update current player
      currentPlayer = currentPlayer + 1;
      if(currentPlayer >= numPlayers){
        currentPlayer = 0;
      }

      //prompt player
      Object[] options = {true};
      boolean result = (boolean) JOptionPane.showInputDialog(
        this,
        players[currentPlayer].getName() + " are you ready?",
        "Player Confirmation",
        JOptionPane.PLAIN_MESSAGE,
        null,
        options,
        1
      );
		
	  }

    private void getPlayerNames(int num) {
        playerNames = new String[num];
        for (int i = 0; i < num; i++) {
            playerNames[i] = (String) JOptionPane.showInputDialog(
                this,
                "What is Player " + (i + 1) + "'s name?",
                "Name Entry",
                JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void printTitle() {
        String title = "World of Sweets. Players: ";
        for (String name : playerNames) {
            title += name + ", ";
        }
        title = title.substring(0, title.length() - 2);
        setTitle(title);
    }

    public BoardPanel getPanel(){
      return this.boardPanel;
    }
}
