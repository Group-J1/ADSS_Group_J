package GUI.supplyGui;

import Supplier_Module.Business.Supplier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditSupplierCardGui extends JPanel {
    private EditSupplierPanel mainGUI;
    private Supplier supplier;
    private JPanel mainPanel;
    private ContactMamberGui contactMamberGui;

    public EditSupplierCardGui(EditSupplierPanel editSupplierPanel, Supplier supplier1) throws IOException {
        this.mainGUI = editSupplierPanel;
        this.supplier = supplier1;
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
//        mainPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new  BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Edit Supplier Card");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
//        mainPanel.add(titleLabel, BorderLayout.NORTH);
//        mainPanel.setLayout(new FlowLayout());

        JLabel addressLabel = new JLabel("Address");
        Font addressLabelFont = addressLabel.getFont();
        Font addressLabelNewFont = addressLabelFont.deriveFont(Font.PLAIN, 18);
        addressLabel.setFont(addressLabelNewFont);
        JTextField addressTextField = new JTextField();
        Font addressTextFieldFont = addressTextField.getFont();
        Font subCategoryTextFieldNewFont = addressTextFieldFont.deriveFont(Font.PLAIN, 18);
        addressTextField.setFont(subCategoryTextFieldNewFont);
        JButton updateAddressButton = new JButton("update");

        JLabel BankLabel = new JLabel("Bank Account");
        Font bankLabelFont = BankLabel.getFont();
        Font bankLabelNewFont = bankLabelFont.deriveFont(Font.PLAIN, 18);
        BankLabel.setFont(bankLabelNewFont);
        JTextField bankTextField = new JTextField();
        Font bankTextFieldFont = bankTextField.getFont();
        Font bankTextFieldNewFont = bankTextFieldFont.deriveFont(Font.PLAIN, 18);
        bankTextField.setFont(bankTextFieldNewFont);
        JButton updateBankButton = new JButton("update");

        JLabel paymentLabel = new JLabel("Payment Method");
        Font paymentLabelFont = paymentLabel.getFont();
        Font paymentLabelNewFont = paymentLabelFont.deriveFont(Font.PLAIN, 18);
        paymentLabel.setFont(paymentLabelNewFont);
        JTextField paymentTextField = new JTextField();
        Font paymentTextFieldFont = paymentTextField.getFont();
        Font paymentTextFieldNewFont = paymentTextFieldFont.deriveFont(Font.PLAIN, 18);
        paymentTextField.setFont(paymentTextFieldNewFont);
        JButton updatePaymentMethodButton = new JButton("update");



        JPanel inputPanel = new JPanel();
        int verticalGap = 15; // Set the desired vertical gap between rows
        int horizontalGap = 10;
        inputPanel.setLayout(new GridLayout(3, 3, horizontalGap, verticalGap));
        inputPanel.add(BankLabel);
        inputPanel.add(bankTextField);
        inputPanel.add(updateBankButton);

        inputPanel.add(addressLabel);
        inputPanel.add(addressTextField);
        inputPanel.add(updateAddressButton);

        inputPanel.add(paymentLabel);
        inputPanel.add(paymentTextField);
        inputPanel.add(updatePaymentMethodButton);

        inputPanel.setOpaque(false);

        JPanel inputWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputWrapperPanel.setOpaque(false);
        inputWrapperPanel.add(inputPanel);

//        mainPanel.add(inputWrapperPanel, BorderLayout.CENTER);

        // Create button panel
        JButton manageContactMembers = new JButton("Manage Contact Members");;
        JButton backButton = new JButton("Back");

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        backPanel.add(manageContactMembers);
        backPanel.add(Box.createVerticalStrut(25)); // Add vertical spacing between buttons
        backPanel.add(backButton);
        backPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        backPanel.setOpaque(false);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(Box.createVerticalGlue()); // Add empty space at the top
        buttonsPanel.add(inputWrapperPanel);
        buttonsPanel.add(Box.createVerticalStrut(25)); // Add vertical spacing between buttons
        buttonsPanel.add(backPanel);
        buttonsPanel.add(Box.createVerticalStrut(40));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.setOpaque(false);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
//        mainPanel.add(inputWrapperPanel,BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);




//         Add action listeners for the buttons
        manageContactMembers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    openEditMembersPanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.showDefaultPanelFromChild();
            }
        });
    }

    private void openEditMembersPanel() throws IOException {
        mainPanel.setVisible(false);

        if (contactMamberGui == null) {
            contactMamberGui = new ContactMamberGui(this, supplier);
            contactMamberGui.setPreferredSize(mainPanel.getSize());
            contactMamberGui.setMaximumSize(mainPanel.getMaximumSize());
            contactMamberGui.setMinimumSize(mainPanel.getMinimumSize());
            contactMamberGui.setSize(mainPanel.getSize());
        }

        add(contactMamberGui, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    public void showDefaultPanelFromChild() {
        mainPanel.setVisible(true);
        removeCurrentChildPanel();
        revalidate();
        repaint();
    }

    private void removeCurrentChildPanel() {
        if (contactMamberGui != null && contactMamberGui.isShowing()) {
            remove(contactMamberGui);
            contactMamberGui = null;
        }
    }
}
