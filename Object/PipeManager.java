package Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class PipeManager {
    private ArrayList<Pipe> pipes;
    private int boardWidth = 360;
    private int boardHeight = 640;
    private int gap = 150;
    private int velocityX = -2; // ความเร็วท่อขยับ

    private Image topPipeImg;
    private Image bottomPipeImg;

    private Random random;

    public PipeManager(Image topPipeImg, Image bottomPipeImg) {
        this.topPipeImg = topPipeImg;
        this.bottomPipeImg = bottomPipeImg;
        this.pipes = new ArrayList<>();
        this.random = new Random();
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    // สร้างท่อใหม่ 1 คู่ 
    
    public void spawnPipe() {
    int minTopHeight = 50; 
    int maxTopHeight = boardHeight - gap - 50; // ป้องกันหลุดล่าง

    int topPipeHeight = minTopHeight + random.nextInt(maxTopHeight - minTopHeight + 1);

    // topPipe
    Pipe topPipe = new Pipe(topPipeImg, boardWidth, 0); // y=0
    topPipe.setHeight(topPipeHeight);

    // bottomPipe
    int bottomPipeY = topPipeHeight + gap;
    int bottomPipeHeight = boardHeight - bottomPipeY;
    Pipe bottomPipe = new Pipe(bottomPipeImg, boardWidth, bottomPipeY);
    bottomPipe.setHeight(bottomPipeHeight);

    pipes.add(topPipe);
    pipes.add(bottomPipe);
    }

    // ขยับท่อและลบท่อที่หลุดจอ
    public void movePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setX(pipe.getX() + velocityX);
        }
        // ลบท่อที่หลุดซ้าย
        pipes.removeIf(pipe -> pipe.getX() + boardWidth < 0);
    }

    // ตรวจชนกับปลา
    public boolean checkCollision(Rectangle FishRect) {
        for (Pipe pipe : pipes) {
            for (Rectangle rect : pipe.getBounds()) {
                if (FishRect.intersects(rect)) {
                    return true;
                }
            }
        }
        return false;
    }
     // method เปลี่ยนรูป
    public void setPipeImages(Image top, Image bottom) {
    this.topPipeImg = top;
    this.bottomPipeImg = bottom;
}


}
