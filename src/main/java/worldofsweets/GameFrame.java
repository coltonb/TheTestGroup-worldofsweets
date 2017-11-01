package worldofsweets;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(Player[] players) {
        setSize(WorldOfSweets.W_WIDTH, WorldOfSweets.W_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        printTitle(players);
    }

    private void printTitle(Player[] players) {
        String title = "World of Sweets. Players: ";
        for (Player player : players) {
            title += player.getName() + ", ";
        }
        title = title.substring(0, title.length() - 2);
        setTitle(title);
    }
}
