package Lib;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import Object.*;
import Object.Character;
import Object.CharacterSkill.Ghost;
import Object.CharacterSkill.Shield;
import Object.CharacterSkill.Skill;
import Object.CharacterSkill.Slow;
import Object.CharacterSkill.dash;

public class GameLogic {
    public Character character;
    public PipeManager pipeManager;
    private Image backgroundImg;
    private int boardWidth = 360;
    private int boardHeight = 640;
    private Score score;
    private String playerName;

    // Theme สำหรับเปลี่ยนท่อ
    private Theme seaTheme = new Theme("/Asset/Sea_Top.png", "/Asset/Sea_Bottom.png");
    private Theme waterTheme = new Theme("/Asset/Water_TopPipe.png", "/Asset/Water_BottomPipe.png");
    private Theme KitchenTheme = new Theme("/Asset/Spoon_Top.png", "/Asset/Fork_Bottom.png");

    private Theme[] themes;
    private String[] backgrounds;

    public GameLogic(CharacterType player, Image background, String playerName) {
        this.character = new Character(100, 340, player);
        this.backgroundImg = background;
        this.pipeManager = new PipeManager(seaTheme);
        this.playerName = playerName;
        this.score = new Score(playerName);
        character.setSkill(skillMap.get(player));

        themes = new Theme[]{seaTheme, waterTheme, KitchenTheme};
        backgrounds = new String[]{"/Asset/Sea.png", "/Asset/Water.png", "/Asset/Kitchen.png"};
    }
    //สร้างskillmapสำหรับแต่ละตัวละคร
    private static final Map<CharacterType, Skill> skillMap = new HashMap<>();
    static{
        skillMap.put(CharacterType.GUK, new Shield());
        skillMap.put(CharacterType.PACHA, new Slow());
        skillMap.put(CharacterType.Twitty, new Ghost());
        skillMap.put(CharacterType.YOUNG, new dash());
    }


public void update() {
    if (character != null) {
        character.update();        // update ตำแหน่ง 
        character.updateInvincible(); // ตรวจสอบเวลา invincible
    }

    // ตรวจสอบชนท่อ (ถ้ายัง alive และไม่ invincible)
    if (character.isAlive() && pipeManager.checkCollision(character.getBounds())) {
        if (character.isShield()) {
            character.setShieldMode(false); // ใช้เกราะกันตาย 1 ครั้ง
        }else if(character.isdash()){
            character.setAlive(true); // แดชชนท่อได้ไม่ตาย
        }else if(character.isGhost()){
            character.setAlive(true); // ล่องหนชนท่อไม่ตาย
        }else if (!character.isInvincible()) {
            character.setAlive(false);
            character.setInvincible(1000); // ถ้าไม่ได้กระพริบ หลังใช้สกิลแล้วชนท่อ ตาย กระพริบ1วิก่อนเปลี่ยนจอ
        }
    }

    
    if (character.isAlive() || character.isInvincible()) {
        pipeManager.movePipes();
    }


    // spawn pipe ใหม่
    ArrayList<Pipe> pipes = pipeManager.getPipes();
    if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < boardWidth - 200) {
        pipeManager.spawnPipe();
    }

    // เพิ่มคะแนนเมื่อผ่านท่อ
    for (Pipe pipe : pipes) {
        if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
            if (score != null) score.increment();
            pipe.setPassed(true);
        }
    }

    // เปลี่ยนธีมตาม score
    int themeIndex = (score.getCurrentScore() / 20) % themes.length;
    backgroundImg = new ImageIcon(getClass().getResource(backgrounds[themeIndex])).getImage();
    pipeManager.setTheme(themes[themeIndex]);

    //เปลี่ยนความเร็วตามธีมด่าน
    // if(themeIndex == 1){
    //     pipeManager.setVelocityX(-2);
    // }else if(themeIndex == 2){
    //     pipeManager.setVelocityX(-3);
    // }
    //เปลี่ยนความเร็วตามคะแนน
    if(score.getCurrentScore() >= 15){
        pipeManager.setVelocityX(-2);
        if(character.getSkill() instanceof Slow && character.getSkill().isActive()){
            pipeManager.setVelocityX(-1);
        }
        if(character.getSkill() instanceof dash && character.getSkill().isActive()){
            pipeManager.setVelocityX(-15);
        }
    }

    // เปลี่ยนขนาดช่องว่างท่อ เมื่อคะแนนถึง
    if(score.getCurrentScore() > 50){
        pipeManager.setGapPipe(100);
    }
}


    public void characterJump() {
        if (character != null && character.isAlive()) character.jump();
    }

    public void drawCharacter(Graphics g) {
        if (character != null) {
            if (character.getSkill() == null || !character.getSkill().isActive()) {
                character.draw(g);
            }
        }


        if (character.getSkill() != null && character != null) {
            character.getSkill().drawEffect(g, character.getX(), character.getY());
        }
        
        
    }

    public void drawPipes(Graphics g) {
        ArrayList<Pipe> pipes = pipeManager.getPipes();
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(),
                    pipe.getPipeBounds(pipe)[0].width, pipe.getPipeBounds(pipe)[0].height, null);

            // // เอาไว้เช็คขอบ
            //     g.setColor(Color.MAGENTA);
            //     for (Rectangle r : pipe.getPipeBounds(pipe)) {
            //     g.drawRect(r.x, r.y, r.width, r.height);

            if(character.getSkill() instanceof Slow && character.getSkill().isActive()){
                // เอาไว้เช็คขอบ
                g.setColor(Color.RED);
                for (Rectangle r : pipe.getPipeBounds(pipe)) {
                g.drawRect(r.x, r.y, r.width, r.height);
                }
            }
                }
        }
    

    public Image getBackground() {
        return backgroundImg;
    }

    public boolean isGameOver() {
        return character == null || !character.isAlive();
    }

    public Character getCharacter() {
        return character;
    }
    
    public Score getScore() {
        return score;
    }

    public void reset() {
        if (character != null) {
            character.setAlive(true);
            character.setX(100);
            character.setY(340);
            character.resetSkill();
        }
        if (pipeManager != null) pipeManager.clearPipes();
        if (score != null) score.reset();
    }

}
