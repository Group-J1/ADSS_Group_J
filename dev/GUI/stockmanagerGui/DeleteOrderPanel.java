package GUI.stockmanagerGui;

import Supplier_Module.Business.Managers.Order_Manager;
import Supplier_Module.Business.Order;
import Supplier_Module.DAO.OrderDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteOrderPanel extends JPanel {
    private OrderManagementGui parent;
    private JPanel mainPanel;

    public DeleteOrderPanel (OrderManagementGui parent)
    {
        this.parent=parent;
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
        JLabel titleLabel = new JLabel("<html>Delete period order :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());


        // Create button panel
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new GridLayout(10,3,25,25));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        //buttonPanel.setOpaque(false);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);
        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        // Add button panel to the main panel
        mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
        mainPanel.add(bottomPanel,BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showDefaultPanelFromChild();
            }
        });

        //JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel enterNumberLabel = new JLabel("Please enter the order id you want to remove:");
        JTextField numberField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        mainPanel.add(enterNumberLabel);
        mainPanel.add(numberField);
        mainPanel.add(submitButton);

        //add(deletePanel, BorderLayout.CENTER);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            String orderID = numberField.getText();
            if(isExistOrder(orderID))
            {
                int id=Integer.parseInt(orderID);
                // Perform delete action based on the supplier number

                // Example: Print a message on the panel
                JTextArea messageArea = new JTextArea();
                messageArea.setEditable(false);
                messageArea.append("Are you sure you want to delete order " + orderID + "?\n");
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");

                JPanel confirmationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                confirmationPanel.add(messageArea);
                confirmationPanel.add(yesButton);
                confirmationPanel.add(noButton);

                add(confirmationPanel, BorderLayout.SOUTH);

                // Add action listener for the yes button
                yesButton.addActionListener(yesEvent -> { // todo: remove order from DATA
                    // Perform delete confirmation action
                    Order order=Order_Manager.getOrder_Manager().getPeriodOrderById(id);
                    OrderDAO.getInstance().Delete(order);
                    JOptionPane.showMessageDialog(null, "Supplier " + orderID + " has been deleted.");
                });

                // Add action listener for the no button
                noButton.addActionListener(noEvent -> {
                    // Perform cancel action
                    JOptionPane.showMessageDialog(null, "Deletion canceled.");
                });

                // Refresh the panel to show the confirmation panel
                revalidate();
                repaint();
            }
            else
            {
                // Perform error logic for invalid input
                JOptionPane.showMessageDialog(null, "Invalid supplier ID. Please try again.");
            }

        });

        add(bottomPanel, BorderLayout.WEST);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showDefaultPanelFromChild();
            }
        });
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
