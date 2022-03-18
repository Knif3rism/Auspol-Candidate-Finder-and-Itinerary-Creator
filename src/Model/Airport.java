package Model;

import java.util.ArrayList;
import java.util.List;

public class Airport 
{
    private String state;
    private String airportName;
    private Double latitude;
    private Double longtitude;
    private List<TravelPath> byPlane;
    private List<TravelPath> byCar;
    private boolean visited;

    //Vertex of multiple Airports
    public Airport(String inState, String inName, Double inLat, Double inLong)
    {
        state = inState;
        airportName = inName;
        latitude = inLat;
        longtitude = inLong;
        byPlane = new ArrayList<TravelPath>();
        byCar = new ArrayList<TravelPath>();
        visited = false;
    }

    public String getState()
    {
        return state;
    }

    public String getAirportName()
    {
        return airportName;
    }

    public Double getLat()
    {
        return latitude;
    }

    public Double getLong()
    {
        return longtitude;
    }

    public String[] getPlaneTravelPaths()
    {
        String[] planePathArr;

        planePathArr = new String[byPlane.size()];

        for (int ii = 0; ii < planePathArr.length; ii++)
        {
            planePathArr[ii] = byPlane.get(ii).getDest().getState();
        }

        return planePathArr;
    }

    public String[] getCarTravelPaths()
    {
        String[] carPathArr;

        carPathArr = new String[byCar.size()];

        for (int ii = 0; ii < carPathArr.length; ii++)
        {
            carPathArr[ii] = byPlane.get(ii).getDest().getState();
        }

        return carPathArr;
    }

    public boolean getVisited()
    {
        return visited;
    }

    public void setVisited()
    {
        visited = true;
    }

    public void clearVisited()
    {
        visited = false;
    }

    public void addPlaneEdge(Airport dest, long dist, String time)
    {
        byPlane.add(new TravelPath(this, dest, dist, time));
    }

    public void addCarEdge(Airport dest, long dist, String time)
    {
        byCar.add(new TravelPath(this, dest, dist, time));
    }

    //Edge
    public class TravelPath
    {
        Airport src;
        Airport dest;
        long dist;
        String time;
        boolean visited;

        TravelPath (Airport inSrc, Airport inDest, long inDist, String inTime)
        {
            this.src = inSrc;
            this.dest = inDest;
            this.dist = inDist;
            this.time = inTime;
            this.visited = false;
        }

        public Airport getSrc()
        {
            return src;
        }

        public Airport getDest()
        {
            return dest;
        }

        public long getDist()
        {
            return dist;
        }

        public String getTime()
        {
            return time;
        }
    }
}
