package ui.gui;


import model.State;

import javax.swing.*;

// Represents radio button for visiting status
public class StateCheckBox extends JCheckBox {
    private State status;

    // EFFECTS: constructs a radio button for given service
    public StateCheckBox(State status) {
        super(status.getDisplayString());
        this.status = status;
    }

    public State getStatus() {
        return status;
    }
}
