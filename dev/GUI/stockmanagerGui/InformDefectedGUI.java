package GUI.stockmanagerGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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


        JLabel barcode = new JLabel("Barcode");
        Font subCategoryLabelFont = barcode.getFont();
        Font subCategoryLabelNewFont = subCategoryLabelFont.deriveFont(Font.PLAIN, 20);
        barcode.setFont(subCategoryLabelNewFont);

        JTextField barcodeTextField = new JTextField();
        Font subCategoryTextFieldFont = barcodeTextField.getFont();
        Font subCategoryTextFieldNewFont = subCategoryTextFieldFont.deriveFont(Font.PLAIN, 20);
        barcodeTextField.setFont(subCategoryTextFieldNewFont);


        JLabel reason = new JLabel("Reason");
        Font subSubCategoryLabelFont = reason.getFont();
        Font subSubCategoryLabelNewFont = subSubCategoryLabelFont.deriveFont(Font.PLAIN, 20);
        reason.setFont(subSubCategoryLabelNewFont);

        JTextField reasonTextField = new JTextField();
        Font subSubCategoryTextFieldFont = reasonTextField.getFont();
        Font subSubCategoryTextFieldNewFont = subSubCategoryTextFieldFont.deriveFont(Font.PLAIN, 20);
        reasonTextField.setFont(subSubCategoryTextFieldNewFont);




        JPanel inputPanel = new JPanel();
        int verticalGap = 40; // Set the desired vertical gap between rows
        int horizontalGap = 30;
        inputPanel.setLayout(new GridLayout(4, 2, horizontalGap, verticalGap));
        inputPanel.add(catalogNumber);
        inputPanel.add(catalogNumberTextField);
        inputPanel.add(barcode);
        inputPanel.add(barcodeTextField);
        inputPanel.add(reason);
        inputPanel.add(reasonTextField);

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
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

}
