package com.dsa.graph.graph2;

import java.util.*;

public class ConnectedComponentsUsingAdjList {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int nEdges = in.nextInt();
        Vector<Integer>[] edges = new Vector[n];
        for (int k=0;k<nEdges;k++) {
            int i = in.nextInt();
            int j = in.nextInt();
            Vector<Integer> edge = edges[i-1];
            if(edge==null) {
                edge = new Vector();
                edges[i-1] = edge;
            }
            edge = edges[j-1];
            if(edge==null) {
                edge = new Vector<>();
                edges[j-1] = edge;
            }
            edges[i-1].add(j-1);
            edges[j-1].add(i-1);
        }
        List<List<Integer>> componentLists = new ArrayList<>();
        visitGraph(edges, n, componentLists);
        for (List<Integer> components : componentLists) {
            for (Integer vertex : components) {
                System.out.print(vertex+1+" ");
            }
            System.out.println();
        }
    }

    public static void visitGraph(Vector[] edges, int n, List<List<Integer>> componentLists) {
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
            visitGraphBFS(edges,n,visited,queue, components);
            Collections.sort(components);
        }
    }

    public static void visitGraphBFS(Vector[] edges, int n, boolean[] visited, Queue<Integer> queue,List<Integer> components) {
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
                components.add(v);
                visited[v] = true;
            }
        }
    }

}
