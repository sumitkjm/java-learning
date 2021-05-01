package com.dsa.graph.graph2;

import java.util.*;

public class BiPartiteGraph {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n!=0) {
            int nEdges = in.nextInt();
            Vector<Integer>[] edgesV = new Vector[n];
            for (int i = 0; i < nEdges; i++) {
                int s = in.nextInt() - 1;
                int e = in.nextInt() - 1;
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
            if(isBiPartiteGraph(n,edgesV)) {
                System.out.println("BI_PARTITE_GRAPH");
            } else  {
                System.out.println("NOT BI_PARTITE_GRAPH");
            }
            n = in.nextInt();
        }

    }

    public static boolean isBiPartiteGraph(int n, Vector<Integer>[] edges) {
        if(n==0) {
            return true;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer>[] sets = new HashSet[2];
        sets[0] = new HashSet<>();
        sets[1] = new HashSet<>();
        sets[0].add(0);
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            Vector<Integer> neighbors = edges[vertex];
            int currentSet = sets[0].contains(vertex)?0:1;
            for (Integer neighbor : neighbors) {
                if(!sets[0].contains(neighbor) && !sets[1].contains(neighbor)) {
                    sets[1-currentSet].add(neighbor);
                    queue.add(neighbor);
                } else if(sets[currentSet].contains(neighbor)) {
                    return false;
                }
            }
        }
        return true;
    }
}
