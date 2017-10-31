package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class WorldOfSweets {

    public static final int W_WIDTH = 600;
    public static final int W_HEIGHT = 750;

    public static final int TILE_WIDTH = 10;
    public static final int TILE_HEIGHT = 7;

    private int numPlayers;
    private int currentPlayer = 0;    
    Player[] players;

    private GameFrame frame;
    private BoardPanel boardPanel;
    private CardPanel cardPanel;

    private Board board;

    public static enum Color {
        RED,
        YELLOW,
        BLUE,
        GREEN,
        ORANGE,
        START,
        FINISH
    }

    public WorldOfSweets() {

        numPlayers = promptNumPlayers();

        players = new Player[numPlayers];

        String[] names = promptPlayerNames(numPlayers);

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
            players[i].setName(names[i]);
        }

        board = new Board();
        board.addPlayers(players);

        frame = new GameFrame(players);
        boardPanel = new BoardPanel(board);
        cardPanel = new CardPanel(this);

        frame.add(boardPanel, BorderLayout.PAGE_START);
        frame.add(cardPanel, BorderLayout.CENTER);

        frame.setVisible(true);

    }

    private int promptNumPlayers() {
        Object[] options = {1, 2, 3, 4};
        int numPlr = (int) JOptionPane.showInputDialog(
            null,
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

    private String[] promptPlayerNames(int numPlr) {
        String[] names = new String[numPlr];
        for (int i = 0; i < numPlr; i++) {
            names[i] = (String) JOptionPane.showInputDialog(
                null,
                "What is Player " + (i + 1) + "'s name?",
                "Name Entry",
                JOptionPane.PLAIN_MESSAGE);
        }
        return names;
    }

    public void makeMove(Card cardDrawn){
        //move player
        //TODO

        //update current player
        currentPlayer = (currentPlayer + 1) % players.length;

        //prompt player
        JOptionPane.showMessageDialog(
            null,
            players[currentPlayer].getName() + ", it's your turn!",
            "Player Confirmation",
            JOptionPane.INFORMATION_MESSAGE);
        
        boardPanel.drawBoard(board);
    }

    public static void main(String[] args) {
        new WorldOfSweets();
    }
}
