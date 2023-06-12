package GUI.storeGui.stockReport;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryReport extends JPanel {
    private MainStockReport parent;

    public CategoryReport(MainStockReport mainStockReport){
        this.parent = mainStockReport;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Category Report");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel category_report_ = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel enterNameLabel = new JLabel("Please enter the category name you want to get details on:");
        JTextField nameField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        category_report_.add(enterNameLabel);
        category_report_.add(nameField);
        category_report_.add(submitButton);

        add(category_report_, BorderLayout.CENTER);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            String category_name = nameField.getText();
            // Perform
            // Refresh the panel to show the confirmation panel
            revalidate();
            repaint();
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.WEST);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.showDefaultPanelFromChild();
            }
        });

    }
}




