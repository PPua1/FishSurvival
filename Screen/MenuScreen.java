package Screen;

import Object.CharacterType;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuScreen extends Screen {
    private String playername;
    private JButton playButton;
    private JButton characterButton;
    private Image backgroundImg;
    private final CharacterType selectedCharacter;
    private JLabel useSkill;

    public MenuScreen(App app, String playerName, CharacterType selectedCharacter) {
        super(app);
        this.playername = playerName;
        // ตั้งค่ารูปตัวละครบนหน้าเมนู
        if(selectedCharacter != null){
            this.selectedCharacter = selectedCharacter;
        }else {
            this.selectedCharacter = selectedCharacter.LUCY;
        }

    }

    @Override
    protected void initial(){
        backgroundImg = new ImageIcon(getClass().getResource("/Asset/mainMenu.png")).getImage();


        setLayout(null);

        playButton = new JButton("Play");
        playButton.setContentAreaFilled(false);  // ไม่วาดพื้นหลัง
        playButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        playButton.setFocusPainted(false);       // ไม่วาดกรอบตอน focus
        playButton.setOpaque(false); 
        playButton.setBounds(0, 350, 360, 45);
        playButton.setFont(new Font("Arial", Font.BOLD, 30));

        characterButton = new JButton("Character");
        playButton.setHorizontalAlignment(SwingConstants.CENTER); // จัดข้อความกลางแนวนอน
        playButton.setVerticalAlignment(SwingConstants.CENTER);   // จัดข้อความกลางแนวตั้ง
        characterButton.setBounds(99, 410, 150, 45);

        characterButton.setFont(new Font("Arial", Font.BOLD, 20));
        characterButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        characterButton.setBackground(Color.WHITE);

        useSkill = new JLabel("Press F for use Skill.");
        useSkill.setHorizontalAlignment(SwingConstants.CENTER);
        useSkill.setBounds(0, 460, 360, 45);
        useSkill.setFont(new Font("Arial", Font.PLAIN, 15));


        add(playButton);
        add(characterButton);
        add(useSkill);

        // ปุ่มกดแล้วทำงาน
        playButton.addActionListener(e -> {
            app.setScreen(new GameScreen(app, playername, selectedCharacter,0));
        });

        characterButton.addActionListener(e -> {
            app.setScreen(new CharacterScreen(app, playername, selectedCharacter));
        });

    }



    @Override
    public void draw(Graphics g) {
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }

        if (selectedCharacter != null) {
            Image charImg = selectedCharacter.getCharacterImage();

            if (charImg != null) {
                int imgWidth = 130;  // ขนาดภาพตัวละคร
                int imgHeight = 130;
                double scale = Math.min((double) imgWidth / charImg.getWidth(null),
                                        (double) imgHeight / charImg.getHeight(null));
                int w = (int) (charImg.getWidth(null) * scale);
                int h = (int) (charImg.getHeight(null) * scale);

                int x = (getWidth() - w) / 2; // วางตรงกลาง
                int y = ((getHeight() - h)/2) - 20;
                g.drawImage(charImg, x, y, w, h, this);
            }
    }
}

    @Override
    public void update() {
     
    }

    @Override
    public void handleInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            app.exitApplication();
        }
    }

    
}
