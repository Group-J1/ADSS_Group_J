package GUI.supplyGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteSupplierPanel extends JPanel {
    private SupplierGUI parent;
    private JPanel mainPanel;
    public DeleteSupplierPanel(SupplierGUI supplierGUI) {
        this.parent = supplierGUI;
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

        JLabel titleLabel = new JLabel("Delete Supplier");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel enterNumberLabel = new JLabel("Please enter the supplier number you want to delete:");
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
            messageArea.append("Are you sure you want to delete supplier " + supplierNumber + "?\n");
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
                JOptionPane.showMessageDialog(null, "Supplier " + supplierNumber + " has been deleted.");
            });

            // Add action listener for the no button
            noButton.addActionListener(noEvent -> {
                // Perform cancel action
                JOptionPane.showMessageDialog(null, "Deletion canceled.");
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

