package Object;

import java.awt.*;
import javax.swing.ImageIcon;
public enum CharacterType {
    LUCY("Lucy","    I'm just a fish nothing special only heart\n                 Choose Me If you Dare..\n"  , "/Asset/characterImage/Lucy.png"),
    GUK("Guk Guk", " Creates a shield that blocks one collision." , "/Asset/characterImage/GukGak.png"),
    PACHA("Pa Cha Nga Som", "    Reveals hidden pipes and slows down \n          movement speed for 30 seconds" ,"/Asset/characterImage/Pachangasom.png"),
    YOUNG("Young Prad", "  Charges forward with unstoppable force,\n      ignoring all obstacles for 5 seconds." , "/Asset/characterImage/YoungPrad.png"),
    Twitty("Twitty Tao Sing", "        Becomes invisible for 30 seconds." , "/Asset//characterImage/TwittyTaoSing.png");

    private final String displayName;
    private final String description;
    private final String imagePath;
    private final ImageIcon icon;

    CharacterType(String displayName,String description, String imagePath){
        this.displayName = displayName;
        this.description = description;
        this.imagePath = imagePath;
        this.icon = new ImageIcon(getClass().getResource(imagePath));
    }
        public String getDisplayName() {
        return displayName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getDescription(){
        return description;
    }

    public Image getCharacterImage() {
        return icon.getImage();
    }
}
