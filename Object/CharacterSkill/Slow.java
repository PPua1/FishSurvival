package Object.CharacterSkill;
import Lib.GameTimer;
import Object.Character;
import Screen.GameScreen;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Slow implements Skill{
    private int duration = 30000; //เวลาการใช้สกิล 30 วิ
    private boolean active = false; 
    private boolean use = false; //ใช้ไปหรือยัง
    private long startTimer; // เวลาที่เริ่มใช้งาน
    private int originalVelocityX; //ค่าความเร็วเดิมของท่อ
    private Image effectImage;

    public Slow(){
        effectImage = new ImageIcon(getClass().getResource("/Asset/SkillImage/SlowSkill.png")).getImage();
    }
    @Override
    public boolean isAvailable() {
        return !use;
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void activate(Character c, GameScreen g) {
        if (!use) {
            use = true;
            active = true;
            startTimer = System.currentTimeMillis();
            originalVelocityX = g.getPipeManager().getPipeSpeed();
            g.getPipeManager().setVelocityX(-1);
       }
    }
    @Override
    public void update(Character c, GameTimer t, GameScreen g) {
        if (active&& System.currentTimeMillis() - startTimer >= duration) {
            active = false;
            g.getPipeManager().setVelocityX(originalVelocityX);
            c.setInvincible(3000);
        }
        
    }
    @Override
    public void drawEffect(Graphics g, int x, int y){
        if (active && effectImage != null) {
                int imgWidth = 50;  // ขนาดภาพตัวละคร
                int imgHeight = 50;
                double scale = Math.min((double) imgWidth / effectImage.getWidth(null),
                                        (double) imgHeight / effectImage.getHeight(null));
                int w = (int) (effectImage.getWidth(null) * scale);
                int h = (int) (effectImage.getHeight(null) * scale);

                g.drawImage(effectImage, x, y, w, h, null);
        }
    }
    @Override
    public void reset() {
        use = false;
        active = false;
    }
}