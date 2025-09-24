// package Screen;
// import Object.*;
// import Object.Character;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.*;
// import javax.swing.ImageIcon;

// public class GameScreen extends Screen{
//     private Character character;
//     private CharacterType Player;
//     private ArrayList<Pipe> pipes;
//     private Score score;
//     private Image backgroundImg;


//     private PipeManager pipeManager; 
//     private int boardWidth = 360;   
//     private int boardHeight = 640;

//     GameScreen(App app, String playerName, CharacterType player) {
//         super(app);
//         this.Player = player;

//         int startX = 100;
//         int startY = 340;
//         character = new Character(startX, startY, player);
//         setFocusable(true);
//         requestFocusInWindow();
//         addKeyListener(new KeyAdapter() {
//             @Override
//         public void keyPressed(KeyEvent e) {
//             handleInput(e);
//         }
//         });
//         pipeManager = new PipeManager(Theme.TopPipe_Sea.pic(), Theme.BottomPipe_Sea.pic());
        



//     }

//     public void spawnPipe(){
//         pipeManager.spawnPipe();

//     }
//     public void render(Graphics g){
//         // This should call draw(g) or be removed if draw() handles rendering
//         draw(g);
//     }

//     public boolean GameOver(){
//             return !character.isAlive();

//     }
//     public void update(){
//         if (character != null && character.isAlive()) {
//             character.update();
//         }else{return; }//ถ้าไม่เข้าเงื่อนไขนี้ให้ออกจากupdateทันที

//         // ขยับท่อ
//             pipeManager.movePipes();

//         // ตรวจชน
//         Rectangle birdRect = character.getBounds();
//         if (pipeManager.checkCollision(birdRect)) {
//             character.setAlive(false); // GameOver
//         }

//         if (pipeManager.getPipes().isEmpty() || pipeManager.getPipes().get(pipeManager.getPipes().size() - 1).getX() < boardWidth - 200) {
//             pipeManager.spawnPipe();
//         }
//     }

//     @Override
//     protected void initial() {
//         backgroundImg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();

//         setLayout(null);

//     }

//     @Override
//     public void draw(Graphics g) {
//         if (backgroundImg != null) {
//             g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
//         }
//         if (character != null) {
//             character.draw(g);
//         }

//         // วาดท่อ

//         for(Pipe pipe : pipeManager.getPipes()){
//             g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getBounds()[0].width, pipe.getBounds()[0].height, null);        }
//     }

//     @Override
//     public void handleInput(KeyEvent e) {
//         if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//             if (character.isAlive()) {
//                 character.jump();
//             }
//         }
//     }

// }
package Screen;

import Object.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import Lib.GameLogic;

public class GameScreen extends Screen{
    private GameLogic logic;

    GameScreen(App app, String playerName, CharacterType player){
        super(app);

        Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
        logic = new GameLogic(player, bg);

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleInput(e);
            }
        });
    }

    @Override
    protected void initial() {
        setLayout(null);
    }

    @Override
    public void draw(Graphics g){
        if(logic.getBackground() != null){
            g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);
        }
        logic.drawCharacter(g);
        logic.drawPipes(g);
    }

    @Override
    public void handleInput(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            logic.characterJump();
        }
    }

    public void update(){
        logic.update();
    }

    public boolean GameOver(){
        return logic.isGameOver();
    }
}
