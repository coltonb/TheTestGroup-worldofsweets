package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class CardPanel extends JPanel {
    private BoardPanel board;
    private GameFrame game;
    public CardPanel(GameFrame gf, BoardPanel bp) {
        game = gf;
        board = bp;
    }
}