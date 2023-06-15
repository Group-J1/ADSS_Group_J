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

public class InformDefectedGUI extends JPanel {

    private JPanel mainPanel;
    private ProductMenuGui parent;

    public InformDefectedGUI(ProductMenuGui parent) {
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
        JLabel titleLabel = new JLabel("<html>Inform Defected Product <br><br> </html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);


        // Create text fields
        JLabel catalogNumber = new JLabel("Catalog Number");
        Font categoryLabelFont = catalogNumber.getFont();
        Font categoryLabelNewFont = categoryLabelFont.deriveFont(Font.PLAIN, 20);
        catalogNumber.setFont(categoryLabelNewFont);

        JTextField catalogNumberTextField = new JTextField();
        Font categoryTextFieldFont = catalogNumberTextField.getFont();
        Font categoryTextFieldNewFont = categoryTextFieldFont.deriveFont(Font.PLAIN, 20);
        catalogNumberTextField.setFont(categoryTextFieldNewFont);

        JLabel invalidCatalogNumber = new JLabel("Invalid Catalog Number");
        Font invalidCatalogNumberFont = invalidCatalogNumber.getFont();
        Font invalidCatalogNumberNewFont = invalidCatalogNumberFont.deriveFont(Font.PLAIN, 18);
        invalidCatalogNumber.setFont(invalidCatalogNumberNewFont);
        invalidCatalogNumber.setForeground(Color.RED);



        JLabel barcode = new JLabel("Barcode");
        Font subCategoryLabelFont = barcode.getFont();
        Font subCategoryLabelNewFont = subCategoryLabelFont.deriveFont(Font.PLAIN, 20);
        barcode.setFont(subCategoryLabelNewFont);

        JTextField barcodeTextField = new JTextField();
        Font subCategoryTextFieldFont = barcodeTextField.getFont();
        Font subCategoryTextFieldNewFont = subCategoryTextFieldFont.deriveFont(Font.PLAIN, 20);
        barcodeTextField.setFont(subCategoryTextFieldNewFont);

        JLabel invalidBarcode = new JLabel("Invalid Barcode");
        Font invalidBarcodeFont = invalidCatalogNumber.getFont();
        Font invalidBarcodeNewFont = invalidBarcodeFont.deriveFont(Font.PLAIN, 18);
        invalidBarcode.setFont(invalidBarcodeNewFont);
        invalidBarcode.setForeground(Color.RED);



        JLabel reason = new JLabel("Reason");
        Font subSubCategoryLabelFont = reason.getFont();
        Font subSubCategoryLabelNewFont = subSubCategoryLabelFont.deriveFont(Font.PLAIN, 20);
        reason.setFont(subSubCategoryLabelNewFont);

        JTextField reasonTextField = new JTextField();
        Font subSubCategoryTextFieldFont = reasonTextField.getFont();
        Font subSubCategoryTextFieldNewFont = subSubCategoryTextFieldFont.deriveFont(Font.PLAIN, 20);
        reasonTextField.setFont(subSubCategoryTextFieldNewFont);

        JLabel invalidReason = new JLabel("Invalid Reason");
        Font invalidReasonFont = invalidCatalogNumber.getFont();
        Font invalidReasonNewFont = invalidReasonFont.deriveFont(Font.PLAIN, 18);
        invalidReason.setFont(invalidReasonNewFont);
        invalidReason.setForeground(Color.RED);





        JPanel inputPanel = new JPanel();
        int verticalGap = 40; // Set the desired vertical gap between rows
        int horizontalGap = 30;
        inputPanel.setLayout(new GridLayout(3, 3, horizontalGap, verticalGap));
        inputPanel.add(catalogNumber);
        inputPanel.add(catalogNumberTextField);
        inputPanel.add(invalidCatalogNumber);
        invalidCatalogNumber.setVisible(false);
        inputPanel.add(barcode);
        inputPanel.add(barcodeTextField);
        inputPanel.add(invalidBarcode);
        invalidBarcode.setVisible(false);
        inputPanel.add(reason);
        inputPanel.add(reasonTextField);
        inputPanel.add(invalidReason);
        invalidReason.setVisible(false);

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

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle submit button action
                ProductService productService = ProductService.getInstance();
                LocalDate localDate = LocalDate.now();
                ArrayList<JLabel> inputsArrayList = new ArrayList<>(Arrays.asList(
                        invalidCatalogNumber,invalidBarcode,invalidReason));
                ArrayList<Boolean> inputChecks = new ArrayList<>();

                String catalogNumberStr = catalogNumberTextField.getText();
                inputChecks.add(!catalogNumberStr.equals(""));
                String barcodeStr = barcodeTextField.getText();
                inputChecks.add(checkIfPositiveIntegerNumber(barcodeStr));
                String reasonStr = reasonTextField.getText();

                inputChecks.add(!reasonStr.equals(""));

                boolean allTrue = !inputChecks.contains(Boolean.FALSE);

                if (allTrue) {
                    for (JLabel currentInput : inputsArrayList) {
                        currentInput.setVisible(false);
                    }
                    Product product = productService.getProductByUniqueCode(catalogNumberTextField.getText());
                    if (product!= null) {
                        if(productService.markAsDamaged(product,Integer.parseInt(barcodeStr),reasonStr)) {
                            catalogNumberTextField.setText("");
                            barcodeTextField.setText("");
                            reasonTextField.setText("");
                            JOptionPane.showMessageDialog(null, "The new product added");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "the Barcode is does not belong to this Catalog Number!");
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

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invalidCatalogNumber.setVisible(false);
                invalidBarcode.setVisible(false);
                invalidReason.setVisible(false);


                catalogNumberTextField.setText("");
                barcode.setText("");
                reasonTextField.setText("");

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

    private Boolean checkIfPositiveIntegerNumber(String number) {
        if(number.equals("")){
            return false;
        }
        return number.matches("[0-9]+") && Integer.parseInt(number) > 0;
    }

}
