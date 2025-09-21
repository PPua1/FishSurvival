package Screen;

import javax.swing.*;

import Lib.FileManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginScreen extends Screen {
    private JTextField nameField;
    private JButton loginButton;
    private String playerName;
    private Image backgroundImg;
    private FileManager fileManager;

    public LoginScreen(App app) {
        super(app);

    }

    @Override
    protected void initial() {
        //ตั้งค่ารูปพื้นหลัง
        backgroundImg = new ImageIcon(getClass().getResource("/Asset/mainMenu.png")).getImage();

        setLayout(null);

        // กล่องโปร่งแสง (Panel สีขาวโปร่ง)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBounds(80, 250, 200, 160);
        formPanel.setOpaque(false); // เราจะวาดพื้นหลังเอง
        add(formPanel);

        //ป้าย login
        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter your Name", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(180, 30));
        nameField.setHorizontalAlignment(JTextField.CENTER);

        // ปุ่มล็อกอิน
        loginButton = new JButton("confirm");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        loginButton.addActionListener(e -> performLogin());

        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(loginLabel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(nameLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(nameField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(loginButton);


    }

    private void performLogin() {
            playerName = nameField.getText().trim();
        if (!playerName.isEmpty()) {
            //write file
            fileManager = new FileManager("Lib/fileScore.txt");
            fileManager.savePlayerData(playerName, 0);

            // go to menuscreen
            app.setScreen(new MenuScreen(app, playerName,null)); 
        } else {
            JOptionPane.showMessageDialog(this, "Please enter your name!");
        }
    }

    @Override
    public void draw(Graphics g) {
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
        }

        // วาดกล่องโปร่งแสงเอง (เหมือนเป็นพื้นหลังของ formPanel)
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(255, 255, 255, 180)); // สีขาวโปร่งแสง
        g2.fillRoundRect(80, 250, 200, 160, 25, 25);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void handleInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            performLogin();
        }
    }
}
