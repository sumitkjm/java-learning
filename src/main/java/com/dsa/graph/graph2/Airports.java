package com.dsa.graph.graph2;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  AIRPORTS
 * Send Feedback
 * AIRPORTS
 *
 * The government of a certain developing nation wants to improve transportation in one of its most inaccessible areas, in an attempt to attract investment. The region consists of several important locations that must have access to an airport.
 * Of course, one option is to build an airport in each of these places, but it may turn out to be cheaper to build fewer airports and have roads link them to all of the other locations. Since these are long distance roads connecting major locations in the country (e.g. cities, large villages, industrial areas), all roads are two-way. Also, there may be more than one direct road possible between two areas. This is because there may be several ways to link two areas (e.g. one road tunnels through a mountain while the other goes around it etc.) with possibly differing costs.
 * A location is considered to have access to an airport either if it contains an airport or if it is possible to travel by road to another location from there that has an airport. You are given the cost of building an airport and a list of possible roads between pairs of locations and their corresponding costs. The government now needs your help to decide on the cheapest way of ensuring that every location has access to an airport. The aim is to make airport access as easy as possible, so if there are several ways of getting the minimal cost, choose the one that has the most airports.
 *
 * Input
 *
 * The first line of input contains the integer T (T < 25), the number of test cases. The rest of the input consists of T cases. Each case starts with two integers N, M and A (0 < N ≤ 10, 000, 0 ≤ M ≤ 100, 000, 0 < A ≤ 10, 000) separated by white space. N is the number of locations, M is the number of possible roads that can be built, and A is the cost of building an airport.
 * The following M lines each contain three integers X, Y and C (1 ≤ X, Y ≤ N, 0 < C ≤ 10, 000), separated by white space. X and Y are two locations, and C is the cost of building a road between X and Y .
 *
 * Output
 *
 * Your program should output exactly T lines, one for each case. Each line should be of the form ‘Case #X: Y Z’, where X is the case number Y is the minimum cost of making roads and airports so that all locations have access to at least one airport, and Z is the number of airports to be built. As mentioned earlier, if there are several answers with minimal cost, choose the one that maximizes the number of airports.
 *
 * Sample Input
 *
 * 2
 * 4 4 100
 * 1 2 10
 * 4 3 12
 * 4 1 41
 * 2 3 23
 * 5 3 1000
 * 1 2 20
 * 4 5 40
 * 3 2 30
 *
 * Sample Output
 *
 * Case #1: 145 1
 * Case #2: 2090 2
 */
public class Airports {
    static class Edge implements Comparable<Edge> {
        int startVertex;
        int endVertex;
        int weight;

        @Override
        public int compareTo(Edge o) {
            if(o==null) {
                return 0;
            }
            if(this.weight<o.weight) {
                return -1;
            } else if(this.weight>o.weight) {
                return 1;
            }
            return 0;
        }
        public String toString() {
            if(startVertex<endVertex) {
                return startVertex+" "+endVertex+" "+ weight;
            } else {
                return endVertex+" "+startVertex+" "+ weight;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int ti=1;ti<=t;ti++) {
            // total number of cities
            int n = in.nextInt();
            // location of roads
            int m = in.nextInt();
            // cost of airport
            int costOfAirport = in.nextInt();
            Edge[] edges = new Edge[m];
            for (int i=0;i<m;i++) {
                Edge edge = new Edge();
                edge.startVertex = in.nextInt()-1;
                edge.endVertex = in.nextInt()-1;
                edge.weight = in.nextInt();
                edges[i] = edge;
            }
            Arrays.sort(edges);
            int[] parents = new int[n];
            for (int i=0;i<n;i++) {
                parents[i] = i;
            }
            int count  = 0;
            int edgeNumber = 0;
            int roadCost = 0;
            int totalRoadCost = 0;
            int totalCost = 0;
            while (count<n-1 && edgeNumber<m) {
                Edge edge = edges[edgeNumber];
                // if road cost is greater than airport then reset roadCost
                // to start building another edges as
                if(roadCost+edge.weight>=costOfAirport) {
                    roadCost = 0;
                    if(edge.weight>=costOfAirport) {
                        edgeNumber++;
                    }
                    continue;
                }
                int startParent = getTopParent(parents,edge.startVertex);
                int endParent = getTopParent(parents,edge.endVertex);
                if(startParent!=endParent) {
                    parents[startParent] = endParent;
                    count++;
                    roadCost+=edge.weight;
                    totalRoadCost+=edge.weight;
                }
                edgeNumber++;
            }
            int numberOfAirports = 0;
            for(int i=0;i<n;i++) {
                if(parents[i]==i) {
                    numberOfAirports++;
                }
            }
            totalCost = numberOfAirports*costOfAirport + totalRoadCost;
            System.out.println("Case #"+ti+": "+totalCost +" "+numberOfAirports);
        }
    }

    private static int getTopParent(int[] parents, int vertex) {
        if(parents[vertex]==vertex) {
            return vertex;
        }
        return getTopParent(parents,parents[vertex]);
    }

}
