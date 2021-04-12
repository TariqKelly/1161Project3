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
import java.util.Collection;
import java.util.Collections;

public class PromoterRemove extends JFrame{
    private JTextField txtID;

    private JButton cmdRemove;
    private JButton cmdClearAll;
    private JButton cmdClose;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    PromoterListing pl;
    PromoterRemove pr;

    public PromoterRemove(PromoterListing pl) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            // TODO: handle exception
        }

        this.pl = pl;
        pr = this;
        setTitle("Removing Promoter");

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Input ID of promoter you want to remove."));
        pnlDisplay.add(new JLabel("ID:"));
        txtID = new JTextField(8);
        pnlDisplay.add(txtID);
        pnlDisplay.setLayout(new GridLayout(4, 6));

        cmdRemove = new JButton("Remove");
        cmdClearAll = new JButton("Clear All");
        cmdClose = new JButton("Close");

        pnlCommand.add(cmdRemove);
        pnlCommand.add(cmdClearAll);
        pnlCommand.add(cmdClose);

        cmdClose.addActionListener(new CloseButtonListener());
        cmdRemove.addActionListener(new RemovePromoterListener());

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

    private class RemovePromoterListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int foundIndex = -1;
            
            for (Promoter promoter : pl.plist) {
                if (promoter.getId() == Integer.parseInt(txtID.getText())) {
                    foundIndex = pl.plist.indexOf(promoter);
                }
            }

            String promName = pl.plist.get(foundIndex).getName();
            String promBudget = String.valueOf(pl.plist.get(foundIndex).getBudget());
            String removeString = promName + " " + promBudget;

            pl.plist.remove(foundIndex);
            System.out.println("Removing " + removeString);
            pl.removeLineFromFile(pl.pfile, removeString);
            pl.model.setRowCount(0);
            pl.showTable(pl.plist);
            setVisible(false);
        }
        
    }

}
