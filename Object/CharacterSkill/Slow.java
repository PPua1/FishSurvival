package Object.CharacterSkill;

import Lib.GameTimer;
import Screen.GameScreen;

public class Slow implements Skill{
    private int duration = 30000; //เวลาการใช้สกิล 30 วิ
    private boolean active = false; 
    private boolean use = false; //ใช้ไปหรือยัง
    private long startTimer; // เวลาที่เริ่มใช้งาน
    private int originalVelocityX; //ค่าความเร็วเดิมของท่อ
    @Override
    public void activate(Character c, GameScreen g) {
        if (!use) {
            use = true;
            active = true;
            startTimer = System.currentTimeMillis();
            originalVelocityX = g.getPipeManager().getVelocityX();
            g.gatPipeManager().setVelocityX(-1);
       }
    }
    @Override
    public void update(Character c, GameTimer t, GameScreen g) {
        if (active&& System.currentTimeMillis() - startTimer >= duration) {
            active = false;
            g.getPipeManager().setVelocityx(originalVelocityX);
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