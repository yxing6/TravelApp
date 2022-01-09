package ui.gui;

import model.State;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private static final int GAP = 10;  // vertical spacing between components in pixels
    private ISelectionListener selectionListener;


    // EFFECTS: constructs panel for displaying user controls
    public ControlPanel(ISelectionListener selectionListener) {

        this.selectionListener = selectionListener;
        Box toolHolder = Box.createVerticalBox();
        addRadioButtons(toolHolder);
        toolHolder.add(Box.createVerticalStrut(GAP));
        this.add(toolHolder);
    }


    // MODIFIES: toolHolder
    // EFFECTS: adds radio buttons (visited list and bucket list) to toolHolder
    private void addRadioButtons(Box toolHolder) {
        Box radioButtonHolder = Box.createVerticalBox();
        radioButtonHolder.setAlignmentX(Box.LEFT_ALIGNMENT);
        toolHolder.add(radioButtonHolder);

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
