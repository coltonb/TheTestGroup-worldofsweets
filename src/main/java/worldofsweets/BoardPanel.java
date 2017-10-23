package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private GameFrame game;
    private int numPlr;

    public BoardPanel(GameFrame gf, int numPlr) {
        game = gf;
        this.numPlr = numPlr;

        setLayout(new GridLayout(WIDTH, HEIGHT));
        drawBoard();
        addPlayers(numPlr);
    }

    /* Draws the board to the grid.
     * This can be hard coded. */
    private void drawBoard() {
        // TODO
    }

    /* Adds the players to the board 
     * Additionally should add them to some kind of
     * internal array */
    private void addPlayers(int num) {
        // TODO
    }
}