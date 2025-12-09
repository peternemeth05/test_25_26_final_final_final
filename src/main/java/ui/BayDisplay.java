package ui;

import hospital.Bay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BayDisplay extends JPanel {

    private ArrayList<Bay> bayList;
    private ArrayList<JLabel> bayLabels;


    public BayDisplay(ArrayList<Bay> bayList) {

        this.bayList = bayList;
        this.bayLabels = new ArrayList<>();


        this.setLayout(new GridLayout(2,4));
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        for (Bay bay : bayList) {
            JLabel label = new JLabel();
            String line = "<html>" + bay.getBayName() + bay.getBayServiceLabel() + "</html>";
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            label.setText(line);
            bayLabels.add(label);
            this.add(label);
        }
    }

    public void refreshDisplay(){
        for (int i = 0; i < bayList.size(); i++) {
            Bay bay = bayList.get(i);
            JLabel label = bayLabels.get(i);

            // 1. update text
            label.setText("<html>" + bay.getBayName() + bay.getBayServiceLabel() + "</html>");
            label.setOpaque(true);
        }
        repaint();
        revalidate();
    }

}
