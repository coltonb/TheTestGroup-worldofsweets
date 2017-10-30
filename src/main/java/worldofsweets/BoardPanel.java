package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
    private final int WIDTH = 10;
    private final int HEIGHT = 7;
    private GameFrame game;
    private BoardTile[][] boardTiles;
    private BoardTile[] path;
    private Color[] colors;
    private Player[] players;

    public BoardPanel(GameFrame gf, int numPlr, Player[] plrs) {
        game = gf;
        players = plrs;
        setLayout(new GridLayout(HEIGHT, WIDTH));

        boardTiles = new BoardTile[HEIGHT][WIDTH];
        path = new BoardTile[WIDTH * HEIGHT];

        colors = new Color[] {
            new Color(231, 76, 60),  // Red
            new Color(241, 196, 15), // Yellow
            new Color(52, 152, 219), // Blue
            new Color(46, 204, 113), // Green
            new Color(230, 126, 34)  // Orange
        };
        createBoard();
        drawBoard();
        addPlayers();
    }

    private void createBoard() {
        int pathIter = 0;
        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                for (int j = 0; j < WIDTH; j++) {
                    BoardTile newTile = new BoardTile(colors[(j + 4) % 5]);
                    boardTiles[i][j] = newTile;
                    path[pathIter++] = newTile;
                    if (j == WIDTH - 1)
                        newTile.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
                            Color.WHITE));
                    if (j == 0)
                        newTile.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0,
                            Color.WHITE));
                }
            } else {
                for (int j = 0; j < WIDTH; j++) {
                    BoardTile newTile = new BoardTile(colors[(j + 4) % 5]);
                    boardTiles[i][WIDTH - 1 - j] = newTile;
                    path[pathIter++] =  newTile;
                    if (j == 0)
                        newTile.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0,
                            Color.WHITE));
                    if (j == WIDTH - 1)
                        newTile.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
                            Color.WHITE));
                }
            }
        }
        boardTiles[HEIGHT - 1][0].setBackground(new Color(255, 255, 255));
        boardTiles[0][WIDTH - 1].setBackground(new Color(255, 255, 255));
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
    private void addPlayers() {
        for (Player player : players) {
            boardTiles[HEIGHT - 1][0].addPlayer(player);
        }
    }

    public int getPlayerNum(){
      return players.length;
    }

    public BoardTile[][] getBoardTiles(){
      return boardTiles;
    }
}
