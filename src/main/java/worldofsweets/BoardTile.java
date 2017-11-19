package worldofsweets;

// import java.awt.*;
// import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardTile extends JPanel {
    private BufferedImage image;
    public BoardTile() {
        this(Color.WHITE);
    }

    public BoardTile(Color color) {
        //System.out.println(color == WorldOfSweets.Color.GOTOBUBBLEGUM.getAwt());
        String fname = "";

        try {
            if (color == WorldOfSweets.Color.GOTOBUBBLEGUM.getAwt()){
                fname = "../resources/images/snowcone.png";
            }
            else if (color == WorldOfSweets.Color.GOTOCANDYCORN.getAwt()){
                fname = "../resources/images/lolipop.png";
            }
            else if (color == WorldOfSweets.Color.GOTOICECREAM.getAwt()){
                fname = "../resources/images/icecream.png";
            }
            else if (color == WorldOfSweets.Color.GOTOLABOONROOM.getAwt()){
                fname = "../resources/images/cookie.png";
            }
            else if (color == WorldOfSweets.Color.GOTOCHOCOLATE.getAwt()){
                fname = "../resources/images/chocolate.png";
            }
            else if (color == WorldOfSweets.Color.FINISH.getAwt()){
                fname = "../resources/images/grandma.png";
            }
            else if (color == WorldOfSweets.Color.RED.getAwt()){
                fname = "../resources/images/redtile.jpg";
            }
            else if (color == WorldOfSweets.Color.BLUE.getAwt()){
                fname = "../resources/images/bluetile.jpg";
            }
            else if (color == WorldOfSweets.Color.GREEN.getAwt()){
                fname = "../resources/images/greentile.jpg";
            }
            else if (color == WorldOfSweets.Color.YELLOW.getAwt()){
                fname = "../resources/images/yellowtile.jpg";
            }
            else if (color == WorldOfSweets.Color.ORANGE.getAwt()){
                fname = "../resources/images/orangetile.jpg";
            }
            image = ImageIO.read(new File(fname));
        } catch (IOException ex) {
            setBackground(color);
        }
        setLayout(new GridLayout(2, 2));
        setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.WHITE));
        setPreferredSize(new Dimension(5, 50));
        setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
