package Object;

import java.awt.*;

public class Pipe {
    private int x;
    private int topHeight;
    private int bottomHeight;
    private int width;
    private int gap;
    private boolean passed = false;
    private Image topImage;
    private Image bottomImage;
    private int speed = 3;

    public Pipe(int x, int topHeight ,int bottomHeight, int width ){
        this.x = x;
        this.topHeight = topHeight;
        this.gap = gap;
        this.width = width;
        this.bottomHeight = 640 - (topHeight + gap); // สมมติความสูงจอ = 640
        this.topImage = topImage;
        this.bottomImage = bottomImage;
    }
    // เลื่อนท่อ
    public void move(){
        x -= speed;
    }

    // ตรวจท่อหลุดจอ
    public boolean isOffScreen(){
            return  (x + width) < 0;

    }

    // ตรวจว่าผ่านตัวละคร เพื่อใช้เพิ่มคะแนน
    public boolean isPassed(Character character){
        if (!passed && (x+width) < character.getX()){
            passed = true;
            return passed;
        }
            return false;
    }

    public void draw(Graphics g){
        g.drawImage(topImage, x, 0, width, topHeight, null);
        g.drawImage(bottomImage, x, topHeight + gap, width, bottomHeight, null);

    }
    public Rectangle[] getBounds(){
        Rectangle topRect = new Rectangle(x, 0, width, topHeight);
        Rectangle bottomRect = new Rectangle(x, topHeight + gap, width, bottomHeight);
        return new Rectangle[]{topRect, bottomRect};
    }
}
