package View;

import java.util.HashMap;
import java.util.Arrays;

import Model.Airport;

public class TravelPath 
{
    public static String[] travelPathDFS(HashMap<String, Airport> airportMap, String src, String dest)
    {
        String [] visitedNodes = new String[2];

        airportMap.forEach((key, value) -> {
            value.clearVisited();
        });

        //dijkstra's algorithm
        //https://stackabuse.com/graphs-in-java-dijkstras-algorithm/
        shortestPath(airportMap, visitedNodes, airportMap.get(src), airportMap.get(dest));
        
        

        return visitedNodes;
    }

    public static void shortestPath(HashMap<String, Airport> airportMap, String[] visitedNodes, Airport src, Airport dest)
    {
        Airport prevNode = src;
        Airport currNode = src;

        HashMap<Airport, Airport> change = new HashMap<>();
        HashMap<Airport, Long> smallestPathCost = new HashMap<>();

        change.put(src, null);

        airportMap.forEach((nextNode, currnode) -> {
            if (nextNode.equals(src.getState()))
            {
                smallestPathCost.put(src, Long.getLong("0"));
            }
            else
            {
                smallestPathCost.put(airportMap.get(nextNode), Long.MAX_VALUE);
            }
        });

        src.setVisited();

        while (true)
        {
            currNode = checkDestIsConnected(currNode, dest);

            if (!currNode.getState().equals(dest.getState()))
            {
                currNode = closestAirport(currNode);

            }
            
            //Check if there is no valid paths
            if (currNode == null)
            {
                return;
            }

            //If the current node is the destination, valid path found
            if (currNode.equals(dest))
            {
                smallestPathCost.put(currNode, prevNode.getPlaneDistance(currNode));
                
                Airport child = dest;
                String path = src.getState();

                while (true)
                {
                    Airport parNode = change.get(child);
                    if (parNode == null)
                    {
                        break;
                    }
                
                    path = parNode.getState() + " " + child.getState();
                    child = parNode;
                }
                visitedNodes[0] = path;
                visitedNodes[1] = smallestPathCost.get(dest).toString();
                return;
            }

            currNode.setVisited();
            checkPath(currNode, smallestPathCost, change);
            prevNode = currNode;
        }
    }

    public static Airport checkDestIsConnected(Airport currNode, Airport dest)
    {
        int ii = 0;

        Airport node = currNode;
        Airport[] connectingAirports;

        connectingAirports = currNode.getPlaneTravelPaths();

        while (ii < connectingAirports.length)
        {
            if (connectingAirports[ii].getState().equals(dest.getState()))
            {
                node = connectingAirports[ii];
                break;
            }

            ii++;
        }

        return node;
    }
    
    public static void checkPath(Airport currNode, HashMap<Airport, Long> smallesPathcost, HashMap<Airport, Airport> shortestPath)
    {
        int ii = 0;

        Airport[] connectingAirports = currNode.getPlaneTravelPaths();
        Long[] travelDistance = currNode.getPlaneTravelDistances();

        for (long currDist : travelDistance)
        {
            if (!connectingAirports[ii].hasBeenVisited())
            {
                if (smallesPathcost.get(currNode) + currDist < smallesPathcost.get(connectingAirports[ii]))
                {
                    smallesPathcost.put(connectingAirports[ii], smallesPathcost.get(currNode) + currDist);
                    shortestPath.put(connectingAirports[ii], currNode);
                }
            }

            ii++;
        }
    }

    //This only takes into the metric, distance. It does not account for time and also, does not account for car travel.
    //It's just assumed that car travel is slower than travel by plane.
    public static Airport closestAirport(Airport currNode)
    {
        int ii = 0;
        Airport nearestAirport = null;

        Airport[] connectingAirports;
        Long[] travelDistances, travelDistancesTemp;

        connectingAirports = currNode.getPlaneTravelPaths();
        travelDistances = currNode.getPlaneTravelDistances();
        travelDistancesTemp = currNode.getPlaneTravelDistances();

        if (travelDistances.length != 0)
        {
            Arrays.sort(travelDistancesTemp);

            while ((!travelDistances[ii].equals(travelDistancesTemp[0])) &&     
                   (ii < travelDistances.length) && (travelDistances.length > 0))
            {
                ii++;
            }

            if (travelDistances.length > 0)
            {
                if (!connectingAirports[ii].hasBeenVisited())
                {
                    do
                    {
                        nearestAirport = connectingAirports[ii];
                        ii++;
                    }
                    while ((ii < connectingAirports.length) && (connectingAirports[ii].hasBeenVisited()));
                }
            }
        }

        return nearestAirport;
    }
}
