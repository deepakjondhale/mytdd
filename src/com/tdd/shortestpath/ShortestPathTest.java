package com.tdd.shortestpath;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShortestPathTest {
    
    Map routeMap=new HashMap(); 
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        Map<String,Integer> distanceMapForAirportA=new HashMap<String,Integer>();
        distanceMapForAirportA.put("B", 10);
        distanceMapForAirportA.put("A", 0);
        Map<String,Map<String,Integer>> destinationMap=new HashMap<String,Map<String,Integer>>();
        routeMap.put("A", distanceMapForAirportA);
        
        Map<String,Integer> distanceMapForAirportB=new HashMap<String,Integer>();
        distanceMapForAirportB.put("C", 20);
        distanceMapForAirportB.put("B", 0);
        routeMap.put("B", distanceMapForAirportB);
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSourceandDestinationisSame() {
        Airport source=new Airport("A",routeMap);
        Assert.assertEquals(true, source.reachableTo(new Airport("A")));
    }
    
    @Test
    public void testSourceandDestinationisDifferentAndIsReachable() {
        Airport source=new Airport("A",routeMap);
        Airport destination = new Airport("B");
        Assert.assertEquals(true, source.reachableTo(destination));
        //source.getShortestDistanceBetween(source,destination,routeMap);
    }
    
    @Test
    public void testShortestPathBetweenSourceandDestination() throws Exception {
        Airport source=new Airport("A",routeMap);
        Airport destination = new Airport("B");
        Assert.assertEquals(10, source.shortestPath(destination),0.01);
    }
    
    @Test
    public void testShortestPathBetweenSourceandDestinationWithThreeAirport() throws Exception {
        Airport source=new Airport("A",routeMap);
        Airport destination = new Airport("C");
        Assert.assertEquals(30, source.shortestPath(destination),0.01);
    }
}
