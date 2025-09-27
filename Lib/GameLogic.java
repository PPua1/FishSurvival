package Lib;

import java.awt.*;
import java.util.ArrayList;
import Object.*;
import Object.Character;

public class GameLogic {
    private Character character;
    private PipeManager pipeManager;
    private Image backgroundImg;
    private int boardWidth = 360;
    private int boardHeight = 640;
    private int score;

    // Theme สำหรับเปลี่ยนท่อ
    private Theme seaTheme = new Theme("/Asset/Sea_Top.png", "/Asset/Sea_Bottom.png");
    private Theme waterTheme = new Theme("/Asset/Water_TopPipe.png", "/Asset/Water_BottomPipe.png");
    private Theme KitchenTheme = new Theme("/Asset/Spoon_Top.png", "/Asset/Fork_Bottom.png");

    public GameLogic(CharacterType player, Image background) {
        this.character = new Character(100, 340, player);
        this.backgroundImg = background;
        this.pipeManager = new PipeManager(seaTheme);
    }

    public void update() {
        if (character != null && character.isAlive()) character.update();
        else return;

        pipeManager.movePipes();

        if (pipeManager.checkCollision(character.getBounds())) {
            character.setAlive(false);
        }

        if (pipeManager.getPipes().isEmpty() || pipeManager.getPipes().get(pipeManager.getPipes().size() - 1).getX() < boardWidth - 200) {
            pipeManager.spawnPipe();
        }

        // เปลี่ยนธีมตาม score
        if (score >= 2) { // ตัวอย่าง
            pipeManager.setTheme(waterTheme);
        }
    }

    public boolean isGameOver() {
        return !character.isAlive();
    }

    public void characterJump() {
        if (character.isAlive()) character.jump();
    }

    public void drawCharacter(Graphics g) {
        if (character != null) character.draw(g);
    }

    public void drawPipes(Graphics g) {
        ArrayList<Pipe> pipes = pipeManager.getPipes();
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getBounds()[0].width, pipe.getBounds()[0].height, null);
        }
    }

    public Image getBackground() {
        return backgroundImg;
    }

    public Character getCharacter() {
        return character;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void reset() {
        character.setAlive(true);
        character.setX(100);
        character.setY(340);
        pipeManager.clearPipes();
        score = 0;
    }
}
