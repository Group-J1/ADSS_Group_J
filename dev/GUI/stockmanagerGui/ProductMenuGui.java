package GUI.stockmanagerGui;

import GUI.storeGui.stockReport.SpecificProductReport;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProductMenuGui extends JPanel{
    private StockManagement parent;
    private AddNewProductGUI addNewProductGUI;
    private UpdateQuantityGUI updateQuantityGUI;
    private InformDefectedGUI informDefectedGUI;
    private ChangeMinQuantityGUI changeMinQuantityGUI;

    private ProductInformation productInformation;
    private JPanel mainPanel;

    public ProductMenuGui(StockManagement parent){
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
        JLabel titleLabel = new JLabel("<html>Welcome to product menu <br> Please select option :</html>");
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
            JButton updateQuantity = createButton("Update Quantity", "/GUI/pictures/stock-manager.jpg");
            JButton informDefected = createButton("Inform Defected Product", "/GUI/pictures/order_manager.jpg");
            JButton productInformation = createButton("Product Information", "/GUI/pictures/order_manager.jpg");
            JButton addNewProduct = createButton("Add New Product", "/GUI/pictures/order_manager.jpg");
            JButton changeMin = createButton("Change Min Quantity", "/GUI/pictures/order_manager.jpg");




        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addNewProduct);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(updateQuantity);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(informDefected);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(productInformation);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(changeMin);
        buttonPanel.add(Box.createHorizontalGlue());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        // Add button panel to the main panel
        mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
        mainPanel.add(buttonPanel,BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);


        add(mainPanel);
        mainPanel.setVisible(true);

        addNewProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                openAddNewProduct();
            }
        });

        updateQuantity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                openUpdateQuantity();
            }

        });

        informDefected.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                openInformDefectedProduct();
            }
        });

        productInformation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                openProductInformation();
            }
        });

        changeMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                openChangeMinQuantity();

            }
        });



        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                parent.showMainPanel();
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
        if (addNewProductGUI != null && addNewProductGUI.isShowing()) {
            remove(addNewProductGUI);
        }
        if (updateQuantityGUI != null && updateQuantityGUI.isShowing()) {
            remove(updateQuantityGUI);
        }
        if (informDefectedGUI != null && informDefectedGUI.isShowing()) {
            remove(informDefectedGUI);
        }
        if(productInformation != null && productInformation.isShowing()){
          remove(productInformation);
        }
        if(changeMinQuantityGUI != null && changeMinQuantityGUI.isShowing()){
          remove(changeMinQuantityGUI);
        }

    }

    public void openAddNewProduct(){
        mainPanel.setVisible(false);

        if (addNewProductGUI == null) {
            addNewProductGUI = new AddNewProductGUI(this);
            addNewProductGUI.setPreferredSize(mainPanel.getSize());
            addNewProductGUI.setMaximumSize(mainPanel.getMaximumSize());
            addNewProductGUI.setMinimumSize(mainPanel.getMinimumSize());
            addNewProductGUI.setSize(mainPanel.getSize());
        }
        addNewProductGUI.setVisible(true);
        add(addNewProductGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void openUpdateQuantity(){
        mainPanel.setVisible(false);

        if (updateQuantityGUI == null) {
            updateQuantityGUI = new UpdateQuantityGUI(this);
            updateQuantityGUI.setPreferredSize(mainPanel.getSize());
            updateQuantityGUI.setMaximumSize(mainPanel.getMaximumSize());
            updateQuantityGUI.setMinimumSize(mainPanel.getMinimumSize());
            updateQuantityGUI.setSize(mainPanel.getSize());
        }
        updateQuantityGUI.setVisible(true);
        add(updateQuantityGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void openInformDefectedProduct(){
        mainPanel.setVisible(false);

        if (informDefectedGUI == null) {
            informDefectedGUI = new InformDefectedGUI(this);
            informDefectedGUI.setPreferredSize(mainPanel.getSize());
            informDefectedGUI.setMaximumSize(mainPanel.getMaximumSize());
            informDefectedGUI.setMinimumSize(mainPanel.getMinimumSize());
            informDefectedGUI.setSize(mainPanel.getSize());
        }
        informDefectedGUI.setVisible(true);
        add(informDefectedGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void openProductInformation(){
        mainPanel.setVisible(false);

        if (productInformation == null) {
            productInformation = new ProductInformation(this);
            productInformation.setPreferredSize(mainPanel.getSize());
            productInformation.setMaximumSize(mainPanel.getMaximumSize());
            productInformation.setMinimumSize(mainPanel.getMinimumSize());
            productInformation.setSize(mainPanel.getSize());
        }
        productInformation.setVisible(true);
        add(productInformation, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void openChangeMinQuantity(){
        mainPanel.setVisible(false);

        if (changeMinQuantityGUI == null) {
            changeMinQuantityGUI = new ChangeMinQuantityGUI(this);
            changeMinQuantityGUI.setPreferredSize(mainPanel.getSize());
            changeMinQuantityGUI.setMaximumSize(mainPanel.getMaximumSize());
            changeMinQuantityGUI.setMinimumSize(mainPanel.getMinimumSize());
            changeMinQuantityGUI.setSize(mainPanel.getSize());
        }
        changeMinQuantityGUI.setVisible(true);
        add(changeMinQuantityGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }



}



