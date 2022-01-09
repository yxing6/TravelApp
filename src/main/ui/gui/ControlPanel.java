package ui.gui;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* CPSC 210 Term Project Version 2:
 * Travel Record - GUI - ControlPanel
 * Author:  Yun Xing
 * Date:    January 08, 2022,
 * Note:    This class is adapted from project version 1 and CPSC 210 Lab 8 ResourceFinder
 *
 * This class handles all the user entry and user selection
 * Observer pattern is applied to reduce coupling.
 */
public class ControlPanel extends JPanel {

    private static final int GAP = 10;  // vertical spacing between components in pixels
    private static final String BUCKET_LIST = "bucket list";
    private static final String VISITED_LIST = "visited list";

    private StateListener selectionListener;
    private CustomizeJTextField nameEntry;
    private CustomizeJTextField latEntry;
    private CustomizeJTextField lonEntry;


    // EFFECTS: constructs panel for displaying user controls
    public ControlPanel(StateListener selectionListener) {

        this.selectionListener = selectionListener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(GAP * 4));
        addRadioButtons();
        add(Box.createVerticalStrut(GAP));
        addUserEntryFields();
        add(Box.createVerticalStrut(GAP));

    }


    // An inner class for formatting
    private static class CustomizeJTextField extends JTextField {
        private CustomizeJTextField() {
            super();
            //setMaximumSize(new Dimension(200, 30));
            setFont(new Font("Verdana", Font.PLAIN, 13));
        }
    }


    // MODIFIES: this
    // EFFECTS: adds radio buttons (visited list and bucket list) to control panel
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addRadioButtons() {
        Box radioButtonHolder = Box.createVerticalBox();
        radioButtonHolder.setAlignmentX(Box.CENTER_ALIGNMENT);

        JLabel label1 = new JLabel("  Welcome!");
        label1.setFont(new Font("Verdana", Font.BOLD, 18));
        JLabel label2 = new JLabel("  To Display places-");
        label2.setFont(new Font("Verdana", Font.PLAIN, 14));
        JLabel label3 = new JLabel("  Choose from: ");
        label3.setFont(new Font("Verdana", Font.PLAIN, 14));
        JRadioButton visited = createVisitedButton();
        JRadioButton bucket = createBucketButton();
        JRadioButton all = createAllButton();

        radioButtonHolder.add(label1);
        radioButtonHolder.add(Box.createVerticalStrut(GAP * 2));
        radioButtonHolder.add(label2);
        radioButtonHolder.add(Box.createVerticalStrut(5));
        radioButtonHolder.add(label3);
        radioButtonHolder.add(Box.createVerticalStrut(GAP));
        radioButtonHolder.add(visited);
        radioButtonHolder.add(Box.createVerticalStrut(GAP));
        radioButtonHolder.add(bucket);
        radioButtonHolder.add(Box.createVerticalStrut(GAP));
        radioButtonHolder.add(all);
        radioButtonHolder.add(Box.createVerticalStrut(GAP * 3));

        ButtonGroup radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(visited);
        radioBtnGroup.add(bucket);
        radioBtnGroup.add(all);

        add(radioButtonHolder);
    }


    // MODIFIES: this
    // EFFECTS: returns radio button for "visited places" option
    //          also calling update with a state of visited
    private JRadioButton createVisitedButton() {
        JRadioButton visited = new JRadioButton("Visited places", false);
        visited.setFont(new Font("Verdana", Font.PLAIN, 13));
        visited.addActionListener(e -> selectionListener.update(State.VISITED));
        return visited;
    }


    // MODIFIES: this
    // EFFECTS: returns radio button for "bucket places" option
    //          also calling update with a state of notVisited
    private JRadioButton createBucketButton() {
        JRadioButton bucket = new JRadioButton("Bucket places", false);
        bucket.setFont(new Font("Verdana", Font.PLAIN, 13));
        bucket.addActionListener(e -> selectionListener.update(State.NotVISITED));
        return bucket;
    }


    // MODIFIES: this
    // EFFECTS: returns radio button for "all places" option
    //          also call the no parameter version of the update
    private JRadioButton createAllButton() {
        JRadioButton all = new JRadioButton("All places", false);
        all.setFont(new Font("Verdana", Font.PLAIN, 13));
        all.addActionListener(e -> selectionListener.update());
        return all;
    }


    // MODIFIES: this
    // EFFECTS: adds labels and text field for user entry to control panel
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addUserEntryFields() {
        JPanel entryHolder = new JPanel();
        entryHolder.setLayout(null);

        JLabel label1 = new JLabel("  To add a place-");
        label1.setFont(new Font("Verdana", Font.PLAIN, 14));
        JLabel label2 = new JLabel("  please enter its: ");
        label2.setFont(new Font("Verdana", Font.PLAIN, 14));
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
        JLabel latLabel = new JLabel("Latitude: ");
        latLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
        JLabel lonLabel = new JLabel("Longitude: ");
        lonLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
        JLabel addingLabel = new JLabel("Adding to: ");
        addingLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
        nameEntry = new CustomizeJTextField();
        latEntry = new CustomizeJTextField();
        lonEntry = new CustomizeJTextField();

        // instantiate AddingListener to add placeOfInterest to the travelList
        JButton addBucket = new JButton(BUCKET_LIST);
        addBucket.addActionListener(new AddingListener(State.NotVISITED));
        addBucket.setFont(new Font("Verdana", Font.PLAIN, 13));
        JButton addVisited = new JButton(VISITED_LIST);
        addVisited.addActionListener(new AddingListener(State.VISITED));
        addVisited.setFont(new Font("Verdana", Font.PLAIN, 13));

        // formatting
        entryHolder.add(label1).setBounds(0, 0, 200, 20);
        entryHolder.add(label2).setBounds(0, 25, 200, 20);
        entryHolder.add(nameLabel).setBounds(10, 60, 120, 20);
        entryHolder.add(nameEntry).setBounds(10, 85, 120, 30);
        entryHolder.add(latLabel).setBounds(10, 125, 120, 20);
        entryHolder.add(latEntry).setBounds(10, 150, 120, 30);
        entryHolder.add(lonLabel).setBounds(10, 190, 120, 20);
        entryHolder.add(lonEntry).setBounds(10, 215, 120, 30);
        entryHolder.add(addingLabel).setBounds(10, 255, 120, 30);
        entryHolder.add(addBucket).setBounds(10, 290, 120, 30);
        entryHolder.add(addVisited).setBounds(10, 335, 120, 30);

        add(entryHolder);
    }


    /* ControlPanel inner class - AddingListener
     * Author:  Yun Xing
     * Date:    January 08, 2022,
     *
     * This class create new placeOfInterest when adding buttons are clicked
     * Observer pattern is applied to reduce coupling.
     */
    private class AddingListener implements ActionListener {

        State state;

        public AddingListener(State state) {
            this.state = state;
        }


        // MODIFIES: this
        // EFFECTS: checks all entry fields
        //          if all parsing succeed, create a placeOfInterest and added to the TravelList by calling update;
        //          if parsing failed, display error message in popup window
        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        @Override
        public void actionPerformed(ActionEvent e) {

            String name = nameEntry.getText();
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                JFrame jframe = new JFrame();
                JOptionPane.showMessageDialog(jframe, "the name of a place of interest cannot be empty");
            }

            double lat = 0;
            try {
                lat = Double.parseDouble(latEntry.getText());
            } catch (NumberFormatException ex) {
                Toolkit.getDefaultToolkit().beep();
                JFrame jframe = new JFrame();
                JOptionPane.showMessageDialog(jframe, "the latitude of a place need to be a number");
                latEntry.requestFocusInWindow();
            }

            double lon = 0;
            try {
                lon = Double.parseDouble(lonEntry.getText());
            } catch (NumberFormatException ex) {
                Toolkit.getDefaultToolkit().beep();
                JFrame jframe = new JFrame();
                JOptionPane.showMessageDialog(jframe, "the latitude of a place need to be a number");
                lonEntry.requestFocusInWindow();
            }

            GeoPoint geoPoint = new GeoPoint(lat, lon);
            PlaceOfInterest place = new PlaceOfInterest(name, geoPoint);
            place.setVisitingStatus(state);

            selectionListener.update(place);

            nameEntry.setText("");
            latEntry.setText("");
            lonEntry.setText("");
        }
    }
}
