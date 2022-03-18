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

        /*Because of the recursive algorithm I created I need to cull some extra invalid bits of
          data that came from other recursion trees*/
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
            for (Airport nextNode : planePaths)
            {
                if (nextNode.getState().equals(dest.getState()))
                {
                    travelPathDFSRecurse(airportMap, visitedNodes, nextNode, dest);
                    foundDest = true;
                }
            }

            //If the destination is not found, recurse naively
            if (!foundDest)
            {
                for (Airport nextNode : planePaths)
                {
                    /*if the next node is QLD and it isn't the destination don't bother recursing to it
                      as it's a sink, meaning it has no outbound flights.*/
                    if (!nextNode.getState().equals("QLD") && !dest.getState().equals("QLD"))
                    {
                        planePaths = curr.getPlaneTravelPaths();
                        travelPathDFSRecurse(airportMap, visitedNodes, nextNode, dest);
                    }

                    /*We remove the current node from the list because it's an invalid path*/
                    else if (nextNode.getState().equals("QLD") && !dest.getState().equals("QLD"))
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
