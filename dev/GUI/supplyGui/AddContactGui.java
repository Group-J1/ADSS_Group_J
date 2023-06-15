package GUI.supplyGui;

import Supplier_Module.Business.Card.SupplierCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddContactGui extends JPanel {
    private ContactMamberGui parent;

    private JPanel mainPanel;
    private SupplierCard supplierCard;
    public AddContactGui(ContactMamberGui contactMamberGui, SupplierCard supplierCard1){
        this.parent =contactMamberGui;
        this.supplierCard = supplierCard1;
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
        JLabel titleLabel = new JLabel("Add Supplier");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);


        // Create text fields
        JLabel nameLabel = new JLabel("Name:");
        Font nameLabelFont = nameLabel.getFont();
        Font nameLabelNewFont = nameLabelFont.deriveFont(Font.BOLD, 14);
        nameLabel.setFont(nameLabelNewFont);

        JTextField nameLabelTextField = new JTextField();
        Font nameLabelTextFieldFont =nameLabelTextField.getFont();
        Font nameTextFieldNewFont = nameLabelTextFieldFont.deriveFont(Font.BOLD, 14);
        nameLabelTextField.setFont(nameTextFieldNewFont);

        JLabel invalidNameLabel = new JLabel("Invalid Name");
        Font invalidNameLabelFont = invalidNameLabel.getFont();
        Font invalidNameLabelNewFont = invalidNameLabelFont.deriveFont(Font.PLAIN, 18);
        invalidNameLabel.setFont(invalidNameLabelNewFont);
        invalidNameLabel.setForeground(Color.RED);



        JLabel mailLabel = new JLabel("Mail");
        Font mailLabelFont = mailLabel.getFont();
        Font mailLabelNewFont = mailLabelFont.deriveFont(Font.PLAIN, 18);
        mailLabel.setFont(mailLabelNewFont);

        JTextField mailTextField = new JTextField();
        Font mailTextFieldFont = mailTextField.getFont();
        Font mailTextFieldNewFont = mailTextFieldFont.deriveFont(Font.PLAIN, 18);
        mailTextField.setFont(mailTextFieldNewFont);

        JLabel invalidmailLabel = new JLabel("Invalid Address");
        Font invalidmailLabelFont =  invalidmailLabel.getFont();
        Font invalidmailLabelNewFont = invalidmailLabelFont.deriveFont(Font.PLAIN, 18);
        invalidmailLabel.setFont(invalidmailLabelNewFont);
        invalidmailLabel.setForeground(Color.RED);


        JLabel idLabel = new JLabel("ID");
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



        JPanel inputPanel = new JPanel();
        int verticalGap = 25; // Set the desired vertical gap between rows
        int horizontalGap = 15;
        inputPanel.setLayout(new GridLayout(3, 3, horizontalGap, verticalGap));
        inputPanel.add(nameLabel);
        inputPanel.add(nameLabelTextField);
        inputPanel.add(invalidNameLabel);
        invalidNameLabel.setVisible(false);
        inputPanel.add(mailLabel);
        inputPanel.add(mailTextField);
        inputPanel.add(invalidmailLabel);
        invalidmailLabel.setVisible(false);
        inputPanel.add(idLabel);
        inputPanel.add(idTextField);
        inputPanel.add(invalidIDLabel);
        invalidIDLabel.setVisible(false);

        inputPanel.setOpaque(false);

//        mainPanel.add(inputPanel, BorderLayout.CENTER);
        JPanel inputWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputWrapperPanel.add(inputPanel);
        mainPanel.add(inputWrapperPanel, BorderLayout.CENTER);

        inputWrapperPanel.setOpaque(false);

        // Create button panel
        JButton submitButton = new JButton("Submit");
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
                invalidNameLabel.setVisible(false);
                invalidmailLabel.setVisible(false);
                invalidIDLabel.setVisible(false);

                nameLabelTextField.setText("");
                mailTextField.setText("");
                idTextField.setText("");

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

