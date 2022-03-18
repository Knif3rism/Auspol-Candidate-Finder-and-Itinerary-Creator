package View;

import java.util.HashMap;
import java.util.List;

import javax.lang.model.util.ElementScanner6;

import java.util.ArrayList;

import Model.Airport;

public class TravelPath 
{
    public static List<String> travelPathDFS(HashMap<String, Airport> airportMap, String src, String dest)
    {
        int ii = 0;
        List<String> tempList = new ArrayList<String>();
        List<String> visitedNodes = new ArrayList<String>();

        airportMap.forEach((key, value) -> {
            value.clearVisited();
        });

        travelPathDFSRecurse(airportMap, tempList, airportMap.get(src), airportMap.get(dest));


        if (airportMap.get(dest).hasBeenVisited() && (tempList.size() > 2))
        {
            visitedNodes.add(src);
            do
            {
                visitedNodes.add(tempList.get(ii));
                ii++;
            }
            while (!tempList.get(ii).equals(dest));
            visitedNodes.add(dest);
        }
        else
        {
            for (ii = 0; ii < tempList.size(); ii++)
            {
                visitedNodes.add(tempList.get(ii));
            }
        }

        return visitedNodes;
    }

    public static void travelPathDFSRecurse(HashMap<String, Airport> airportMap, List<String> visitedNodes, Airport curr, Airport dest)
    {
        Airport[] planePaths;
        boolean foundDest = false;

        curr.setVisited();
        visitedNodes.add(curr.getState());

        if (!dest.hasBeenVisited())
        {
            planePaths = curr.getPlaneTravelPaths();
            
            //Initial Pass attempts to find the destination in the adj. matrix recurse to it
            for (Airport air : planePaths)
            {
                if (air.getState().equals(dest.getState()))
                {
                    travelPathDFSRecurse(airportMap, visitedNodes, air, dest);
                    foundDest = true;
                }
            }

            //If the destination is not found, recurse naively
            if (!foundDest)
            {
                for (Airport air : planePaths)
                {
                    if (!air.getState().equals("QLD") && !dest.getState().equals("QLD") &&
                        !air.getState().equals(curr.getState()))
                    {
                        planePaths = curr.getPlaneTravelPaths();
                        travelPathDFSRecurse(airportMap, visitedNodes, air, dest);
                    }

                    //If one of the next destinations is QLD, which is a sink, drop this current node
                    else if (air.getState().equals("QLD") && !dest.getState().equals("QLD"))
                    {
                        visitedNodes.remove(visitedNodes.size()-1);
                    }
                    
                }
            }
        }
        else
        {
            return;
        }
    }
}
