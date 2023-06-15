package GUI.supplyGui;

import Supplier_Module.Business.Supplier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddSupplierProduct extends JPanel {
    private EditSupplierAgreemantGui parent;
    private JPanel mainPanel;
    private Supplier supplier;

    public AddSupplierProduct(EditSupplierAgreemantGui editSupplierAgreemantGui, Supplier supplier1) {
        this.parent = editSupplierAgreemantGui;
        this.supplier = supplier1;

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

        JLabel titleLabel = new JLabel("Add Supplier Product");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);


        // Create text fields
        JLabel nameLabel = new JLabel("Product Name:");
        Font nameLabelFont = nameLabel.getFont();
        Font nameLabelNewFont = nameLabelFont.deriveFont(Font.BOLD, 18);
        nameLabel.setFont(nameLabelNewFont);

        JTextField nameLabelTextField = new JTextField();
        Font nameLabelTextFieldFont = nameLabelTextField.getFont();
        Font nameTextFieldNewFont = nameLabelTextFieldFont.deriveFont(Font.BOLD, 18);
        nameLabelTextField.setFont(nameTextFieldNewFont);

        JLabel invalidNameLabel = new JLabel("Invalid Name");
        Font invalidNameLabelFont = invalidNameLabel.getFont();
        Font invalidNameLabelNewFont = invalidNameLabelFont.deriveFont(Font.PLAIN, 18);
        invalidNameLabel.setFont(invalidNameLabelNewFont);
        invalidNameLabel.setForeground(Color.RED);

        JLabel idLabel = new JLabel("Catalog Number");
        Font idLabelFont = idLabel.getFont();
        Font idLabelNewFont = idLabelFont.deriveFont(Font.PLAIN, 18);
        idLabel.setFont(idLabelNewFont);

        JTextField idTextField = new JTextField();
        Font idTextFieldFont = idTextField.getFont();
        Font idTextFieldNewFont = idTextFieldFont.deriveFont(Font.PLAIN, 18);
        idTextField.setFont(idTextFieldNewFont);

        JLabel invalidIDLabel = new JLabel("Invalid ID");
        Font invalidIDLabelFont = invalidIDLabel.getFont();
        Font invalidIDLabelNewFont = invalidIDLabelFont.deriveFont(Font.PLAIN, 18);
        invalidIDLabel.setFont(invalidIDLabelNewFont);
        invalidIDLabel.setForeground(Color.RED);


        JLabel wieghtLabel = new JLabel("Weight");
        Font weightLabelFont = wieghtLabel.getFont();
        Font wightLabelNewFont = weightLabelFont.deriveFont(Font.PLAIN, 18);
        wieghtLabel.setFont(wightLabelNewFont);

        JTextField weightTextField = new JTextField();
        Font weightTextFieldFont = weightTextField.getFont();
        Font weightTextFieldNewFont = weightTextFieldFont.deriveFont(Font.PLAIN, 18);
        weightTextField.setFont(weightTextFieldNewFont);

        JLabel invalidmWeightLabel = new JLabel("Invalid Address");
        Font invalidWeightLabelFont = invalidmWeightLabel.getFont();
        Font invalidweightLabelNewFont = invalidWeightLabelFont.deriveFont(Font.PLAIN, 18);
        invalidmWeightLabel.setFont(invalidweightLabelNewFont);
        invalidmWeightLabel.setForeground(Color.RED);


        JLabel priceLabel = new JLabel(" Unit Price:");
        Font priceLabelFont = priceLabel.getFont();
        Font priceLabelNewFont = priceLabelFont.deriveFont(Font.BOLD, 18);
        priceLabel.setFont(priceLabelNewFont);

        JTextField priceLabelTextField = new JTextField();
        Font priceLabelTextFieldFont = priceLabelTextField.getFont();
        Font priceTextFieldNewFont = priceLabelTextFieldFont.deriveFont(Font.BOLD, 18);
        priceLabelTextField.setFont(priceTextFieldNewFont);

        JLabel invalidPriceLabel = new JLabel("Invalid Name");
        Font invalidPriceLabelFont = invalidPriceLabel.getFont();
        Font invalidPriceLabelNewFont = invalidPriceLabelFont.deriveFont(Font.PLAIN, 18);
        invalidPriceLabel.setFont(invalidPriceLabelNewFont);
        invalidPriceLabel.setForeground(Color.RED);

        JLabel amountLabel = new JLabel("Avialiable Amount:");
        Font amountLabelFont = amountLabel.getFont();
        Font amountLabelNewFont = amountLabelFont.deriveFont(Font.BOLD, 18);
        amountLabel.setFont(amountLabelNewFont);

        JTextField amountLabelTextField = new JTextField();
        Font amountLabelTextFieldFont = amountLabelTextField.getFont();
        Font amountTextFieldNewFont = amountLabelTextFieldFont.deriveFont(Font.BOLD, 18);
        amountLabelTextField.setFont(amountTextFieldNewFont);

        JLabel invalidAmountLabel = new JLabel("Invalid Name");
        Font invalidAmountLabelFont = invalidAmountLabel.getFont();
        Font invalidAmountLabelNewFont = invalidAmountLabelFont.deriveFont(Font.PLAIN, 18);
        invalidAmountLabel.setFont(invalidAmountLabelNewFont);
        invalidAmountLabel.setForeground(Color.RED);

        JLabel discountLabel = new JLabel("Discount");
        Font dixcountLabelFont = discountLabel.getFont();
        Font discountLabelNewFont = dixcountLabelFont.deriveFont(Font.BOLD, 18);
        discountLabel.setFont(discountLabelNewFont);

        JTextField discountLabelTextField = new JTextField();
        Font discountLabelTextFieldFont = discountLabelTextField.getFont();
        Font discountTextFieldNewFont = discountLabelTextFieldFont.deriveFont(Font.BOLD, 18);
        discountLabelTextField.setFont(discountTextFieldNewFont);

        JLabel invalidDiscountLabel = new JLabel("Invalid Name");
        Font invalidDiscountLabelFont = invalidDiscountLabel.getFont();
        Font invalidDiscountLabelNewFont = invalidDiscountLabelFont.deriveFont(Font.PLAIN, 18);
        invalidDiscountLabel.setFont(invalidDiscountLabelNewFont);
        invalidDiscountLabel.setForeground(Color.RED);


        JPanel inputPanel = new JPanel();
        int verticalGap = 25; // Set the desired vertical gap between rows
        int horizontalGap = 15;
        inputPanel.setLayout(new GridLayout(6, 3, horizontalGap, verticalGap));
        inputPanel.add(nameLabel);
        inputPanel.add(nameLabelTextField);
        inputPanel.add(invalidNameLabel);
        invalidNameLabel.setVisible(false);
        inputPanel.add(idLabel);
        inputPanel.add(idTextField);
        inputPanel.add(invalidIDLabel);
        invalidIDLabel.setVisible(false);
        inputPanel.add(wieghtLabel);
        inputPanel.add(weightTextField);
        inputPanel.add(invalidmWeightLabel);
        invalidmWeightLabel.setVisible(false);
        inputPanel.add(priceLabel);
        inputPanel.add(priceLabelTextField);
        inputPanel.add(invalidPriceLabel);
        invalidPriceLabel.setVisible(false);
        inputPanel.add(amountLabel);
        inputPanel.add(amountLabelTextField);
        inputPanel.add(invalidAmountLabel);
        invalidAmountLabel.setVisible(false);
        inputPanel.add(discountLabel);
        inputPanel.add(discountLabelTextField);
        inputPanel.add(invalidDiscountLabel);
        invalidDiscountLabel.setVisible(false);

        inputPanel.setOpaque(false);

//        mainPanel.add(inputPanel, BorderLayout.CENTER);
        JPanel inputWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputWrapperPanel.add(inputPanel);
        mainPanel.add(inputWrapperPanel, BorderLayout.CENTER);

        inputWrapperPanel.setOpaque(false);

        // Create button panel
        JButton submitButton = new JButton("Add Supplier");
        //JButton submitButton = createButton("Submit", "/GUI/pictures/stock-manager.jpg");
        JButton backButton = new JButton("Back");

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(submitButton);
        buttonsPanel.add(backButton);

        buttonsPanel.setOpaque(false);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

//        submitButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
        // Handle submit button action
//                MarketService marketService = MarketService.getInstance();
//                ArrayList<JLabel> inputsArrayList = new ArrayList<>(Arrays.asList(
//                        invalidCategoryLabel, invalidSubCategoryLabel, invalidSubSubCategoryLabel, invalidDiscountLabel));
//                ArrayList<Boolean> inputChecks = new ArrayList<>();
//
//                String categoryStr = categoryTextField.getText();
//                inputChecks.add(checkIfOnlyLetters(categoryStr));
//                String subCategoryStr = subCategoryTextField.getText();
//                inputChecks.add(checkSubCategory(subCategoryStr));
//                String subSubCategoryStr = subSubCategoryTextField.getText();
//                inputChecks.add(checkSubSubCategory(subSubCategoryStr));
//                String discountStr = discountTextField.getText();
//                inputChecks.add(checkIfPositiveDoubleNumber(discountStr));
//
//                boolean allTrue = !inputChecks.contains(Boolean.FALSE);
//
//                if (allTrue) {
//                    for (JLabel currentInput: inputsArrayList) {
//                        currentInput.setVisible(false);
//                    }
//                    if (marketService.setDiscountForProduct(categoryStr,subCategoryStr,subSubCategoryStr,
//                            Double.parseDouble(discountStr))) {
//                        categoryTextField.setText("");
//                        subCategoryTextField.setText("");
//                        subSubCategoryTextField.setText("");
//                        discountTextField.setText("");
//                        JOptionPane.showMessageDialog(null,"Discount updated");
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(null,"Product Not Found");
//                    }
//                }
//                else {
//                    int index = 0;
//                    for (boolean currentInputValid : inputChecks) {
//                        // Perform operations on the 'element' using the index 'index'
//                        if (!currentInputValid) {
//                            inputsArrayList.get(index).setVisible(true);
//                        }
//                        else {
//                            inputsArrayList.get(index).setVisible(false);
//                        }
//                        index++;
//                    }
//                }
//            }
//        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invalidDiscountLabel.setVisible(false);
                invalidmWeightLabel.setVisible(false);
                invalidIDLabel.setVisible(false);
                invalidAmountLabel.setVisible(false);
                invalidPriceLabel.setVisible(false);
                invalidNameLabel.setVisible(false);


                discountLabelTextField.setText("");
                weightTextField.setText("");
                idTextField.setText("");
                priceLabelTextField.setText("");
                amountLabelTextField.setText("");
                nameLabelTextField.setText("");

                parent.showDefaultPanelFromChild();
            }
        });

    }


    private JPanel createTextFieldPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    // All the functions that check if the input is valid
    Boolean checkIfOnlyLetters(String str) {
        if (str.equals("")) {
            return false;
        }
        return str.matches("[a-zA-Z' ]+");
    }

    Boolean checkSubCategory(String subCategoryStr) {
        if (subCategoryStr.equals("")) {
            return false;
        }
        return subCategoryStr.matches("[a-zA-Z0-9% ]+");
    }

    Boolean checkSubSubCategory(String subSubCategoryStr) {
        /**
         * Checks if a given string matches the format of a sub-sub category.
         * A sub-sub category should consist of a number followed by a space and a word.
         * Example: "5 g", "1 l", "10 p".
         *
         * @param subSubCategoryStr the sub-sub category string to be checked
         * @return true if the string matches the format of a sub-sub category, false otherwise
         */
        if (subSubCategoryStr.equals("")) {
            return false;
        }
        String[] parts = subSubCategoryStr.split(" ");
        double number;
        if (parts.length == 2) {
            try {
                number = Double.parseDouble(parts[0]);
                String word = parts[1];
                if (!word.matches("[a-zA-Z]+")) {
                    System.out.println("your product's subSubCategory does not match the format.");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("your product's subSubCategory does not match the format.");
                return false;
            }
        } else {
            System.out.println("your product's subSubCategory does not match the format.");
            return false;
        }
        return true;
    }

    boolean checkIfPositiveDoubleNumber(String number) {
        try {
            if (number.equals("")) {
                return false;
            }
            double d = Double.parseDouble(number);
            return d > 0.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


