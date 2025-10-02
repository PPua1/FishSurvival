package Lib;

import Object.*;
import Object.Character;
import Object.CharacterSkill.Ghost;
import Object.CharacterSkill.Shield;
import Object.CharacterSkill.Skill;
import Object.CharacterSkill.Slow;
import Object.CharacterSkill.dash;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

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

    //สร้างskillmapสำหรับแต่ละตัวละคร
    private static final Map<CharacterType, Skill> skillMap = new HashMap<>();
    static{
        skillMap.put(CharacterType.GUK, new Shield());
        skillMap.put(CharacterType.LUCY, null);
        skillMap.put(CharacterType.PACHA, new Slow());
        skillMap.put(CharacterType.Twitty, new Ghost());
        skillMap.put(CharacterType.YOUNG, new dash());
    }
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


    public void update() {
        if (character != null && character.isAlive()) {
            character.update();
        } else return;

        pipeManager.movePipes();

        // ตรวจสอบชนท่อ
        if (pipeManager.checkCollision(character.getBounds())) {
            if(!character.isGhost() && !character.isShield()){
                character.setAlive(false);
            }
        }
            

        // spawn pipe ใหม่ถ้าจำเป็น
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

        //เปลี่ยนธีมตาม score
        // เปลี่ยนธีมตามคะแนนจาก Score object
        int themeIndex = (score.getCurrentScore() / 20) % themes.length;
        backgroundImg = new ImageIcon(getClass().getResource(backgrounds[themeIndex])).getImage();
        pipeManager.setTheme(themes[themeIndex]);
    }

    public void characterJump() {
        if (character != null && character.isAlive()) character.jump();
    }

    public void drawCharacter(Graphics g) {
        if (character != null) character.draw(g);
    }

    public void drawPipes(Graphics g) {
        ArrayList<Pipe> pipes = pipeManager.getPipes();
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(),
                    pipe.getBounds()[0].width, pipe.getBounds()[0].height, null);
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
