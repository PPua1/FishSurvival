package Object.CharacterSkill;
import Object.Character;
import Lib.GameTimer;
import Screen.GameScreen;

public class Shield implements Skill{
    private boolean active = false;
    private boolean use = false; 
    
    @Override
    public void activate(Character c, GameScreen g) {
        if (!use) {
            use = true;
            active = true;
            c.setShieldMode(true);
        }
    }
    @Override
    public void update(Character c, GameTimer t, GameScreen g) {
        if (active && !c.isShield()) {
           active = false; //สิ้นสุดการใช้งานเมื่อชนท่อ
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
    @Override
    public void activate(java.lang.Character c, GameScreen g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }
    @Override
    public void update(java.lang.Character c, GameTimer t, GameScreen g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}