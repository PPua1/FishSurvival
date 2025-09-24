
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
