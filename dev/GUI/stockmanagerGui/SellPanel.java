package GUI.stockmanagerGui;

import Stock.Business.Product;
import Stock.Service.ProductService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SellPanel extends JPanel {


    public SellPanel(){

        setOpaque(true);

        JLabel catalogNumberLabel = new JLabel("Catalog Number");
        Font categoryLabelFont = catalogNumberLabel.getFont();
        Font catalogNumberLabelNewFont = categoryLabelFont.deriveFont(Font.PLAIN, 18);
        catalogNumberLabel.setFont(catalogNumberLabelNewFont);

        JTextField catalogNumberTextField = new JTextField();
        Font categoryTextFieldFont = catalogNumberTextField.getFont();
        Font catalogNumberTextFieldNewFont = categoryTextFieldFont.deriveFont(Font.PLAIN, 18);
        catalogNumberTextField.setFont(catalogNumberTextFieldNewFont);

        JLabel filler = new JLabel("");


        JLabel quantityLabel = new JLabel("Quantity");
        Font quantityLabelFont = quantityLabel.getFont();
        Font quantityLabelNewFont = quantityLabelFont.deriveFont(Font.PLAIN, 18);
        quantityLabel.setFont(quantityLabelNewFont);

        JTextField quantityTextField = new JTextField();
        Font quantityTextFieldFont = quantityTextField.getFont();
        Font quantityTextFieldNewFont = quantityTextFieldFont.deriveFont(Font.PLAIN, 18);
        quantityTextField.setFont(quantityTextFieldNewFont);

        JLabel invalidQuantityLabel = new JLabel("Invalid Quantity");
        Font invalidQuantityLabelFont = invalidQuantityLabel.getFont();
        Font invalidQuantityLabelNewFont = invalidQuantityLabelFont.deriveFont(Font.PLAIN, 18);
        invalidQuantityLabel.setFont(invalidQuantityLabelNewFont);
        invalidQuantityLabel.setForeground(Color.RED);

        JPanel inputPanel = new JPanel();
        int verticalGap = 15; // Set the desired vertical gap between rows
        int horizontalGap = 15;
        inputPanel.setLayout(new GridLayout(2, 3, horizontalGap, verticalGap));
        inputPanel.add(catalogNumberLabel);
        inputPanel.add(catalogNumberTextField);
        inputPanel.add(filler);
        filler.setVisible(false);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityTextField);
        inputPanel.add(invalidQuantityLabel);
        invalidQuantityLabel.setVisible(false);

        inputPanel.setOpaque(false);

        JPanel inputWrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputWrapperPanel.add(inputPanel);
        add(inputWrapperPanel, BorderLayout.WEST);

        inputWrapperPanel.setOpaque(false);

        // Create button panel
        JButton submitButton = new JButton("Submit");

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(submitButton);

        buttonsPanel.setOpaque(false);

        add(buttonsPanel, BorderLayout.SOUTH);



//        JLabel catalogNumber = new JLabel("Catalog Number");
//
//        JTextField catalogNumberLabel = new JTextField();
//        catalogNumberLabel.setColumns(15);
//
//        JLabel quantity = new JLabel("Quantity");
//        JTextField quantityLabel = new JTextField();
//        catalogNumberLabel.setColumns(15);
//        JPanel texts = new JPanel();
//        texts.setLayout(new BoxLayout(texts, BoxLayout.Y_AXIS));
//        texts.setOpaque(false);
//
//        texts.add(createTextFieldPanel(catalogNumber,catalogNumberLabel));
//        texts.add(createTextFieldPanel(quantity,quantityLabel));
//        add(texts,BorderLayout.CENTER);
//
//        try {
//            JButton submit = createButton("Submit", "/GUI/pictures/stock-manager.jpg");
//            add(submit,BorderLayout.SOUTH);
//
//
//
//        setVisible(false);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle submit button action
                ProductService productService = ProductService.getInstance();
                LocalDate localDate = LocalDate.now();
                ArrayList<JLabel> inputsArrayList = new ArrayList<>();
                inputsArrayList.add(invalidQuantityLabel);
                ArrayList<Boolean> inputChecks = new ArrayList<>();

//                String categoryStr = categoryTextField.getText();
//                inputChecks.add(checkIfOnlyLetters(categoryStr));
//                String subCategoryStr = subCategoryTextField.getText();
//                inputChecks.add(checkSubCategory(subCategoryStr));
//                String subSubCategoryStr = subSubCategoryTextField.getText();
//                inputChecks.add(checkSubSubCategory(subSubCategoryStr));
//                String manufacturerStr = manufacturerTextField.getText();
//                inputChecks.add(checkIfOnlyLetters(manufacturerStr));
                String quantityStr = quantityTextField.getText();
                inputChecks.add(!quantityStr.equals("") && checkIfPositiveIntegerNumber(quantityStr));
//                String weightStr = weightTextField.getText();
//                inputChecks.add(checkIfPositiveDoubleNumber(weightStr));
//                String minQuantityStr = minQuantityTextField.getText();
//                inputChecks.add(checkIfPositiveIntegerNumber(minQuantityStr));
//                String dateStr = dateTextField.getText();
//                Date expirationDate = dateInput(dateStr);
//                inputChecks.add(expirationDate != null);

                boolean allTrue = !inputChecks.contains(Boolean.FALSE);

                if (allTrue) {
                    for (JLabel currentInput : inputsArrayList) {
                        currentInput.setVisible(false);
                    }
                    Product product = productService.getProductByUniqueCode(catalogNumberTextField.getText());
                    if (product!= null) {
                        if (productService.sellProductsByUniqueCode(product,Integer.parseInt(quantityTextField.getText()),LocalDate.now())) {
                            catalogNumberTextField.setText("");
                            quantityTextField.setText("");
                            JOptionPane.showMessageDialog(null, quantityTextField.getText() + " " + product.getName() + " sold");
                        }else {
                            JOptionPane.showMessageDialog(null, "There are not enough " + product.getName());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "The product was not found");
                    }
                } else {
                    int index = 0;
                    for (boolean currentInputValid : inputChecks) {
                        // Perform operations on the 'element' using the index 'index'
                        if (!currentInputValid) {
                            inputsArrayList.get(index).setVisible(true);
                        } else {
                            inputsArrayList.get(index).setVisible(false);
                        }
                        index++;
                    }
                }
            }
        });


    }
    private Boolean checkIfPositiveIntegerNumber(String number) {
        if(number.equals("")){
            return false;
        }
        return number.matches("[0-9]+") && Integer.parseInt(number) > 0;
    }
    private JPanel createTextFieldPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    private JButton createButton(String text, String imagePath) throws IOException {
        // Create button panel
        int width = 100;
        int height = 100;
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setLayout(new BorderLayout());
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove label margin

        // Create image label
        JLabel imageLabel = new JLabel();
        Image image = ImageIO.read(getClass().getResource(imagePath));
        Image small_image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(small_image);
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0,0,width,height);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(imageLabel, BorderLayout.CENTER);

        // Create text label
        Font buttonFont = new Font("Tahoma", Font.BOLD, 12);
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(buttonFont);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(textLabel, BorderLayout.SOUTH);

        // Create button
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.add(buttonPanel, BorderLayout.CENTER);
        button.setFocusPainted(false);
        button.setVerticalAlignment(SwingConstants.TOP); // Adjust vertical alignment
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Adjust vertical text position
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Adjust horizontal text position
        button.setMargin(new Insets(0, 0, 0, 0)); // Set the margin to zer


        return button;

    }

}
