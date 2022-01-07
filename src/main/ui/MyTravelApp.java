package ui;

import model.PlaceOfInterest;
import model.TravelList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

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

}
