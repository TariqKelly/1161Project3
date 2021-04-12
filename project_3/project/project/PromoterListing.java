package project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;


public class PromoterListing extends JPanel {
    private JButton cmdAddPromoter;
    private JButton cmdRemovePromoter;
    private JButton cmdClose;
    private JButton cmdSortAge;
    private JButton cmdSortName;

    private JPopupMenu removeMenu;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    public ArrayList<Promoter> plist;
    private PromoterListing thisForm;
    private JScrollPane scrollPane;

    private JPanel pnlSearch;
    private JTextField searchText;
    private TableRowSorter sorter;


    public JTable table;
    public DefaultTableModel model;

    public File pfile = new File("project\\promoter.txt");


    public PromoterListing() {
        super(new GridLayout(3, 1));
        thisForm = this;

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlSearch = new JPanel();
        
        plist = loadPromoters("project\\promoter.txt");
        String[] columnNames = {"ID", "Name", "Budget", "Plans", "Events" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        showTable(plist);

        table.setPreferredScrollableViewportSize(new Dimension(500, plist.size() * 15 + 50));
        table.setFillsViewportHeight(true);

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        cmdAddPromoter = new JButton("Add Promoter");
        cmdRemovePromoter = new JButton("Remove Promoter");
        cmdSortName = new JButton("Sort by Name");
        cmdClose = new JButton("Close");

        removeMenu = new JPopupMenu("Menu");
        JMenuItem RemoveSelected = new JMenuItem("Remove Selected Promoter");
        JMenuItem RemoveByID = new JMenuItem("Remove Promoter By ID");

        removeMenu.add(RemoveSelected);
        removeMenu.add(RemoveByID);

        RemoveSelected.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int rowToRemove = table.getSelectedRow();
                Object idToRemove = table.getValueAt(rowToRemove, 0);
                Object promToRemove = table.getValueAt(rowToRemove, 1);
                Object budgetofRemoved = table.getValueAt(rowToRemove, 2);
                String removeString = promToRemove + " " + budgetofRemoved;
                System.out.println("Removing " + removeString);
                model.removeRow(rowToRemove);
                removeLineFromFile(pfile, String.valueOf(removeString));
                plist.remove(Integer.parseInt(String.valueOf(idToRemove)));
                System.out.println(plist);
                
            }
            
        });
        
        RemoveByID.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                PromoterRemove promoterRemove = new PromoterRemove(thisForm);
            }
            
        });

        cmdClose.addActionListener(new CloseButtonListener());
        cmdAddPromoter.addActionListener(new AddPromoterListener());
        cmdRemovePromoter.addActionListener(new RemovePromoterListener());
        cmdSortName.addActionListener(new SortNameListener());

       
        table.setShowVerticalLines(false);
        table.setFont(table.getFont().deriveFont(14f));
        table.setRowHeight(18);

        pnlCommand.add(cmdAddPromoter);
        pnlCommand.add(cmdRemovePromoter);
        pnlCommand.add(cmdSortName);
        pnlCommand.add(cmdClose);

        add(pnlCommand);

        searchText = new JTextField();
        pnlSearch.add(new JLabel("Search"));
        pnlSearch.add(searchText);

        pnlSearch.setSize(new Dimension(20,5));

        searchText.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                search(searchText.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                search(searchText.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                search(searchText.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
            
        });
        add(pnlSearch);
    }

    public void showTable(ArrayList<Promoter> plist) {
        if (plist.size() > 0) {
            for (Promoter Promoter : plist) {
                addToTable(Promoter);
            }
        }

    }

    private void addToTable(Promoter p) {
        String[] item = { String.valueOf(p.getId()), "" + p.getName(), "" + p.getBudget(), "" + 0, "" + 0, "" };
        model.addRow(item);
    }

    private void addToFile(Promoter p, File pfile) {
        try {
            FileWriter pWriter = new FileWriter(pfile, true);
            pWriter.write("\n" + p.getName() + " " + p.getBudget());
            pWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error accessing file");
            e.printStackTrace();
        }
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            //TODO: handle exception
            System.err.println("Failed to initialize LaF");
        }

        // Create and set up the window.
        JFrame frame = new JFrame("List of Promoters");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        PromoterListing newContentPane = new PromoterListing();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public void addPromoter(Promoter p) {
        System.out.println(p.toString());
        plist.add(p);
        addToTable(p);
        addToFile(p, pfile);

    }

    public void removeLineFromFile(File pfile, String lineToRemove) {
        //Credit to:
        // https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
        try {


            if (!pfile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            // Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(pfile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(pfile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            // Read from the original file and write to the new
            // unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            // Delete the original file
            if (!pfile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(pfile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private ArrayList<Promoter> loadPromoters(String pfile) {
        Scanner pscan = null;
        ArrayList<Promoter> plist = new ArrayList<Promoter>();
        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0];
                double budget = Double.parseDouble(nextLine[1]);
                Promoter p = new Promoter(name, budget, new Ministry("min",1), new ArrayList<Venue>());
                plist.add(p);
            }

            pscan.close();
        } catch (IOException e) {
            System.out.println("Error loading file");
            System.out.println(e);
        }
        return plist;
    }

    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    private class AddPromoterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PromoterEntry PromoterEntry = new PromoterEntry(thisForm);
        }
    }

    private class RemovePromoterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            removeMenu.show(pnlCommand, (int)cmdRemovePromoter.getLocation().getX(), (int)cmdRemovePromoter.getLocation().getY()+10);
            
        }
    }

    private class SortNameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            model.setRowCount(0);
            Collections.sort(plist);
            showTable(plist);
        }

    }

}