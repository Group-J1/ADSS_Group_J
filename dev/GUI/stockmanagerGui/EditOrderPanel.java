package GUI.stockmanagerGui;

import Supplier_Module.Business.Managers.Order_Manager;
import Supplier_Module.Business.Order;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditOrderPanel extends JPanel {
    private OrderManagementGui parentPanel;
    private JPanel mainPanel;
    private DeleteProductFromOrderPanel deleteProductFromOrderPanel;
    private EditProductFromOrderPanel editProductFromOrderPanel;
    private JTextField numberField;
    private JButton deleteButton = null;
    private JButton editButton = null;

    public EditOrderPanel (OrderManagementGui parnet)
    {
        this.parentPanel = parnet;
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
        JLabel titleLabel = new JLabel("<html>Edit:<br></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());

        // Create button panel
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new GridLayout(10,3,25,25));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(Box.createHorizontalGlue());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        // Add button panel to the main panel
        mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
        mainPanel.add(buttonPanel,BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        JLabel enterNumberLabel = new JLabel("Please enter the order number :");
        numberField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        mainPanel.add(enterNumberLabel);
        mainPanel.add(numberField);
        mainPanel.add(submitButton);
        add(mainPanel, BorderLayout.CENTER);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {parnet.showDefaultPanelFromChild();}
        });

// Add action listener for the submit button
        submitButton.addActionListener(e -> {
            String supplierNumber = numberField.getText();
            // Check if valid OrderID todo
            if(!isExistOrder(supplierNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid order ID");
            }
//            if (supplierNumber.isEmpty()) {
//                // Remove buttons if they exist
//                if (deleteButton != null) {
//                    buttonPanel.remove(deleteButton);
//                    deleteButton = null;
//                }
//                if (editButton != null) {
//                    buttonPanel.remove(editButton);
//                    editButton = null;
//                }
//                JOptionPane.showMessageDialog(null, "Invalid order ID");

            else {
                // Input is valid, add buttons if they haven't been added
                if (deleteButton == null) {
                    try {
                        deleteButton = createButton("Delete Product", "/GUI/pictures/delete.jpg");
                        deleteButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                openDeleteProductFromOrderPanel();
                            }
                        });
                        buttonPanel.add(deleteButton);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (editButton == null) {
                    try {
                        editButton = createButton("Edit Product", "/GUI/pictures/order-report.jpg");
                        editButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    openEditProductFromOrderPanel();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                        buttonPanel.add(editButton);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                // Hide the components
                enterNumberLabel.setVisible(false);
                numberField.setVisible(false);
                submitButton.setVisible(false);
            }

            // Update the container and layout
            buttonPanel.revalidate();
            buttonPanel.repaint();
            mainPanel.revalidate();
            mainPanel.repaint();
        });


    }

    private void openDeleteProductFromOrderPanel() {
        mainPanel.setVisible(false);

        if (deleteProductFromOrderPanel == null) {
            deleteProductFromOrderPanel = new DeleteProductFromOrderPanel(parentPanel, Integer.parseInt(numberField.getText()));
            deleteProductFromOrderPanel.setPreferredSize(mainPanel.getSize());
            deleteProductFromOrderPanel.setMaximumSize(mainPanel.getMaximumSize());
            deleteProductFromOrderPanel.setMinimumSize(mainPanel.getMinimumSize());
            deleteProductFromOrderPanel.setSize(mainPanel.getSize());
        }

        add(deleteProductFromOrderPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void openEditProductFromOrderPanel() throws IOException {
        mainPanel.setVisible(false);

        if (editProductFromOrderPanel == null) {
            editProductFromOrderPanel = new EditProductFromOrderPanel(parentPanel,Integer.parseInt(numberField.getText()));
            editProductFromOrderPanel.setPreferredSize(mainPanel.getSize());
            editProductFromOrderPanel.setMaximumSize(mainPanel.getMaximumSize());
            editProductFromOrderPanel.setMinimumSize(mainPanel.getMinimumSize());
            editProductFromOrderPanel.setSize(mainPanel.getSize());
        }

        add(editProductFromOrderPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JButton createButton(String text, String imagePath) throws IOException {
        // Create button panel
        int width = 100;
        int height = 100;
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

    public void showDefaultPanelFromChild() {
        mainPanel.setVisible(true);
        removeCurrentChildPanel();
        revalidate();
        repaint();
    }

    private void removeCurrentChildPanel() {
        if (deleteProductFromOrderPanel != null && deleteProductFromOrderPanel.isShowing()) {
            remove(deleteProductFromOrderPanel);
        }
        if (editProductFromOrderPanel != null && editProductFromOrderPanel.isShowing()) {
            remove(editProductFromOrderPanel);
        }
    }

    public boolean isPositiveInteger(String input) {
        if (input == null || input.isEmpty())
        {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        int number = Integer.parseInt(input);
        return number > 0;
    }
    public boolean isExistOrder(String id)
    {
        if(!isPositiveInteger(id))
            return false;
        else {
            return Order_Manager.getOrder_Manager().isExistOrder(Integer.parseInt(id));
        }
    }


}
