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

    public long getPlaneDistance(Airport closestAirport)
    {
        boolean airportFound = false;
        int ii = 0;
        long outDist = 0;

        while (ii < byPlane.size() && !airportFound)
        {
            if (byPlane.get(ii).dest.getState().equals(closestAirport.getState()))
            {
                airportFound = true;
                outDist = byPlane.get(ii).getDist();
            }
            ii++;
        }

        return outDist;
    }

    public boolean hasBeenVisited()
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

    public Airport[] getPlaneTravelPaths()
    {
        Airport[] planePathArr;

        planePathArr = new Airport[byPlane.size()];

        for (int ii = 0; ii < planePathArr.length; ii++)
        {
            planePathArr[ii] = byPlane.get(ii).dest;
        }

        return planePathArr;
    }

    public Long[] getPlaneTravelDistances()
    {
        Long[] planeTravelDist;

        planeTravelDist = new Long[byPlane.size()];

        for (int ii = 0; ii < planeTravelDist.length; ii++)
        {
            planeTravelDist[ii] = byPlane.get(ii).dist;
        }

        return planeTravelDist;
    }

    public String[] getPlaneTravelTimes()
    {
        String[] planeTravelDist;

        planeTravelDist = new String[byPlane.size()];

        for (int ii = 0; ii < planeTravelDist.length; ii++)
        {
            planeTravelDist[ii] = byPlane.get(ii).time;
        }

        return planeTravelDist;
    }

    public Airport[] getCarTravelPaths()
    {
        Airport[] carPathArr;

        carPathArr = new Airport[byCar.size()];

        for (int ii = 0; ii < carPathArr.length; ii++)
        {
            carPathArr[ii] = byCar.get(ii).dest;
        }

        return carPathArr;
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
