package com.dsa.graph;

import java.util.Scanner;

/**
 *  Code : Is Connected ?
 * Send Feedback
 * Given an undirected graph G(V,E), check if the graph G is connected graph or not.
 * Note:
 *
 * 1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
 * 2. E is the number of edges present in graph G.
 *
 * Input Format :
 *
 * The first line of input contains two integers, that denote the value of V and E.
 * Each of the following E lines contains two integers, that denote that there exists an edge between vertex a and b.
 *
 * Output Format :
 *
 * The first and only line of output contains "true" if the given graph is connected or "false", otherwise.
 *
 * Constraints :
 *
 * 0 <= V <= 1000
 * 0 <= E <= (V * (V - 1)) / 2
 * 0 <= a <= V - 1
 * 0 <= b <= V - 1
 * Time Limit: 1 second
 *
 * Sample Input 1:
 *
 * 4 4
 * 0 1
 * 0 3
 * 1 2
 * 2 3
 *
 * Sample Output 1:
 *
 * true
 *
 * Sample Input 2:
 *
 * 4 3
 * 0 1
 * 1 3
 * 0 3
 *
 * Sample Output 2:
 *
 * false
 *
 * Sample Output 2 Explanation
 *
 * The graph is not connected, even though vertices 0,1 and 3 are connected to each other but there isn’t any path from vertices 0,1,3 to vertex 2.
 */
public class IsConnectedGraph {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int nEdges = in.nextInt();
        int[][] adjMatrix = new int[n][n];
        for (int k=0;k<nEdges;k++) {
            int i = in.nextInt();
            int j = in.nextInt();
            adjMatrix[i][j] = 1;
            adjMatrix[j][i] = 1;
        }
        boolean [] visited = new boolean[n];
        if(n>0){
            visitGraphDFS(adjMatrix,n,0,visited);
        }
        boolean isConnected = true;
        for (int i=0;i<n;i++) {
            if(!visited[i]) {
                isConnected = false;
                break;
            }
        }
        System.out.println(isConnected);
    }


    public static void visitGraphDFS(int[][]adjMatrix, int n, int sv, boolean[] visited) {
//        System.out.println(sv);
        visited[sv] = true;
        for (int i=0;i<n;i++) {
            // to avoid sv to sv connection
            if(i==sv) {
                continue;
            }
            // if sv and i both are connected then visit that node further
            if(adjMatrix[sv][i]==1) {
                // if i'th is already visited then continue
                if(visited[i]) {
                    continue;
                }
                visitGraphDFS(adjMatrix, n, i, visited);
            }
        }
    }

}
