package Screen;

import Lib.ScoreEntry;
import Object.CharacterType;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GameOverScreen extends Screen{

    private int finalScore;
    private int highScore;
    private ArrayList<ScoreEntry> highScores;
    private String playerName;
    private CharacterType player;
    private App app;
    private Image backgroundImg;

public GameOverScreen(App app, String playerName, CharacterType player, int finalScore) {
    super(app);
    this.app = app;
    this.finalScore = finalScore;
    
    // Validate and set default if null
    if (playerName == null || playerName.trim().isEmpty()) {
        this.playerName = "Guest_" + System.currentTimeMillis();
        
    } else {
        this.playerName = playerName;
    }
    
    this.player = player;

    // save score user(เฉพาะถ้าสูงกว่าเดิม)
    if (finalScore > 0) {
        System.out.println("GameOverScreen: Saving score for player " + this.playerName + " with score " + finalScore);
        app.getFileManager().saveScore(this.playerName, finalScore);
    } else {
        System.out.println("GameOverScreen: Final score <= 0, no save");
    }
    
    // โหลดคะแนน Top 5 จาก FileManager
    this.highScores = app.getFileManager().getTop5Score();
    if (this.highScores == null) this.highScores = new ArrayList<>();

    //อัปเดตคะแนนสูงสุด (High Score)
    highScore = app.getFileManager().getPlayerHighScore(this.playerName);

    initial();
}

    @Override
    protected void initial() {
        setLayout(null);

        // Game Over label
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(45, 50, 658, 80);
        add(gameOverLabel);

        // Score
        JLabel scoreLabel = new JLabel("Score : " + finalScore);
        scoreLabel.setBounds(115, 140, 200, 30);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(scoreLabel);

        JLabel highScoreLabel = new JLabel("High Score : " + highScore);
        highScoreLabel.setBounds(115, 180, 200, 30);
        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        
        add(highScoreLabel);

        // Retry Button
        JButton retryButton = new JButton("Play Again");
        retryButton.setBounds(100, 240, 160, 40);
        retryButton.setBorderPainted(false);
        retryButton.setFocusPainted(false);
        retryButton.setFont(new Font("Arial", Font.BOLD, 20));
        retryButton.setBackground(Color.WHITE);
        retryButton.addActionListener(e -> {
            app.setScreen(new GameScreen(app, playerName, player, 0)); // เริ่มใหม่ score = 0
        });
        add(retryButton);

        // Menu Button
        JButton menuButton = new JButton("Menu");
        menuButton.setBounds(100, 300, 160, 40);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font("Arial", Font.BOLD, 20));
        menuButton.setBackground(Color.WHITE);
        menuButton.addActionListener(e -> {
            app.setScreen(new MenuScreen(app, playerName, player));
        });
        add(menuButton);

        // Score Board
        JLabel boardLabel = new JLabel("Leaders Board");
        boardLabel.setBounds(93, 370, 200, 30);
        boardLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(boardLabel);
        

        JPanel scoreBoardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
            }
        };
        scoreBoardPanel.setOpaque(false);
        scoreBoardPanel.setLayout(null);
        scoreBoardPanel.setBounds(55, 360, 250, 230);
        add(scoreBoardPanel);

        // แสดงคะแนน
        scoreBoardPanel.removeAll();
        int y = 60;
        for (int i = 0; i < highScores.size(); i++) {
            ScoreEntry entry = highScores.get(i);
            JLabel entryLabel = new JLabel((i + 1) + ". " + entry.getName(), SwingConstants.LEFT);
            entryLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            entryLabel.setBounds(20, y, 100, 25);
            scoreBoardPanel.add(entryLabel);

            JLabel scoreBoardLabel = new JLabel("-\t" + entry.getScore(), SwingConstants.RIGHT);
            scoreBoardLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // แนะนำให้เพิ่ม font ด้วย
            scoreBoardLabel.setBounds(15, y, 210, 25);
            scoreBoardPanel.add(scoreBoardLabel);


            y += 30;
        }
        scoreBoardPanel.revalidate();
        scoreBoardPanel.repaint();
    }

    @Override
    public void draw(Graphics g) {
        backgroundImg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void handleInput(KeyEvent e) {

    }
}


