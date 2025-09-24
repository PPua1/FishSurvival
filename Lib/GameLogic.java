

package Lib;

import java.awt.*;
import java.util.ArrayList;

import Object.CharacterType;
import Object.PipeManager;
import Object.Theme;
import Object.Character;
import Object.Pipe;

public class GameLogic {
    private Character character;
    private PipeManager pipeManager;
    private Image backgroundImg;
    private int boardWidth = 360;
    private int boardHeight = 640;

    public GameLogic(CharacterType player, Image background){
        int startX = 100;
        int startY = 340;
        this.character = new Character(startX, startY, player);
        this.backgroundImg = background;
        this.pipeManager = new PipeManager(Theme.TopPipe_Sea.pic(), Theme.BottomPipe_Sea.pic());
    }

    public void update(){
        if(character != null && character.isAlive()){
            character.update();
        } else { return; }

        pipeManager.movePipes();

        if(pipeManager.checkCollision(character.getBounds())){
            character.setAlive(false);
        }

        if(pipeManager.getPipes().isEmpty() || pipeManager.getPipes().get(pipeManager.getPipes().size()-1).getX() < boardWidth-200){
            pipeManager.spawnPipe();
        }
    }

    public boolean isGameOver(){
        return !character.isAlive();
    }

    public void characterJump(){
        if(character.isAlive()) character.jump();
    }

    // วาด character
    public void drawCharacter(Graphics g){
        if(character != null) character.draw(g);
    }

    // วาดท่อ
    public void drawPipes(Graphics g){
        ArrayList<Pipe> pipes = pipeManager.getPipes();
        for(Pipe pipe : pipes){
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getBounds()[0].width, pipe.getBounds()[0].height, null);
        }
    }

    public Image getBackground(){
        return backgroundImg;
    }

    public Character getCharacter(){
        return character;
    }
}
