import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Airport;
import View.TravelPath;


public class Main 
{
    public static void main(String[] args)
    {
        HashMap<String, Airport> airportMap;
        Airport[] adjAirports;
        List<String> list;

        String src = "VIC", dest = "NT";

        //Load Airport Map and Connections
        airportMap = FileIO.Input.setAirport();
        airportMap = FileIO.Input.setAirportPaths(airportMap);

        //Keep in mind that the data provided by "AirportDist1.0" has few valid paths
        list = TravelPath.travelPathDFS(airportMap, src, dest);
        System.out.println("\n" + src + " to " + dest + ":");
        if (list.size() == 1)
        {
            System.out.println("Invalid Route");
        }
        else
        {
            for (int ii = 0; ii < list.size(); ii++)
            {
                System.out.println(list.get(ii));
            }
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