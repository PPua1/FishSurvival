package Object;
import Lib.GameTimer;
import Object.CharacterSkill.Skill;
import Screen.GameScreen;
import java.awt.*;

public class Character {
    private int x;
    private int y;
    private int speedY = 0;//ความเร็วแนวตั้ง
    private Image icon;
    private CharacterType characterType;
    private boolean isAlive = true;
    private boolean isJumping = false;

    private Skill skill;
    private boolean ghostMode = false;
    private boolean shieldMode = false;

    private static final int Gravity = 1; //แรงโน้มถ่วงเป็น1
    private static final int Jump =  -10 ; //ความเร็วตอนกระโดด
    private static final int Character_Size = 40;
    public Character(int x, int y , CharacterType type){
        this.x = x;
        this.y = y;
        this.characterType = type ; 
        this.icon = type.getCharacterImage();
    }
    public void setSkill(Skill skill){
        this.skill = skill;
    } 
    public Skill getSkill(){
        return this.skill;
    }
    public void useSkill(GameScreen g){
        if (skill != null && skill.isAvailable()) {
            skill.activate(this, g);
        }
    }
    public void updateSkill(GameTimer timer, GameScreen g){
        if (skill != null && skill.isActive()) {
            skill.update(this, timer, g);
        }
    }
    public boolean isGhost(){
        return ghostMode;
    }
    public void setGhostMode(boolean ghost){
        this.ghostMode = ghost;
    }
    public boolean isShield(){
        return shieldMode;
    }
    public void setShieldMode(boolean shield){
        this.shieldMode = shield;
    }
    public void resetSkill(){
        if (skill!= null) {
            skill.reset();
        }
    }
    public void Reset(int startX, int startY){
        this.x = startX;
        this.y = startY;
        this.speedY = 0;
        this.isAlive = true;
        this.isJumping = false;
    }
    public void jump(){
        if (isAlive) {
            speedY = Jump;
            isJumping = true;
        }
    }
    public void update(){
        if (!isAlive) {
            return;
        }

        int ground = 640 - Character_Size; //พื้น
        speedY += Gravity;
        y += speedY;

        //ตกถึงพื้น -> ตาย
        if (y >= ground) {
            y = ground;
            speedY = 0;
            isJumping = false;
            collide();
        }
        //หัวชนเพดาน -> ตาย
        if (y <= 0) {
            y = 0;
            speedY = 0;
            
        }
    }

    //ชนแล้วตาย
    public void collide(){
        this.isAlive = false;

    }

    public void draw(Graphics g){

        int imgWidth = 60;  // ขนาดภาพตัวละคร
        int imgHeight = 60;
        int w = 0, h = 0;
        if (icon != null) {
                double scale = Math.min((double) imgWidth / icon.getWidth(null),
                                        (double) imgHeight / icon.getHeight(null));
                w = (int) (icon.getWidth(null) * scale);
                h = (int) (icon.getHeight(null) * scale);
            }
            if (skill != null && skill.isActive()) {
                skill.drawEffect(g, x, y);
            }else if (icon != null) {
                g.drawImage(icon, x, y, w, h, null);
            }
    }

    public Rectangle getBounds() {
            return new Rectangle(x, y, Character_Size, Character_Size);
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public boolean isAlive(){ return isAlive; }
    public CharacterType getType(){ return characterType; }

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public void setAlive(boolean alive){ this.isAlive = alive; }
}