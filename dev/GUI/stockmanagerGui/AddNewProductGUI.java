package GUI.stockmanagerGui;

import Stock.Service.ProductService;
import Supplier_Module.Service.SupplierService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AddNewProductGUI extends JPanel{

    private JPanel mainPanel;
    private ProductMenuGui parent;

    public AddNewProductGUI(ProductMenuGui parent){
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
        mainPanel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("<html>Welcome to Order Management <br> Please select option :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());


        // Create button panel
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        // Create buttons
        try {

            JPanel texts = new JPanel();
            texts.setLayout(new BoxLayout(texts, BoxLayout.Y_AXIS));

            JLabel catLabel = new JLabel("Category");
            JLabel subCatLabel = new JLabel("Sub Category");
            JLabel subSubCatLabel = new JLabel("Sub Sub Category");
            JLabel manuLabel = new JLabel("Manufacturer");
            JLabel quanLabel = new JLabel("Quantity");
            JLabel minQuanLabel = new JLabel("Minimum Quantity");
            JLabel weightLabel = new JLabel("Weight");
            JLabel dateLabel = new JLabel("Date  (DD/MM/YYYY)");

            JTextField category = new JTextField();
            category.setColumns(15);
            JTextField subCategory = new JTextField();
            subCategory.setColumns(15);
            JTextField subsubCategory = new JTextField();
            subsubCategory.setColumns(15);
            JTextField manufacturer = new JTextField();
            manufacturer.setColumns(15);
            JTextField quantity = new JTextField();
            quantity.setColumns(15);
            JTextField minimumQuantity = new JTextField();
            minimumQuantity.setColumns(15);
            JTextField weight = new JTextField();
            weight.setColumns(15);
            JTextField date = new JTextField();
            date.setColumns(15);
            texts.add(createTextFieldPanel(catLabel, category));
            texts.add(createTextFieldPanel(subCatLabel, subCategory));
            texts.add(createTextFieldPanel(subSubCatLabel, subsubCategory));
            texts.add(createTextFieldPanel(manuLabel, manufacturer));
            texts.add(createTextFieldPanel(quanLabel, quantity));
            texts.add(createTextFieldPanel(minQuanLabel, minimumQuantity));
            texts.add(createTextFieldPanel(weightLabel, weight));
            texts.add(createTextFieldPanel(dateLabel, date));
            texts.setVisible(true);
            mainPanel.add(texts);





            JButton submit = createButton("Submit", "/GUI/pictures/stock-manager.jpg");




            buttonPanel.add(Box.createHorizontalGlue());
            buttonPanel.add(submit);
            buttonPanel.add(Box.createHorizontalGlue());

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.add(backButton);

            // Add button panel to the main panel
            mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
            mainPanel.add(buttonPanel,BorderLayout.CENTER);


            mainPanel.add(Box.createVerticalStrut(200));
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);


            add(mainPanel, BorderLayout.CENTER);
            mainPanel.setVisible(true);


            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    ArrayList<Boolean> inputChecks = new ArrayList<>();
                    String categoryStr = category.getText();
//                    if (!checkIfOnlyLetters(categoryStr)) {
//                        System.out.println("your product's category is not a valid string ");
//                        return;
//                    }
                    inputChecks.add(checkIfOnlyLetters(categoryStr));
//                    System.out.println("Whats is your product's sub-category? ");
                    String subCategoryStr = subCategory.getText();
//                    if (!checkSubCategory(subCategoryStr)) {
//                        System.out.println("your product's subCategory is not a valid string ");
//                        return;
//                    }
                    inputChecks.add(checkSubCategory(subCategoryStr));
//                    System.out.println("Whats is your product's sub-sub-category, in <double string> format? ");
                    String subSubCategoryStr = subsubCategory.getText();
//                    if (!checkSubSubCategory(subSubCategoryStr)) {
//                        return;
//                    }
                    inputChecks.add(checkSubSubCategory(subSubCategoryStr));
//                    System.out.println("Whats is your product's manufacturer? ");
                    String manufacturerStr =  manufacturer.getText();
//                    if (!checkIfOnlyLetters(manufacturerStr)) {
//                        System.out.println("your product's manufacturer is not a valid string ");
//                        return;
//                    }
                    inputChecks.add(checkIfOnlyLetters(manufacturerStr));

//                    System.out.println("Whats is your product's quantity? ");
                    String quantityStr = quantity.getText();
//                    if (!checkIfPositiveIntegerNumber(quantityStr)) {
//                        System.out.println("your product's quantity is not a positive number ");
//                        return;
//                    }
                    inputChecks.add(!quantityStr.equals("") && checkIfPositiveIntegerNumber(quantityStr));

//                    System.out.println("Whats is your product's weight? ");
                    String weightStr = weight.getText();
//                    if (!checkIfPositiveDoubleNumber(weightStr)) {
//                        System.out.println("your product's weight is not a positive number ");
//                        return;
//                    }
                    inputChecks.add(checkIfPositiveDoubleNumber(weightStr));


//                    System.out.println("Whats is your product's minimum quantity? ");
                    String minQuantityStr = minimumQuantity.getText();
//                    if (!checkIfPositiveIntegerNumber(minQuantityStr)) {
//                        System.out.println("your product's minimum quantity is not a positive number ");
//                        return;
//                    }
                    inputChecks.add(checkIfPositiveIntegerNumber(minQuantityStr));

                    String dateStr = date.getText();
                    Date expirationDate = dateInput(dateStr);
//                    if (expirationDate == null) {
//                        return;
//                    }
                    inputChecks.add(expirationDate != null);
                    ProductService productService = ProductService.getInstance();
                    LocalDate localDate = LocalDate.now();
                    for(int i = 0 ; i < 8;i++){
                        if(!inputChecks.get(i)) {
                            JOptionPane.showMessageDialog(null, "there was a problem with the " + i + " input");
                            return;
                        }

                    }
                    if (!productService.addNewProduct(categoryStr, subCategoryStr, subSubCategoryStr, manufacturerStr,
                            Integer.parseInt(quantityStr), Integer.parseInt(minQuantityStr), Double.parseDouble(weightStr), expirationDate,localDate)) {
//                        System.out.println("The product already exist in stock! ");
                        //TODO: product already exist
                        JOptionPane.showMessageDialog(null,"The product already exist");


                    }
                    else {
//                        SupplierService.getSupplierService().updatePeriodOrders(ProductService.getInstance().sendToSupplierAllProductsQuantity(),localDate);
//                        System.out.println("Product added! ");
                        //TODO: product added
                        JOptionPane.showMessageDialog(null,"The new product added");


                    }
                }
            });



            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    parent.showDefaultPanelFromChild();
                }
            });
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }



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
    Boolean checkIfPositiveIntegerNumber(String number) {
        if(number.equals("")){
            return false;
        }
        return number.matches("[0-9]+") && Integer.parseInt(number) > 0;
    }
    Date dateInput(String dateStr) {
        /**
         * Prompts the user to enter a date in the format "dd/MM/yyyy" and returns it as a Date object.
         * If the date is invalid or in the past, it prints an error message and returns null.
         *
         * @return the date entered by the user as a Date object, or null if it is invalid or in the past.
         */
//        Scanner input = new Scanner(System.in);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//        System.out.println("Enter a expiration date in dd/MM/yyyy format:");
//        String dateStr = input.nextLine();
        if(dateStr.equals("")){
            return null;
        }
        String[] parts = dateStr.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (month < 1 || month > 12) {
            System.out.println("Invalid month");
            return null;
        }
        if (day < 1 || day > 31) {
            System.out.println("Invalid day");
            return null;
        }
        LocalDate date = LocalDate.of(year, month, day);
        if (date.isBefore(LocalDate.now())) {
            System.out.println("The date is not in the future");
            return null;
        }
        Date dateToReturn = Date.from(date.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        return dateToReturn;
    }


}
