package GUI.loginRegisterGui;

import GUI.MainGUI;
import GUI.orderGui.AddOrderPanel;
import GUI.orderGui.DeleteOrderPanel;
import GUI.orderGui.EditOrderPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class loginRegisterGUI extends JPanel {

    private MainGUI mainGUI;
    private JPanel mainPanel;
    private AddOrderPanel addOrderPanel;
    private EditOrderPanel editOrderPanel;
    private DeleteOrderPanel deleteOrderPanel;

    public loginRegisterGUI(MainGUI mainGUI) throws IOException {
        this.mainGUI = mainGUI;

        setLayout(new BorderLayout());

        // Create main panel
        Image background = null;
        try {
            background = ImageIO.read(getClass().getResource("/GUI/pictures/background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image finalBackground = background;
        mainPanel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("<html> Please login / register :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.SOUTH);
        mainPanel.setLayout(new FlowLayout());


        // Create button panel
//        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        // Create buttons
        JButton Login = createButton("Login", "/GUI/pictures/add-order.jpg");
        JButton register = createButton("Register", "/GUI/pictures/update.jpg");


        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(Login);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(register);


        JTextField usernameField = new JTextField(15);
        JTextField passwordField = new JTextField(15);
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new FlowLayout());
        textFieldPanel.setOpaque(false);
        textFieldPanel.add(new JLabel("Username: "));
        textFieldPanel.add(usernameField);
        textFieldPanel.add(new JLabel("Password: "));
        textFieldPanel.add(passwordField);

        //////////////
        String[] options = {"Supplier Manager", "Stock Manager", "Market Manager"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Create panel for combo box
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout());
        comboBoxPanel.setOpaque(false);
        comboBoxPanel.add(new JLabel("Select an option: "));
        textFieldPanel.add(comboBox);

        // Add combo box panel to the main panel
//        mainPanel.add(comboBoxPanel, BorderLayout.CENTER);

        //////////////

        mainPanel.add(textFieldPanel, new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));




        // Add button panel to the main panel
        mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
        mainPanel.add(buttonPanel,BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));


        add(mainPanel, BorderLayout.CENTER);

        // Add action listeners for the buttons
        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //openAddOrderPanel();
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //openEditOrderPanel();

            }
        });

//        deleteOrderButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                openDeleteOrderPanel();
//            }
//        });
//
//        backButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                mainGUI.showMainPanel();
//            }
//        });
    }
    private JButton createButton(String text, String imagePath) throws IOException {
        // Create button panel
        int width = 150;
        int height = 150;
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setLayout(new BorderLayout());
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove label margin

        // Create image label
        JLabel imageLabel = new JLabel();
        Image image = ImageIO.read(getClass().getResource(imagePath));
        Image small_image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(small_image);
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0,0,width,height);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(imageLabel, BorderLayout.CENTER);

        // Create text label
        Font buttonFont = new Font("Tahoma", Font.BOLD, 16);
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(buttonFont);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(textLabel, BorderLayout.SOUTH);

        // Create button
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.add(buttonPanel, BorderLayout.CENTER);
        button.setFocusPainted(false);
        button.setVerticalAlignment(SwingConstants.TOP); // Adjust vertical alignment
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Adjust vertical text position
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Adjust horizontal text position
        button.setMargin(new Insets(0, 0, 0, 0)); // Set the margin to zer


        return button;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame mainGUI = null;
                try {
                    mainGUI = new JFrame();
                    mainGUI.setSize(800, 650);
                    mainGUI.add(new loginRegisterGUI(new MainGUI()));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mainGUI.setVisible(true);
            }
        });
    }
}


