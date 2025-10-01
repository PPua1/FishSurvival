
// package Screen;

// import Object.*;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;

// import javax.swing.ImageIcon;
// import Lib.GameLogic;
// import Object.Character;

// public class GameScreen extends Screen{
//     private GameLogic logic;
//     private Character character;
//     private CharacterType Player;
//     private ArrayList<Pipe> pipes;
//     private Score score;
//     private boolean paused;
//     private Image backgroundImg;
//     private String playerName;
//     private boolean alive ;
//     private PipeManager pipeManager;

//     GameScreen(App app, String playerName, CharacterType player){
//         super(app);
//         this.Player = player;
//         this.playerName = playerName;
//         score = new Score(playerName);

//         int startX = 100;
//         int startY = 340;

//         Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
//         logic = new GameLogic(player, bg);
//         this.character = logic.getCharacter();
        
//         setFocusable(true);
//         requestFocusInWindow();
//         addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 handleInput(e);
//             }
//         });
        
//         setLayout(null);

//         initial();

//          if (character.isAlive()==false) {
//             app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, Player));
            
//         }
//     }

//     @Override
//     protected void initial() {
//         setLayout(null);
//         if (character == null && logic != null) {
//             this.character = logic.getCharacter();
//         }
//         if (character != null) {
//             character.setAlive(true);
//         }
//         // Reset paused state
//         this.paused = false;
//         // Clear existing pipes and start fresh
//         if (pipeManager != null) {
//             pipeManager.clearPipes(); // Assuming this method exists, or you might need pipeManager.getPipes().clear()
//         }
//         if(logic != null){
//             logic.reset();
//         }
//     }

//     @Override
//     public void draw(Graphics g){
//         if(logic.getBackground() != null){
//             g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);
//         }
//         logic.drawCharacter(g);
//         logic.drawPipes(g);
//     }

//     @Override
//     public void handleInput(KeyEvent e){
//         if(e.getKeyCode() == KeyEvent.VK_SPACE){
//             logic.characterJump();
//         }
//     }

//     public void update(){
//         logic.update();
//     }

//     public boolean GameOver(){
//         return logic.isGameOver();
//     }
// }

// package Screen;

// import Lib.GameLogic;
// import Object.*;
// import Object.Character;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;
// import javax.swing.ImageIcon;

// public class GameScreen extends Screen{
//     private GameLogic logic;
//     private Character character;
//     private CharacterType Player;
//     private ArrayList<Pipe> pipes;
//     private Score score;
//     private boolean paused;
//     private Image backgroundImg;
//     private String playerName;
//     private boolean alive ;
//     private PipeManager pipeManager;

//     public GameScreen(App app, String playerName, CharacterType player){
//         super(app);
//         this.Player = player;
//         this.playerName = playerName;
//         score = new Score(playerName);

//         int startX = 100;
//         int startY = 340;
        
//         Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
//         logic = new GameLogic(player, bg);

//         this.character = logic.getCharacter();

//         setFocusable(true);
//         requestFocusInWindow();

//         addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 handleInput(e);
//             }
//         });
    
//         setLayout(null);
//         pipeManager = new PipeManager(Theme.TopPipe_Sea.pic(), Theme.BottomPipe_Sea.pic());

//         initial();
        
//          //if (character.isAlive()==false) {
//             // app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, Player));
            
//         // }
//     }
//     public void spawnPipe(){
//         pipeManager.spawnPipe();
//     }

//     public void pausegame(){
//                // Stop game timer/loop here
//                  this.paused = true;
//              }
//              public void resumeGame(){
//                 // Resume game timer/loop here
//              this.paused = false;
//              }

//     @Override
//     protected void initial() {
//         setLayout(null);
//         if (character == null && logic != null) {
//             this.character = logic.getCharacter();
//         }
//         if (character != null) {
//             character.setAlive(true);
//         }
//         // Reset paused state
//         this.paused = false;
//         // Clear existing pipes and start fresh
//         if (pipeManager != null) {
//             pipeManager.clearPipes(); // Assuming this method exists, or you might need pipeManager.getPipes().clear()
//         }
//         if(logic != null){
//             logic.reset();
//         }
    
