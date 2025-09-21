package Object.CharacterSkill;

import Lib.GameTimer;

public class Shield implements Skill{
    private int cooldown = 30000;
    private boolean active;
    private long lastUsed;
    

    @Override
    public void activate(Character c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    @Override
    public void update(Character c, GameTimer t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean isAvailable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAvailable'");
    }

    @Override
    public boolean isActive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isActive'");
    }
    
}
