package Object;

import javax.swing.*;
import java.awt.*;

public class BackgroundManager {
    private Image[] backgrounds;
    private int currentIndex = 0;

    public BackgroundManager() {
        backgrounds = new Image[]{
            new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage(),
            new ImageIcon(getClass().getResource("/Asset/Water.png")).getImage(),
            new ImageIcon(getClass().getResource("/Asset/Kitchen.png")).getImage()
        };
    }

    public Image getCurrentBackground() {
        return backgrounds[currentIndex];
    }

    public void nextBackground() {
        currentIndex = (currentIndex + 1) % backgrounds.length;
    }

    public void reset() {
        currentIndex = 0;
    }
}
