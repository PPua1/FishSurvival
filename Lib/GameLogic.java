// package Lib;

// import java.awt.*;
// import java.util.ArrayList;
// import Object.*;
// import Object.Character;

// public class GameLogic {
//     private Character character;
//     public PipeManager pipeManager;
//     private Image backgroundImg;
//     private int boardWidth = 360;
//     private int boardHeight = 640;
//     private int score;
//     private String playerName;

//     // Theme สำหรับเปลี่ยนท่อ
//     private Theme seaTheme = new Theme("/Asset/Sea_Top.png", "/Asset/Sea_Bottom.png");
//     private Theme waterTheme = new Theme("/Asset/Water_TopPipe.png", "/Asset/Water_BottomPipe.png");
//     private Theme KitchenTheme = new Theme("/Asset/Spoon_Top.png", "/Asset/Fork_Bottom.png");

//     public GameLogic(CharacterType player, Image background) {
//         int startX = 100;
//         int startY = 340;
//         this.character = new Character(100, 340, player);
//         this.backgroundImg = background;
//         this.pipeManager = new PipeManager(KitchenTheme);
//     }

//     public void update() {
//         if (character != null && character.isAlive())
//         {
//             character.update();
//         } 
//         else return;

//         pipeManager.movePipes();

//         if (pipeManager.checkCollision(character.getBounds())) {
//             character.setAlive(false);
//         }

//         if (pipeManager.getPipes().isEmpty() || pipeManager.getPipes().get(pipeManager.getPipes().size() - 1).getX() < boardWidth - 200) {
//             pipeManager.spawnPipe();
//         }

//         // เปลี่ยนธีมตาม score
//         // if (score >= 2) { // ตัวอย่าง
//         //     pipeManager.setTheme(waterTheme);
//         // }
//     }

//     public boolean isGameOver() {
//         return !character.isAlive();
//     }

//     public void characterJump() {
//         if (character.isAlive()) character.jump();
//     }

//     public void drawCharacter(Graphics g) {
//         if (character != null) character.draw(g);
//     }

//     public void drawPipes(Graphics g) {
//         ArrayList<Pipe> pipes = pipeManager.getPipes();
//         for (Pipe pipe : pipes) {
//             g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getBounds()[0].width, pipe.getBounds()[0].height, null);
//         }
//     }

//     public Image getBackground() {
//         return backgroundImg;
//     }

//     public Character getCharacter() {
//         return character;
//     }

//     public int getScore() {
//         return score;
//     }

//     public void setScore(int score) {
//         this.score = score;
//     }

//     public void reset() {
//         character.setAlive(true);
//         character.setX(100);
//         character.setY(340);
//         pipeManager.clearPipes();
//         score = 0;
//     }
// }

package Lib;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Object.*;
import Object.Character;

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
            character.setAlive(false);
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
        }
        if (pipeManager != null) pipeManager.clearPipes();
        if (score != null) score.reset();
    }

}
