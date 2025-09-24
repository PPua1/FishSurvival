package Object;

public class Score {
    private int currentScore;
    private int highScore;
    private String playerName;
    public Score(String playerName) {
        this.playerName = playerName;
    }
    public void Increment(){

    }
    public void Reset(){

    }
    public int getScore(){
            return this.currentScore;
    }
    public int getHighScore(){
            return this.highScore;
        
    }
    public void updateHighScore(){
        
    }
}