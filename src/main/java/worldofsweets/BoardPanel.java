package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
    private final int WIDTH = 10;
    private final int HEIGHT = 7;
    BoardTile[][] boardTiles;

    private final Color[] COLORS = {
        new Color(231, 76, 60),  // Red
        new Color(241, 196, 15), // Yellow
        new Color(52, 152, 219), // Blue
        new Color(46, 204, 113), // Green
        new Color(230, 126, 34)  // Orange
    };
    
    public BoardPanel(Board board) {
        setLayout(new GridLayout(WorldOfSweets.TILE_HEIGHT,
                                 WorldOfSweets.TILE_WIDTH));
        
        boardTiles = new BoardTile[HEIGHT][WIDTH];
                                 
        drawBoard(board);
    }

    public void clearBoard() {
        removeAll();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boardTiles[i][j] = null;
            }
        }
    }

    /* colton: kept here strictly for reference. will delete later
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
    }*/

    public void drawBoard(Board board) { 
        clearBoard();    
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Color tileColor = getRGBFromColor(tiles[i][j].getColor());
                BoardTile boardTile = new BoardTile(tileColor);
                
                if (tiles[i][j].hasPlayers()) {
                    for (Player player : tiles[i][j].getPlayers()) {
                        if (player != null) {
                            boardTile.add(new PlayerTile(player));
                        } else {
                            boardTile.add(new PlayerTile());
                        }
                    }
                }

                add(boardTile);
            }
        }
    }

    // Determining which literal awt.Color value should correspond to the 
    // world of sweets colors. We use white for any color we don't have mapped.
    private Color getRGBFromColor(WorldOfSweets.Color c) {
        if (c == WorldOfSweets.Color.RED) {
            return COLORS[0];
        }
        if (c == WorldOfSweets.Color.YELLOW) {
            return COLORS[1];
        }
        if (c == WorldOfSweets.Color.BLUE) {
            return COLORS[2];
        }
        if (c == WorldOfSweets.Color.GREEN) {
            return COLORS[3];
        }
        if (c == WorldOfSweets.Color.ORANGE) {
            return COLORS[4];
        }
        return Color.WHITE;
    }
}
