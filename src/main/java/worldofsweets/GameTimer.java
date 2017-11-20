package worldofsweets;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import javax.swing.*;


public class GameTimer extends JLabel {
    Timer timer = null;
    long time = -1;
    WorldOfSweets game = null;

    public GameTimer(WorldOfSweets game) {
        timer = new Timer();
        this.game = game;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (game != null && !game.isPaused()) {
                    time++;
                    GameTimer.this.setText(GameTimer.getTimeString(time));
                }
            }
        }, 0, 1000);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static String getTimeString(long time) {
        long seconds = -1;
        long minutes = -1;
        long hours = -1;

        hours = time / (60 * 60);
        time -= hours * (60 * 60);
        minutes = time / 60;
        time -= minutes * 60;
        seconds = time;

        String timeStr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeStr;
    }

    public String save() {
        return Long.toString(time);
    }

    public boolean load(String loadString) {
        try {
            time = Long.parseLong(loadString);
            return true;
        } catch (Exception e) {
            // Invalid time loaded
            return false;
        }
    }
}
