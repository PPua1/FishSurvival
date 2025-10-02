package Object.CharacterSkill;
import Lib.GameTimer;
import Object.Character;
import Screen.GameScreen;
import java.awt.Graphics;

public interface Skill {
    public void activate(Character c, GameScreen g);
    public void update(Character c, GameTimer t , GameScreen g);
    public boolean isAvailable();
    public boolean isActive();

    //วาดเอฟเฟกต์ของสกิล
    default void drawEffect(Graphics g, int x, int y){
        //ไม่มีภาพเอฟเฟกต์ใน default
    }
    void reset();
}