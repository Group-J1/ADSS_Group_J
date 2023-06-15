package GUI.stockmanagerGui;

import Supplier_Module.Business.Agreement.SupplierProduct;
import Supplier_Module.Business.Managers.Order_Manager;
import Supplier_Module.Business.Managers.SupplyManager;
import Supplier_Module.Business.Order;
import Supplier_Module.DAO.OrderDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteProductFromOrderPanel extends JPanel {
   // //private EditOrderPanelB parentPanel;
    private OrderManagementGui parentPanel;
    private JPanel mainPanel;
    private Order order;


    public DeleteProductFromOrderPanel(OrderManagementGui parent, int orderID) {
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
        JLabel titleLabel = new JLabel("<html>Delete Product From Order: <br></html>");
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


        JLabel messageLabel = new JLabel(); // Label to display messages
        JPanel currectIdScreen= new JPanel();

        // Create and add the new components
        String[] items = getAllProductsNameOfOrder(orderID);
        JComboBox<String> comboBox = new JComboBox<>(items);
        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                // Perform validation or further processing with the selected item and entered text
                if (selectedItem == null ) {
                    messageLabel.setText("Please select an item and enter text.");
                } else {
                    messageLabel.setText("Selected item: " + selectedItem);
                    // Perform the desired action with the selected item and entered text //todo
                    Order order1=Order_Manager.getOrder_Manager().getPeriodOrderById(orderID);
                    SupplierProduct itemToDelete=order1.isProductInOrder(selectedItem);
                    Order_Manager.getOrder_Manager().deleteProductFromOrder(order1,itemToDelete);
                    // Remove the selected item from the JComboBox
                    comboBox.removeItem(selectedItem);
                    if(comboBox.getItemCount()==0) // todo: delete the order and go back to the prev page
                    {
                        OrderDAO.getInstance().Delete(order1);
                        JOptionPane.showMessageDialog(null, "There is no more products in the order \n Order Deleted");
                        parent.showDefaultPanelFromChild();
                    }
                }
            }
        });
        currectIdScreen.add(comboBox);
        currectIdScreen.add(removeButton);


        revalidate();
        repaint();


        currectIdScreen.add(messageLabel);
        mainPanel.add(currectIdScreen);



    }

    public String[] getAllProductsNameOfOrder(int orderID)
    {
        return Order_Manager.getOrder_Manager().getAllProductsNameOfOrder(orderID);
    }
}