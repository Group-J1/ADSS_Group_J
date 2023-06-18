package GUI.supplyGui;

import Supplier_Module.Business.Card.SupplierCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSupplierPanel extends JPanel {
    private SupplierGUI supplierGUI;
    public AddSupplierPanel(SupplierGUI supplier) {
        this.supplierGUI = supplier;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Add Supplier");
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
            supplierGUI.showDefaultPanelFromChild();
        });
    }
//    public class AddSupplierPanel extends JPanel {
//        public AddSupplierPanel(SupplierGUI supplierGUI) {
//            setLayout(new GridLayout(6, 2, 10, 10));
//
//            JLabel nameLabel = new JLabel("Name:");
//            JTextField nameField = new JTextField(20);
//
//            JLabel supplierNumberLabel = new JLabel("Supplier Number:");
//            JTextField supplierNumberField = new JTextField(20);
//
//            JLabel companyNumberLabel = new JLabel("Company Number:");
//            JTextField companyNumberField = new JTextField(20);
//
//            JLabel bankAccountLabel = new JLabel("Bank Account:");
//            JTextField bankAccountField = new JTextField(20);
//
//            JLabel addressLabel = new JLabel("Address:");
//            JTextField addressField = new JTextField(20);
//
//            JButton addButton = new JButton("Add");
//
//            addButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String name = nameField.getText();
//                    int supplierNumber = Integer.parseInt(supplierNumberField.getText());
//                    int companyNumber = Integer.parseInt(companyNumberField.getText());
//                    int bankAccount = Integer.parseInt(bankAccountField.getText());
//                    String address = addressField.getText();
//
//                    SupplierCard supplier = new SupplierCard(name, supplierNumber, companyNumber, bankAccount, address);
//
//                    // Perform any necessary operations with the supplier object (e.g., add it to a database, etc.)
//
//                    // Update the existing screen with the supplier details
//                    printSupplierDetails(supplier);
//                }
//            });
//
//            add(nameLabel);
//            add(nameField);
//            add(supplierNumberLabel);
//            add(supplierNumberField);
//            add(companyNumberLabel);
//            add(companyNumberField);
//            add(bankAccountLabel);
//            add(bankAccountField);
//            add(addressLabel);
//            add(addressField);
//            add(new JLabel());
//            add(addButton);
//        }
//
//        private void printSupplierDetails(SupplierCard supplier) {
//            // Clear the existing components
//            removeAll();
//
//            setLayout(new GridLayout(7, 2, 10, 10));
//
//            JLabel nameLabel = new JLabel("Name:");
//            JLabel nameValueLabel = new JLabel(supplier.getSupplier_name());
//
//            JLabel supplierNumberLabel = new JLabel("Supplier Number:");
//            JLabel supplierNumberValueLabel = new JLabel(String.valueOf(supplier.getSupplier_number()));
//
////        JLabel companyNumberLabel = new JLabel("Company Number:");
////        JLabel companyNumberValueLabel = new JLabel(String.valueOf(supplier.getC()));
//
//            JLabel bankAccountLabel = new JLabel("Bank Account:");
//            JLabel bankAccountValueLabel = new JLabel(String.valueOf(supplier.getBank_account()));
//
//            JLabel addressLabel = new JLabel("Address:");
//            JLabel addressValueLabel = new JLabel(supplier.getAddress());
//
//            add(nameLabel);
//            add(nameValueLabel);
//            add(supplierNumberLabel);
//            add(supplierNumberValueLabel);
////        add(companyNumberLabel);
////        add(companyNumberValueLabel);
//            add(bankAccountLabel);
//            add(bankAccountValueLabel);
//            add(addressLabel);
//            add(addressValueLabel);
//
////            JButton addButton = new JButton("Add Supplier");
////            addButton.addActionListener(new ActionListener() {
////                @Override
////                public void actionPerformed(ActionEvent e) {
////                    removeAll();
////                    add(new AddSupplierPanel(SupplierGUI ));
////                    revalidate();
////                    repaint();
////                }
////            });
////
////            add(new JLabel());
////            add(addButton);
//
//            revalidate();
//            repaint();
//        }
    }

