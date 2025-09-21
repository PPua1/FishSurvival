// package Screen;

// import Lib.*;
// import Object.CharacterType;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.KeyEvent;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

// public class GameScreen extends Screen {
//     private String playerName;
//     private Bird bird;
//     private List<Pipe> pipes;
//     private int score;
//     private boolean gameOver;
//     private Random random;
//     private int pipeTimer;
    
//     private final int GRAVITY = 1;
//     private final int JUMP_STRENGTH = -15;
//     private final int PIPE_WIDTH = 60;
//     private final int PIPE_GAP = 150;

//     public GameScreen(App app, String playerName, CharacterType selectedCharacter) {
//         super(app);
//         this.playerName = playerName;
//         setTitle("Game - Flappy Bird");
//     }

//     @Override
//     protected void initial() {
//         panel.setBackground(new Color(135, 206, 235));
//         panel.setPreferredSize(new Dimension(360, 640));
        
//         // Initialize game objects
//         bird = new Bird(50, 300);
//         pipes = new ArrayList<>();
//         random = new Random();
//         score = 0;
//         gameOver = false;
//         pipeTimer = 0;
        
//         add(panel);
//     }

//     @Override
//     public void update() {
//         if (!gameOver) {
//             // Update bird
//             bird.update();
            
//             // Generate pipes
//             pipeTimer++;
//             if (pipeTimer >= 90) {
//                 generatePipe();
//                 pipeTimer = 0;
//             }
            
//             // Update pipes
//             for (int i = pipes.size() - 1; i >= 0; i--) {
//                 Pipe pipe = pipes.get(i);
//                 pipe.update();
                
//                 // Check collision
//                 if (pipe.collidesWith(bird)) {
//                     gameOver = true;
//                     app.getFileManager().saveHighScore(score, playerName);
//                 }
                
//                 // Score when bird passes pipe
//                 if (!pipe.isScored() && pipe.getX() + PIPE_WIDTH < bird.getX()) {
//                     score++;
//                     pipe.setScored(true);
//                 }
                
//                 // Remove off-screen pipes
//                 if (pipe.getX() + PIPE_WIDTH < 0) {
//                     pipes.remove(i);
//                 }
//             }
            
//             // Check ground/ceiling collision
//             if (bird.getY() > getHeight() - 100 || bird.getY() < 50) {
//                 gameOver = true;
//                 app.getFileManager().saveHighScore(score, playerName);
//             }
//         }
//     }

//     private void generatePipe() {
//         int pipeHeight = random.nextInt(200) + 100;
//         pipes.add(new Pipe(getWidth(), pipeHeight, PIPE_GAP));
//     }

//     @Override
//     public void draw(Graphics g) {
//         // Clear background
//         g.setColor(new Color(135, 206, 235));
//         g.fillRect(0, 0, getWidth(), getHeight());
        
//         // Draw pipes
//         g.setColor(Color.GREEN);
//         for (Pipe pipe : pipes) {
//             pipe.draw(g);
//         }
        
//         // Draw bird
//         bird.draw(g);
        
//         // Draw score
//         g.setColor(Color.WHITE);
//         g.setFont(new Font("Arial", Font.BOLD, 24));
//         g.drawString("Score: " + score, 10, 80);
        
//         // Draw game over
//         if (gameOver) {
//             g.setColor(new Color(0, 0, 0, 150));
//             g.fillRect(0, 0, getWidth(), getHeight());
            
//             g.setColor(Color.WHITE);
//             g.setFont(new Font("Arial", Font.BOLD, 32));
//             String gameOverText = "GAME OVER";
//             FontMetrics fm = g.getFontMetrics();
//             int x = (getWidth() - fm.stringWidth(gameOverText)) / 2;
//             g.drawString(gameOverText, x, getHeight() / 2);
            
//             g.setFont(new Font("Arial", Font.PLAIN, 16));
//             String restartText = "Press SPACE to return to menu";
//             fm = g.getFontMetrics();
//             x = (getWidth() - fm.stringWidth(restartText)) / 2;
//             g.drawString(restartText, x, getHeight() / 2 + 50);
//         }
//     }

//     @Override
//     public JComponent getComponent() {
//         return panel;
//     }

//     @Override
//     public void handleInput(KeyEvent e) {
//         if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//             if (gameOver) {
//                 app.setScreen(new MenuScreen(app, playerName));
//             } else {
//                 bird.jump();
//             }
//         } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//             app.setScreen(new MenuScreen(app, playerName));
//         }
//     }

//     // Inner classes for game objects
//     private class Bird {
//         private int x, y;
//         private int velocity;
        
//         public Bird(int x, int y) {
//             this.x = x;
//             this.y = y;
//             this.velocity = 0;
//         }
        
//         public void update() {
//             velocity += GRAVITY;
//             y += velocity;
//         }
        
//         public void jump() {
//             velocity = JUMP_STRENGTH;
//         }
        
//         public void draw(Graphics g) {
//             g.setColor(Color.YELLOW);
//             g.fillOval(x, y, 30, 30);
//             g.setColor(Color.ORANGE);
//             g.fillOval(x + 20, y + 10, 10, 10); // Beak
//         }
        
//         public int getX() { return x; }
//         public int getY() { return y; }
//         public Rectangle getBounds() { return new Rectangle(x, y, 30, 30); }
//     }

//     private class Pipe {
//         private int x, topHeight, bottomY;
//         private boolean scored = false;
        
//         public Pipe(int x, int topHeight, int gap) {
//             this.x = x;
//             this.topHeight = topHeight;
//             this.bottomY = topHeight + gap;
//         }
        
//         public void update() {
//             x -= 3;
//         }
        
//         public void draw(Graphics g) {
//             // Top pipe
//             g.setColor(Color.GREEN);
//             g.fillRect(x, 50, PIPE_WIDTH, topHeight);
//             g.setColor(Color.DARK_GRAY);
//             g.drawRect(x, 50, PIPE_WIDTH, topHeight);
            
//             // Bottom pipe  
//             g.setColor(Color.GREEN);
//             g.fillRect(x, bottomY, PIPE_WIDTH, getHeight() - bottomY);
//             g.setColor(Color.DARK_GRAY);
//             g.drawRect(x, bottomY, PIPE_WIDTH, getHeight() - bottomY);
//         }
        
//         public boolean collidesWith(Bird bird) {
//             Rectangle birdBounds = bird.getBounds();
//             Rectangle topPipe = new Rectangle(x, 50, PIPE_WIDTH, topHeight);
//             Rectangle bottomPipe = new Rectangle(x, bottomY, PIPE_WIDTH, getHeight() - bottomY);
            
//             return birdBounds.intersects(topPipe) || birdBounds.intersects(bottomPipe);
//         }
        
//         public int getX() { return x; }
//         public boolean isScored() { return scored; }
//         public void setScored(boolean scored) { this.scored = scored; }
//     }
// }