//     }

//     @Override
//     public void draw(Graphics g){
//         if(logic.getBackground() != null){
//             g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);
//         }
//         logic.drawCharacter(g);
//         logic.drawPipes(g);
//     }

//     @Override
//     public void handleInput(KeyEvent e){
//         if(e.getKeyCode() == KeyEvent.VK_SPACE&& !paused && character != null && character.isAlive()){
//             logic.characterJump();
//         }
//     }

//     public void update(){
//         if (!paused) {
//             if(character != null && character.isAlive()) {
//                              // อัพเดตตำแหน่งตัวละคร
//                              character.update();
//                          }
            
//                          // ขยับท่อ
//                         pipeManager.movePipes();
//                          // ตรวจชน
//          Rectangle birdRect = character.getBounds();
//          if (pipeManager.checkCollision(birdRect)) {
//              character.setAlive(false); // GameOver
//          }
//         }
            

//          if (pipeManager.getPipes().isEmpty() || pipeManager.getPipes().get(pipeManager.getPipes().size() - 1).getX() < getWidth() - 200) {
//              pipeManager.spawnPipe();
//          }
//              // ถ้า character ตาย -> ไปหน้า GameOverScreen
//              if (character != null && !character.isAlive()) {
//                 app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, Player));
//              }
        
//         logic.update();
        
//     }
    

//     public boolean GameOver(){
//         //return !character.isAlive();
//         return character != null && !character.isAlive();
//     }
// }




// package Screen;

// import Object.*;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import javax.swing.ImageIcon;
// import Lib.GameLogic;

// public class GameScreen extends Screen{
//     private GameLogic logic;

//     GameScreen(App app, String playerName, CharacterType player){
//         super(app);

//         Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
//         logic = new GameLogic(player, bg);

//         setFocusable(true);
//         requestFocusInWindow();
//         addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 handleInput(e);
//             }
//         });
//     }

//     @Override
//     protected void initial() {
//         setLayout(null);
//     }

//     @Override
//     public void draw(Graphics g){
//         if(logic.getBackground() != null){
//             g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);
//         }
//         logic.drawCharacter(g);
//         logic.drawPipes(g);
//     }

//     @Override
//     public void handleInput(KeyEvent e){
//         if(e.getKeyCode() == KeyEvent.VK_SPACE){
//             logic.characterJump();
//         }
//     }

//     public void update(){
//         logic.update();
//     }

//     public boolean GameOver(){
//         return logic.isGameOver();
//     }
// }



// package Screen;

// import Lib.GameLogic;
// import Object.*;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;

// import javax.swing.ImageIcon;
// import Object.Character;

// public class GameScreen extends Screen {
//     private GameLogic logic;
//     private Character character;
//     private CharacterType player;
//     private Score score;
//     private boolean paused;
//     private String playerName;
//     private App app;
//     private int finalScore;
//     private PipeManager pipeManager;
//     private ArrayList<Pipe> pipes;

//     public GameScreen(App app, String playerName, CharacterType player, int finalScore) {
//         super(app);
//         this.app = app;
//         this.player = player;
//         this.playerName = playerName;
//         this.score = new Score(playerName);
//         this.finalScore = finalScore;
//         score = new Score(playerName);
        
//         Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
//         logic = new GameLogic(player, bg);
//         character = logic.getCharacter();

//         setFocusable(true);
//         requestFocusInWindow();

//         addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) { handleInput(e); }
//         });

//         if (character.isAlive()==false) {
//              app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, player));
            
//          }
//         initial();
//     }

//     public void spawnPipe(){
//         pipeManager.spawnPipe();
//     }


//     @Override
//     protected void initial() {
//         logic.reset();
//         for (Pipe pipe : pipes) {
//             // ตรวจสอบว่าท่อนี้ยังไม่ถูกผ่านและตัวละครข้ามมันแล้ว
//             if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
//                 score.increment();       // เพิ่มคะแนน
//                 pipe.setPassed(true);    // ป้องกันการนับซ้ำ
//             }
//         }
//         if (character == null && logic != null) {
//             this.character = logic.getCharacter();
//         }
//         if (character != null) {
//             character.setAlive(true);
//         }

