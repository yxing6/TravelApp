package ui.gui;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.TravelList;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

// A window for displaying information about resources
public class InfoWindow extends JPanel {
    private static final double X_SCALE = 0.7; // proportion of map window to be used for info window
    private static final double Y_SCALE = 0.3;
    private static final int WIDTH = (int) (TravelApp.MAP_WIDTH * X_SCALE);
    private static final int HEIGHT = (int) (TravelApp.MAP_HEIGHT * Y_SCALE);
    private JLabel textPane;
    // private TravelList travelList;
    private JXMapViewer mapViewer;
    private PlaceOfInterest placeOfInterest;

    // EFFECTS: constructs info window, not visible to user
    public InfoWindow(PlaceOfInterest placeOfInterest, JXMapViewer mapViewer) {
        super();
        this.placeOfInterest = placeOfInterest;
        this.mapViewer = mapViewer;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        buildTextPane();
        this.add(textPane);
        // getViewport().setView(textPane);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: builds pane in which placeOfInterest info will be displayed
    private void buildTextPane() {
        textPane = new JLabel();
//        textPane.setEditable(false);
//        textPane.setMargin(new Insets(4, 10, 4, 10));
        displayPlaceInfo(placeOfInterest);
    }

    // MODIFIES: this
    // EFFECTS: display information for given resource in text pane
    private void displayPlaceInfo(PlaceOfInterest selected) {
        textPane.setText(selected.toString());
        setVisible(true);
//        getParent().validate();
//        getParent().repaint();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                getVerticalScrollBar().setValue(0);
//            }
//        });
    }

}
