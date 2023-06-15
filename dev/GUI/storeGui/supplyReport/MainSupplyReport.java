package GUI.storeGui.supplyReport;

import GUI.storeGui.StoreManagerGUI;
import Supplier_Module.Business.Managers.SupplyManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainSupplyReport extends JPanel {
    private StoreManagerGUI parent;
    private JPanel mainPanel;
    public MainSupplyReport(StoreManagerGUI storeManagerGUI){
        this.parent = storeManagerGUI;
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
        JLabel titleLabel = new JLabel("<html>Supplier Report <br></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout());


        // Create button panel
        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);
        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showDefaultPanelFromChild();
            }
        });

        JLabel enterNumberLabel = new JLabel("Please enter the supplier number you want to watch:");
        JTextField numberField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        mainPanel.add(enterNumberLabel);
        mainPanel.add(numberField);
        mainPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            String supplierNumber = numberField.getText();
            if (isExistSupplier(supplierNumber)) {
                int supID = Integer.parseInt(supplierNumber);

                // Get the supplier report
                String[] lines = SupplyManager.getSupply_manager().getSupplier(supID).getSupplierReport();

                // Create a new panel for the report
                //JPanel reportPanel = new JPanel(new BorderLayout());
                //reportPanel.setOpaque(false);

                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

                // Concatenate the lines with line breaks
                StringBuilder reportBuilder = new StringBuilder();
                int longestLineWidth = 0;
                for (String line : lines) {
                    reportBuilder.append(line).append("\n");
                    int lineWidth = SwingUtilities.computeStringWidth(textArea.getFontMetrics(textArea.getFont()), line);
                    longestLineWidth = Math.max(longestLineWidth, lineWidth);
                }
                String reportText = reportBuilder.toString();
                textArea.setText(reportText);
                textArea.setPreferredSize(new Dimension(longestLineWidth, textArea.getPreferredSize().height));
                textArea.setOpaque(false);
                // Create a scroll pane for the text area
                //JScrollPane scrollPane = new JScrollPane(textArea);

                //reportPanel.add(scrollPane, BorderLayout.CENTER);
                mainPanel.add(textArea, BorderLayout.CENTER);
                //reportPanel.setBackground(new Color(0, 0, 0, 0));

                // Remove the existing components and add the report panel
                //mainPanel.add(reportPanel, BorderLayout.CENTER);
                mainPanel.remove(enterNumberLabel);
                mainPanel.remove(submitButton);
                mainPanel.remove(numberField);
                mainPanel.revalidate();
                mainPanel.repaint();
            } else {

                JOptionPane.showMessageDialog(null, "Invalid supplier ID. Please try again.");
            }
        });



    }

    public boolean isPositiveInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        int number = Integer.parseInt(input);
        return number > 0;
    }

    public boolean isExistSupplier(String input)
    {
        if(!isPositiveInteger(input))
            return false;
        return SupplyManager.getSupply_manager().isExist(Integer.parseInt(input));
    }
}