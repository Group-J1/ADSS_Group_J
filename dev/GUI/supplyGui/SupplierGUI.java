package GUI.supplyGui;

import GUI.MainGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SupplierGUI extends JPanel {
    private MainGUI mainGUI;
    private JPanel mainPanel;
    private AddSupplierPanel addSupplierPanel;
    private EditSupplierPanel editSupplierPanel;
    private DeleteSupplierPanel deleteSupplierPanel;

    public SupplierGUI(MainGUI mainGUI) {
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
        JLabel titleLabel = new JLabel("<html>Responsible for supplier relations <br> Please select option :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // Create buttons
        JButton addSupplierButton = new JButton("Add Supplier");
        JButton editSupplierButton = new JButton("Edit Supplier");
        JButton deleteSupplierButton = new JButton("Delete Supplier");
        JButton backButton = new JButton("Back");

        // Customize button appearance
        Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 16);
        Color buttonBackground = new Color(80, 100, 120);
        Color buttonForeground = Color.BLACK;
        Dimension buttonSize = new Dimension(300, 40);
        Border buttonBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        addSupplierButton.setFont(buttonFont);
        addSupplierButton.setBackground(buttonBackground);
        addSupplierButton.setForeground(buttonForeground);
        addSupplierButton.setPreferredSize(buttonSize);
        addSupplierButton.setBorder(buttonBorder);

        editSupplierButton.setFont(buttonFont);
        editSupplierButton.setBackground(buttonBackground);
        editSupplierButton.setForeground(buttonForeground);
        editSupplierButton.setPreferredSize(buttonSize);
        editSupplierButton.setBorder(buttonBorder);

        deleteSupplierButton.setFont(buttonFont);
        deleteSupplierButton.setBackground(buttonBackground);
        deleteSupplierButton.setForeground(buttonForeground);
        deleteSupplierButton.setPreferredSize(buttonSize);
        deleteSupplierButton.setBorder(buttonBorder);

        backButton.setFont(buttonFont);
        backButton.setBackground(buttonBackground);
        backButton.setForeground(buttonForeground);
        backButton.setPreferredSize(buttonSize);
        backButton.setBorder(buttonBorder);


        // Add buttons to the button panel
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(Box.createVerticalStrut(100));
        buttonPanel.add(addSupplierButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(editSupplierButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(deleteSupplierButton);
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
        addSupplierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddSupplierPanel();
            }
        });

        editSupplierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openEditSupplierPanel();
            }
        });

        deleteSupplierButton.addActionListener(new ActionListener() {
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

        if (addSupplierPanel == null) {
            addSupplierPanel = new AddSupplierPanel(this);
            addSupplierPanel.setPreferredSize(mainPanel.getSize());
            addSupplierPanel.setMaximumSize(mainPanel.getMaximumSize());
            addSupplierPanel.setMinimumSize(mainPanel.getMinimumSize());
            addSupplierPanel.setSize(mainPanel.getSize());
        }

        add(addSupplierPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void openEditSupplierPanel() {
        mainPanel.setVisible(false);

        if (editSupplierPanel == null) {
            editSupplierPanel = new EditSupplierPanel(this);
            editSupplierPanel.setPreferredSize(mainPanel.getSize());
            editSupplierPanel.setMaximumSize(mainPanel.getMaximumSize());
            editSupplierPanel.setMinimumSize(mainPanel.getMinimumSize());
            editSupplierPanel.setSize(mainPanel.getSize());
        }

        add(editSupplierPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void openDeleteSupplierPanel() {
        mainPanel.setVisible(false);

        if (deleteSupplierPanel == null) {
            deleteSupplierPanel = new DeleteSupplierPanel(this);
            deleteSupplierPanel.setPreferredSize(mainPanel.getSize());
            deleteSupplierPanel.setMaximumSize(mainPanel.getMaximumSize());
            deleteSupplierPanel.setMinimumSize(mainPanel.getMinimumSize());
            deleteSupplierPanel.setSize(mainPanel.getSize());
        }

        add(deleteSupplierPanel, BorderLayout.CENTER);
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
        if (addSupplierPanel != null && addSupplierPanel.isShowing()) {
            remove(addSupplierPanel);
        } else if (editSupplierPanel != null && editSupplierPanel.isShowing()) {
            remove(editSupplierPanel);
        } else if (deleteSupplierPanel != null && deleteSupplierPanel.isShowing()) {
            remove(deleteSupplierPanel);
        }
    }

}