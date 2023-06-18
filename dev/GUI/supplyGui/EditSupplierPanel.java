package GUI.supplyGui;

import javax.swing.*;
import java.awt.*;

public class EditSupplierPanel extends JPanel {
    private SupplierGUI supplierGUI;
    public EditSupplierPanel(SupplierGUI supplier) {
        this.supplierGUI = supplier;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Edit Supplier");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Add components for editing a supplier

        // Example: Add a text field for editing supplier information
        JTextField supplierNameField = new JTextField();
        add(supplierNameField, BorderLayout.CENTER);

        JButton updateButton = new JButton("Update");
        add(updateButton, BorderLayout.SOUTH);

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
            supplierGUI.showDefaultPanelFromChild();
        });
    }
}
