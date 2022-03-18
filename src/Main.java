import java.util.HashMap;
import java.util.Scanner;

import Model.Airport;


public class Main 
{
    public static void main(String[] args)
    {
        HashMap<String, Airport> airportMap;

        //Load Airport Map and Connections
        airportMap = FileIO.Input.setAirport();
        airportMap = FileIO.Input.setAirportPaths(airportMap);

        //Load Candidates

        airportMap.forEach((key, value) -> {
            System.out.println("---");
            System.out.println(key + " has " + value.getPlaneTravel().size() + " plane travel paths");
            System.out.println(key + " has " + value.getCarTravel().size() + " car travel paths");
        });
            


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