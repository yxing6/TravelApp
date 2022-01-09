package ui.gui;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.State;
import model.TravelList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private static final int GAP = 10;  // vertical spacing between components in pixels
    private static final String ATB = "Add to bucket list";
    private static final String ATV = "Add to visited list";

    private StateSelectionListener selectionListener;
    private TravelList travelList;

    private CustomizeJTextField nameEntry;
    private CustomizeJTextField latEntry;
    private CustomizeJTextField lonEntry;


    // EFFECTS: constructs panel for displaying user controls
    public ControlPanel(StateSelectionListener selectionListener, TravelList travelList) {

        this.selectionListener = selectionListener;
        this.travelList = travelList;

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
    private void addUserEntryFields() {
        Box entryHolder = Box.createVerticalBox();
        entryHolder.setAlignmentX(Box.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Name");
        JLabel latLabel = new JLabel("Latitude");
        JLabel lonLabel = new JLabel("Longitude");
        nameEntry = new CustomizeJTextField();
        latEntry = new CustomizeJTextField();
        lonEntry = new CustomizeJTextField();
        JButton addBucket = new JButton(ATB);
        addBucket.addActionListener(new AddingListener(State.NotVISITED));
        JButton addVisited = new JButton(ATV);
        addVisited.addActionListener(new AddingListener(State.VISITED));

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

    private class AddingListener implements ActionListener {

        State state;

        public AddingListener(State state) {
            this.state = state;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = nameEntry.getText();
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                nameEntry.requestFocusInWindow();
                // return;
            }

            double lat = 0;
            double lon = 0;
            try {
                lat = Double.parseDouble(latEntry.getText());
                lon = Double.parseDouble(lonEntry.getText());
            } catch (NumberFormatException ex) {
                Toolkit.getDefaultToolkit().beep();
                latEntry.requestFocusInWindow();
                lonEntry.requestFocusInWindow();
            }

            GeoPoint geoPoint = new GeoPoint(lat, lon);
            PlaceOfInterest place = new PlaceOfInterest(name, geoPoint);
            place.setVisitingStatus(state);
            travelList.addPlace(place);

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


    // EFFECTS: returns radio button for "visited list" option
    private JRadioButton createVisitedButton() {
        JRadioButton visited = new JRadioButton("Visited places", false);
        visited.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionListener.update(State.VISITED);
            }
        });

        return visited;
    }


    // EFFECTS: returns radio button for "bucket list" option
    private JRadioButton createBucketButton() {
        JRadioButton bucket = new JRadioButton("Bucket places", false);

        bucket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionListener.update(State.NotVISITED);
            }
        });

        return bucket;
    }


    private JRadioButton createAllButton() {
        JRadioButton all = new JRadioButton("All places", false);

        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionListener.update();
            }
        });
        return all;
    }
}
