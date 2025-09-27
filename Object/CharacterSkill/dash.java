package Object.CharacterSkill;

import Lib.GameTimer;
import Screen.GameScreen;
import java.rmi.server.UID;

public class dash implements Skill{
    private int duration = 5000; //เวลาการใช้สกิล 5 วิ
    private boolean active = false; 
    private boolean use = false; //ใช้ไปหรือยัง
    private long startTimer; // เวลาที่เริ่มใช้งาน
    private int originalVelocityX; //ค่าความเร็วเดิมของท่อ

    @Override
    public void activate(Character c, GameScreen g) {
        if (!use) {
            active = true;
            use = true;
            startTimer = System.currentTimeMillis();
            originalVelocityX =g.getPipeManager().getVelocityX();
            g.getPipeManager().setVelocityX(originalVelocityX*2);//เร็วขึ้น2เท่าจากความเร็วเดิม
            c.setGhostMode(true);//ปลาล่องหน
        }
    }
    @Override
    public void update(Character c, GameTimer t, GameScreen g) {
        if (active&& System.currentTimeMillis() - startTimer >= duration) {
            active = false;
            g.getPipeManager().setVelocityx(originalVelocityX);//คืนความเร็วท่อ
            c.setGhostMode(false);
        }
    }
    @Override
    public boolean isAvailable() {
        return !use;
    }
    @Override
    public boolean isActive() {
        return active;
    }
}
