import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Airport;


public class Main 
{
    public static void main(String[] args)
    {
        HashMap<String, Airport> airportMap;
        String[] adjAirports;

        //Load Airport Map and Connections
        airportMap = FileIO.Input.setAirport();
        airportMap = FileIO.Input.setAirportPaths(airportMap);

        adjAirports = airportMap.get("WA").getPlaneTravelPaths();

        System.out.println("WA Plane Paths: ");
        for (int ii = 0; ii < adjAirports.length; ii++)
        {
            System.out.println(adjAirports[ii]);
        }

        adjAirports = airportMap.get("WA").getCarTravelPaths();

        System.out.println("\nWA Car Paths: ");
        for (int ii = 0; ii < adjAirports.length; ii++)
        {
            System.out.println(adjAirports[ii]);
        }

        //Load Candidates

            


        /*Scanner sc = new Scanner(System.in);
        int choice = 1;

        do
        {
            System.out.println("\n(1) - List Nominees\n"  +
                               "(2) - Nominee Search\n" + 
                               "(3) - List By Margin\n" +
                               "(4) - Itinerary by Margin\n" +
                               "(0) - Exit\n");

            System.out.print("Menu: ");
            choice = sc.nextInt();
            menuSelection(choice);
        }
        while (choice != 0);

        sc.close();*/
    }

    public static void menuSelection(int choice)
    {
        switch (choice)
        {
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 0:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid menu selection!");

        }
    }
}