package com.dsa.graph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Code : Get Path - DFS
 * Send Feedback
 * Given an undirected graph G(V, E) and two vertices v1 and v2(as integers), find and print the path from v1 to v2 (if exists). Print nothing if there is no path between v1 and v2.
 * Find the path using DFS and print the first path that you encountered.
 * Note:
 *
 * 1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
 * 2. E is the number of edges present in graph G.
 * 3. Print the path in reverse order. That is, print v2 first, then intermediate vertices and v1 at last.
 * 4. Save the input graph in Adjacency Matrix.
 *
 * Input Format :
 *
 * The first line of input contains two integers, that denote the value of V and E.
 * Each of the following E lines contains two integers, that denote that there exists an edge between vertex 'a' and 'b'.
 * The following line contain two integers, that denote the value of v1 and v2.
 *
 * Output Format :
 *
 * Print the path from v1 to v2 in reverse order.
 *
 * Constraints :
 *
 * 2 <= V <= 1000
 * 1 <= E <= (V * (V - 1)) / 2
 * 0 <= a <= V - 1
 * 0 <= b <= V - 1
 * 0 <= v1 <= 2^31 - 1
 * 0 <= v2 <= 2^31 - 1
 * Time Limit: 1 second
 *
 * Sample Input 1 :
 *
 * 4 4
 * 0 1
 * 0 3
 * 1 2
 * 2 3
 * 1 3
 *
 * Sample Output 1 :
 *
 * 3 0 1
 *
 * Sample Input 2 :
 *
 * 6 3
 * 5 3
 * 0 1
 * 3 4
 * 0 3
 *
 * Sample Output 2 :
 */
public class GetPathDFS {
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
        int sv = in.nextInt();
        int se = in.nextInt();
        boolean [] visited = new boolean[n];
//        printDFS(adjMatrix,n,0,visited);
        String path = getPath(adjMatrix,n,sv,se,visited);
        if(path!=null) {
            System.out.println(path.trim());
        }
    }

    public static String getPath(int[][]adjMatrix, int n, int sv, int se, boolean[] visited) {
        if(sv==se && !visited[se]) {
            return se+" ";
        }
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
                String pathStr = getPath(adjMatrix, n, i,se, visited);
                if(pathStr==null) {
                    continue;
                } else {
                    return pathStr + sv+" ";
                }
            }
        }
        return null;
    }

    public static void print(int[][] adjMatrix, int n) {
        boolean [] visited = new boolean[n];
        for (int l = 0; l < n; l++) {
            if(visited[l]) {
                continue;
            }
            visited[l] = true;
            printDFS(adjMatrix,n,l,visited);
        }
    }


    public static void printDFS(int[][]adjMatrix, int n, int sv, boolean[] visited) {
        System.out.println(sv);
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
                printDFS(adjMatrix, n, i, visited);
            }
        }
    }

}
