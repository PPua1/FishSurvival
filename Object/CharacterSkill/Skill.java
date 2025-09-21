package Object.CharacterSkill;

import Lib.GameTimer;

public interface Skill {
    public void activate(Character c);
    public void update(Character c, GameTimer t);
    public boolean isAvailable();
    public boolean isActive();

}
