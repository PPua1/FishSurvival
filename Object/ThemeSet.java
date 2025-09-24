package Object;

import java.awt.Image;

public class ThemeSet {
    private Image background;
    private Image topImage;
    private Image bottomImage;

    public ThemeSet(Image background, Image top, Image bottom) {
        this.background = background;
        this.topImage = top;
        this.bottomImage = bottom;
    }

    public Image getBackground() {
        return background;
    }

    public Image getTopPipe() {
        return topImage;
    }

    public Image getBottomPipe() {
        return bottomImage;
    }


}
