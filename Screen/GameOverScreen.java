// package Screen;

// import Object.CharacterType;
// import Lib.ScoreEntry;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.ArrayList;
// import javax.swing.*;

// public class GameOverScreen extends Screen {
//     private int finalScore;
//     private int highScore;
//     private ArrayList<ScoreEntry> highScores;
//     private String playerName;
//     private CharacterType player;
//     private App app;
//     private Image backgroundImg;

//     public GameOverScreen(App app, String playerName, CharacterType player, int finalScore) {
//         super(app);
//         this.app = app;
//         this.finalScore = finalScore;
//         this.playerName = playerName;
//         this.player = player;
        
//         // โหลดคะแนนจาก FileManager
//         this.highScores = app.getFileManager().loadScores();
        
//         // หา high score สูงสุด
//         this.highScore = 0;
//         for (ScoreEntry entry : highScores) {
//             if (entry.getScore() > highScore) {
//                 highScore = entry.getScore();
//             }
//         }
        
//         // ถ้าคะแนนปัจจุบันสูงกว่า ให้อัปเดต highScore
//         if (finalScore > highScore) {
//             highScore = finalScore;
//         }

//         initial();
//     }

//     @Override
//     protected void initial() {
//         setLayout(null);

//         // Game Over label
//         JLabel gameOverLabel = new JLabel("Game Over");
//         gameOverLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
//         gameOverLabel.setForeground(Color.RED);
//         gameOverLabel.setBounds(50, 50, 658, 80);
//         add(gameOverLabel);

//         // Score
//         JLabel scoreLabel = new JLabel("Score : " + finalScore);
//         scoreLabel.setBounds(130, 130, 200, 30);
//         add(scoreLabel);

//         JLabel highScoreLabel = new JLabel("High Score : " + highScore);
//         highScoreLabel.setBounds(130, 170, 200, 30);
//         add(highScoreLabel);

//         // Retry Button
//         JButton retryButton = new JButton("Play Again");
//         retryButton.setBounds(100, 240, 160, 40);
//         retryButton.setBorderPainted(false);
//         retryButton.setFocusPainted(false);
//         retryButton.setFont(new Font("Arial", Font.BOLD, 20));
//         retryButton.setBackground(Color.WHITE);
//         retryButton.addActionListener(e -> {
//             app.setScreen(new GameScreen(app, playerName, player, 0)); // เริ่มใหม่ score = 0
//         });
//         add(retryButton);

//         // Menu Button
//         JButton menuButton = new JButton("Menu");
//         menuButton.setBounds(100, 300, 160, 40);
//         menuButton.setBorderPainted(false);
//         menuButton.setFocusPainted(false);
//         menuButton.setFont(new Font("Arial", Font.BOLD, 20));
//         menuButton.setBackground(Color.WHITE);
//         menuButton.addActionListener(e -> {
//             app.setScreen(new MenuScreen(app, playerName, player));
//         });
//         add(menuButton);

//         // Score Board
//         JLabel boardLabel = new JLabel("Score Board");
//         boardLabel.setBounds(145, 360, 200, 30);
//         add(boardLabel);

//         JPanel scoreBoardPanel = new JPanel() {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 super.paintComponent(g);
//                 Graphics2D g2 = (Graphics2D) g.create();
//                 g2.setColor(Color.WHITE);
//                 g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
//                 g2.dispose();
//             }
//         };
//         scoreBoardPanel.setOpaque(false);
//         scoreBoardPanel.setLayout(null);
//         scoreBoardPanel.setBounds(55, 360, 250, 230);
//         add(scoreBoardPanel);

//         // เพิ่มคะแนนของผู้เล่นปัจจุบันเข้าไปใน highScores
//         ScoreEntry newEntry = new ScoreEntry(playerName, finalScore);
//         highScores.add(newEntry);

//         // เรียงลำดับคะแนนจากมากไปน้อย
//         highScores.sort((a, b) -> b.getScore() - a.getScore());

//         // เก็บไว้แค่ top 5
//         if (highScores.size() > 5) {
//             highScores = new ArrayList<>(highScores.subList(0, 5));
//         }

//         // บันทึกคะแนนลงไฟล์
//         app.getFileManager().saveScores(highScores);

//         // แสดงคะแนน
//         scoreBoardPanel.removeAll();
//         int y = 20;
//         for (int i = 0; i < highScores.size(); i++) {
//             ScoreEntry entry = highScores.get(i);
//             JLabel entryLabel = new JLabel((i + 1) + ". " + entry.getName() + "\t- " + entry.getScore());
//             entryLabel.setBounds(20, y, 210, 25);
//             scoreBoardPanel.add(entryLabel);
//             y += 30;
//         }

//         // บันทึกคะแนนลงไฟล์
//         app.getFileManager().saveScores(highScores);
//     }

//     @Override
//     public void draw(Graphics g) {
//         backgroundImg = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
//         if (backgroundImg != null) {
//             g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
//         }
//     }

//     @Override
//     public void update() {

//     }

//     @Override
//     public void handleInput(KeyEvent e) {

//     }
// }
package Screen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import Lib.ScoreEntry;
import Object.CharacterType;

public class GameOverScreen extends Screen {
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
        this.playerName = playerName;
        this.player = player;

        // save score user(เฉพาะถ้าสูงกว่าเดิม)
        app.getFileManager().saveScore(playerName, finalScore);

        // โหลดคะแนน Top 5 จาก FileManager
        this.highScores = app.getFileManager().getTop5Score();
        if (this.highScores == null) this.highScores = new ArrayList<>();

        //อัปเดตคะแนนสูงสุด (High Score)
        highScore = app.getFileManager().LoadHighScore();

        initial();
    }

    @Override
    protected void initial() {
        setLayout(null);

        // Game Over label
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(50, 50, 658, 80);
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
        JLabel boardLabel = new JLabel("Score Board");
        boardLabel.setBounds(145, 360, 200, 30);
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
        int y = 20;
        for (int i = 0; i < highScores.size(); i++) {
            ScoreEntry entry = highScores.get(i);
            JLabel entryLabel = new JLabel((i + 1) + ". " + entry.getName() + "\t- " + entry.getScore());
            entryLabel.setBounds(20, y, 210, 25);
            scoreBoardPanel.add(entryLabel);
            y += 30;
        }
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
