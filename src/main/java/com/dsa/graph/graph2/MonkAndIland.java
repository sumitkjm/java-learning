package com.dsa.graph.graph2;

import java.util.*;

/**
 *  Monk and the Islands
 * Send Feedback
 * MONK AND THE ISLAND
 *
 * Monk visits the land of Islands. There are a total of N islands numbered from 1 to N. Some pairs of islands are connected to each other by Bidirectional bridges running over water.
 * Monk hates to cross these bridges as they require a lot of efforts. He is standing at Island #1 and wants to reach the Island #N. Find the minimum the number of bridges that he shall have to cross, if he takes the optimal route.
 *
 * Input:
 *
 * First line contains T. T testcases follow.
 * First line of each test case contains two space-separated integers N, M.
 * Each of the next M lines contains two space-separated integers X and Y , denoting that there is a bridge between Island X and Island Y.
 *
 * Output:
 *
 * Print the answer to each test case in a new line.
 *
 * Constraints:
 *
 * 1 ≤ T ≤ 10
 * 1 ≤ N ≤ 10000
 * 1 ≤ M ≤ 100000
 * 1 ≤ X, Y ≤ N
 *
 * SAMPLE INPUT
 *
 * 2
 * 3 2
 * 1 2
 * 2 3
 * 4 4
 * 1 2
 * 2 3
 * 3 4
 * 4 2
 *
 * SAMPLE OUTPUT
 *
 * 2
 * 2
 */
public class MonkAndIland {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        while (t-- > 0) {
            int V = in.nextInt();
            int E = in.nextInt();
//            int[][] adjMatrix = new int[V][V];
            Vector<Integer>[] edgesV = new Vector[V];
            for (int k = 0; k < E; k++) {
                int s = in.nextInt() - 1;
                int e = in.nextInt() - 1;
//                adjMatrix[s][e] = 1;
//                adjMatrix[e][s] = 1;
                Vector<Integer> edge = edgesV[s];
                if (edge == null) {
                    edge = new Vector<>();
                    edgesV[s] = edge;
                }
                Vector<Integer> edge1 = edgesV[e];
                if (edge1 == null) {
                    edge1 = new Vector<>();
                    edgesV[e] = edge1;
                }
                edgesV[s].add(e);
                edgesV[e].add(s);
            }
            boolean[] visited = new boolean[V];
            int[] level = new int[V];
            level[0] = 1;
            visitGraph(edgesV,V,level);
            System.out.println(level[V - 1]);
        }
    }

    public static void visitGraph(Vector[] edges, int n, int[] level) {
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int l = 0; l < n; l++) {
            if(visited[l]) {
                continue;
            }
            queue.offer(l);
            visited[l] = true;
            level[l] = 0;
            visitGraphBFS(edges,n,visited,queue, level);
        }
    }

    public static void visitGraphBFS(Vector[] edges, int n, boolean[] visited, Queue<Integer> queue,int[] level) {
        while (!queue.isEmpty()){
            int vertex = queue.poll();
            // get all edges starting from vertex
            Vector<Integer> edgesFromVertex = edges[vertex];
            if(edgesFromVertex==null) {
                continue;
            }
            for (Integer v : edgesFromVertex) {
                if(visited[v]) {
                    continue;
                }
                queue.offer(v);
                level[v] = level[vertex] +1;
                visited[v] = true;
            }
        }
    }
}
