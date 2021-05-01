package com.dsa.graph.graph2;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Kruskal's Algorithm
 * Send Feedback
 * Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
 * Find and print the Minimum Spanning Tree (MST) using Kruskal's algorithm.
 * For printing MST follow the steps -
 *
 * 1. In one line, print an edge which is part of MST in the format -
 * v1 v2 w
 * where, v1 and v2 are the vertices of the edge which is included in MST and whose weight is w. And v1  <= v2 i.e. print the smaller vertex first while printing an edge.
 * 2. Print V-1 edges in above format in different lines.
 *
 * Note : Order of different edges doesn't matter.
 * Input Format :
 *
 * Line 1: Two Integers V and E (separated by space)
 * Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
 *
 * Output Format :
 *
 * Print the MST, as described in the task.
 *
 * Constraints :
 *
 * 2 <= V, E <= 10^5
 * Time Limit: 1 sec
 *
 * Sample Input 1 :
 *
 * 4 4
 * 0 1 3
 * 0 3 5
 * 1 2 1
 * 2 3 8
 *
 * Sample Output 1 :
 *
 * 1 2 1
 * 0 1 3
 * 0 3 5
 */
public class KruskalAglorithm {

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
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();

        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        Edge[] edges = new Edge[e];
        for (int i=0;i<e;i++) {
            Edge edge = new Edge();
            edge.startVertex = sc.nextInt();
            edge.endVertex = sc.nextInt();
            edge.weight = sc.nextInt();
            edges[i] = edge;
        }
        Arrays.sort(edges);
        int[] parents = new int[n];
        for (int i=0;i<n;i++) {
            parents[i] = i;
        }
        int count  = 0;
        int edgeNumber = 0;
        while (count<n-1) {
            Edge edge = edges[edgeNumber];
            int startParent = getTopParent(parents,edge.startVertex);
            int endParent = getTopParent(parents,edge.endVertex);
            if(startParent!=endParent) {
                System.out.println(edge);
                parents[startParent] = endParent;
                count++;
            }
            edgeNumber++;
        }
    }



    private static boolean hasSameTopParent(int[] parents, Edge edge) {
        if(parents[edge.startVertex]==parents[edge.endVertex]) {
            return true;
        } else {
            if(parents[edge.startVertex]==edge.startVertex&&parents[edge.endVertex]== edge.endVertex) {
                return false;
            } else if(parents[edge.startVertex]==edge.startVertex) {
                int vertex = edge.endVertex;
                int topParent = getTopParent(parents, vertex);
                if(topParent==edge.startVertex) {
                    return true;
                } else {
                    return false;
                }
            } else if(parents[edge.endVertex]==edge.endVertex) {
                int vertex = edge.startVertex;
                int topParent = getTopParent(parents, vertex);
                if(topParent==edge.endVertex) {
                    return true;
                } else {
                    return false;
                }
            } else {
                int topParentOfStart = getTopParent(parents, edge.startVertex);
                int topParentOfEnd = getTopParent(parents,edge.endVertex);
                if(topParentOfEnd==topParentOfStart) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private static int getTopParent(int[] parents, int vertex) {
        if(parents[vertex]==vertex) {
            return vertex;
        }
        return getTopParent(parents,parents[vertex]);
    }


    private static int getTopParen2t(int[] parents, int vertex) {
        int parent = parents[vertex];
        while (parent!= vertex) {
            vertex = parent;
            parent = parents[vertex];
        }
        return parent;
    }

}
