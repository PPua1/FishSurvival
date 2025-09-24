package Screen;
import Lib.ScoreEntry;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameOverScreen extends Screen {
    private int finalScore;
    private int highScore;
    private ArrayList<ScoreEntry> highScores;

    public GameOverScreen(App app, int finalScore, int highScore, ArrayList<ScoreEntry> highScores) {
        super(app);
        this.finalScore = finalScore;
        this.highScore = highScore;
        this.highScores = highScores;

        initial(); // เรียกเมธอดสร้าง UI
    }

    public GameOverScreen(App app) {
        super(app);
    }

    @Override
    protected void initial() {
        setLayout(null);

        // Game Over label
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("SanSerif",Font.BOLD,40));;
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds(100, 30, 300, 50);
        add(gameOverLabel);

        // Score
        JLabel scoreLabel = new JLabel("Score : " + finalScore);
        scoreLabel.setBounds(120, 100, 200, 30);
        add(scoreLabel);

        JLabel highScoreLabel = new JLabel("High Score : " + highScore);
        highScoreLabel.setBounds(120, 140, 200, 30);
        add(highScoreLabel);

        // Retry Button
        JButton retryButton = new JButton("Play Again");
        retryButton.setBounds(120, 200, 160, 40);
        retryButton.addActionListener(e->onRetryClicked());
        add(retryButton);

        // Menu Button
        JButton menuButton = new JButton("Menu");
        menuButton.setBounds(120, 260, 160, 40);
        menuButton.addActionListener(e -> onMenuClicked());
        add(menuButton);

        // Score Board
        JLabel boardLabel = new JLabel("Score Board");
        boardLabel.setBounds(120, 320, 200, 30);
        add(boardLabel);

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
        app.setScreen(new GameScreen(app, "", null)); // เปลี่ยนไปหน้าเล่นเกมใหม่
    }
    private void onMenuClicked() {
        app.setScreen(new MenuScreen(app, null, null)); // กลับเมนูหลัก
    }


    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void handleInput(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInput'");
    }
    
   
   
}