package com.dsa.graph;

import java.util.*;

/**
 *  Code : All connected components
 * Send Feedback
 * Given an undirected graph G(V,E), find and print all the connected components of the given graph G.
 * Note:
 *
 * 1. V is the number of vertices present in graph G and vertices are numbered from 0 to V-1.
 * 2. E is the number of edges present in graph G.
 * 3. You need to take input in main and create a function which should return all the connected components. And then print them in the main, not inside function.
 *
 * Print different components in new line. And each component should be printed in increasing order (separated by space). Order of different components doesn't matter.
 * Input Format :
 *
 * The first line of input contains two integers, that denote the value of V and E.
 * Each of the following E lines contains two space separated integers, that denote that there exists an edge between vertex a and b.
 *
 * Output Format :
 *
 * Print different components in new line. And each component should be printed in increasing order of vertices (separated by space). Order of different components doesn't matter.
 *
 * Constraints :
 *
 * 0 <= V <= 1000
 * 0 <= E <= (V * (V - 1)) / 2
 * 0 <= a <= V - 1
 * 0 <= b <= V - 1
 *
 * Sample Input 1:
 *
 * 4 2
 * 0 1
 * 2 3
 *
 * Sample Output 1:
 *
 * 0 1
 * 2 3
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
 * 0 1 3
 * 2
 */
public class ConnectedComponents {

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
        List<List<Integer>> componentLists = new ArrayList<>();
        visitGraph(adjMatrix, n, componentLists);
        for (List<Integer> components : componentLists) {
            for (Integer vertex : components) {
                System.out.print(vertex+" ");
            }
            System.out.println();
        }
    }

    public static void visitGraph(int[][] adjMatrix, int n, List<List<Integer>> componentLists) {
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int l = 0; l < n; l++) {
            if(visited[l]) {
                continue;
            }
            queue.offer(l);
            visited[l] = true;
            List<Integer> components = new ArrayList<>();
            componentLists.add(components);
            components.add(l);
            visitGraphBFS(adjMatrix,n,visited,queue, components);
            Collections.sort(components);
        }
    }

    public static void visitGraphBFS(int[][] adjMatrix, int n, boolean[] visited, Queue<Integer> queue,List<Integer> components) {
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
                    components.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
