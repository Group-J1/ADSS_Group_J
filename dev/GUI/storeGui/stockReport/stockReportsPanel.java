package GUI.storeGui.stockReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stockReportsPanel extends JPanel {

    private MainStockReport parent;
    private String report;

    public stockReportsPanel(MainStockReport parent, String report){
        this.parent = parent;
        this.report = report;


        JPanel stockRep = new JPanel();
        JTextArea textArea = new JTextArea(report);
        stockRep.add(textArea);
        JButton back = new JButton("Back");

        stockRep.add(back);

        parent.add(stockRep);
//        mainPanel.setVisible(false);
        stockRep.setVisible(true);
        back.setVisible(true);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               back.setVisible(false);
               stockRep.setVisible(false);
                parent.showDefaultPanelFromChild();
            }
        });
    }




}
