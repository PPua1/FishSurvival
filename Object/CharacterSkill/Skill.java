package Object.CharacterSkill;
import Lib.GameTimer;
import Screen.GameScreen;

public interface Skill {
    public void activate(Character c, GameScreen g);
    public void update(Character c, GameTimer t , GameScreen g);
    public boolean isAvailable();
    public boolean isActive();
}
