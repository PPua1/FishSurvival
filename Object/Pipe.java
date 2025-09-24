package Object;

import java.awt.Image;
import java.awt.Rectangle;

public class Pipe {
        private int x ;
        private int y ;
        private int width = 64;
        private int height = 512 ;
        private boolean passed = false;
        private Image img;

        Pipe(Image img, int x, int y) {
            this.x = x;
            this.y = y;
            // this.width = width;
            // this.height = height;
            this.height = img.getHeight(null);

            this.img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            
        }
        
        public boolean isOffScreen() {
            return x + width < 0;
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

        public void setWidth(int width) { 
            this.width = width; 
        }
        
        public int getWidth() { 
            return width; 
        }

    public void setHeight(int height) { this.height = height; }
    public int getHeight() { return height; }

        public Rectangle[] getBounds() {
            int marginX = -3;
            int marginY = 5;
            return new Rectangle[]{
                new Rectangle(x + marginX, y + marginY, width + 2*marginX, height - 2*marginY)
            };
        }


        public boolean isPassed() {
            return passed;
        }

        public void setPassed(boolean p) {
            passed = p;
        }

        public Image getImage() {
            return img; 
        }
    }