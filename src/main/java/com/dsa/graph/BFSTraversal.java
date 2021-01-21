package com.dsa.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 *  Code : BFS Traversal
 * Send Feedback
 * Given an undirected and disconnected graph G(V, E), print its BFS traversal.
 * Note:
 *
 * 1. Here you need to consider that you need to print BFS path starting from vertex 0 only.
 * 2. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
 * 3. E is the number of edges present in graph G.
 * 4. Take graph input in the adjacency matrix.
 * 5. Handle for Disconnected Graphs as well
 *
 * Input Format :
 *
 * The first line of input contains two integers, that denote the value of V and E.
 * Each of the following E lines contains space separated two integers, that denote that there exists an edge between vertex a and b.
 *
 * Output Format :
 *
 * Print the BFS Traversal, as described in the task.
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
 * 0 1 3 2
 */
public class BFSTraversal {
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
        print(adjMatrix, n);
    }

    public static void print(int[][] adjMatrix, int n) {
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int l = 0; l < n; l++) {
            if(visited[l]) {
                continue;
            }
            queue.offer(l);
            visited[l] = true;
            printBFS(adjMatrix,n,visited,queue);
        }
    }

    public static void printBFS(int[][] adjMatrix, int n, boolean[] visited, Queue<Integer> queue) {
        while (!queue.isEmpty()){
            int vertex = queue.poll();
            System.out.print(vertex+" ");
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
    }


    public static void printBFS2(int[][] adjMatrix, int n, boolean[] visited, Queue<Integer> queue) {
        for (int l=0;l<n;l++) {
            if(queue.isEmpty()) {
                // start explore another vertex which has not yet visited
                // this should happen for disconnected graphs
                for (int j=0;j<n;j++) {
                    if(visited[j]) {
                        continue;
                    }
                    queue.offer(j);
                    visited[j] = true;
                    break;
                }
            }
            int vertex = queue.poll();
            System.out.print(vertex+" ");
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
    }
}
