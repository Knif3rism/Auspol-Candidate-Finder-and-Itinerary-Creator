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

    //Vertex of multiple Airports
    public Airport(String inState, String inName, Double inLat, Double inLong)
    {
        state = inState;
        airportName = inName;
        latitude = inLat;
        longtitude = inLong;
        byPlane = new ArrayList<TravelPath>();
        byCar = new ArrayList<TravelPath>();
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

    public List<TravelPath> getPlaneTravel()
    {
        return byPlane;
    }

    public List<TravelPath> getCarTravel()
    {
        return byCar;
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
    class TravelPath
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
