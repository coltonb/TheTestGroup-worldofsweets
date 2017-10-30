package worldofsweets;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 750;

    private BoardPanel boardPanel;
    private CardPanel cardPanel;

    private int numPlr;
    private String[] playerNames;

    public static enum CardType {
        RED, YELLOW, BLUE, GREEN, ORANGE, DOUBLERED, DOUBLEYELLOW, DOUBLEBLUE,
        DOUBLEGREEN, DOUBLEORANGE;
    }

    public GameFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numPlr = getNumPlayers();
        getPlayerNames(numPlr);
        printTitle();

        boardPanel = new BoardPanel(this, numPlr);
        cardPanel = new CardPanel(this, boardPanel);

        add(boardPanel, BorderLayout.PAGE_START);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private int getNumPlayers() {
        int numPlr = 0;
        Object[] options = {1, 2, 3, 4};
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

    private void getPlayerNames(int num) {
        playerNames = new String[num];
        for (int i = 0; i < num; i++) {
            playerNames[i] = (String) JOptionPane.showInputDialog(
                this,
                "What is Player " + (i + 1) + "'s name?",
                "Name Entry",
                JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void printTitle() {
        String title = "World of Sweets. Players: ";
        for (String name : playerNames) {
            title += name + ", ";
        }
        title = title.substring(0, title.length() - 2);
        setTitle(title);
    }
}