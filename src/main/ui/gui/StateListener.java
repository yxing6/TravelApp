package ui.gui;

import model.PlaceOfInterest;
import model.State;

/* CPSC 210 Term Project Version 2:
 * Travel Record - GUI - ISelectionListener
 * Author:  Yun Xing
 * Date:    January 07, 2022,
 * Apply Observer pattern - method update to be called when changes are observed
 */

public interface StateListener {

    // called when a new place is added to the program
    void update(PlaceOfInterest place);


    // called when visiting status selection has been changed
    void update(State state);


    // called when passing certain string
    void update(String string);

    // called when user want to display all places
    void update();

}
