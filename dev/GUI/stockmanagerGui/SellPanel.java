package GUI.stockmanagerGui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SellPanel extends JPanel {


    public SellPanel(){

        setOpaque(true);

        JLabel catalogNumber = new JLabel("Catalog Number");

        JTextField catalogNumberLabel = new JTextField();
        catalogNumberLabel.setColumns(15);

        JLabel quantity = new JLabel("Quantity");
        JTextField quantityLabel = new JTextField();
        catalogNumberLabel.setColumns(15);
        JPanel texts = new JPanel();
        texts.setLayout(new BoxLayout(texts, BoxLayout.Y_AXIS));
        texts.setOpaque(false);

        texts.add(createTextFieldPanel(catalogNumber,catalogNumberLabel));
        texts.add(createTextFieldPanel(quantity,quantityLabel));
        add(texts,BorderLayout.CENTER);

        try {
            JButton submit = createButton("Submit", "/GUI/pictures/stock-manager.jpg");
            add(submit,BorderLayout.SOUTH);



        setVisible(false);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // input check


                // use the sell function


            }
        });
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

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
