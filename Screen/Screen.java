package Screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Screen extends JPanel {
    protected App app;

    public Screen(App app) {
        this.app = app;
        setFocusable(true);
        setLayout(null);
    }

    protected abstract  void initial();
    public abstract void draw(Graphics g);
    public abstract void update();
    public abstract void handleInput(KeyEvent e);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
