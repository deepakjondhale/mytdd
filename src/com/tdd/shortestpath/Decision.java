package com.tdd.shortestpath;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
 *what is given: Start City A Destination City Z

List of Distances between Cities:

A - B : 10
F - K : 23
R - M : 8
K - O : 40
Z - P : 18
J - K : 25
D - B : 11
M - A : 8
P - R : 15
 */



public class Decision
{
   

    public static void main(String[] args)
    {
        // mark all the vertices 
        Airport A = new Airport("A");
        Airport B = new Airport("B");
        Airport D = new Airport("D");
        Airport F = new Airport("F");
        Airport K = new Airport("K");
        Airport J = new Airport("J");
        Airport M = new Airport("M");
        Airport O = new Airport("O");
        Airport P = new Airport("P");
        Airport R = new Airport("R");
        Airport Z = new Airport("Z");

        // set the edges and weight
        A.adjacencies = new Route[] { new Route(M, 8),new Route(P, 82) , new Route(B, 80)  };
       // A.adjacencies = new Edge[] { new Edge(P, 2) };
        //A.adjacencies = new Edge[] { new Edge(B, 80) };
        B.adjacencies = new Route[]{ new Route(D, 11) };
        D.adjacencies = new Route[]{ new Route(B, 11) };
        F.adjacencies = new Route[]{ new Route(K, 23) };
        K.adjacencies = new Route[]{ new Route(O, 40) };
        J.adjacencies = new Route[]{ new Route(K, 25) };
        M.adjacencies = new Route[]{ new Route(R, 8),new Route(P, 7) };
        O.adjacencies = new Route[]{ new Route(K, 40) };
        P.adjacencies = new Route[]{ new Route(Z, 18) };
        R.adjacencies = new Route[]{ new Route(P, 15) };
        Z.adjacencies = new Route[]{ new Route(P, 18) };


        computePaths(A); // run Dijkstra
        System.out.println("Distance to " + Z + ": " + Z.minDistance);
        List<Airport> path = getShortestPathTo(Z);
        System.out.println("Path: " + path);
    }
    
    public static void computePaths(Airport source)
    {
        source.minDistance = 0.;
        PriorityQueue<Airport> vertexQueue = new PriorityQueue<Airport>();
    vertexQueue.add(source);
    System.out.println("1:"+vertexQueue);
    while (!vertexQueue.isEmpty()) {
        Airport u = vertexQueue.poll();
       // System.out.println("After poll:"+vertexQueue+" adjacencies :"+Arrays.asList(u.adjacencies));
            // Visit each edge exiting u
            for (Route e : u.adjacencies)
            {
                Airport v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
            vertexQueue.remove(v);

            v.minDistance = distanceThroughU ;
            v.previous = u;
            vertexQueue.add(v);
            System.out.println("2:"+vertexQueue);
        } 
            }
        }
    } 

    public static List<Airport> getShortestPathTo(Airport target)
    {
        List<Airport> path = new ArrayList<Airport>();
        for (Airport vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
}


class Airport implements Comparable<Airport>
{
    public final String name;
    public Route[] adjacencies;

    public double minDistance = Double.POSITIVE_INFINITY;
    public Airport previous;
    public Airport(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Airport other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Route
{
    public final Airport target;
    public final double weight;
    public Route(Airport argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}