//         // Clear existing pipes and start fresh
//         if (pipeManager != null) {
//             pipeManager.clearPipes();
//         }
//     }

//     @Override
//     public void draw(Graphics g) {
//         if (logic.getBackground() != null)
//             g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);

//         logic.drawCharacter(g);
//         logic.drawPipes(g);

//         g.setColor(Color.BLACK);
//         g.setFont(new Font("Arial", Font.BOLD, 20));
//         g.drawString("Score: " + score.getCurrentScore(), 20, 40);
//     }

//     @Override
//     public void handleInput(KeyEvent e) {
//         if(e.getKeyCode() == KeyEvent.VK_SPACE && !paused && character != null && character.isAlive())
//             logic.characterJump();
//     }

//     public void update() {
//         if (!paused) {
//             logic.update();
//             if (!character.isAlive())
//                 app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, player));
//         }
//     }



//     public boolean isGameOver() { return character != null && !character.isAlive(); }


// }
// package Screen;

// import Lib.GameLogic;
// import Object.*;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;

// import javax.swing.ImageIcon;
// import Object.Character;

// public class GameScreen extends Screen {
//     private GameLogic logic;
//     private Character character;
//     private CharacterType player;
//     private Score score;
//     private boolean paused;
//     private String playerName;
//     private App app;
//     private int finalScore;
//     private PipeManager pipeManager;
//     private ArrayList<Pipe> pipes;

//     public  GameScreen(App app, String playerName, CharacterType player, int finalScore) {
//     super(app);

//     // 1. กำหนดค่าพื้นฐาน
//     this.app = app;
//     this.player = player;
//     this.playerName = playerName;
//     this.finalScore = finalScore;
//     this.score = new Score(playerName);

//     // 2. สร้าง logic ก่อน แล้วดึง character
//     Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
//     logic = new GameLogic(player, bg);
//     character = logic.getCharacter();

//     // 3. สร้าง pipeManager และ pipes
//     pipeManager = new PipeManager();
//     pipes = new ArrayList<>();

//     // 4. ตั้งค่า focus และ key listener
//     setFocusable(true);
//     requestFocusInWindow();
//     addKeyListener(new KeyAdapter() {
//         @Override
//         public void keyPressed(KeyEvent e) { handleInput(e); }
//     });

//     // 5. ตรวจสอบ character
//     if (character != null && !character.isAlive()) {
//         app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, player));
//     }

//     // 6. เริ่ม initial
//     initial();
// }

// @Override
// protected void initial() {
//     if (logic != null) logic.reset();

//     if (pipes == null) pipes = new ArrayList<>();
//     for (Pipe pipe : pipes) {
//         if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
//             score.increment();
//             pipe.setPassed(true);
//         }
//     }

//     if (character != null) character.setAlive(true);

//     if (pipeManager != null) pipeManager.clearPipes();
//     }

// @Override
// public void draw(Graphics g) {
//             if (logic.getBackground() != null)
//             g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);

//         logic.drawCharacter(g);
//         logic.drawPipes(g);

//         g.setColor(Color.BLACK);
//         g.setFont(new Font("Arial", Font.BOLD, 20));
//         g.drawString("Score: " + score.getCurrentScore(), 20, 40);
// }

// @Override
// public void update() {
//         if (!paused) {
//             logic.update();
//             if (!character.isAlive())
//                 app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, player));
//         }
// }

// @Override
// public void handleInput(KeyEvent e) {
//         if(e.getKeyCode() == KeyEvent.VK_SPACE && !paused && character != null && character.isAlive())
//             logic.characterJump();}
// }

// package Screen;

// import Lib.GameLogic;
// import Object.*;
// import java.awt.*;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;

// import javax.swing.ImageIcon;
// import Object.Character;

// import javax.swing.ImageIcon;

