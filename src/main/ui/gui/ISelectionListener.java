package ui.gui;

import model.State;

// Service selection listener
public interface ISelectionListener {
    // called when service selection has been changed
    void update(State state);
}
