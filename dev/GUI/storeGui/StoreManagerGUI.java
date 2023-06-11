package GUI.storeGui;

import GUI.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreManagerGUI extends JPanel {
    private MainGUI mainGUI;
    private JButton backButton;

    public StoreManagerGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        backButton = new JButton("Back");

        add(backButton, BorderLayout.NORTH);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.showMainPanel();
            }
        });
    }
}
