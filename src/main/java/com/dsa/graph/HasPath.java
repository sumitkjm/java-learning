package com.dsa.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 *  Code : Has Path
 * Send Feedback
 * Given an undirected graph G(V, E) and two vertices v1 and v2 (as integers), check if there exists any path between them or not. Print true if the path exists and false otherwise.
 * Note:
 *
 * 1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
 * 2. E is the number of edges present in graph G.
 *
 * Input Format :
 *
 * The first line of input contains two integers, that denote the value of V and E.
 * Each of the following E lines contains two integers, that denote that there exists an edge between vertex 'a' and 'b'.
 * The following line contain two integers, that denote the value of v1 and v2.
 *
 * Output Format :
 *
 * The first and only line of output contains true, if there is a path between v1 and v2 and false otherwise.
 *
 * Constraints :
 *
 * 0 <= V <= 1000
 * 0 <= E <= 1000
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
 * true
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
 *
 * false
 */
public class HasPath {
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
        System.out.println(hasPath(adjMatrix,n,sv,se));
    }

    public static boolean hasPath(int[][] adjMatrix, int n, int sv, int se) {
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(sv);
        visited[sv] = true;
        return hasPathDFS(adjMatrix,n,visited,queue, se);
    }

    public static boolean hasPathDFS(int[][] adjMatrix, int n, boolean[] visited, Queue<Integer> queue, int se) {
        boolean hasPath = false;
        while (!queue.isEmpty()){
            int vertex = queue.poll();
            if(vertex==se) {
                hasPath = true;
                break;
            }
            // get all adjacent vertices of vertex and push them in queue
            for (int i=0;i<n;i++) {
                // if already visited then do not visit
                if(visited[i]) {
                    continue;
                }
                if(adjMatrix[vertex][i]==1) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        return hasPath;
    }

}
