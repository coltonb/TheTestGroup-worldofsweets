package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class WorldOfSweets {

    public static final int W_WIDTH = 600;
    public static final int W_HEIGHT = 750;

    public static final int TILE_WIDTH = 10;
    public static final int TILE_HEIGHT = 7;

    private int numPlayers = -1;
    private int currentPlayer = -1;
    Player[] players = null;

    private GameFrame frame = null;
    private BoardPanel boardPanel = null;
    private CardPanel cardPanel = null;

    private Board board = null;
    private static final java.awt.Color REDAWT    =
        new java.awt.Color(231, 76, 60);
    private static final java.awt.Color YELLOWAWT =
        new java.awt.Color(241, 196, 15);
    private static final java.awt.Color BLUEAWT   =
        new java.awt.Color(52, 152, 219);
    private static final java.awt.Color GREENAWT  =
        new java.awt.Color(46, 204, 113);
    private static final java.awt.Color ORANGEAWT =
        new java.awt.Color(230, 126, 34);
    private static final java.awt.Color WHITEAWT =
        new java.awt.Color(255,255,255);


    private static final java.awt.Color FINISHAWT =
        new java.awt.Color(255,213,79);


    private static final java.awt.Color BUBBLEGUMAWT =
        new java.awt.Color(244,143,177);
    private static final java.awt.Color CHOCOLATEAWT =
        new java.awt.Color(62,39,35);
    private static final java.awt.Color LABOONROOMAWT =
        new java.awt.Color(0,0,0);
    private static final java.awt.Color CANDYCORNAWT =
        new java.awt.Color(255,167,38);


    public static enum Color {
        RED(REDAWT),
        YELLOW(YELLOWAWT),
        BLUE(BLUEAWT),
        GREEN(GREENAWT),
        ORANGE(ORANGEAWT),
        GOTOBUBBLEGUM(BUBBLEGUMAWT),
        GOTOCANDYCORN(CANDYCORNAWT),
        GOTOICECREAM(WHITEAWT),
        GOTOLABOONROOM(LABOONROOMAWT),
        GOTOCHOCOLATE(CHOCOLATEAWT),
        SKIP(WHITEAWT),
      //  START(WHITEAWT),
        FINISH(FINISHAWT);

        private final java.awt.Color AWTCOLOR;

        Color(java.awt.Color c) {
            this.AWTCOLOR = c;
        }

        public java.awt.Color getAwt() {
            return AWTCOLOR;
        }
    }

    public WorldOfSweets() {

        numPlayers = promptNumPlayers();

        players = new Player[numPlayers];
        currentPlayer = 0;

        String[] names = promptPlayerNames(numPlayers);

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
            players[i].setName(names[i]);

        }


        board = new Board();
        System.out.println(board.getLength());
        board.addPlayers(players);

        frame = new GameFrame(players);
        boardPanel = new BoardPanel(board);
        cardPanel = new CardPanel(this);

        frame.add(boardPanel, BorderLayout.PAGE_START);
        frame.add(cardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        //prompt player
        promptPlayerTurn(players[currentPlayer]);
    }

    //Alerts player that it is their turn
    private void promptPlayerTurn(Player player) {
        JOptionPane.showMessageDialog(
            null,
            player.getName() + ", it's your turn!",
            "Player Confirmation",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Displays a dialog with a dropdown menu to ask for the number of players.
     */
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

    /*
     * Prompts numPlr players for their names
     * Returns an array of names given a number of players.
     */
    private String[] promptPlayerNames(int numPlr) {
        String[] names = new String[numPlr];
        for (int i = 0; i < numPlr; i++) {
            names[i] = (String) JOptionPane.showInputDialog(
                null,
                "What is Player " + (i + 1) + "'s name?",
                "Name Entry",
                JOptionPane.PLAIN_MESSAGE);
            // Default name to Player i+1 should they not provide one
            if (names[i].length() == 0) names[i] = "Player " + (i + 1);
        }
        return names;
    }

    public void makeMove(Card cardDrawn){
        // move player
        board.movePlayer(players[currentPlayer], cardDrawn);
        // update board
        boardPanel.drawBoard(board);

        // Check for winners here, do something about it
        if (players[currentPlayer] == board.checkWinner()){
          System.out.println("There is a winner!");
          JOptionPane.showMessageDialog(
              null,
              players[currentPlayer].getName() + " wins!",
              "Winner!",
              JOptionPane.INFORMATION_MESSAGE);

              if (JOptionPane.showConfirmDialog(null, "Play Again?", "",
                  JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                  frame.setVisible(false); //you can't see me!
                  frame.dispose();
                  new WorldOfSweets();
              }else {
                  System.exit(0);
              }
        }


        //update current player
        currentPlayer = (currentPlayer + 1) % players.length;

        //prompt player
        promptPlayerTurn(players[currentPlayer]);
    }

    public static void main(String[] args) {
        new WorldOfSweets();
    }
}
