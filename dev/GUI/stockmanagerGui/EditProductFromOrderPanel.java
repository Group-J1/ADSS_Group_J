package GUI.stockmanagerGui;

import Supplier_Module.Business.Agreement.SupplierProduct;
import Supplier_Module.Business.Managers.Order_Manager;
import Supplier_Module.Business.Managers.SupplyManager;
import Supplier_Module.Business.Order;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditProductFromOrderPanel extends JPanel {
    //priv//ate EditOrderPanelB parentPanel;
    private OrderManagementGui parentPanel;
    private JPanel mainPanel;

    public EditProductFromOrderPanel(OrderManagementGui parent,int orderID)throws IOException
    {
        this.parentPanel = parent;
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
        JLabel titleLabel = new JLabel("<html>Welcome to Stock Manger <br> Please select option :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());


        // Create button panel
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);
        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showDefaultPanelFromChild();
            }
        });


        JPanel currectIdScreen= new JPanel();
        // Create and add the new components
        String[] items = getAllProductsNameOfOrder(orderID);
        JComboBox<String> comboBox = new JComboBox<>(items);
        JTextField newTextfield = new JTextField(10);
        JButton editButton = new JButton("Edit");
        JLabel messageLabel=new JLabel();

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                String enteredText = newTextfield.getText();
                // Perform validation or further processing with the selected item and entered text
                if (selectedItem == null || enteredText.isEmpty()) {
                    messageLabel.setText("Please select an item and enter text.");
                } else { //todo: change the amount of the product
                    Order order1=Order_Manager.getOrder_Manager().getPeriodOrderById(orderID);
                    int supID= order1.getSupplier_id();
                    if(isValidAmount(supID,enteredText,selectedItem))
                    {
                        int amount=Integer.parseInt(enteredText);
                        SupplierProduct itemToDelete=order1.isProductInOrder(selectedItem);
                        Order_Manager.getOrder_Manager().editProductAmount(order1,itemToDelete,amount); // todo
                        messageLabel.setText("Selected item: " + selectedItem + " | Entered text: " + enteredText);
                    }
                    else
                    {
                        messageLabel.setText("Invalid Amount, Please try again");
                    }
                }
            }
        });
        currectIdScreen.add(comboBox);
        currectIdScreen.add(newTextfield);
        currectIdScreen.add(editButton);
        currectIdScreen.add(messageLabel);



        revalidate();
        repaint();


        mainPanel.add(currectIdScreen);



    }

    public String[] getAllProductsNameOfOrder(int orderID)
    {
        return Order_Manager.getOrder_Manager().getAllProductsNameOfOrder(orderID);
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
            int availableAmount= SupplyManager.getSupply_manager().getSupplier(supID).getAgreement().getProduct(productName).getAmount_available();
            int requestedAmount=Integer.parseInt(amount);
            return availableAmount>=requestedAmount;
        }
    }
}
