package Object;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Theme {
    private String topPipePath;
    private String bottomPipePath;
    private ImageIcon topPipeIcon;
    private ImageIcon bottomPipeIcon;

    public Theme(String topPipePath, String bottomPipePath) {
        this.topPipePath = topPipePath;
        this.bottomPipePath = bottomPipePath;
    }

    public Image getTopPipeImage() {
        if (topPipeIcon == null) topPipeIcon = loadImage(topPipePath);
        return topPipeIcon != null ? topPipeIcon.getImage() : null;
    }

    public Image getBottomPipeImage() {
        if (bottomPipeIcon == null) bottomPipeIcon = loadImage(bottomPipePath);
        return bottomPipeIcon != null ? bottomPipeIcon.getImage() : null;
    }

    private ImageIcon loadImage(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.err.println("ไม่พบไฟล์: " + path);
            return null;
        }
        return new ImageIcon(url);
    }
}
