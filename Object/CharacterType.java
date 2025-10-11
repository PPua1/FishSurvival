package Object;

import java.awt.*;
import javax.swing.ImageIcon;
public enum CharacterType {
    LUCY("Lucy","Skill Description" , "/Asset/characterImage/Lucy.png"),
    GUK("Guk Guk", "Skill Description" , "/Asset/characterImage/GukGak.png"),
    PACHA("Pa Cha Nga Som", "Skill Description" ,"/Asset/characterImage/Pachangasom.png"),
    YOUNG("Young Prad", "Skill Description" , "/Asset/characterImage/YoungPrad.png"),
    Twitty("Twitty Tao Sing", "Skill Description" , "/Asset//characterImage/TwittyTaoSing.png");

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
