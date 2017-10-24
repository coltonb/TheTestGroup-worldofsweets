package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardTile extends JPanel {
    PlayerTile[] playerTiles;

    public BoardTile() {
        this(Color.WHITE);
    }

    public BoardTile(Color color) {
        setBackground(color);
        setLayout(new GridLayout(2, 2));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(5, 50));
        playerTiles = new PlayerTile[4];
        for (int i = 0; i < 4; i++) {
            playerTiles[i] = new PlayerTile();
            add(playerTiles[i]);
        }
        setVisible(true);
    }

    public void addPlayer(String token) {
        for (int i = 0; i < playerTiles.length; i++) {
            if (playerTiles[i].isEmpty()) {
                playerTiles[i].set(token);
                break;
            }
        }
    }

    public void removePlayer(String token) {
        for (int i = 0; i < playerTiles.length; i++) {
            if (playerTiles[i].isEmpty()) continue;
            if (playerTiles[i].getText().equals(token)) {
                playerTiles[i].empty();
                break;
            }
        }
    }
}