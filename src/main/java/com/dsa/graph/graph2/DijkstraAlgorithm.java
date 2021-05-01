package com.dsa.graph.graph2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Dijkstra's Algorithm
 * Send Feedback
 *
 * ID: 1725
 * Given an undirected, connected and weighted graph G(V, E) with V number of vertices (which are numbered from 0 to V-1) and E number of edges.
 * Find and print the shortest distance from the source vertex (i.e. Vertex 0) to all other vertices (including source vertex also) using Dijkstra's Algorithm.
 * Input Format :
 *
 * Line 1: Two Integers V and E (separated by space)
 * Next E lines : Three integers ei, ej and wi, denoting that there exists an edge between vertex ei and vertex ej with weight wi (separated by space)
 *
 * Output Format :
 *
 * For each vertex, print its vertex number and its distance from source, in a separate line. The vertex number and its distance needs to be separated by a single space.
 *
 * Note : Order of vertices in output doesn't matter.
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
 * 0 0
 * 1 3
 * 2 4
 * 3 5
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int V = s.nextInt();
        int E = s.nextInt();

        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        int[][] adjMatrix = new int[V][V];
//        PrimAlgorithm.Edge[] edges = new PrimAlgorithm.Edge[E];
        for (int k=0;k<E;k++) {
            int i = s.nextInt();
            int j = s.nextInt();
            int w = s.nextInt();
            adjMatrix[i][j] = w;
            adjMatrix[j][i] = w;
//            PrimAlgorithm.Edge edge = new PrimAlgorithm.Edge();
//            edge.startVertex = i;
//            edge.endVertex = j;
//            edge.weight = w;
//            edges[k] = edge;
        }
        boolean[] visited = new boolean[V];
//        Map<Integer, List<Integer>> neighboursVerticesMap = getNeighboursVertices(edges);
        int[] distance = new int[V];
        distance[0] = 0;
        for (int i=1;i<V;i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        int count = 0;
        while (count<V-1) {
            // choose the vertex which has minimum weight
            int minDistanceVertex = getMinDistanceVertex(distance,visited);
            visited[minDistanceVertex] = true;
            // get it's unvisited neighbours
//            List<Integer> unvisitedNeighbours = getNeighboursVertices(neighboursVerticesMap,visited,minWeightVertex);
            List<Integer> unvisitedNeighbours = getNeighboursUnvisited(adjMatrix,visited,minDistanceVertex);

            // find the edges between minWeightVertex and it's unvisited Neighbours and
            // compare its previous weight with new one
            // if new one is less than previous, update their parent and weight by new one
            for (int unvisitedNeighbour : unvisitedNeighbours) {
                if(adjMatrix[minDistanceVertex][unvisitedNeighbour] + distance[minDistanceVertex]<distance[unvisitedNeighbour]) {
                    distance[unvisitedNeighbour] = distance[minDistanceVertex] + adjMatrix[minDistanceVertex][unvisitedNeighbour];
                }
            }
            count++;
        }
        for (int i=0;i<V;i++) {
            System.out.println(i+" "+distance[i]);
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


    private static int getMinDistanceVertex(int[] distance, boolean[] visited) {
        int minWeightVertex = -1;
        for (int i=0;i<distance.length;i++) {
            if(!visited[i] && ((minWeightVertex==-1) ||distance[minWeightVertex]>distance[i]) ) {
                minWeightVertex = i;
            }
        }
        return minWeightVertex;
    }


}


