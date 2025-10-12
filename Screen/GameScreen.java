package Screen;
import Lib.GameLogic;
import Lib.GameTimer;
import Lib.ScoreEntry;
import Object.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import Object.Character;

public class GameScreen extends Screen {
    private GameLogic logic;
    private Character character;
    private CharacterType player;
    private String playerName;
    private App app;
    private int finalScore;
    private GameTimer gameTimer;
    private boolean isCountdown = false;
    private long countdownStartTime;

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
        //เริ่มจับเวลา countdown
        countdownStartTime = System.currentTimeMillis();
        gameTimer = new GameTimer(16,() ->{
            update();
            repaint();
        });
        gameTimer.start();

        initial();
    }
    public PipeManager getPipeManager(){
        return logic.pipeManager;
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
        g.drawString("Score: " + logic.getScore().getCurrentScore(), 20, 40);
    }

    @Override
    public void handleInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && character != null && character.isAlive()){
            logic.characterJump();
        }else if(e.getKeyCode() == KeyEvent.VK_F && character != null && character.isAlive()) {
            character.useSkill(this);
        }
    }

    public boolean isGameOver() {
        return character != null && !character.isAlive();
    }

    @Override
    public void update() {
        // Countdown ก่อนเริ่มเกม
        if (!isCountdown) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - countdownStartTime >= 1000) {
                isCountdown = true;
            } else return;
        }

        if (character != null) {
            character.updateSkill(gameTimer, this); // อัพเดตสกิล
            character.updateInvincible();           // อัพเดตเวลากระพริบ

            // ถ้า character ตายแล้ว แต่ยัง invincible ให้กระพริบและหยุดท่อ
            if (!character.isAlive() && character.isInvincible()) {
                // ตัวละครกระพริบอยู่กับที่, pipeManager ไม่อัพเดต
                return; // รอจนหมดเวลากระพริบ
            }

            // GameOver จริง ๆ
            if (!character.isAlive() && !character.isInvincible()) {
                int finalScore = logic.getScore().getCurrentScore();
                gameTimer.stop();
                app.setScreen(new GameOverScreen(app, this.playerName, player, finalScore));
                return;
            }
        }

        // อัพเดต Logic ตามปกติ ถ้า character ยัง alive หรือไม่ได้กระพริบ
        if (logic != null) {
            logic.update();
        }
    }


}
