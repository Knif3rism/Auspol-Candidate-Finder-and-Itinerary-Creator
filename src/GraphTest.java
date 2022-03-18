import java.util.HashMap;

import Model.Airport;

public class GraphTest 
{
    public static void Main(String [] args)
    {
        HashMap<String, Airport> airportMap;

        airportMap = FileIO.Input.setAirport();
        airportMap = FileIO.Input.setAirportPaths(airportMap);

        /*airportMap.forEach((key, value) -> {
            System.out.println("---");
            System.out.println(key + " has " + value.getPlaneTravel().size() + " plane travel paths");
            System.out.println(key + " has " + value.getCarTravel().size() + " car travel paths");
        });*/

    }
}
