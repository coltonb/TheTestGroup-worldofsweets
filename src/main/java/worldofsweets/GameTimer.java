package worldofsweets;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import javax.swing.*;


public class GameTimer extends JLabel {
    Timer timer = null;
    long time = -1;
    public GameTimer() {
        timer = new Timer();
        time = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time++;
                GameTimer.this.setText(Long.toString(time));
            }
        }, 1000, 1000);
    }
}