package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class BoardTile extends JPanel {
    public BoardTile() {
        this(Color.WHITE);
    }

    public BoardTile(Color color) {
        setBackground(color);
        setLayout(new GridLayout(2, 2));
        setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.WHITE));
        setPreferredSize(new Dimension(5, 50));
        setVisible(true);
    }
}
