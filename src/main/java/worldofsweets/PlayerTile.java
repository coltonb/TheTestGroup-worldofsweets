package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class PlayerTile extends JLabel {
    private boolean empty;

    public PlayerTile() {
        empty = true;
        // colton: we won't use comic sans :v
        setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean set(String token) {
        if (isEmpty()) {
            setText("Player " + token);
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
