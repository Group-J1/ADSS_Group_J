package GUI.stockmanagerGui;

import javax.swing.*;
import java.awt.*;

public class EditOrderPanel extends JPanel {
    private OrderManagementGui stockManagerGUI;
    public EditOrderPanel(OrderManagementGui stockManagerGUI1) {
        this.stockManagerGUI = stockManagerGUI1;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Edit Period Order");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Add components for editing a supplier

        // Example: Add a text field for editing supplier information
        JTextField supplierNameField = new JTextField();
        add(supplierNameField, BorderLayout.CENTER);

        JButton updateButton = new JButton("Update");
        add(updateButton, BorderLayout.SOUTH);

//        JButton backButton = new JButton("Back");
//        add(backButton, BorderLayout.WEST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.WEST);

        // Add action listener for the update button
        updateButton.addActionListener(e -> {
            // Handle update action
        });
        // Add action listener for the back button
        backButton.addActionListener(e -> {
            stockManagerGUI.showDefaultPanelFromChild();
        });
    }
}
