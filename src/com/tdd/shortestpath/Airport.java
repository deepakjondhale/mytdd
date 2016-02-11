package com.tdd.shortestpath;

import java.util.Map;

public class Airport {
    private String airportName;
    private Map<String,Map<String,Integer>> routeMap;
    
    public Airport(String airportName) {
        this.airportName=airportName;
    }

    public Airport(String airportName, Map routeMap) {
        this.airportName=airportName;
        this.routeMap=routeMap;
    }

    public boolean reachableTo(Airport airport) {
        boolean  isReachable =false;
        Map<String, Integer> distanceMap = routeMap.get(getAirportName());
        if(distanceMap.containsKey(airport.getAirportName())) {
            isReachable=true;
        }
        return isReachable;
    }
    //TODO Pradeep IS HERE
    public String getAirportName() {
        return airportName;
    }

    public Integer shortestPath(Airport destination) throws Exception {
        if(this.reachableTo(destination)) {
            return routeMap.get(getAirportName()).get(destination.getAirportName());
        }else {
            throw new Exception("Destination Not Reachable");
        }
    }

}
