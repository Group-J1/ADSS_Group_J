package GUI.storeGui.stockReport;
import GUI.storeGui.StoreManagerGUI;
import Stock.Business.StockReport;
import Stock.DataAccess.CategoryDAO;
import Stock.DataAccess.ProductDAO;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainStockReport extends JPanel {
    private StoreManagerGUI mainGUI;
    private JPanel mainPanel;
    private CategoryReport categoryReport;
    private SpecificProductReport specificProductReport;

    public MainStockReport(StoreManagerGUI storeManagerGUI) throws IOException {
        setLayout(new BorderLayout());
        this.mainGUI = storeManagerGUI;
        setLayout(new BorderLayout());
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
        JLabel titleLabel = new JLabel("<html>Welcome To Stock Report <br> Please select required report  :</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new GridLayout(2,3,35,35));

//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        // Create buttons
        JButton DamageReport = createButton("Damage Report", "/GUI/pictures/supply.JPG");
        JButton ShortagesReport = createButton("Shortages Report", "/GUI/pictures/stock-manager.jpg");
        JButton OrderReport = createButton("Order Report", "/GUI/pictures/stock-manager.jpg");
        JButton StockReport = createButton("Stock Report", "/GUI/pictures/supply.JPG");
        JButton CategoryReport = createButton("Category Report", "/GUI/pictures/stock-manager.jpg");
        JButton SpecificProduct = createButton("Product Report", "/GUI/pictures/stock-manager.jpg");

        // Add buttons to the button panel
//        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(DamageReport);
//        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(ShortagesReport);
//        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(OrderReport);
        buttonPanel.add(StockReport);
        buttonPanel.add(CategoryReport);
        buttonPanel.add(SpecificProduct);
//        buttonPanel.add(Box.createHorizontalGlue());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);

        // Add button panel to the main panel
        mainPanel.add(Box.createVerticalStrut(120)); // Adjust the spacing as needed
        mainPanel.add(buttonPanel,BorderLayout.CENTER);


        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);


        // Add action listeners
        DamageReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDamageReport();
            }
        });
        ShortagesReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    openShortagesReports();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        OrderReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openOrderReport();
            }
        });
        StockReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openStockReport();
            }
        });
        CategoryReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCategoryReport();
            }
        });
        SpecificProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSpecificProductReport();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.showDefaultPanelFromChild();
            }
        });

    }
    private JButton createButton(String text, String imagePath) throws IOException {
        // Create button panel
        int width = 120;
        int height = 120;
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setLayout(new BorderLayout());

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
        Font buttonFont = new Font("Tahoma", Font.BOLD, 14);
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

    private void openDamageReport() {
        //open new frame of this report
    }
    private void openShortagesReports() throws IOException {
        //open new frame if this report
    }
    private void openOrderReport()  {
        //open new frame if this report
    }
    private void openStockReport()  {
        //open new frame if this report
        ProductDAO productDAO = ProductDAO.getInstance();
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        StockReport stock = new StockReport(productDAO.getAllProducts(),categoryDAO.getAllTheCategories());
        JPanel stockRep = new JPanel();
        JTextArea textArea = new JTextArea(stock.toString());
        stockRep.add(textArea);
        JButton back = new JButton("Back");
        stockRep.add(back, BorderLayout.CENTER);
        stockRep.setVisible(true);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.showDefaultPanelFromChild();
            }
        });





        mainPanel.setVisible(false);


    }
    private void openCategoryReport()  {
        //new frame of categry name
        mainPanel.setVisible(false);

        if (categoryReport == null) {
            categoryReport = new CategoryReport(this);
            categoryReport.setPreferredSize(mainPanel.getSize());
            categoryReport.setMaximumSize(mainPanel.getMaximumSize());
            categoryReport.setMinimumSize(mainPanel.getMinimumSize());
            categoryReport.setSize(mainPanel.getSize());
        }

        add(categoryReport, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
        //open new frame if this report
    private void openSpecificProductReport()  {
        //new frame of product name
        //open new frame if this report
        mainPanel.setVisible(false);

        if (specificProductReport == null) {
            specificProductReport = new SpecificProductReport(this);
            specificProductReport.setPreferredSize(mainPanel.getSize());
            specificProductReport.setMaximumSize(mainPanel.getMaximumSize());
            specificProductReport.setMinimumSize(mainPanel.getMinimumSize());
            specificProductReport.setSize(mainPanel.getSize());
        }

        add(specificProductReport, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Add methods to open other screens and handle back button

    public void showDefaultPanelFromChild() {
        mainPanel.setVisible(true);
        removeCurrentChildPanel();
        revalidate();
        repaint();
    }

    private void removeCurrentChildPanel() {
        if (categoryReport != null && categoryReport.isShowing()) {
            remove(categoryReport);
        } else if (specificProductReport != null && specificProductReport.isShowing()) {
            remove(specificProductReport);
        }
    }
}


