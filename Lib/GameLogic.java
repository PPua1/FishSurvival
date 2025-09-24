// // package Lib;
// // import java.awt.*;
// // import java.util.*;
// // import java.util.List;

// // import Object.*;
// // import Object.Character;

// // public class GameLogic {
// //     private GameState gameState;
// //     private int difficult;
// //     private Random random;

// //     public GameLogic(int difficult){
// //         this.difficult = difficult;
// //         this.random = new Random();

// //     }
    
// //     public boolean checkCollisoin(Character c , List<Pipe> pipes){
// //         for (Pipe pipe : pipes) {
// //             Rectangle[] bounds = pipe.getBounds();
// //             for (Rectangle rect : bounds) {
// //                 if (c.getBounds().intersects(rect)) {
// //                     return true;
// //                 }
// //             }
// //         }
// //         return false;
// //     }
// //     public void updateScore(Character c , List<Pipe> pipes, Score score){
// //         for (Pipe pipe : pipes) {
// //             if (!pipe.getPassed() && pipe.isPassed(c)) {
// //                 pipe.setPassed(true);
// //                 score.increment();    // เพิ่มคะแนน
// //             }
// //         }

// //     }
// //     public boolean isGameOver(Character c, List<Pipe> pipes, int screenHeight){
// //         if (!c.isAlive()) return true;
// //         if (c.getY() < 0 || c.getY() + c.getHeight() > screenHeight) return true;
// //         if (checkCollisoin(c, pipes)) return true;
// //         return false;
// //     }

// //     // กำหนดช่องว่างระหว่างท่อ
// //     public int generatePipeGap() {
// //         return difficult;
 
        
// //     }

// //     // สุ่มความสูงท่อด้านบน
// //     public int getRandomPipeHeight(int minHeight, int maxHeight) {
// //         return random.nextInt(maxHeight - minHeight) + minHeight;
// //     }


// //     // สร้าง Pipe ใหม่ พร้อม ThemeSet
// //     public Pipe createPipe(int startX, int width, ThemeSet theme) {
// //         int gap = generatePipeGap();
// //         int topHeight = getRandomPipeHeight(50, 300);
// //         return new Pipe(startX, width, topHeight, gap, theme.getTopPipe(), theme.getBottomPipe());
// //     }


// // }

// package Lib;

// import Object.*;
// import java.awt.*;
// import java.util.List;
// import java.util.Random;
// import Object.Character;

// public class GameLogic {
//     private int difficult;
//     private Random random;

//     public GameLogic(int difficult){
//         this.difficult = difficult;
//         this.random = new Random();
//     }

//     // ตรวจสอบการชน
//     public boolean checkCollision(Character c , List<Pipe> pipes){
//         for (Pipe pipe : pipes) {
//             Rectangle[] bounds = pipe.getBounds();
//             for (Rectangle rect : bounds) {
//                 if (c.getBounds().intersects(rect)) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

//     // อัปเดตคะแนน
//     public void updateScore(Character c , List<Pipe> pipes, Score score){
//         for (Pipe pipe : pipes) {
//             if (!pipe.getPassed() && pipe.isPassed(c)) {
//                 pipe.setPassed(true);
//                 score.Increment();    // เพิ่มคะแนน
//             }
//         }
//     }

//     // ตรวจสอบ Game Over
//     public boolean isGameOver(Character c, List<Pipe> pipes, int screenHeight){
//         if (!c.isAlive()) return true;
//         if (c.getY() < 0 || c.getY() + c.getHeight() > screenHeight) return true;
//         if (checkCollision(c, pipes)) return true;
//         return false;
//     }

//     // กำหนดช่องว่างระหว่างท่อ
//     public int generatePipeGap() {
//         switch (difficult) {
//             case 1: return 150;
//             case 2: return 120;
//             case 3: return 100;
//             default: return 120;
//         }
//     }

//     // สุ่มความสูงท่อด้านบน
//     public int getRandomPipeHeight(int minHeight, int maxHeight) {
//         return random.nextInt(maxHeight - minHeight) + minHeight;
//     }

//     // สร้าง Pipe ใหม่ พร้อม ThemeSet
//     public Pipe createPipe(int startX, int width, ThemeSet theme) {
//         int gap = generatePipeGap();
//         int topHeight = getRandomPipeHeight(50, 300);
//         return new Pipe(startX, width, topHeight, gap, theme.getTopPipe(), theme.getBottomPipe());
//     }
// }

// public class GameLogic{
//     int velocityX = -4;
//     int velocityY = 0;
//     int Gravity = 1;
// }

package Lib;

import java.awt.*;
import java.util.ArrayList;

import Object.CharacterType;
import Object.PipeManager;
import Object.Theme;
import Object.Character;
import Object.Pipe;

public class GameLogic {
    private Character character;
    private PipeManager pipeManager;
    private Image backgroundImg;
    private int boardWidth = 360;
    private int boardHeight = 640;

    public GameLogic(CharacterType player, Image background){
        int startX = 100;
        int startY = 340;
        this.character = new Character(startX, startY, player);
        this.backgroundImg = background;
        this.pipeManager = new PipeManager(Theme.TopPipe_Sea.pic(), Theme.BottomPipe_Sea.pic());
    }

    public void update(){
        if(character != null && character.isAlive()){
            character.update();
        } else { return; }

        pipeManager.movePipes();

        if(pipeManager.checkCollision(character.getBounds())){
            character.setAlive(false);
        }

        if(pipeManager.getPipes().isEmpty() || pipeManager.getPipes().get(pipeManager.getPipes().size()-1).getX() < boardWidth-200){
            pipeManager.spawnPipe();
        }
    }

    public boolean isGameOver(){
        return !character.isAlive();
    }

    public void characterJump(){
        if(character.isAlive()) character.jump();
    }

    // วาด character
    public void drawCharacter(Graphics g){
        if(character != null) character.draw(g);
    }

    // วาดท่อ
    public void drawPipes(Graphics g){
        ArrayList<Pipe> pipes = pipeManager.getPipes();
        for(Pipe pipe : pipes){
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getBounds()[0].width, pipe.getBounds()[0].height, null);
        }
    }

    public Image getBackground(){
        return backgroundImg;
    }

    public Character getCharacter(){
        return character;
    }
}