// public class GameScreen extends Screen {
//     private GameLogic logic;
//     private Character character;
//     private CharacterType player;
//     private Score score;
//     private boolean paused;
//     private String playerName;
//     private App app;
//     private int finalScore;

//     public GameScreen(App app, String playerName, CharacterType player, int finalScore) {
//         super(app);

//         // 1. กำหนดค่าพื้นฐาน
//         this.app = app;
//         this.player = player;
//         this.playerName = playerName;
//         this.finalScore = finalScore;
//         this.score = new Score(playerName);

//         // 2. สร้าง logic ก่อน แล้วดึง character
//         Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
//         logic = new GameLogic(player, bg);
//         character = logic.getCharacter();

//         // 3. ตั้งค่า focus และ key listener
//         setFocusable(true);
//         requestFocusInWindow();
//         addKeyListener(new KeyAdapter() {
//             @Override
//             public void keyPressed(KeyEvent e) {
//                 handleInput(e);
//             }
//         });

//         // 4. ตรวจสอบ character
//         if (character != null && !character.isAlive()) {
//             app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, player));
//         }

//         // 5. เริ่ม initial
//         initial();
//     }

//     @Override
//     protected void initial() {
//         if (logic != null) logic.reset();

//         ArrayList<Pipe> pipes = logic.pipeManager.getPipes(); // ใช้ pipes ของ logic
//         if (pipes != null) {
//             for (Pipe pipe : pipes) {
//                 if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
//                     score.increment();
//                     pipe.setPassed(true);
//                 }
//             }
//         }

//         if (character != null) character.setAlive(true);
//     }

//     @Override
//     public void draw(Graphics g) {
//         if (logic.getBackground() != null)
//             g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);

//         logic.drawCharacter(g);
//         logic.drawPipes(g);

//         g.setColor(Color.BLACK);
//         g.setFont(new Font("Arial", Font.BOLD, 20));
//         g.drawString("Score: " + score.getCurrentScore(), 20, 40);
//     }

//     @Override
//     public void update() {
//         if (!paused) {
//             logic.update();
//             if (!character.isAlive())
//                 app.setScreen(new GameOverScreen(app, 0, 0, null, playerName, player));
//         }
//     }

//     @Override
//     public void handleInput(KeyEvent e) {
//         if (e.getKeyCode() == KeyEvent.VK_SPACE && !paused && character != null && character.isAlive())
//             logic.characterJump();
//     }

//     public boolean isGameOver() {
//         return character != null && !character.isAlive();
//     }
// }

package Screen;
import Lib.GameLogic;
import Lib.ScoreEntry;
import Object.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import Object.Character;

public class GameScreen extends Screen {
    private GameLogic logic;
    private Character character;
    private CharacterType player;
    private String playerName;
    private App app;
    private int finalScore;

    public GameScreen(App app, String playerName, CharacterType player, int finalScore) {
        super(app);

        this.app = app;
        this.player = player;
        this.playerName = playerName;
        this.finalScore = finalScore;

        // สร้าง logic และ character
        Image bg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
        logic = new GameLogic(player, bg, playerName);
        character = logic.getCharacter();

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { handleInput(e); }
        });

        initial();
    }

    @Override
    protected void initial() {
        if (logic != null) logic.reset();
        if (character != null) character.setAlive(true);
    }



    @Override
    public void draw(Graphics g) {
        if (logic.getBackground() != null)
            g.drawImage(logic.getBackground(), 0, 0, getWidth(), getHeight(), null);

        logic.drawCharacter(g);
        logic.drawPipes(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        // ใช้ logic.score แทน
        g.drawString("Score: " + logic.getScore().getCurrentScore(), 20, 40);
    }



    @Override
    public void handleInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && character != null && character.isAlive())
            logic.characterJump();
    }

    public boolean isGameOver() {
        return character != null && !character.isAlive();
    }

    @Override
    public void update() {
        if (logic != null) {
        logic.update();  
    }

        if (character != null && !character.isAlive()) {
        int finalScore = logic.getScore().getCurrentScore();
        app.setScreen(new GameOverScreen(app, playerName, player, finalScore));
        }
    }


}
