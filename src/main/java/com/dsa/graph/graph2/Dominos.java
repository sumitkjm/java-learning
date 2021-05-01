package com.dsa.graph.graph2;

import java.util.*;

/**
 *  Dominos
 * Send Feedback
 * Domino
 *
 * Dominos are lots of fun. Children like to stand the tiles on their side in long lines. When one domino falls, it knocks down the next one, which knocks down the one after that, all the way down the line.
 * However, sometimes a domino fails to knock the next one down. In that case, we have to knock it down by hand to get the dominos falling again.
 * Your task is to determine, given the layout of some domino tiles, the minimum number of dominos that must be knocked down by hand in order for all of the dominos to fall.
 *
 * Input
 *
 * The first line of input contains one integer specifying the number of test cases to follow.Each test case begins with a line containing two integers,each no larger than 100 000. The first integer n is the number of domino tiles and the second integer m is the number of lines to follow in the test case. The domino tiles are numbered from 1 to n.
 * Each of the following lines contains two integers x and y indicating that if domino number x falls, it will cause domino number y to fall as well.
 *
 * Output
 *
 * For each test case, output a line containing one integer, the minimum number of dominos that must be knocked over by hand in order for all the dominos to fall.
 *
 * Sample Input
 *
 * 1
 * 3 2
 * 1 2
 * 2 3
 *
 * Sample Output
 *
 * 1
 */
public class Dominos {
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int ti=0;ti<t;ti++) {
            int n = in.nextInt();
            int nEdges = in.nextInt();
            Vector<Integer>[] edgesV = new Vector[n];
            Vector<Integer>[] edgesVTranspose = new Vector[n];
            for (int i = 0; i < nEdges; i++) {
                int s = in.nextInt()-1;
                int e = in.nextInt()-1;
                Vector<Integer> edge = edgesV[s];
                if (edge == null) {
                    edge = new Vector<>();
                    edgesV[s] = edge;
                }
                Vector edge1 = edgesVTranspose[e];
                if (edge1 == null) {
                    edge1 = new Vector<>();
                    edgesVTranspose[e] = edge1;
                }
                edgesV[s].add(e);
                edgesVTranspose[e].add(s);
            }
            Stack<Integer> stack = new Stack<>();
            visitDFS(edgesV, n, stack);
            List<List<Integer>> connectedComponents = new ArrayList<>();
            // use transposed graph, stack do DFS and retrieve connected components
            visitDFSToGetConnectedGraph(edgesV, n, stack, connectedComponents);
            System.out.println(connectedComponents.size());
        }
    }

    private static void visitDFSToGetConnectedGraph(Vector<Integer>[] edges, int n, Stack<Integer> stack, List<List<Integer>> connectedComponents) {
        boolean[] visited = new boolean[n];
        while (!stack.isEmpty()) {
            Integer v = stack.pop();
            if(visited[v]) {
                continue;
            }
            List<Integer> connectedComponent = new ArrayList<>();
            connectedComponents.add(connectedComponent);
            dfsToGetConnectedGraph(edges, visited, v, connectedComponent);
        }
    }

    private static void dfsToGetConnectedGraph(Vector<Integer>[] edges, boolean[] visited, int node, List<Integer> connectedComponent) {
        visited[node] = true;
        connectedComponent.add(node);
        Vector<Integer> edge = edges[node];
        if(edge==null||edge.isEmpty()) {
            return;
        }
        for(Integer v : edge) {
            if(visited[v]) {
                continue;
            }
            dfsToGetConnectedGraph(edges,visited,v,connectedComponent);
        }
    }

    private static void visitDFS(Vector<Integer>[] edges, int n, Stack<Integer> stack) {
        boolean[] visited = new boolean[n];
        for (int i=0;i<n;i++) {
            if(visited[i]) {
                continue;
            }
            dfs(edges, stack, visited, i);
        }
    }

    private static void dfs(Vector<Integer>[] edges,Stack<Integer> stack, boolean[] visited, int node) {
        visited[node] = true;
        Vector<Integer> edge = edges[node];
        if (edge != null) {
            for (Integer v : edge) {
                if (visited[v]) {
                    continue;
                }
                dfs(edges, stack, visited, v);
            }
        }
        stack.push(node);
    }

}
