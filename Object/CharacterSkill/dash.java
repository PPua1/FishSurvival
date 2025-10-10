package Object.CharacterSkill;

import Lib.GameTimer;
import Object.Character;
import Screen.GameScreen;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class dash implements Skill{
    private int duration = 10000; //เวลาการใช้สกิล 5 วิ
    private boolean active = false; 
    private boolean use = false; //ใช้ไปหรือยัง
    private long startTimer; // เวลาที่เริ่มใช้งาน
    private int originalVelocityX; //ค่าความเร็วเดิมของท่อ
    private Image effectImage;

    public dash(){
        effectImage = new ImageIcon(getClass().getResource("/Asset/SkillImage/DashSkill.png")).getImage();
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
            active = true;
            use = true;
            startTimer = System.currentTimeMillis();

            originalVelocityX = g.getPipeManager().getPipeSpeed();
            g.getPipeManager().setVelocityX(originalVelocityX*10);//เร็วขึ้น4เท่าจากความเร็วเดิม
            c.setDashMode(true);
        }
    }
    @Override
    public void update(Character c, GameTimer t, GameScreen g) {
        if (active&& System.currentTimeMillis() - startTimer >= duration) {
            active = false;
            g.getPipeManager().setVelocityX(originalVelocityX);//คืนความเร็วท่อ
            c.setDashMode(false);
            c.setInvincible(3000);
        }
    }

    @Override
    public void drawEffect(Graphics g, int x, int y){
        if (active && effectImage != null) {
            int imgWidth = 70;  // ขนาดภาพตัวละคร
            int imgHeight = 70;
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