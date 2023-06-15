package GUI.stockmanagerGui;

import Supplier_Module.Business.Agreement.SupplierProduct;
import Supplier_Module.Business.Managers.SupplyManager;
import Supplier_Module.Business.Supplier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class AddOrderPanel extends JPanel {
    private OrderManagementGui parentPanel;
    private JPanel mainPanel;
    private LinkedList productsOfOrder;

    public AddOrderPanel(OrderManagementGui parnet) {
        this.parentPanel = parnet;
        this.productsOfOrder=new LinkedList<>();
        setLayout(new BorderLayout());

        // Create main panel
        Image background = null;
        try {
            background = ImageIO.read(getClass().getResource("/GUI/pictures/background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image finalBackground = background;
        mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("<html>Create a new periodic order:<br></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());

        // Create button panel
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new GridLayout(10, 3, 25, 25));
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
        mainPanel.add(buttonPanel, BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        JLabel enterNumberLabel = new JLabel("Enter the supplier id you want to order from him :");
        JTextField numberField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        mainPanel.add(enterNumberLabel);
        mainPanel.add(numberField);
        mainPanel.add(submitButton);
        add(mainPanel, BorderLayout.CENTER);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parnet.showDefaultPanelFromChild();
            }
        });


// Add action listener for the submit button
        submitButton.addActionListener(e -> {
            String supplierNumber = numberField.getText();
            if (isPositiveInteger(supplierNumber)) {
                // Remove the existing components
                mainPanel.remove(enterNumberLabel);
                mainPanel.remove(numberField);
                mainPanel.remove(submitButton);

                // Create new components
                int supID=Integer.parseInt(supplierNumber);
                String[] items= getProductOfSupplier(supID);
                JComboBox<String> itemComboBox = new JComboBox<>(items);
                JTextField textBox = new JTextField(10);
                JButton addToCartButton = new JButton("Add to Cart");

                // Add new components to the main panel
                mainPanel.add(itemComboBox);
                mainPanel.add(textBox);
                mainPanel.add(addToCartButton);

                // Add action listener for the addToCartButton
                addToCartButton.addActionListener(actionEvent -> {
                    String item = (String) itemComboBox.getSelectedItem();
                    String quantity = textBox.getText();

                    // Perform input validation for the new components
                    if (isValidAmount(supID,quantity,item))
                    {
                        // Perform success logic here
                        productsOfOrder.add(item);
                        itemComboBox.removeItem(item);
                        JOptionPane.showMessageDialog(null, "Successfully added to cart");
                        // Initialize as needed
                    } else {
                        // Perform error logic here
                        JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
                        // Initialize as needed
                    }
                });

                // Update the container and layout
                JButton createOrderButton = new JButton("Create the order");
                mainPanel.add(createOrderButton);
                createOrderButton.addActionListener(actionEvent -> {
                    if(productsOfOrder.isEmpty()){
                        JOptionPane.showMessageDialog(null, "There is no products in the order");
                    }
                    else
                    {
                        //todo create the order to dao

                        JOptionPane.showMessageDialog(null, "Order add successfully");
                    }

                });



                mainPanel.revalidate();
                mainPanel.repaint();
            }
            else
            {
                // Perform error logic for invalid input
                JOptionPane.showMessageDialog(null, "Invalid supplier ID. Please try again.");
            }

        });


    }


    public String[] getProductOfSupplier(int supplierNumber)
    {
        return SupplyManager.getSupply_manager().productsNamesOfSupplier(supplierNumber);
    }

    public boolean isPositiveInteger(String input) {
        if (input == null || input.isEmpty()) {
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

    public boolean isValidAmount(int supID,String amount, String productName)
    {
        if(!isPositiveInteger(amount))
            return false;
        else
        {
            int availableAmount=SupplyManager.getSupply_manager().getSupplier(supID).getAgreement().getProduct(productName).getAmount_available();
            int requestedAmount=Integer.parseInt(amount);
            return availableAmount>=requestedAmount;
        }
    }
}




