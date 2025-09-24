package Object;

import java.awt.Image;

import javax.swing.ImageIcon;

public enum Theme {
    Sea("Sea","/Asset/Sea.png"),
    TopPipe_Sea("TopPipe_Sea","/Asset/Sea_Top.png"),
    BottomPipe_Sea("BottomPipe_Sea","/Asset/Sea_Bottom.png"),

    Water("Water","/Asset/Water.png"),
    TopPipe_Water("TopPipe_Water","/Asset/Water_TopPipe.png"),
    BottomPipe_Water("BottomPipe_Water", "/Asset/Water_BottomPipe.png"),

    Kitchen("Kitchen","/Asset/Kitchen.png"),
    TopPipe_Kitchen("TopPipe_Kitchen","/Asset/Fork_Top.png"),
    BottomPipe_Kitchen("BottomPipe_Kitchen","/Asset/Spoon_Bottom.png");

    private String imagePath;
    private String name;
    private ImageIcon Pic;

    Theme(String name,String imagePath){
        this.name = name;
        this.imagePath = imagePath;
        this.Pic = new ImageIcon(getClass().getResource(imagePath));
    }

    public Image pic(){
        return Pic.getImage();
    }

    
}
