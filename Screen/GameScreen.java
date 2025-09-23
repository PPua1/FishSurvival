package Screen;
import Object.*;
import Object.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.ImageIcon;

public class GameScreen extends Screen{
    private Character character;
    private CharacterType Player;
    private ArrayList<Pipe> pipes;
    private Score score;
    private boolean paused;
    private Button puaseButton;
    private Button resumeButton;
    private Button menuButton;
    private Image backgroundImg;

    GameScreen(App app, String playerName, CharacterType player) {
        super(app);
        this.Player = player;

        int startX = 100;
        int startY = 340;
        character = new Character(startX, startY, player);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
        public void keyPressed(KeyEvent e) {
            handleInput(e);
        }
        });
        
    }

    public Pipe spawnPipe(){
            return null;

    }
    public void render(Graphics g){
        // This should call draw(g) or be removed if draw() handles rendering
        draw(g);
    }
    public void pausegame(){
        // Stop game timer/loop here
        this.paused = true;
    }
    public void resumeGame(){
        // Resume game timer/loop here
        this.paused = false;
    }
    public boolean GameOver(){
            return !character.isAlive();

    }
    public void update(){
        if (character != null && character.isAlive() || !paused) {
            character.update();
        }else{return; }//ถ้าไม่เข้าเงื่อนไขนี้ให้ออกจากupdateทันที


    }

    @Override
    protected void initial() {
        backgroundImg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();

        setLayout(null);

    }

    @Override
    public void draw(Graphics g) {
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
        }
        if (character != null) {
            character.draw(g);
        }
    }

    @Override
    public void handleInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!paused&&character.isAlive()) {
                character.jump();
            }
        }
    }

}
