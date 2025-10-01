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
