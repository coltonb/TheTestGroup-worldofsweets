package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel {
    BoardTile[][] boardTiles = null;
    
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
