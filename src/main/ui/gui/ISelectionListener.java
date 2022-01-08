package ui.gui;

import model.State;

/* CPSC 210 Term Project Version 2:
 * Travel Record - GUI - ISelectionListener
 * Author:  Yun Xing
 * Date:    January 07, 2022,
 * Apply Observer pattern - method update to be called when user select an alternative visiting status
 */

public interface ISelectionListener {

    // called when visiting status selection has been changed
    void update(State state);

}
