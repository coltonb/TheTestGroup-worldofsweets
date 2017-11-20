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
        // MyCanvas m=new MyCanvas();
        // JFrame f=new JFrame();
        // f.add(m);
        // f.setSize(400,400);
        // f.setVisible(true);
        // ImageIcon imageIcon = new ImageIcon("src/main/resources/images/p1.jpg");
        // Image image = imageIcon.getImage(); // transform it
        // Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        // imageIcon = new ImageIcon(newimg);
        // JLabel label = new JLabel("", imageIcon, JLabel.CENTER);
        // label.setBounds(10,10,40,40);
        // add(label);
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
