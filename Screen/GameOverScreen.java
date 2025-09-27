package Screen;
import Lib.ScoreEntry;
import Object.CharacterType;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameOverScreen extends Screen {
    private int finalScore;
    private int highScore;
    private ArrayList<ScoreEntry> highScores;
    private String playerName;
    private CharacterType player;
    private Image backgroundImg;
    

    public GameOverScreen(App app, int finalScore, int highScore, ArrayList<ScoreEntry> highScores,String playerName,CharacterType player) {
        super(app);
        this.finalScore = finalScore;
        this.highScore = highScore;
        this.highScores = highScores;
        this.playerName = playerName;
        this.player = player;
        

        initial(); // เรียกเมธอดสร้าง UI
    }

    

    @Override
    protected void initial() {
        setLayout(null);

        // Game Over label
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("SanSerif",Font.BOLD,40));;
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(70, 50, 658, 80);
        add(gameOverLabel);

        // Score
        JLabel scoreLabel = new JLabel("Score : " + finalScore);
        scoreLabel.setBounds(130, 130, 200, 30);
        add(scoreLabel);

        JLabel highScoreLabel = new JLabel("High Score : " + highScore);
        highScoreLabel.setBounds(130, 170, 200, 30);
        add(highScoreLabel);

        // Retry Button
        JButton retryButton = new JButton("Play Again");
        retryButton.setBounds(100, 240, 160, 40);
        retryButton.addActionListener(e->onRetryClicked());
        add(retryButton);

        // Menu Button
        JButton menuButton = new JButton("Menu");
        menuButton.setBounds(100, 300, 160, 40);
        menuButton.addActionListener(e -> onMenuClicked());
        add(menuButton);


        // Score Board
        JLabel boardLabel = new JLabel("Score Board");
        boardLabel.setBounds(145, 360, 200, 30);
        add(boardLabel); 

        JPanel scoreBoardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.WHITE); // สีขาวทึบ
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); // กรอบขอบมน
                g2.dispose();
            }
        };
        scoreBoardPanel.setOpaque(false);
        scoreBoardPanel.setLayout(null); // ใช้ absolute layout
        scoreBoardPanel.setBounds(55, 360, 250, 230);
        add(scoreBoardPanel); 

        

        int y = 360;
        if (highScores != null) {
        for (ScoreEntry entry : highScores) {
            JLabel entryLabel = new JLabel(entry.getName() + " - " + entry.getScore());
            entryLabel.setBounds(120, y, 250, 25);
            add(entryLabel);
            y += 30;
            }
        }
        
        
        
    }
    

    private void onRetryClicked() {
        GameScreen gameScreen = new GameScreen(app, playerName, player);
        app.setScreen(gameScreen);
        String playerName = null;
               // app.setScreen(new GameScreen(app, playerName, player)); // เปลี่ยนไปหน้าเล่นเกมใหม่
                
    }
    private void onMenuClicked() {
        MenuScreen menuScreen = new MenuScreen(app, playerName, player);
        app.setScreen(menuScreen);
       // app.setScreen(new MenuScreen(app, null, null)); // กลับเมนูหลัก
    }
    
    @Override
    public void draw(Graphics g) {
        
            backgroundImg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
            
     
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void handleInput(KeyEvent e) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'handleInput'");
    }
    
   
   
}