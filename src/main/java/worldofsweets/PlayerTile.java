package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class PlayerTile extends JLabel {
    public PlayerTile() {
        setFont(new Font("Arial", Font.BOLD, 15));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public PlayerTile(Player player) {
        this();
        setText(player.getName().substring(0, 1));
    }
}
