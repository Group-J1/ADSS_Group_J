package GUI.stockmanagerGui;

import Stock.Service.MarketService;
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

public class DiscountByCategoryGUI extends JPanel {

    private JPanel mainPanel;
    private MarketMenuGui parent;

    public DiscountByCategoryGUI(MarketMenuGui parent) {
        this.parent = parent;
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
        JLabel titleLabel = new JLabel("<html>Discount By Category <br><br> </html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);


        // Create text fields
        JLabel categoryLabel = new JLabel("Category");
        Font categoryLabelFont = categoryLabel.getFont();
        Font categoryLabelNewFont = categoryLabelFont.deriveFont(Font.PLAIN, 18);
        categoryLabel.setFont(categoryLabelNewFont);

        JTextField categoryTextField = new JTextField();
        Font categoryTextFieldFont = categoryTextField.getFont();
        Font categoryTextFieldNewFont = categoryTextFieldFont.deriveFont(Font.PLAIN, 18);
        categoryTextField.setFont(categoryTextFieldNewFont);

        JLabel invalidCategoryLabel = new JLabel("Invalid Category");
        Font invalidCategoryLabelFont = invalidCategoryLabel.getFont();
        Font invalidCategoryLabelNewFont = invalidCategoryLabelFont.deriveFont(Font.PLAIN, 18);
        invalidCategoryLabel.setFont(invalidCategoryLabelNewFont);
        invalidCategoryLabel.setForeground(Color.RED);



        JLabel subCategoryLabel = new JLabel("Sub Category");
        Font subCategoryLabelFont = subCategoryLabel.getFont();
        Font subCategoryLabelNewFont = subCategoryLabelFont.deriveFont(Font.PLAIN, 18);
        subCategoryLabel.setFont(subCategoryLabelNewFont);

        JTextField subCategoryTextField = new JTextField();
        Font subCategoryTextFieldFont = subCategoryTextField.getFont();
        Font subCategoryTextFieldNewFont = subCategoryTextFieldFont.deriveFont(Font.PLAIN, 18);
        subCategoryTextField.setFont(subCategoryTextFieldNewFont);

        JLabel invalidSubCategoryLabel = new JLabel("Invalid Sub Category");
        Font invalidSubCategoryLabelFont =  invalidSubCategoryLabel.getFont();
        Font invalidSubCategoryLabelNewFont = invalidSubCategoryLabelFont.deriveFont(Font.PLAIN, 18);
        invalidSubCategoryLabel.setFont(invalidSubCategoryLabelNewFont);
        invalidSubCategoryLabel.setForeground(Color.RED);


        JLabel subSubCategoryLabel = new JLabel("Sub Sub Category");
        Font subSubCategoryLabelFont = subSubCategoryLabel.getFont();
        Font subSubCategoryLabelNewFont = subSubCategoryLabelFont.deriveFont(Font.PLAIN, 18);
        subSubCategoryLabel.setFont(subSubCategoryLabelNewFont);

        JTextField subSubCategoryTextField = new JTextField();
        Font subSubCategoryTextFieldFont = subSubCategoryTextField.getFont();
        Font subSubCategoryTextFieldNewFont = subSubCategoryTextFieldFont.deriveFont(Font.PLAIN, 18);
        subSubCategoryTextField.setFont(subSubCategoryTextFieldNewFont);

        JLabel invalidSubSubCategoryLabel = new JLabel("Invalid Sub Sub Category");
        Font invalidSubSubCategoryLabelFont = invalidSubSubCategoryLabel.getFont();
        Font invalidSubSubCategoryLabelNewFont = invalidSubSubCategoryLabelFont.deriveFont(Font.PLAIN, 18);
        invalidSubSubCategoryLabel.setFont(invalidSubSubCategoryLabelNewFont);
        invalidSubSubCategoryLabel.setForeground(Color.RED);


        JLabel discountLabel = new JLabel("Discount");
        Font discountLabelFont = discountLabel.getFont();
        Font discountLabelNewFont = discountLabelFont.deriveFont(Font.PLAIN, 18);
        discountLabel.setFont(discountLabelNewFont);

        JTextField discountTextField = new JTextField();
        Font discountTextFieldFont = discountTextField.getFont();
        Font discountTextFieldNewFont = discountTextFieldFont.deriveFont(Font.PLAIN, 18);
        discountTextField.setFont(discountTextFieldNewFont);

        JLabel invalidDiscountLabel = new JLabel("Invalid Sub Sub Category");
        Font invalidDiscountLabelFont = invalidDiscountLabel.getFont();
        Font invalidDiscountLabelNewFont = invalidDiscountLabelFont.deriveFont(Font.PLAIN, 18);
        invalidDiscountLabel.setFont(invalidDiscountLabelNewFont);
        invalidDiscountLabel.setForeground(Color.RED);


        JPanel inputPanel = new JPanel();
        int verticalGap = 35; // Set the desired vertical gap between rows
        int horizontalGap = 15;
        inputPanel.setLayout(new GridLayout(4, 3, horizontalGap, verticalGap));
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryTextField);
        inputPanel.add(invalidCategoryLabel);
        invalidCategoryLabel.setVisible(false);
        inputPanel.add(subCategoryLabel);
        inputPanel.add(subCategoryTextField);
        inputPanel.add(invalidSubCategoryLabel);
        invalidSubCategoryLabel.setVisible(false);
        inputPanel.add(subSubCategoryLabel);
        inputPanel.add(subSubCategoryTextField);
        inputPanel.add(invalidSubSubCategoryLabel);
        invalidSubSubCategoryLabel.setVisible(false);
        inputPanel.add(discountLabel);
        inputPanel.add(discountTextField);
        inputPanel.add(invalidDiscountLabel);
        invalidDiscountLabel.setVisible(false);


        inputPanel.setOpaque(false);

//        mainPanel.add(inputPanel, BorderLayout.CENTER);
        JPanel inputWrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputWrapperPanel.add(inputPanel);
        mainPanel.add(inputWrapperPanel, BorderLayout.WEST);

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

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle submit button action
                MarketService marketService = MarketService.getInstance();
                ArrayList<JLabel> inputsArrayList = new ArrayList<>(Arrays.asList(
                        invalidCategoryLabel, invalidSubCategoryLabel, invalidSubSubCategoryLabel, invalidDiscountLabel));
                ArrayList<Boolean> inputChecks = new ArrayList<>();

                String categoryStr = categoryTextField.getText();
                inputChecks.add(checkIfOnlyLetters(categoryStr));
                String subCategoryStr = subCategoryTextField.getText();
                inputChecks.add(checkSubCategory(subCategoryStr));
                String subSubCategoryStr = subSubCategoryTextField.getText();
                inputChecks.add(checkSubSubCategory(subSubCategoryStr));
                String discountStr = discountTextField.getText();
                inputChecks.add(checkIfPositiveDoubleNumber(discountStr));

                boolean allTrue = !inputChecks.contains(Boolean.FALSE);

                if (allTrue) {
                    for (JLabel currentInput: inputsArrayList) {
                        currentInput.setVisible(false);
                    }
                    if (marketService.setDiscountForProduct(categoryStr,subCategoryStr,subSubCategoryStr,
                            Double.parseDouble(discountStr))) {
                        categoryTextField.setText("");
                        subCategoryTextField.setText("");
                        subSubCategoryTextField.setText("");
                        discountTextField.setText("");
                        JOptionPane.showMessageDialog(null,"Discount updated");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Product Not Found");
                    }
                }
                else {
                    int index = 0;
                    for (boolean currentInputValid : inputChecks) {
                        // Perform operations on the 'element' using the index 'index'
                        if (!currentInputValid) {
                            inputsArrayList.get(index).setVisible(true);
                        }
                        else {
                            inputsArrayList.get(index).setVisible(false);
                        }
                        index++;
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invalidCategoryLabel.setVisible(false);
                invalidSubCategoryLabel.setVisible(false);
                invalidSubSubCategoryLabel.setVisible(false);
                invalidDiscountLabel.setVisible(false);
                parent.showDefaultPanelFromChild();
            }
        });

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
        imageLabel.setBounds(0, 0, width, height);
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

    public void showDefaultPanelFromChild() {
        mainPanel.setVisible(true);
        removeCurrentChildPanel();
        revalidate();
        repaint();
    }

    private void removeCurrentChildPanel() {

    }

    private JPanel createTextFieldPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    // All the functions that check if the input is valid
    Boolean checkIfOnlyLetters(String str) {
        if(str.equals("")){
            return false;
        }
        return str.matches("[a-zA-Z' ]+");
    }
    Boolean checkSubCategory(String subCategoryStr) {
        if(subCategoryStr.equals("")){
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
        if(subSubCategoryStr.equals("")){
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
            if(number.equals("")){
                return false;
            }
            double d = Double.parseDouble(number);
            return d > 0.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}