package worldofsweets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class BoomerangPanel extends JPanel {
    private JButton throwButton = null;
    private int numBoomerangs = -1;
    private WorldOfSweets game = null;
    private CardPanel panel = null;

    private final String PICTURE_NAME = "src/main/resources/images/boomerang.png";

    private BufferedImage loadImage() {
        try {
            return ImageIO.read(new File(PICTURE_NAME));
        } catch (Exception e) {
            return null;
        }
    }

    public BoomerangPanel(WorldOfSweets game, CardPanel panel) {
        this.game = game;
        this.panel = panel;

        throwButton = new JButton("Throw Boomerang");

        throwButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if(game.getCurrentPlayer().getType().equals("Human"))
              {
                if (numBoomerangs > 0) {
                    Player target = promptPlayer();
                    if (target != null) {
                        throwBoomerang(target);
                    }
                } else {
                    promptNoBoomerangs();
                }
              }
            }
        });

        BufferedImage image = loadImage();

        if (image != null) {
            JPanel boomerangGraphic = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, 64, 64, null);
                }
            };
            boomerangGraphic.setPreferredSize(new Dimension(64,64));
            add(boomerangGraphic, BorderLayout.PAGE_START);
        }

        numBoomerangs = 0;
        add(throwButton, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private Player promptPlayer() {
        Player[] players = game.getPlayers();

        Object[] options = new Object[players.length - 1];


        for (int i = 0, j = 0; i < players.length; i++) {
            if (!game.getCurrentPlayer().equals(players[i])) {
                options[j++] = players[i].getName();
            }
        }

        String result = (String) JOptionPane.showInputDialog(
            null,
            "Who is your target?",
            "Pick a victim.",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            1);

        if (result == null) {
            return null;
        }

        for (int i = 0; i < players.length; i++) {
            if (players[i].getName().equals(result)) {
                return players[i];
            }
        }

        return null;
    }

    private void promptNoBoomerangs() {
        JOptionPane.showMessageDialog(
            null,
            "Sorry " + game.getCurrentPlayer().getName() + ", you have no more boomerangs!",
            "Notice",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void throwBoomerang(Player target) {
        if (numBoomerangs > 0) {
            game.getCurrentPlayer().decrementBoomerangs();
            panel.drawACard(target);
        }
    }

    public void update(int numBoomerangs) {
        this.numBoomerangs = numBoomerangs;
        throwButton.setText("Throw Boomerang: " + numBoomerangs);
        setVisible(true);
    }
}
