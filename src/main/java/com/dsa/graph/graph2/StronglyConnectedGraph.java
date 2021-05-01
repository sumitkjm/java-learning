package com.dsa.graph.graph2;

import java.util.*;

/**
 * Strongly connected graphs are cyclic directed graphs
 */
public class StronglyConnectedGraph {

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n!=0) {
            int nEdges = in.nextInt();
            Vector<Integer>[] edgesV = new Vector[n];
            Vector<Integer>[] edgesVTranspose = new Vector[n];
            for (int i = 0; i < nEdges; i++) {
                int s = in.nextInt() - 1;
                int e = in.nextInt() - 1;
                Vector<Integer> edge = edgesV[s];
                if (edge == null) {
                    edge = new Vector<>();
                    edgesV[s] = edge;
                }
                Vector<Integer> edge1 = edgesVTranspose[e];
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
            visitDFSToGetConnectedGraph(edgesVTranspose, n, stack, connectedComponents);
            for (List<Integer> connectedComponent : connectedComponents) {
                printComponent(connectedComponent);
            }
        }
    }

    private static void printComponent(List<Integer> component) {
        Collections.sort(component);
        for (Integer v : component) {
            System.out.print(v+1+" ");
        }
        System.out.println();
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
        if(edge!=null) {
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
