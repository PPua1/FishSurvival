package Object;

public class Score {
    private int currentScore;
    private int highScore;
    private String playerName;
    private int pipeCount = 0;

    public Score(String playerName) {
        this.playerName = playerName;
        this.currentScore = 0;
        this.highScore = 0;
    }

    // เพิ่มคะแนนทีละ 1
    public void increment() {
        pipeCount++;
        currentScore = pipeCount / 2;
        updateHighScore();
    }

    // รีเซ็ตคะแนนปัจจุบัน
    public void reset() {
        currentScore = 0;
        pipeCount = 0;
    }

    // ดึงคะแนนปัจจุบัน
    public int getCurrentScore() {
        return currentScore;
    }

    // ดึงคะแนนสูงสุด
    public int getHighScore() {
        return highScore;
    }

    // อัปเดตคะแนนสูงสุด
    private void updateHighScore() {
        if (currentScore > highScore) {
            highScore = currentScore;
        }
    }

    // ดึงชื่อผู้เล่น
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
