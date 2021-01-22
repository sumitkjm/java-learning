package com.dsa.graph;

import java.util.*;

/**
 *  Code : Get Path - BFS
 * Send Feedback
 * Given an undirected graph G(V, E) and two vertices v1 and v2 (as integers), find and print the path from v1 to v2 (if exists). Print nothing if there is no path between v1 and v2.
 * Find the path using BFS and print the shortest path available.
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
 * Each of the following E lines contains two integers, that denote that there exists an edge between vertex a and b.
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
public class GethPathBFS {
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
        int ev = in.nextInt();
        Map<Integer, Integer> pathMap = getPath(adjMatrix,n,sv,ev);
        // retrieve path starting from end vertex (ev)
        Integer nextAdjVertex = pathMap.get(ev);
        if(nextAdjVertex!=null) {
            System.out.print(ev+" ");
        }
        while (nextAdjVertex!=null) {
            System.out.print(nextAdjVertex+" ");
            if(nextAdjVertex==sv) {
                break;
            }
            // retrive next vertex
            nextAdjVertex = pathMap.get(nextAdjVertex);
        }
    }

    public static Map<Integer, Integer> getPath(int[][] adjMatrix, int n, int sv, int ev) {
        Map<Integer, Integer> pathMap = new HashMap<>();
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(sv);
        visited[sv] = true;
        getPathBFS(adjMatrix,n,visited,queue, ev, pathMap);
        return pathMap;
    }

    public static void getPathBFS(int[][] adjMatrix, int n, boolean[] visited, Queue<Integer> queue, int ev, Map<Integer, Integer> pathMap) {
        while (!queue.isEmpty()){
            int vertex = queue.poll();
            // get all adjacent vertices of vertex and push them in queue
            for (int i=0;i<n;i++) {
                // if already visited then do not visit
                if(visited[i]) {
                    continue;
                }
                if(adjMatrix[vertex][i]==1) {
                    pathMap.put(i,vertex);
                    queue.offer(i);
                    visited[i] = true;
                    if(i==ev) {
                        // that means whe have reached end vertex path so return
                        return;
                    }
                }
            }
        }
    }



}
