package Object.CharacterSkill;

import Lib.GameTimer;
import Screen.GameScreen;

public class noSkill implements Skill{

    @Override
    public void activate(Character c, GameScreen g) {}

    @Override
    public void update(Character c, GameTimer t, GameScreen g) {}
    
    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    
}