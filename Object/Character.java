package Object;
import java.awt.*;

public class Character {
    private int x;
    private int y;
    private int speedY = 0;//ความเร็วแนวตั้ง
    private Image icon;
    private CharacterType characterType;
    private boolean isAlive = true;
    private boolean isJumping = false;

    private static final int Gravity = 1; //แรงโน้มถ่วงเป็น1
    private static final int Jump =  -10 ; //ความเร็วตอนกระโดด
    private static final int Character_Size = 60;
    public Character(int x, int y , CharacterType type){
        this.x = x;
        this.y = y;
        this.characterType = type ; 
        this.icon = type.getCharacterImage();
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
            collide();
        }
    }

    //ชนแล้วตาย
    public void collide(){
        this.isAlive = false;

    }

    public void draw(Graphics g){

        if (icon != null) {
                int imgWidth = 60;  // ขนาดภาพตัวละคร
                int imgHeight = 60;
                double scale = Math.min((double) imgWidth / icon.getWidth(null),
                                        (double) imgHeight / icon.getHeight(null));
                int w = (int) (icon.getWidth(null) * scale);
                int h = (int) (icon.getHeight(null) * scale);

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

// package Object;

// import java.awt.*;

// public class Character {
//     private int x;
//     private int y;
//     private int speedY = 0;
//     private Image icon;
//     private CharacterType characterType;
//     private boolean isAlive = true;
//     private boolean isJumping = false;

//     private static final int Gravity = 1;
//     private static final int Jump = -10;
//     private static final int Character_Size = 60;

//     public Character(int x, int y , CharacterType type){
//         this.x = x;
//         this.y = y;
//         this.characterType = type ; 
//         this.icon = type.getCharacterImage();
//     }

//     public void reset(int startX, int startY){
//         this.x = startX;
//         this.y = startY;
//         this.speedY = 0;
//         this.isAlive = true;
//         this.isJumping = false;
//     }

//     public void jump(){
//         if (isAlive) {
//             speedY = Jump;
//             isJumping = true;
//         }
//     }

//     public void update(){
//         if (!isAlive) return;

//         int ground = 640 - Character_Size;
//         speedY += Gravity;
//         y += speedY;

//         if (y >= ground) {
//             y = ground;
//             speedY = 0;
//             isJumping = false;
//             collide();
//         }
//         if (y <= 0) {
//             y = 0;
//             speedY = 0;
//             collide();
//         }
//     }

//     public void collide(){
//         this.isAlive = false;
//     }

//     public void draw(Graphics g){
//         if (icon != null) {
//             int imgWidth = 60;
//             int imgHeight = 60;
//             double scale = Math.min((double) imgWidth / icon.getWidth(null),
//                                     (double) imgHeight / icon.getHeight(null));
//             int w = (int) (icon.getWidth(null) * scale);
//             int h = (int) (icon.getHeight(null) * scale);
//             g.drawImage(icon, x, y, w, h, null);
//         }
//     }

//     public Rectangle getBounds() {
//         return new Rectangle(x, y, Character_Size, Character_Size);
//     }

//     public int getX(){ return x; }
//     public int getY(){ return y; }
//     public boolean isAlive(){ return isAlive; }
//     public CharacterType getType(){ return characterType; }

//     public void setX(int x){ this.x = x; }
//     public void setY(int y){ this.y = y; }
//     public void setAlive(boolean alive){ this.isAlive = alive; }
// }
