package com.dsa.graph.graph2;

import java.util.*;

/**
 *  Prim's Algorithm
 * Send Feedback
 *
 * ID: 1724
 * Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
 * Find and print the Minimum Spanning Tree (MST) using Prim's algorithm.
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
 * 0 1 3
 * 1 2 1
 * 0 3 5
 */
public class PrimAlgorithm {

    static class Edge implements Comparable<KruskalAglorithm.Edge> {
        Integer startVertex;
        Integer endVertex;
        Integer weight;

        @Override
        public int compareTo(KruskalAglorithm.Edge o) {
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
        Scanner s = new Scanner(System.in);
        int V = s.nextInt();
        int E = s.nextInt();

        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */

        int[][] adjMatrix = new int[V][V];
        Edge[] edges = new Edge[E];
        for (int k=0;k<E;k++) {
            int i = s.nextInt();
            int j = s.nextInt();
            int w = s.nextInt();
            adjMatrix[i][j] = w;
            adjMatrix[j][i] = w;
            Edge edge = new Edge();
            edge.startVertex = i;
            edge.endVertex = j;
            edge.weight = w;
            edges[k] = edge;
        }
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        for (int i=0;i<V;i++) {
            parent[i] = -1;
        }
//        Map<Integer, List<Integer>> neighboursVerticesMap = getNeighboursVertices(edges);
        int[] weight = new int[V];
        weight[0] = 0;
        for (int i=1;i<V;i++) {
            weight[i] = Integer.MAX_VALUE;
        }
        int count = 0;
        while (count<V-1) {
            // choose the vertex which has minimum weight
            int minWeightVertex = getMinWeightVertex(weight,visited);
            visited[minWeightVertex] = true;
            // get it's unvisited neighbours
//            List<Integer> unvisitedNeighbours = getNeighboursVertices(neighboursVerticesMap,visited,minWeightVertex);
            List<Integer> unvisitedNeighbours = getNeighboursUnvisited(adjMatrix,visited,minWeightVertex);

            // find the edges between minWeightVertex and it's unvisited Neighbours and
            // compare its previous weight with new one
            // if new one is less than previous update their parent and weight by new one
            for (int unvisitedNeighbour : unvisitedNeighbours) {
                if(adjMatrix[minWeightVertex][unvisitedNeighbour]<weight[unvisitedNeighbour]) {
                    weight[unvisitedNeighbour] = adjMatrix[minWeightVertex][unvisitedNeighbour];
                    parent[unvisitedNeighbour] = minWeightVertex;
                }
            }
            count++;
        }
        for (int i=1;i<V;i++) {
            if(i<parent[i]) {
                System.out.println(i+" "+parent[i]+" "+adjMatrix[i][parent[i]]);
            } else {
                System.out.println(parent[i]+" "+i+" "+adjMatrix[i][parent[i]]);
            }
        }
    }

    private static List<Integer> getNeighboursUnvisited(int[][]adjMatrix, boolean[] visited, int vertex) {
        List<Integer> neighbours = new ArrayList<>();
        for (int i=0;i<adjMatrix.length;i++) {
            if(visited[i] || adjMatrix[vertex][i]==0) {
                continue;
            }
            neighbours.add(i);
        }
        return neighbours;
    }

    private static int getMinWeightVertex(int[] weight, boolean[] visited) {
        int minWeight = Integer.MAX_VALUE;
        int minWeightVertex = 0;
        for (int i=0;i<weight.length;i++) {
            if(minWeight>weight[i] && !visited[i]) {
                minWeight = weight[i];
                minWeightVertex = i;
            }
        }
        return minWeightVertex;
    }

    private static int getMinWeightVertex2(int[] weight, boolean[] visited) {
        int minWeightVertex = -1;
        for (int i=0;i<weight.length;i++) {
            if(!visited[i] && ((minWeightVertex==-1) ||weight[minWeightVertex]>weight[i]) ) {
                minWeightVertex = i;
            }
        }
        return minWeightVertex;
    }


    private static List<Integer> getNeighboursVertices(Map<Integer, List<Integer>> neighboursVerticesMap, boolean[] visited, int vertex) {
        List<Integer> neighbourList = neighboursVerticesMap.get(vertex);
        List<Integer> list = new ArrayList<>();
        for (int v : neighbourList) {
            if(!visited[v]) {
                list.add(v);
            }
        }
        return list;
    }


    private static Map<Integer, List<Integer>> getNeighboursVertices(Edge[] edges) {
        Map<Integer, List<Integer>> neighbourEdgeMap = new HashMap<>();
        for(int i=0;i<edges.length;i++) {
            List<Integer> list = neighbourEdgeMap.get(edges[i].startVertex);
            if(list==null) {
                list = new ArrayList<>();
                neighbourEdgeMap.put(edges[i].startVertex,list);
            }
            list.add(edges[i].endVertex);
            list = neighbourEdgeMap.get(edges[i].endVertex);
            if(list==null) {
                list = new ArrayList<>();
                neighbourEdgeMap.put(edges[i].endVertex,list);
            }
            list.add(edges[i].startVertex);
        }
        return neighbourEdgeMap;
    }
}
