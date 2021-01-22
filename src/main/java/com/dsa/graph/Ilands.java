package com.dsa.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  Islands
 * Send Feedback
 * An island is a small piece of land surrounded by water . A group of islands is said to be connected if we can reach from any given island to any other island in the same group . Given V islands (numbered from 1 to V) and E connections or edges between islands. Can you count the number of connected groups of islands.
 * Input Format :
 *
 * The first line of input contains two integers, that denote the value of V and E.
 * Each of the following E lines contains two integers, that denote that there exists an edge between vertex a and b.
 *
 * Output Format :
 *
 * Print the count the number of connected groups of islands
 *
 * Constraints :
 *
 * 0 <= V <= 1000
 * 0 <= E <= (V * (V-1)) / 2
 * 0 <= a <= V - 1
 * 0 <= b <= V - 1
 * Time Limit: 1 second
 *
 * Sample Input 1:
 *
 * 5 8
 * 0 1
 * 0 4
 * 1 2
 * 2 0
 * 2 4
 * 3 0
 * 3 2
 * 4 3
 *
 * Sample Output 1:
 *
 * 1
 */
public class Ilands {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[][] takeInput() throws IOException {
        String[] strNums;
        strNums = br.readLine().split("\\s");
        int n = Integer.parseInt(strNums[0]);
        int e = Integer.parseInt(strNums[1]);

        int[][] edges = new int[n][n];
        int firstvertex, secondvertex;

        for (int i = 0; i < e; i++) {
            String[] strNums1;
            strNums1 = br.readLine().split("\\s");
            firstvertex = Integer.parseInt(strNums1[0]);
            secondvertex = Integer.parseInt(strNums1[1]);
            edges[firstvertex][secondvertex] = 1;
            edges[secondvertex][firstvertex] = 1;
        }
        return edges;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        int [][]edges = takeInput();

        int ans = numConnected(edges, edges.length);
        System.out.println(ans);

    }

    public static int numConnected(int[][] edges, int n) {

        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        int numberOfConnectedGraphs = 0;
        for (int l = 0; l < n; l++) {
            if(visited[l]) {
                continue;
            }
            queue.offer(l);
            visited[l] = true;
            numberOfConnectedGraphs++;
            visitGraphBFS(edges,n,visited,queue);
        }
        return numberOfConnectedGraphs;
    }

    public static void visitGraphBFS(int[][] adjMatrix, int n, boolean[] visited, Queue<Integer> queue) {
        while (!queue.isEmpty()){
            int vertex = queue.poll();

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
