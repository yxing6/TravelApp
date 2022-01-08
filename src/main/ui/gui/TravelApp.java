package ui.gui;

import model.TravelList;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.*;
import javax.swing.*;

public class TravelApp extends JFrame {

    public static final int MAP_WIDTH = 800;
    public static final int MAP_HEIGHT = 600;
    private TravelList travelList;
    private JXMapViewer mapViewer;
}
