package ui.gui;

import model.*;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TravelApp extends JFrame implements ISelectionListener {

    private static final int MAP_WIDTH = 1000;
    private static final int MAP_HEIGHT = 700;
    private static final String JSON_STORE = "./data/travels.json";

    private DefaultListModel listModel;
    private JList jlist;
    private TravelList travelListIn;
    private TravelList travelListOut;
    private JXMapViewer mapViewer;


    public TravelApp() {
        super("Travel Tracker");
        initContentFields();
        createMenu();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buildMapViewer();
        add(BorderLayout.CENTER, mapViewer);
        add(BorderLayout.EAST, new ControlPanel(this));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: initiate fields
    public void initContentFields() {

        listModel = new DefaultListModel();
        jlist = new JList(listModel);
        travelListIn = new TravelList();
        travelListOut = new TravelList();
    }


    // MODIFIES: this
    // EFFECTS: initiate and set up a menu bar containing menuItems
    //          Each menuItem is associated with an ActionListener for file load and save
    public void createMenu() {

        JMenuBar menuBar = new JMenuBar();
        JMenu topMenu = new JMenu("FILE");
        menuBar.add(topMenu);

        JMenuItem load = new JMenuItem("LOAD");
        JMenuItem save = new JMenuItem("SAVE");
        topMenu.add(load);
        topMenu.add(save);

        this.setJMenuBar(menuBar);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTravelList();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeTravelList();
            }
        });

    }


    // MODIFIES: this
    // EFFECTS: Load in travelList.json and handle file IO Exception
    private void loadTravelList() {

        try {
            JsonReader jsonReader = new JsonReader(JSON_STORE);
            travelListIn = jsonReader.read();
            travelListOut = travelListIn;
            displayPlaces(travelListIn.getPlaces());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    e.getMessage() + "\nCaused by: " + e.getCause().getMessage(),
                    "Reader error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // MODIFIES: this
    // EFFECTS: write all panel content to the travelList.json file
    private void writeTravelList() {

        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(travelListOut);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: build map viewer centred on the world map center
    private void buildMapViewer() {
        mapViewer = new JXMapViewer();
        mapViewer.setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));

        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        tileFactory.setThreadPoolSize(8);
        mapViewer.setTileFactory(tileFactory);

        GeoPosition center = new GeoPosition(0.0, 0.0);
        mapViewer.setZoom(17);
        mapViewer.setAddressLocation(center);
    }

    @Override
    public void update(State state) {
        if (state == State.VISITED) {
            displayPlaces(travelListIn.getVisitedList());
        } else {
            displayPlaces(travelListIn.getBucketList());
        }
    }

//    @Override
//    public void update(State state, boolean display) {
//        if (state == State.VISITED) {
//            displayPlaces(travelListIn.getVisitedList(), display);
//        } else {
//            displayPlaces(travelListIn.getBucketList(), display);
//        }
//    }


    // MODIFIES: this
    // EFFECTS: add markers to map corresponding to the GeoPoint for all places in the parameter
    private void displayPlaces(List<PlaceOfInterest> places) {
        Set<Waypoint> markers = new HashSet<>();

        for (PlaceOfInterest p : places) {
            GeoPoint geoPoint = p.getLocation();
            GeoPosition geoPosition = new GeoPosition(geoPoint.getLatitude(), geoPoint.getLongitude());
            markers.add(new DefaultWaypoint(geoPosition));
        }

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(markers);
        mapViewer.setOverlayPainter(waypointPainter);


    }


    public static void main(String[] args) {
        new TravelApp();
    }
}
