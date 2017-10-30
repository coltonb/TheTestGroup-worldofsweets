package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class PlayerTile extends JLabel {
    private boolean empty;

    public PlayerTile() {
        empty = true;
        setFont(new Font("Arial", Font.PLAIN, 15));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean set(String token) {
        if (isEmpty()) {
            setText(token.substring(0, 1));
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
