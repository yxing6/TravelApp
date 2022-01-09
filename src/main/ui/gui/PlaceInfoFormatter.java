package ui.gui;



import model.PlaceOfInterest;

import java.util.ArrayList;
import java.util.List;

// Formats resource information using HTML
public class PlaceInfoFormatter {
    private PlaceOfInterest place;

    // EFFECTS: constructs HTML formatter for given resource
    public PlaceInfoFormatter(PlaceOfInterest place) {
        this.place = place;
    }

    // EFFECTS: returns place as a string
    public String format() {
        String result = "";

        result = place.toString();

        return result;
    }

//    // EFFECTS: returns contact information as list of strings
//    private List<String> getContactInfoAsStringList() {
//        List<String> contactItems = new ArrayList<>();
//        ContactInfo contactInfo = resource.getContactInfo();
//        contactItems.add(contactInfo.getAddress());
//        contactItems.add(contactInfo.getPhoneNumber());
//        contactItems.add(toAnchor(contactInfo.getWebAddress()));
//
//        return contactItems;
//    }

//    // EFFECTS: returns services associated with resource as list of strings
//    private List<String> getServicesAsStringList() {
//        List<String> serviceNames = new ArrayList<>();
//
//        for (Service next : resource.getServices()) {
//            serviceNames.add(next.getDisplayName());
//        }
//
//        return serviceNames;
//    }
//
//    // EFFECTS: returns given title formatted as HTML header
//    private String toHeader(String title) {
//        return "<h2>" + title + "</h2>";
//    }

//    // EFFECTS: returns given title and list of strings as HTML unordered list
//    private String toUnorderedList(String title, List<String> items) {
//        String unorderedList = "";
//
//        unorderedList += "<h3>" + title + "</h3>";
//        unorderedList += "<ul>";
//
//        for (String item : items) {
//            unorderedList += toListItem(item);
//        }
//
//        unorderedList += "</ul>";
//
//        return unorderedList;
//    }

//    // EFFECTS: returns item as HTML list item
//    private String toListItem(String item) {
//        return "<li>" + item + "</li>";
//    }
//
//    // EFFECTS: returns url as HTML anchor
//    private String toAnchor(URL url) {
//        String anchor = "";
//
//        anchor += "<a href='";
//        anchor += url.toString();
//        anchor += "'>";
//        anchor += url.toString();
//        anchor += "</a>";
//
//        return anchor;
//    }
}
