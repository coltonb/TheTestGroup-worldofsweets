package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
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
        
        boardTiles = new BoardTile[WorldOfSweets.TILE_HEIGHT][WorldOfSweets.TILE_WIDTH];
                                 
        drawBoard(board);
    }

    public void clearBoard() {
        removeAll();
        for (int i = 0; i < WorldOfSweets.TILE_HEIGHT; i++) {
            for (int j = 0; j < WorldOfSweets.TILE_WIDTH; j++) {
                boardTiles[i][j] = null;
            }
        }
    }

    /* colton: kept here strictly for reference. will delete later
    private void createBoard() {
        int pathIter = 0;
        for (int i = TILE_HEIGHT - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                for (int j = 0; j < TILE_WIDTH; j++) {
                    BoardTile newTile = new BoardTile(colors[(j + 4) % 5]);
                    boardTiles[i][j] = newTile;
                    path[pathIter++] = newTile;
                    if (j == TILE_WIDTH - 1)
                        newTile.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
                            Color.WHITE));
                    if (j == 0)
                        newTile.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0,
                            Color.WHITE));
                }
            } else {
                for (int j = 0; j < TILE_WIDTH; j++) {
                    BoardTile newTile = new BoardTile(colors[(j + 4) % 5]);
                    boardTiles[i][TILE_WIDTH - 1 - j] = newTile;
                    path[pathIter++] =  newTile;
                    if (j == 0)
                        newTile.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0,
                            Color.WHITE));
                    if (j == TILE_WIDTH - 1)
                        newTile.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
                            Color.WHITE));
                }
            }
        }
        boardTiles[TILE_HEIGHT - 1][0].setBackground(new Color(255, 255, 255));
        boardTiles[0][TILE_WIDTH - 1].setBackground(new Color(255, 255, 255));
    }*/

    public void drawBoard(Board board) { 
        clearBoard();    
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < WorldOfSweets.TILE_HEIGHT; i++) {
            for (int j = 0; j < WorldOfSweets.TILE_WIDTH; j++) {
                Color tileColor = tiles[i][j].getColor().getAwt();
                BoardTile boardTile = new BoardTile(tileColor);
                if (j == WorldOfSweets.TILE_WIDTH - 1) {
                    boardTile.setBorder(BorderFactory.createMatteBorder(
                        3 * (i & 1),
                        0,
                        3 * ((i + 1) & 1),
                        0,
                        Color.WHITE));
                }
                if (j == 0) {
                    boardTile.setBorder(BorderFactory.createMatteBorder(
                        3 * ((i + 1) & 1),
                        0,
                        3 * (i & 1),
                        0,
                        Color.WHITE));
                }
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
}
