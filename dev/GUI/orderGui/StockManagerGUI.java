package GUI.orderGui;

import GUI.MainGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class StockManagerGUI extends JPanel {
    private MainGUI mainGUI;
    private JPanel mainPanel;
    private AddOrderPanel addOrderPanel;
    private EditOrderPanel editOrderPanel;
    private DeleteOrderPanel deleteOrderPanel;

    public StockManagerGUI(MainGUI mainGUI) throws IOException {
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
        mainPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("<html>Welcome storekeeper <br> Please select option :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());


        // Create button panel
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        // Create buttons
        JButton addOrderButton = createButton("Add Period Order", "/GUI/pictures/add-order.jpg");
        JButton editOrderButton = createButton("Edit Period Order", "/GUI/pictures/update.jpg");
        JButton deleteOrderButton = createButton("Delete Period Order", "/GUI/pictures/delete.jpg");


        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addOrderButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(editOrderButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(deleteOrderButton);
        buttonPanel.add(Box.createHorizontalGlue());


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        // Add button panel to the main panel
        mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
        mainPanel.add(buttonPanel,BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        add(mainPanel, BorderLayout.CENTER);

        // Add action listeners for the buttons
        addOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddOrderPanel();
            }
        });

        editOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openEditOrderPanel();
            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDeleteOrderPanel();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.showMainPanel();
            }
        });
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

    private void openAddOrderPanel() {
        mainPanel.setVisible(false);

        if (addOrderPanel == null) {
            addOrderPanel = new AddOrderPanel(this);
            addOrderPanel.setPreferredSize(mainPanel.getSize());
            addOrderPanel.setMaximumSize(mainPanel.getMaximumSize());
            addOrderPanel.setMinimumSize(mainPanel.getMinimumSize());
            addOrderPanel.setSize(mainPanel.getSize());
        }

        add(addOrderPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void openEditOrderPanel() {
        mainPanel.setVisible(false);

        if (editOrderPanel == null) {
            editOrderPanel = new EditOrderPanel(this);
            editOrderPanel.setPreferredSize(mainPanel.getSize());
            editOrderPanel.setMaximumSize(mainPanel.getMaximumSize());
            editOrderPanel.setMinimumSize(mainPanel.getMinimumSize());
            editOrderPanel.setSize(mainPanel.getSize());
        }

        add(editOrderPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void openDeleteOrderPanel() {
        mainPanel.setVisible(false);

        if (deleteOrderPanel == null) {
            deleteOrderPanel = new DeleteOrderPanel(this);
            deleteOrderPanel.setPreferredSize(mainPanel.getSize());
            deleteOrderPanel.setMaximumSize(mainPanel.getMaximumSize());
            deleteOrderPanel.setMinimumSize(mainPanel.getMinimumSize());
            deleteOrderPanel.setSize(mainPanel.getSize());
        }

        add(deleteOrderPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void showDefaultPanelFromChild() {
        mainPanel.setVisible(true);
        removeCurrentChildPanel();
        revalidate();
        repaint();
    }

    private void removeCurrentChildPanel() {
        if (addOrderPanel != null && addOrderPanel.isShowing()) {
            remove(addOrderPanel);
        } else if (editOrderPanel != null && editOrderPanel.isShowing()) {
            remove(editOrderPanel);
        } else if (deleteOrderPanel != null && deleteOrderPanel.isShowing()) {
            remove(deleteOrderPanel);
        }
    }

}