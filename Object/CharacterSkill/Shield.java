package Object.CharacterSkill;
import Lib.GameTimer;
import Object.Character;
import Screen.GameScreen;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Shield implements Skill{
    private boolean active = false;
    private boolean use = false; 
    private Image effectImage;

    public Shield(){
        effectImage = new ImageIcon(getClass().getResource("/Asset/SkillImage/ShieldSkill.png")).getImage();
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
    public void drawEffect(Graphics g, int x, int y){
        if (active && effectImage != null) {
            g.drawImage(effectImage, x, y, 50, 50, null);
        }
    }
    @Override
    public void reset() {
        use = false;
        active = false;
    }
}