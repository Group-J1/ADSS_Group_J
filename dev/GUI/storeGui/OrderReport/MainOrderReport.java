package GUI.storeGui.OrderReport;

import GUI.storeGui.StoreManagerGUI;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainOrderReport extends JPanel {
    private StoreManagerGUI mainGUI;
    private JPanel mainPanel;
    private SpecificOrder specificOrder;

    public MainOrderReport(StoreManagerGUI storeManagerGUI) throws IOException {
        this.mainGUI = storeManagerGUI;
        setLayout(new BorderLayout());

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
        JLabel titleLabel = new JLabel("<html>Welcome To Order Report <br> Please select required report  :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new GridLayout(2,2,25,25));

        buttonPanel.setOpaque(false);

        // Create buttons
        JButton periodic_order = createButton(" Period Orders", "/GUI/pictures/supply.JPG");
        JButton on_the_way_orders = createButton("On The Way Orders", "/GUI/pictures/stock-manager.jpg");
        JButton historic_orders = createButton("Historic Orders", "/GUI/pictures/stock-manager.jpg");
        JButton specific_order = createButton("Specific Order", "/GUI/pictures/supply.JPG");

        // Add buttons to the button panel
        buttonPanel.add(periodic_order);
        buttonPanel.add(on_the_way_orders);
        buttonPanel.add(historic_orders);
        buttonPanel.add(specific_order);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        // Add button panel to the main panel
        mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
        mainPanel.add(buttonPanel,BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Add action listeners
        periodic_order.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPeriodic_order();
            }
        });
        on_the_way_orders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    openOn_the_way_orders();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        historic_orders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openHistoric_orders();
            }
        });
        specific_order.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSpecific_order();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.showDefaultPanelFromChild();
            }
        });

    }
    private JButton createButton(String text, String imagePath) throws IOException {
        // Create button panel
        int width = 120;
        int height = 120;
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setLayout(new BorderLayout());

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
        Font buttonFont = new Font("Tahoma", Font.BOLD, 12);
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

    private void openPeriodic_order() {
        //open new frame of this report
    }
    private void openOn_the_way_orders() throws IOException {
        //open new frame if this report
    }
    private void openHistoric_orders()  {
        //open new frame if this report
    }
    private void openSpecific_order()  {
        //new frame of categry name
        mainPanel.setVisible(false);

        if (specificOrder == null) {
            specificOrder = new SpecificOrder(this);
            specificOrder.setPreferredSize(mainPanel.getSize());
            specificOrder.setMaximumSize(mainPanel.getMaximumSize());
            specificOrder.setMinimumSize(mainPanel.getMinimumSize());
            specificOrder.setSize(mainPanel.getSize());
        }

        add(specificOrder, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    // Add methods to open other screens and handle back button
    public void showDefaultPanelFromChild() {
        mainPanel.setVisible(true);
        removeCurrentChildPanel();
        revalidate();
        repaint();
    }

    private void removeCurrentChildPanel() {
        if (specificOrder != null && specificOrder.isShowing()) {
            remove(specificOrder);
        }
    }

}

