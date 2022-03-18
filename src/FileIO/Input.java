package FileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import Model.Airport;

public class Input 
{
    public static HashMap<String, Airport> setAirport()
    {
        String[] temp;
        
        HashMap<String, Airport> airportMap = new HashMap<String, Airport>();
        Airport tempAirport;

        try 
        {
            FileInputStream file = new FileInputStream("resources/AirportDist1.0.csv");
            Scanner sc = new Scanner(file);

            sc.nextLine();

            //First Pass
            while(sc.hasNextLine())
            {
                temp = sc.nextLine().split(",");
                if (!(airportMap.containsKey(temp[0])))
                {
                    tempAirport = new Airport(temp[0], temp[1], Double.parseDouble(temp[2]), Double.parseDouble(temp[3]));
                    airportMap.put(temp[0], tempAirport);
                }

                if (!(airportMap.containsKey(temp[4])))
                {
                    tempAirport = new Airport(temp[4], temp[5], Double.parseDouble(temp[6]), Double.parseDouble(temp[7]));
                    airportMap.put(temp[4], tempAirport);
                }
            }

            sc.close();
        } 
        catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return airportMap;
    }

    public static HashMap<String, Airport> setAirportPaths(HashMap<String, Airport> airportMap)
    {
        Airport tempAir;
        String[] tempStr;

        try 
        {
            FileInputStream file = new FileInputStream("resources/AirportDist1.0.csv");
            Scanner sc = new Scanner(file);

            sc.nextLine();

            while(sc.hasNextLine())
            {
                tempStr = sc.nextLine().split(",");
                tempAir = airportMap.get(tempStr[0]);
                
                if (tempStr[10].equals("plane"))
                {
                    tempAir.addPlaneEdge(airportMap.get(tempStr[4]), Long.parseLong(tempStr[8]), tempStr[9]);
                }
                else
                {
                    tempAir.addCarEdge(airportMap.get(tempStr[4]), Long.parseLong(tempStr[8]), tempStr[9]);
                }
            }

            sc.close();
        } 
        catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return airportMap;
    }
}
