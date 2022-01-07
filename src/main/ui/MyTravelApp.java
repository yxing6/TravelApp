package ui;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.State;
import model.TravelList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;


// Travel info application
//


/* CPSC 210 Term Project Version 2:
 * Travel Record - MyTravelApp
 * Author: Yun Xing
 * Date: January 07, 2022
 *
 * This class is the console application of the TravelApp
 * built with reference to the TellAPP application and the JsonSerializationDemo example from CPSC 210
 * containing methods to read user console input and parsing info to modify the travelList
 * can also load data from and save data to external JSON file.
 */
public class MyTravelApp {

    private static final String JSON_STORE = "./data/travels.json";
    private TravelList travelList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the travel info application
    public MyTravelApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAgent();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAgent() {
        boolean keepAdding = true;
        String commandA;

        init();

        while (keepAdding) {
            displayMenu();
            System.out.print("\t");
            commandA = input.next();
            commandA = commandA.toLowerCase();

            if (commandA.equals("q")) {
                keepAdding = false;
            } else {
                processCommandA(commandA);
            }
        }
        System.out.println("\n\tGreat! Come back to me in the future to update your travel list!");
        System.out.println("\n-----------------------------------------------------------------------------");
    }


    // EFFECTS: constructs a new travelList
    private void init() {
        travelList = new TravelList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view: view the place of interests on my list");
        System.out.println("\tm -> modify: modify the place of interests on my list");
        System.out.println("\ts -> modify: save the travel list");
        System.out.println("\tl -> modify: load the travel list");
        System.out.println("\tq -> quit: skip action now and come back later!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandA(String command) {
        switch (command) {
            case "v":
                viewTravelList();
                break;
            case "m":
                modifyTravelList();
                break;
            case "l":
                loadTravelList();
                break;
            case "s":
                saveTravelList();
                break;
            default:
                System.out.println("\tSelection not valid... Return to the main menu");
                break;
        }
        System.out.println("\n-----------------------------------------------------------------------------");
    }


    // EFFECTS: print existing travel list to console
    private void viewTravelList() {

        if (travelList.getPlaces().size() == 0) {
            System.out.println("\n\tYou have not added any place of interest onto you travel list");
        }

        if (travelList.getVisitedList().size() == 0) {
            System.out.println("\n\tYour visited list is empty. You have not been to any place... not yet!");
        } else {
            Set<PlaceOfInterest> visitedPlaces = travelList.getVisitedList();
            viewOneList(visitedPlaces);
        }

        if (travelList.getBucketList().size() == 0) {
            System.out.println("\n\tYour bucket list is empty. Explore where you want to go!");
        } else {
            Set<PlaceOfInterest> bucketPlaces = travelList.getBucketList();
            viewOneList(bucketPlaces);
        }
    }


    // EFFECTS: a helper method to print each placeOfInterest in a list
    private void viewOneList(Set<PlaceOfInterest> places) {
        System.out.println("\n\tThe countries on your visited list are:");
        for (PlaceOfInterest p: places) {
            System.out.println(p.toString());
        }
    }


    // EFFECTS: prompt user for name of the list to modify
    private void modifyTravelList() {

        PlaceOfInterest place;
        String commandB;

        System.out.println("\n\t\tSelect from:");
        System.out.println("\t\t\ta -> I want to add one place to my travel list.");
        System.out.println("\t\t\td -> I want to delete one place from my travel list.");
        System.out.print("\t\t\t");
        commandB = input.next();
        commandB = commandB.toLowerCase();

        if (commandB.equals("a")) {
            place = parsePOI();
            travelList.addPlace(place);
        } else if (commandB.equals("d")) {
            place = parsePOI();
            travelList.removePlace(place);
        } else {
            System.out.println("\t\t\tSelection not valid... Return to the main menu");
        }
    }


    // EFFECTS:  to construct a new placeOfInterest based on user input
    private PlaceOfInterest parsePOI() {
        String name;
        GeoPoint location;
        State status;
        PlaceOfInterest poi;

        System.out.println("\t\t\tEnter the name of the place of interest: ");
        System.out.print("\t\t\t");
        name = input.next();
        location = parseGeoPoint();
        status = parseState();

        poi = new PlaceOfInterest(name, location);
        poi.setVisitingStatus(status);

        return poi;
    }


    // EFFECTS:  to construct a new geoPoint based on user input
    private GeoPoint parseGeoPoint() {

        GeoPoint location;
        double lat;
        double lon;

        System.out.println("\t\t\tEnter the latitude of the place: ");
        System.out.print("\t\t\t");
        lat = input.nextDouble();
        System.out.println("\t\t\tEnter the longitude of the place: ");
        System.out.print("\t\t\t");
        lon = input.nextDouble();
        location = new GeoPoint(lat, lon);

        return location;
    }


    // EFFECTS: to parse place visiting status based on user input
    private State parseState() {

        State status = null;
        String statusString;

        System.out.println("\t\t\tHave you been to this place? Enter y for Yes, n for No: ");
        System.out.print("\t\t\t");
        statusString = input.next();
        statusString = statusString.toLowerCase();
        if (statusString.equals("y")) {
            status = State.VISITED;
        } else if (statusString.equals("n")) {
            status = State.NotVISITED;
        } else {
            System.out.println("\t\t\tSelection not valid... Return to the main menu");
        }
        return status;
    }

}
