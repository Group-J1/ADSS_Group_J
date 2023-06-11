package GUI.orderGui;
import GUI.orderGui.StockManagerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddOrderPanel extends JPanel {
    private StockManagerGUI stockManagerGUI;
    public AddOrderPanel(StockManagerGUI stockManagerGUI1) {

        this.stockManagerGUI = stockManagerGUI1;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Add Period Order");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Add components for adding a supplier

        // Example: Add a text field for entering supplier information
        JTextField supplierNameField = new JTextField();
        add(supplierNameField, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        add(saveButton, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.WEST);

        // Add action listener for the save button
        saveButton.addActionListener(e -> {
            // Handle save action
        });

        backButton.addActionListener(e -> {
            stockManagerGUI.showDefaultPanelFromChild();
        });
    }
}



