package ui.gui;

import model.SelectionState;
import model.State;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel {

    private static final int GAP = 10;  // vertical spacing between components in pixels
    private ISelectionListener selectionListener;
    private List<StateCheckBox> checkBoxes;

    // EFFECTS: constructs panel for displaying user controls
    public ControlPanel(ISelectionListener selectionListener) {

        this.selectionListener = selectionListener;
        checkBoxes = new ArrayList<>();
        Box toolHolder = Box.createVerticalBox();
        addRadioButtons(toolHolder);
        toolHolder.add(Box.createVerticalStrut(GAP));
        add(toolHolder);
    }


    // MODIFIES: toolHolder
    // EFFECTS: adds radio buttons (visited list and bucket list) to toolHolder
    private void addRadioButtons(Box toolHolder) {
        Box radioBtnHolder = Box.createHorizontalBox();
        radioBtnHolder.setAlignmentX(Box.LEFT_ALIGNMENT);
        toolHolder.add(radioBtnHolder);

        JRadioButton visited = createVisitedButton();
        JRadioButton bucket = createBucketButton();

        radioBtnHolder.add(visited);

        radioBtnHolder.add(bucket);

        ButtonGroup radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(visited);
        radioBtnHolder.add(Box.createVerticalStrut(GAP));
        radioBtnGroup.add(bucket);
    }

    // EFFECTS: returns radio button for "visited list" option
    private JRadioButton createVisitedButton() {
        JRadioButton visited = new JRadioButton("visited list", true);
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
        JRadioButton bucket = new JRadioButton("bucket list", true);
        bucket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionListener.update(State.NotVISITED);
            }
        });

        return bucket;
    }
}
