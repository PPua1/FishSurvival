package Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PipeManager {
    private ArrayList<Pipe> pipes;
    private int boardWidth = 360;
    private int boardHeight = 640;
    private int gap = 150;
    private int velocityX = -2;

    private Theme currentTheme;
    private Random random;

    public PipeManager(Theme initialTheme) {
        this.currentTheme = initialTheme;
        this.pipes = new ArrayList<>();
        this.random = new Random();
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public void spawnPipe() {
        int minTopHeight = 50;
        int maxTopHeight = boardHeight - gap - 50;
        int topPipeHeight = minTopHeight + random.nextInt(maxTopHeight - minTopHeight + 1);

        // top pipe
        Pipe topPipe = new Pipe(currentTheme.getTopPipeImage(), boardWidth, 0);
        topPipe.setHeight(topPipeHeight);

        // bottom pipe
        int bottomPipeY = topPipeHeight + gap;
        int bottomPipeHeight = boardHeight - bottomPipeY;
        Pipe bottomPipe = new Pipe(currentTheme.getBottomPipeImage(), boardWidth, bottomPipeY);
        bottomPipe.setHeight(bottomPipeHeight);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void movePipes() {
        for (Pipe pipe : pipes) {
            pipe.setX(pipe.getX() + velocityX);
        }
        pipes.removeIf(pipe -> pipe.getX() + boardWidth < 0);
    }

    public boolean checkCollision(Rectangle FishRect) {
        for (Pipe pipe : pipes) {
            for (Rectangle rect : pipe.getBounds()) {
                if (FishRect.intersects(rect)) return true;
            }
        }
        return false;
    }

    // เปลี่ยนธีม runtime
    public void setTheme(Theme theme) {
        this.currentTheme = theme;
        // อัปเดตรูปของ pipe ที่มีอยู่
        for (int i = 0; i < pipes.size(); i += 2) { // top-bottom คู่ละ 2
            Pipe top = pipes.get(i);
            Pipe bottom = pipes.get(i + 1);
            top.setImage(currentTheme.getTopPipeImage());
            bottom.setImage(currentTheme.getBottomPipeImage());
        }
    }

    public void clearPipes(){
        pipes.clear();
    }
    public int getPipeSpeed(){
        return velocityX;
    }
    public void setVelocityX(int velocityX){
        this.velocityX = velocityX;
    }
}
