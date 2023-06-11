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

    public StockManagerGUI(MainGUI mainGUI) {
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

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // Create buttons
        JButton addOrderButton = new JButton("Add Period Order");
        JButton editOrderButton = new JButton("Edit Period Order");
        JButton deleteOrderButton = new JButton("Delete Period Order");
        JButton backButton = new JButton("Back");

        // Customize button appearance
        Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 16);
        Color buttonBackground = new Color(80, 100, 120);
        Color buttonForeground = Color.BLACK;
        Dimension buttonSize = new Dimension(300, 40);
        Border buttonBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        addOrderButton.setFont(buttonFont);
        addOrderButton.setBackground(buttonBackground);
        addOrderButton.setForeground(buttonForeground);
        addOrderButton.setPreferredSize(buttonSize);
        addOrderButton.setBorder(buttonBorder);

        editOrderButton.setFont(buttonFont);
        editOrderButton.setBackground(buttonBackground);
        editOrderButton.setForeground(buttonForeground);
        editOrderButton.setPreferredSize(buttonSize);
        editOrderButton.setBorder(buttonBorder);

        deleteOrderButton.setFont(buttonFont);
        deleteOrderButton.setBackground(buttonBackground);
        deleteOrderButton.setForeground(buttonForeground);
        deleteOrderButton.setPreferredSize(buttonSize);
        deleteOrderButton.setBorder(buttonBorder);

        backButton.setFont(buttonFont);
        backButton.setBackground(buttonBackground);
        backButton.setForeground(buttonForeground);
        backButton.setPreferredSize(buttonSize);
        backButton.setBorder(buttonBorder);


        // Add buttons to the button panel
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(Box.createVerticalStrut(100));
        buttonPanel.add(addOrderButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(editOrderButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(deleteOrderButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(Box.createVerticalGlue());
        JPanel horizontalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        horizontalPanel.setOpaque(false);
        horizontalPanel.add(buttonPanel);
        // Add the buttons panel to the center of the main panel
        mainPanel.add(horizontalPanel, BorderLayout.CENTER);
        // Add button panel to the center of the main panel
//        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add main panel to the SupplierGUI panel
        add(mainPanel, BorderLayout.CENTER);

        // Add action listeners for the buttons
        addOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddSupplierPanel();
            }
        });

        editOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openEditSupplierPanel();
            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDeleteSupplierPanel();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.showMainPanel();
            }
        });
    }

    private void openAddSupplierPanel() {
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

    private void openEditSupplierPanel() {
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

    private void openDeleteSupplierPanel() {
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