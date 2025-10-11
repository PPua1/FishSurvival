package Lib;
public class ScoreEntry {
    private String name;
    private int score;
    
    public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int finalScore) {
        this.score = finalScore;
    }
    public int compareTo(ScoreEntry other){
        return Integer.compare(other.score, this.score);//เปรียบเทียบคะแนนมากกว่าอยู่บน
    }

    @Override
    public String toString(){
        return name + "," + score;
    }
}