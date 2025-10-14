package Screen;

import Lib.BackgroundMusic;
import Lib.FileManager;
import Lib.GameTimer;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private Screen currentScreen;
    private GameTimer timer;
    private FileManager fileManager;
    private String playerName;
    private BackgroundMusic musicPlayer = new BackgroundMusic();


    public void setScreen(Screen screen) {
        if (currentScreen != null) {
            remove(currentScreen); // เอาหน้าจอเก่าออกจาก JFrame
        }

        currentScreen = screen;
        add(currentScreen, BorderLayout.CENTER);        // ใส่หน้าจอใหม่เข้า JFrame
        
        currentScreen.initial();
        currentScreen.requestFocusInWindow();
        revalidate();              // บอก JFrame ให้จัด layout ใหม่
        repaint();                 // วาดใหม่
}

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public App() {
        super("Fish Survival");

        this.fileManager = new FileManager("Lib/highscore.txt");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // ให้ JPanel เต็มหน้าจอ
        setVisible(true); 

        // เริ่มต้นที่ LoginScreen
        setScreen(new LoginScreen(this));
 
        // Timer สำหรับ loop เกม
        timer = new GameTimer(16, () -> {
            if (currentScreen != null) {
                currentScreen.update();
                currentScreen.repaint();
            }
        });
        timer.start();
        musicPlayer.playMusic("Asset/BGM03.wav");
    }
  
    public FileManager getFileManager() {
        return this.fileManager;
    }

    public void exitApplication() {
        if (currentScreen != null) {
            remove(currentScreen);
        }
        System.exit(0);
    }
 
    public static void main(String[] args) {
        new App();
    }  
}  
