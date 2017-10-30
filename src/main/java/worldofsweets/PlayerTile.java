package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class PlayerTile extends JLabel {
    private boolean empty;
    private Player player;

    public PlayerTile() {
        empty = true;
        setFont(new Font("Arial", Font.PLAIN, 15));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean set(Player player) {
        if (isEmpty()) {
            setText(player.getName().substring(0, 1));
            empty = false;
            return true;
        } else {
            return false;
        }
    }

    public void empty() {
        setText("");
        empty = true;
    }
}
