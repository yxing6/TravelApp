**My Personal Project**

This is the updated version of my first Java Gui project.   

In this version, I use PlaceOfInterest with
- a field of String to represent its name
- a field of GeoPoint to represent its location
- a field of State to represent if the user has been to the location or have not

Adding a State as a field of my object is the design choice different from the previous version.   
In the previous version, two separate lists, *bucketList & visitedList*, were used to collect travel destinations.  
I believe the new design will reduce the coupling between classes and lower the burden of building the final GUI 
