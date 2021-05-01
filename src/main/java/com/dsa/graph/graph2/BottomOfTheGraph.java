package com.dsa.graph.graph2;

import java.util.*;

/**
 *  BOTTOM
 * Send Feedback
 *
 * We will use the following (standard) definitions from graph theory. Let V be a non-empty and finite set, its elements being called vertices (or nodes). Let E be a subset of the Cartesian product V×V, its elements being called edges. Then G=(V,E) is called a directed graph.
 *
 * Let n be a positive integer, and let p=(e1,…,en) be a sequence of length n of edges ei∈E such that ei=(vi,vi+1)for a sequence of vertices (v1,…,vn+1). Then p is called a path from vertex v1 to vertex vn+1 in G and we say that vn+1 is reachable from v1, writing (v1→vn+1).
 *
 * Here are some new definitions. A node v in a graph G=(V,E) is called a sink, if for every node w in G that is reachable from v, v is also reachable from w. The bottom of a graph is the subset of all nodes that are sinks, i.e., bottom(G)={v∈V∣∀w∈V:(v→w)⇒(w→v)}. You have to calculate the bottom of certain graphs.
 *
 * Input Specification
 *
 * The input contains several test cases, each of which corresponds to a directed graph G. Each test case starts with an integer number v, denoting the number of vertices of G=(V,E) where the vertices will be identified by the integer numbers in the set V={1,…,v}. You may assume that 1≤v≤5000. That is followed by a non-negative integer e and, thereafter, e pairs of vertex identifiers v1,w1,…,ve,we with the meaning that (vi,wi)∈E. There are no edges other than specified by these pairs. The last test case is followed by a zero.
 *
 * Output Specification
 *
 * For each test case output the bottom of the specified graph on a single line. To this end, print the numbers of all nodes that are sinks in sorted order separated by a single space character. If the bottom is empty, print an empty line.
 *
 * Sample Input
 *
 * 3 3
 * 1 3 2 3 3 1
 * 2 1
 * 1 2
 * 0
 *
 * Sample Output
 *
 * 1 3
 * 2
 */
public class BottomOfTheGraph {
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
            List<Integer> bottomList = new ArrayList<>();
            for (List<Integer> connectedComponent : connectedComponents) {
                boolean existInAnotherComponent = false;
                for (Integer v : connectedComponent) {
                    Vector<Integer> verticesWithEdgeV = edgesV[v];
                    if(verticesWithEdgeV == null) {
                        continue;
                    }
                    for (Integer vertexWithEdgeV : verticesWithEdgeV) {
                        if(!connectedComponent.contains(vertexWithEdgeV)) {
                            existInAnotherComponent = true;
                            break;
                        }
                    }
                    if(existInAnotherComponent) {
                        break;
                    }
                }
                if(!existInAnotherComponent) {
                    bottomList.addAll(connectedComponent);
                }
            }
            if(bottomList.isEmpty()) {
                System.out.println();
            } else {
                printComponent(bottomList);
            }
            n = in.nextInt();
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
