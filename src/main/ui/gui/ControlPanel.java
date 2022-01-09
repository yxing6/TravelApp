package ui.gui;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.State;

import javax.swing.*;
import java.awt.*;
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
    private static final String ATB = "Add to bucket list";
    private static final String ATV = "Add to visited list";

    private StateListener selectionListener;
    private CustomizeJTextField nameEntry;
    private CustomizeJTextField latEntry;
    private CustomizeJTextField lonEntry;


    // EFFECTS: constructs panel for displaying user controls
    public ControlPanel(StateListener selectionListener) {

        this.selectionListener = selectionListener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(GAP));
        addUserEntryFields();
        add(Box.createVerticalStrut(GAP));
        addRadioButtons();
        add(Box.createVerticalStrut(GAP));
    }


    // An inner class for formatting
    private static class CustomizeJTextField extends JTextField {
        private CustomizeJTextField() {
            super();
            setMaximumSize(new Dimension(200, 30));
        }
    }


    // MODIFIES: this
    // EFFECTS: adds labels and text field for user entry to control panel
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addUserEntryFields() {
        Box entryHolder = Box.createVerticalBox();
        entryHolder.setAlignmentX(Box.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Name");
        JLabel latLabel = new JLabel("Latitude");
        JLabel lonLabel = new JLabel("Longitude");
        nameEntry = new CustomizeJTextField();
        latEntry = new CustomizeJTextField();
        lonEntry = new CustomizeJTextField();

        // instantiate AddingListener to add placeOfInterest to the travelList
        JButton addBucket = new JButton(ATB);
        addBucket.addActionListener(new AddingListener(State.NotVISITED));
        JButton addVisited = new JButton(ATV);
        addVisited.addActionListener(new AddingListener(State.VISITED));


        // formatting
        entryHolder.add(Box.createVerticalStrut(GAP));
        entryHolder.add(nameLabel);
        entryHolder.add(Box.createVerticalStrut(GAP));
        entryHolder.add(nameEntry);
        entryHolder.add(Box.createVerticalStrut(GAP * 2));
        entryHolder.add(latLabel);
        entryHolder.add(Box.createVerticalStrut(GAP));
        entryHolder.add(latEntry);
        entryHolder.add(Box.createVerticalStrut(GAP * 2));
        entryHolder.add(lonLabel);
        entryHolder.add(Box.createVerticalStrut(GAP));
        entryHolder.add(lonEntry);
        entryHolder.add(Box.createVerticalStrut(GAP * 3));
        entryHolder.add(addBucket);
        entryHolder.add(Box.createVerticalStrut(GAP * 3));
        entryHolder.add(addVisited);
        entryHolder.add(Box.createVerticalStrut(GAP * 4));

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


    // MODIFIES: this
    // EFFECTS: adds radio buttons (visited list and bucket list) to control panel
    private void addRadioButtons() {
        Box radioButtonHolder = Box.createVerticalBox();
        radioButtonHolder.setAlignmentX(Box.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Visiting Status");
        JRadioButton visited = createVisitedButton();
        JRadioButton bucket = createBucketButton();
        JRadioButton all = createAllButton();

        radioButtonHolder.add(label);
        radioButtonHolder.add(Box.createVerticalStrut(GAP));
        radioButtonHolder.add(visited);
        radioButtonHolder.add(Box.createVerticalStrut(GAP));
        radioButtonHolder.add(bucket);
        radioButtonHolder.add(Box.createVerticalStrut(GAP));
        radioButtonHolder.add(all);
        radioButtonHolder.add(Box.createVerticalStrut(GAP));

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
        visited.addActionListener(e -> selectionListener.update(State.VISITED));
        return visited;
    }


    // MODIFIES: this
    // EFFECTS: returns radio button for "bucket places" option
    //          also calling update with a state of notVisited
    private JRadioButton createBucketButton() {
        JRadioButton bucket = new JRadioButton("Bucket places", false);

        bucket.addActionListener(e -> selectionListener.update(State.NotVISITED));
        return bucket;
    }


    // MODIFIES: this
    // EFFECTS: returns radio button for "all places" option
    //          also call the no parameter version of the update
    private JRadioButton createAllButton() {
        JRadioButton all = new JRadioButton("All places", false);

        all.addActionListener(e -> selectionListener.update());
        return all;
    }
}
