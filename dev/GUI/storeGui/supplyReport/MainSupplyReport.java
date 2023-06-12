package GUI.storeGui.supplyReport;

import GUI.storeGui.StoreManagerGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSupplyReport extends JPanel {
    private StoreManagerGUI parent;
    public MainSupplyReport(StoreManagerGUI storeManagerGUI){
        this.parent = storeManagerGUI;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Suppliers Details");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel enterNumberLabel = new JLabel("Please enter the supplier number you want to watch:");
        JTextField numberField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        deletePanel.add(enterNumberLabel);
        deletePanel.add(numberField);
        deletePanel.add(submitButton);

        add(deletePanel, BorderLayout.CENTER);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            String supplierNumber = numberField.getText();
            // Perform delete action based on the supplier number

            // Example: Print a message on the panel
            JTextArea messageArea = new JTextArea();
            messageArea.setEditable(false);
            messageArea.append("Do you want to watch this supplier order history? " + supplierNumber + "?\n");
            JButton yesButton = new JButton("Yes");
            JButton noButton = new JButton("No");

            JPanel confirmationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            confirmationPanel.add(messageArea);
            confirmationPanel.add(yesButton);
            confirmationPanel.add(noButton);

            add(confirmationPanel, BorderLayout.SOUTH);

            // Add action listener for the yes button
            yesButton.addActionListener(yesEvent -> {
                // Perform delete confirmation action
                JOptionPane.showMessageDialog(null, "Supplier " + supplierNumber + " history order:");//TODO open new screen with all the orders
            });

            // Add action listener for the no button
            noButton.addActionListener(noEvent -> {
                // Perform cancel action
                JOptionPane.showMessageDialog(null, "");
            });

            // Refresh the panel to show the confirmation panel
            revalidate();
            repaint();
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.WEST);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showDefaultPanelFromChild();
            }
        });

    }

}

