package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 750;

    private JFrame frame = new JFrame("World of Sweets");

    private BoardPanel boardPanel;
    private CardPanel cardPanel;

    public static enum Card {
        RED, YELLOW, BLUE, GREEN, ORANGE, DOUBLERED, DOUBLEYELLOW, DOUBLEBLUE,
        DOUBLEGREEN, DOUBLEORANGE;
    }

    public GameFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int numPlr = promptPlrs();

        boardPanel = new BoardPanel(this, numPlr);
        cardPanel = new CardPanel(this, boardPanel);

        add(boardPanel, BorderLayout.PAGE_START);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private int promptPlrs() {
        int numPlr = 0;
        Object[] options = {1, 2, 3, 4};
        String prompt = "How many people are playing?";
        numPlr = (int) JOptionPane.showInputDialog(
            this,
            "How many people are playing?",
            "Welcome!",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            1);
        return numPlr;
    }
}