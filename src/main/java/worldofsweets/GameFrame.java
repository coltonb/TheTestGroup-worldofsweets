package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 800;

    private JFrame frame = new JFrame("World of Sweets");

    private BoardPanel boardPanel;
    private CardPanel cardPanel;

    public GameFrame() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int numPlr = promptPlrs();

        boardPanel = new BoardPanel(this, numPlr);
        cardPanel = new CardPanel(this, boardPanel);

        frame.add(boardPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
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