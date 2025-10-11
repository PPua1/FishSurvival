package Screen;

import Object.CharacterType;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class CharacterScreen extends Screen {
    private String playerName;
    private JButton RightButton;
    private JButton LeftButton;
    private JButton SelectedButton;
    private JButton ReturnButton;
    private Image backgroundImage;
    private int currentIndex = 0;
    private CharacterType[] characters = CharacterType.values();
    private CharacterType selectedCharacter;
    private JLabel character;
    private Image characterImage;
    private JLabel characterNameLabel;
    private JTextArea characterDescription;
    private JLabel Skillcon;
    
    public CharacterScreen(App app, String playerName, CharacterType selectedCharacter) {
        super(app);

        // ตั้งค่า currentIndex ให้ตรงกับ selectedCharacter ที่ส่งมา
        this.selectedCharacter = selectedCharacter; // ตัวละครที่ MenuScreen ส่งมา
        currentIndex = 0;
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == selectedCharacter) {
                currentIndex = i;
                break;
            }
        }
    }

    //@Override
    protected void initial() {
        //ตั้งค่ารูปพื้นหลัง
        backgroundImage = new ImageIcon(getClass().getResource("/Asset/Sea.png")).getImage();
        //ป้ายแสดงหน้าตัวละครด้านบน
        character = new JLabel("Character");
        character.setBounds(70, 30, 300, 70);
        character.setFont(new Font("Arial", Font.BOLD, 45));
        //ปุ่มเลื่อนขวา
        RightButton = new JButton(">");
        RightButton.setFont(new Font("Arial", Font.BOLD, 45));
        RightButton.setBounds(260, 150, 90, 70);
        RightButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        RightButton.setContentAreaFilled(false);  // ไม่วาดพื้นหลัง
        RightButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        RightButton.setFocusPainted(false);       // ไม่วาดกรอบตอน focus
        //ปุ่มเลื่อนซ้าย
        LeftButton = new JButton("<");
        LeftButton.setFont(new Font("Arial", Font.BOLD, 45));
        LeftButton.setBounds(5, 150, 90, 70);
        LeftButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        LeftButton.setContentAreaFilled(false);  // ไม่วาดพื้นหลัง
        LeftButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        LeftButton.setFocusPainted(false);       // ไม่วาดกรอบตอน focus
        // ชื่อตัวละคร
        characterNameLabel = new JLabel("", SwingConstants.CENTER);
        characterNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        characterNameLabel.setBounds(0, 270, 360, 40);
       //คำอธิบายสกิลตัวละคร
        characterDescription = new JTextArea("",3, SwingConstants.CENTER);
        characterDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        characterDescription.setBounds(30, 320, 360, 70);
        characterDescription.setLineWrap(true);
        characterDescription.setWrapStyleWord(true);
        characterDescription.setEditable(false);
        characterDescription.setOpaque(false);
        characterDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Skillcon = new JLabel("One time use.");
        Skillcon.setBounds(130, 360, 360, 45);
        Skillcon.setFont(new Font("Arial", Font.BOLD, 15));

        //ปุ่มยืนยันเลือกตัวละคร
        SelectedButton = new JButton("Seclect");
        SelectedButton.setBounds(99, 410, 150, 45);
        SelectedButton.setFont(new Font("Arial", Font.BOLD, 20));
        SelectedButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        SelectedButton.setBackground(Color.WHITE);
        //ปุ่มย้อนกลับไปหน้าเมนู
        ReturnButton = new JButton("Return");
        ReturnButton.setBounds(99, 478, 150, 45);
        ReturnButton.setFont(new Font("Arial", Font.BOLD, 20));
        ReturnButton.setBorderPainted(false);      // ไม่วาดเส้นขอบ
        ReturnButton.setBackground(Color.WHITE);
        

        add(character);
        add(RightButton);
        add(LeftButton);
        add(characterNameLabel);
        add(characterDescription);
        add(SelectedButton);
        add(ReturnButton);
        add(Skillcon);
        
        updateCharacter();

            //การทำงานของปุ่มต่าง ๆ 

        RightButton.addActionListener(e -> {
            currentIndex = (currentIndex + 1) % characters.length;
            updateCharacter();
            repaint();
        });

        LeftButton.addActionListener(e -> {
            currentIndex = (currentIndex - 1 + characters.length) % characters.length;
            updateCharacter();
            repaint();
        });

        SelectedButton.addActionListener(e -> {
            selectedCharacter = characters[currentIndex];
        });

        ReturnButton.addActionListener(e -> {
            app.setScreen(new MenuScreen(app, playerName, this.selectedCharacter));
        });



    }

    private void updateCharacter(){
        // เปลี่ยนรายละเอียดตัวละครเมื่อมีการกระทำต่าง ๆ 
        CharacterType current = characters[currentIndex];
        characterImage = current.getCharacterImage();
        characterNameLabel.setText(current.getDisplayName());
        characterDescription.setText(current.getDescription());
    }
    @Override
    public void draw(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        // วาดตัวละคร
        if (characterImage != null) {
            int imgWidth = 160;
            int imgHeight = 160;

            double scale = Math.min((double) imgWidth / characterImage.getWidth(null),
                                    (double) imgHeight / characterImage.getHeight(null));
            int w = (int) (characterImage.getWidth(null) * scale);
            int h = (int) (characterImage.getHeight(null) * scale);

            int x = 100 + (imgWidth - w)/2; // center ภายในพื้นที่ 160x160
            int y = 100 + (imgHeight - h)/2;

            g.drawImage(characterImage, x, y, w, h, null);
        }
    }
    @Override
    public void update() {
    }
    @Override
    public void handleInput(KeyEvent e) {
        
    }

    
}