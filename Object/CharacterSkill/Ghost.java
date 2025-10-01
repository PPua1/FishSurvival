package Object.CharacterSkill;
import Object.Character;
import Lib.GameTimer;
import Screen.GameScreen;

public class Ghost implements Skill {
    private int duration = 30000; //เวลาการใช้สกิล 30 วิ
    private boolean active = false; // ตอนนี้ล่องหนอยู่ไหม
    private boolean use = false; //ใช้ไปหรือยัง
    private long startTimer; // เวลาที่เริ่มใช้งาน
    @Override
    public void activate(Character c, GameScreen g) {
       if (!use) {
            use = true; //เปลี่ยนสถานะเป็นใช้แล้ว
            active = true; // กำลังล่องหน
            startTimer = System.currentTimeMillis(); //ดึงเวลาปัจจุบัน
           c.setGhostMode(true); //ตัวละครไม่ชนท่อ
       }
    }
    @Override
    public void update(Character c, GameTimer t, GameScreen g) {
        if (active && System.currentTimeMillis() - startTimer >= duration) {
            active = false;
            c.setGhostMode(false);//หยุดสกิล
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