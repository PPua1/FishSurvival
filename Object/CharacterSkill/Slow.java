package Object.CharacterSkill;

import Lib.GameTimer;
import Screen.GameScreen;

public class Slow implements Skill{
    private int cooldown = 20000;
    private long lastUsed = -cooldown;
    private int duration = 4000;
    private boolean active = false;
    private int originalSpeed;

    // @Override
    // public void activate(Character c, GameScreen g) {
    //     if (isAvailable()) {
    //         lastUsed = System.currentTimeMillis();
    //         active = true;
    //         originalSpeed = g.getPipeSpeed();
    //         g.setPipeSpeed(originalSpeed / 2); 
    //         c.setSlowMotion(true); // เปลี่ยน sprite เป็น slow-motion
    //     }
    // }

    // @Override
    // public void update(Character c, GameScreen g) {
    //     if (active && System.currentTimeMillis() - lastUsed > duration) {
    //         g.setPipeSpeed(originalSpeed); 
    //         active = false;
    //         c.setSlowMotion(false); // กลับเป็น sprite ปกติ
    //     }
    // }

    // @Override
    // public boolean isAvailable() {
    //     return (System.currentTimeMillis() - lastUsed) >= cooldown;
    // }

    // @Override
    // public void activate(Character c) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'activate'");
    // }

    // @Override
    // public void update(Character c, GameTimer t) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'update'");
    // }

    // @Override
    // public boolean isActive() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isActive'");
    // }
    
}
