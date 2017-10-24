package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
    private final int WIDTH = 10;
    private final int HEIGHT = 7;
    private GameFrame game;
    private int numPlr;
    private BoardTile[][] boardTiles;
    private BoardTile[] path;
    private Color[] colors;

    public BoardPanel(GameFrame gf, int numPlr) {
        game = gf;
        this.numPlr = numPlr;
        setLayout(new GridLayout(HEIGHT, WIDTH));

        boardTiles = new BoardTile[HEIGHT][WIDTH];
        path = new BoardTile[WIDTH * HEIGHT];

        colors = new Color[] {
            new Color(255, 0, 0),   // Red
            new Color(255, 255, 0), // Yellow
            new Color(0, 0, 255),   // Blue
            new Color(0, 255, 0),   // Green
            new Color(255, 165, 0)  // Orange
        };
        createBoard();
        drawBoard();
        addPlayers(numPlr);
    }

    private void createBoard() {
        int pathIter = 0;
        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                for (int j = 0; j < WIDTH; j++) {
                    boardTiles[i][j] = new BoardTile(colors[(j + 4) % 5]);
                    path[pathIter++] = boardTiles[i][j];
                }
            } else {
                for (int j = 0; j < WIDTH; j++) {
                    boardTiles[i][WIDTH - 1 - j] = new BoardTile(colors[(j + 4) % 5]);
                    path[pathIter++] = boardTiles[i][j];
                }
            }
        }
        boardTiles[HEIGHT - 1][0].setBackground(new Color(165, 165, 165));
        boardTiles[0][WIDTH - 1].setBackground(new Color(165, 165, 165));
    }

    // Adds the board to the grid.
    private void drawBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                add(boardTiles[i][j]);
            }
        }
    }

    /* Adds the players to the board 
     * Additionally should add them to some kind of
     * internal array */
    private void addPlayers(int num) {
        for (int i = 1; i <= num; i++) {
            boardTiles[HEIGHT - 1][0].addPlayer(Integer.toString(i));
        }
    }
}