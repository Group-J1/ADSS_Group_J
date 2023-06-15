package GUI.storeGui.OrderReport;

import Supplier_Module.Business.Managers.Order_Manager;
import Supplier_Module.Business.Order;
import Supplier_Module.DAO.OrderDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SpecificOrder extends JPanel {
    private MainOrderReport parent;
    private JPanel mainPanel;
    public SpecificOrder(MainOrderReport mainOrderReport){

        this.parent = mainOrderReport;
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
        mainPanel.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0; // Column index
        titleConstraints.gridy = 0; // Row index
        titleConstraints.anchor = GridBagConstraints.NORTH; // Align in the center horizontally
        titleConstraints.insets = new Insets(10, 0, 10, 0);

        JLabel titleLabel = new JLabel("Delete Supplier");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        centerPanel.add(titleLabel, titleConstraints);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel enterNumberLabel = new JLabel("Please enter the supplier number you want to delete:");
        JTextField numberField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        deletePanel.add(enterNumberLabel);
        deletePanel.add(numberField);
        deletePanel.add(submitButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.5; // Place components vertically in the middle
        gbc.anchor = GridBagConstraints.NORTH; // Center-align components
        centerPanel.add(deletePanel, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            String orderNumber = numberField.getText();
            // Perform delete action based on the supplier number
            if(!isInteger(orderNumber) || Integer.parseInt(orderNumber) < 0){
                JOptionPane.showMessageDialog(null, "invalid input!", "ERROR",  JOptionPane.ERROR_MESSAGE);
            }
            else if(OrderDAO.getInstance().getOrderById(Integer.parseInt(orderNumber)) == null){//check if the order is in the system
                JOptionPane.showMessageDialog(null, "there is order with number " + orderNumber , "ERROR",  JOptionPane.ERROR_MESSAGE);
            }
            else {

                Order order = OrderDAO.getInstance().getOrderById(Integer.parseInt(orderNumber));
                centerPanel.remove(deletePanel);
                GridBagConstraints gbcDetails = new GridBagConstraints();
                gbcDetails.gridx = 0;
                gbcDetails.gridy = 2;
                gbcDetails.weighty = 0.5; // Place component vertically in the middle
                gbcDetails.anchor = GridBagConstraints.CENTER; // Center-align component
                JLabel supplierDetails = new JLabel("");
                centerPanel.add(supplierDetails, gbcDetails);


                revalidate();
                repaint();
            }
        });

    }
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
