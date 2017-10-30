package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 750;

    Player[] players;	//0-3, holds the corresponding amount of Player Objects
    private int numPlayers = 0;		//1-4, effectively size of players[]
    private int currentPlayer = 0;	//0-3, which player's turn it is, corresponds with players[]

    private BoardPanel boardPanel;
    private CardPanel cardPanel;

    public static enum Cards {
        RED, YELLOW, BLUE, GREEN, ORANGE, DOUBLERED, DOUBLEYELLOW, DOUBLEBLUE,
        DOUBLEGREEN, DOUBLEORANGE;
    }

    public GameFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numPlayers = getNumPlayers();
        getPlayerNames();
        printTitle();

        boardPanel = new BoardPanel(this, numPlayers, players);
        cardPanel = new CardPanel(this, boardPanel);

        add(boardPanel, BorderLayout.PAGE_START);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private int getNumPlayers() {
        Object[] options = {1, 2, 3, 4};
        int numPlr = (int) JOptionPane.showInputDialog(
            this,
            "How many people are playing?",
            "Welcome!",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            1);

        players = new Player[numPlr];

        for (int i = 0; i < numPlr; i++) {
            players[i] = new Player();
        }
        
        return numPlr;
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
        JOptionPane.showMessageDialog(
            this,
            players[currentPlayer].getName() + ", it's your turn!",
            "Player Confirmation",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void getPlayerNames() {
        for (int i = 0; i < players.length; i++) {
            players[i].setName((String) JOptionPane.showInputDialog(
                this,
                "What is Player " + (i + 1) + "'s name?",
                "Name Entry",
                JOptionPane.PLAIN_MESSAGE));
        }
    }

    private void printTitle() {
        String title = "World of Sweets. Players: ";
        for (Player player : players) {
            title += player.getName() + ", ";
        }
        title = title.substring(0, title.length() - 2);
        setTitle(title);
    }

    public BoardPanel getPanel(){
      return this.boardPanel;
    }
}
