package ui.gui;

import model.State;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel {

    private static final int GAP = 10;  // vertical spacing between components in pixels
    private ISelectionListener selectionListener;
    private List<JCheckBox> checkBoxes;

    // EFFECTS: constructs panel for displaying user controls
    public ControlPanel(ISelectionListener selectionListener) {

        this.selectionListener = selectionListener;
        checkBoxes = new ArrayList<>();
        Box toolHolder = Box.createVerticalBox();
        addCheckBox(toolHolder);
        toolHolder.add(Box.createVerticalStrut(GAP));
        add(toolHolder);
    }


    // MODIFIES: toolHolder
    // EFFECTS: adds radio buttons (visited list and bucket list) to toolHolder
    private void addCheckBox(Box toolHolder) {
        Box checkBoxHolder = Box.createHorizontalBox();
        checkBoxHolder.setAlignmentX(Box.LEFT_ALIGNMENT);
        toolHolder.add(checkBoxHolder);

        JCheckBox visited = createVisitedBox();
        JCheckBox bucket = createBucketBox();
        checkBoxes.add(visited);
        checkBoxes.add(bucket);
        checkBoxHolder.add(visited);
        checkBoxHolder.add(bucket);

        toolHolder.add(checkBoxHolder);

    }

    // EFFECTS: returns radio button for "visited list" option
    private JCheckBox createVisitedBox() {
        JCheckBox visited = new JCheckBox("visited list", false);
//        visited.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                selectionListener.update(State.VISITED);
//            }
//        });
        visited.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectionListener.update(State.VISITED, e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        return visited;
    }


    // EFFECTS: returns radio button for "bucket list" option
    private JCheckBox createBucketBox() {
        JCheckBox bucket = new JCheckBox("bucket list", false);
        bucket.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectionListener.update(State.NotVISITED, e.getStateChange() == ItemEvent.SELECTED);
            }
        });
//        bucket.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                selectionListener.update(State.NotVISITED, e.g);
//            }
//        });

        return bucket;
    }
}
