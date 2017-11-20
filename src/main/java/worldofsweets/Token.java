package worldofsweets;

import javax.swing.*;
import java.awt.*;

// import java.awt.*;
// import javax.swing.JFrame;

public class Token extends Canvas{

    public void paint(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("src/main/resources/images/p1.jpg");
        g.drawImage(i, 120,100,this);

    }
}

// public class Token extends JPanel {
//     private final int num;
//     private static int x;
//     private static int y;
//     private static int width;
//     private static int height;
//     private String name;
//     private Image img;
//
//     public Token(int pNum, String pName) {
//         JLabel j = new JLabel(pName);
//         x = 500;
//         y = 600;
//         width = 20;
//         height = 39;
//         name = pName;
//         num = pNum;
//         setBounds(x, y, width, height);
//
//         if (pNum == 1)
//             img = new ImageIcon("src/main/resources/images/p1.jpg").getImage();
//         else if (pNum == 2)
//             img = new ImageIcon("src/main/resources/images/p1.jpg").getImage();
//         else if (pNum == 3)
//             img = new ImageIcon("src/main/resources/images/p1.jpg").getImage();
//         else
//             img = new ImageIcon("src/main/resources/images/p1.jpg").getImage();
//     }
//
//     public void setCoords(int newX, int newY) {
//         if (num == 1){
//             x = newX;
//             y = newY;
//          } //else if (num == 2){
//         //     x = newX+20;
//         //     y = newY;
//         // }else if (num == 3){
//         //     x = newX;
//         //     y = newY+15;
//         // }else{
//         //     x = newX+20;
//         //     y = newY+15;
//         // }
//         setBounds(x, y, width, height);
//     }
//
//     public void setSize(int newW, int newH) {
//         width = newW;
//         height = newH;
//         setBounds(x, y, width, height);
//     }
//
//     public String getName() {
//         return name;
//     }
//
//     public void paintComponent(Graphics g) {
//         g.drawImage(img, 50, 50, null);
//     }
//
//     public Image getImage() {
//         return img;
//     }
// }
