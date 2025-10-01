package Object;

import java.awt.Image;
import java.awt.Rectangle;

public class Pipe {
    private int x;
    private int y;
    private int width = 50;
    private int height = 300;
    private Image image;
    private boolean passed = false;
    private boolean bottomPipe;

    public Pipe(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    // คืนค่า Rectangle ของท่อนี้ สำหรับตรวจชน
    public Rectangle[] getBounds() {
        return new Rectangle[] {
            new Rectangle(x, y, width, height)
        };
    }

    public boolean isPassed() {
            return passed;
    }
        public boolean isBottomPipe() { 
            return bottomPipe; 
    }

        public void setPassed(boolean p) {
            passed = p;
    }
}
