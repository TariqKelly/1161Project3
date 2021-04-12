package project;

import project.Promoter;
import project.Venue;
import project.Ministry;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import java.util.ArrayList;

public class PromoterEntry extends JFrame {
    private JTextField txtName; // name
    private JTextField txtBudget; // age
    private JButton cmdSave;
    private JButton cmdClose;
    private JButton cmdClearAll;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    PromoterListing pl;
    PromoterEntry pe;

    public PromoterEntry(PromoterListing pl) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());;
        } catch (Exception e) {
            // TODO: handle exception
        }

        this.pl = pl;
        pe = this;
        setTitle("Entering new Promoter");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Name:"));
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Budget:"));
        txtBudget = new JTextField(8);
        pnlDisplay.add(txtBudget);
        pnlDisplay.setLayout(new GridLayout(3, 4));

        cmdSave = new JButton("Save");
        cmdClose = new JButton("Close");
        cmdClose.addActionListener(new CloseButtonListener());
        cmdSave.addActionListener(new SaveButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

    private class SaveButtonListener implements ActionListener {
        private boolean isDouble = true;

        public void actionPerformed(ActionEvent e) {
            try {
                Double.parseDouble(txtBudget.getText());
            } catch (NumberFormatException nfe) {
                // TODO: handle exception
                System.out.println("Input entered is not a numerical value");
                isDouble = false;
            }

            
            if ((!txtName.getText().equals("")) && (isDouble)) {
                System.out.println("Creating Promoter...");
                pl.addPromoter(new Promoter(txtName.getText(), Double.parseDouble(txtBudget.getText()),new Ministry("min", 1), new ArrayList<Venue>()));
                setVisible(false);
            }

        }
    }

}