package GUI.stockmanagerGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MarketMenuGui extends JPanel{
    private StockManagement parent;

    private DiscountByCategoryGUI discountByCategoryGUI;
    private DiscountByCatalogNumberGUI discountByCatalogNumberGUI;
    private DiscountForCategoryGUI discountForCategoryGUI;
    private AddShelvesToMarketGUI addShelvesToMarketGUI;



    private JPanel mainPanel;

    public MarketMenuGui(StockManagement parent){
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
        JLabel titleLabel = new JLabel("<html>Welcome to Market Menu <br> Please select option :</html>");
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
            JButton discountByCategory = createButton("Discount By Category", "/GUI/pictures/stock-manager.jpg");
            JButton discountByCatalogNumber = createButton("Discount By Catalog Number", "/GUI/pictures/order_manager.jpg");
            JButton discountForCategory = createButton("Discount For Category", "/GUI/pictures/order_manager.jpg");
            JButton addShelves = createButton("Add Shelves", "/GUI/pictures/order_manager.jpg");




            buttonPanel.add(Box.createHorizontalGlue());
            buttonPanel.add(discountByCategory);
            buttonPanel.add(Box.createHorizontalStrut(20));
            buttonPanel.add(discountByCatalogNumber);
            buttonPanel.add(Box.createHorizontalStrut(20));
            buttonPanel.add(discountForCategory);
            buttonPanel.add(Box.createHorizontalStrut(20));
            buttonPanel.add(addShelves);
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

            discountByCategory.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                    openDiscountByCategory();
                }
            });


            discountByCatalogNumber.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                    openDiscountByCatalogNumber();
                }
            });

            discountForCategory.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                    openDiscountForCategory();
                }
            });

            addShelves.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                parent.showDefaultPanelFromChild();
                    openAddShelvesToMarket();

                }
            });


            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    parent.showMainPanel();
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
        if (discountByCategoryGUI != null && discountByCategoryGUI.isShowing()) {
            remove(discountByCategoryGUI);
        }
        else if (discountByCatalogNumberGUI != null && discountByCatalogNumberGUI.isShowing()) {
            remove(discountByCatalogNumberGUI);
        }
        else if (discountForCategoryGUI != null && discountForCategoryGUI.isShowing()) {
            remove(discountForCategoryGUI);
        }
        else if (addShelvesToMarketGUI != null && addShelvesToMarketGUI.isShowing()) {
            remove(addShelvesToMarketGUI);
        }
    }

    public void openDiscountByCategory(){
        mainPanel.setVisible(false);

        if (discountByCategoryGUI == null) {
            discountByCategoryGUI = new DiscountByCategoryGUI(this);
            discountByCategoryGUI.setPreferredSize(mainPanel.getSize());
            discountByCategoryGUI.setMaximumSize(mainPanel.getMaximumSize());
            discountByCategoryGUI.setMinimumSize(mainPanel.getMinimumSize());
            discountByCategoryGUI.setSize(mainPanel.getSize());
        }
        discountByCategoryGUI.setVisible(true);
        add(discountByCategoryGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void openDiscountByCatalogNumber(){
        mainPanel.setVisible(false);

        if (discountByCatalogNumberGUI == null) {
            discountByCatalogNumberGUI = new DiscountByCatalogNumberGUI(this);
            discountByCatalogNumberGUI.setPreferredSize(mainPanel.getSize());
            discountByCatalogNumberGUI.setMaximumSize(mainPanel.getMaximumSize());
            discountByCatalogNumberGUI.setMinimumSize(mainPanel.getMinimumSize());
            discountByCatalogNumberGUI.setSize(mainPanel.getSize());
        }
        discountByCatalogNumberGUI.setVisible(true);
        add(discountByCatalogNumberGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void openDiscountForCategory(){
        mainPanel.setVisible(false);

        if (discountForCategoryGUI == null) {
            discountForCategoryGUI = new DiscountForCategoryGUI(this);
            discountForCategoryGUI.setPreferredSize(mainPanel.getSize());
            discountForCategoryGUI.setMaximumSize(mainPanel.getMaximumSize());
            discountForCategoryGUI.setMinimumSize(mainPanel.getMinimumSize());
            discountForCategoryGUI.setSize(mainPanel.getSize());
        }
        discountForCategoryGUI.setVisible(true);
        add(discountForCategoryGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void openAddShelvesToMarket(){
        mainPanel.setVisible(false);

        if (addShelvesToMarketGUI == null) {
            addShelvesToMarketGUI = new AddShelvesToMarketGUI(this);
            addShelvesToMarketGUI.setPreferredSize(mainPanel.getSize());
            addShelvesToMarketGUI.setMaximumSize(mainPanel.getMaximumSize());
            addShelvesToMarketGUI.setMinimumSize(mainPanel.getMinimumSize());
            addShelvesToMarketGUI.setSize(mainPanel.getSize());
        }
        addShelvesToMarketGUI.setVisible(true);
        add(addShelvesToMarketGUI, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}



