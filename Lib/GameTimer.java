package Lib;

import javax.swing.Timer;
import java.awt.event.ActionListener;

public class GameTimer {
    private Timer timer;
    private int delay;
    private ActionListener action;

    public GameTimer(int delay, Runnable action) {
        this.delay = delay;
        this.action = e -> action.run();
        this.timer = new Timer(delay, this.action);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public boolean isRunning() {
        return timer.isRunning();
    }
}
