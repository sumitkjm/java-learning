package com.dsa.graph.graph2;

import java.util.Scanner;
import java.util.Arrays;

public class Solution {

    static class Edge implements Comparable<Edge> {
        int startVertex;
        int endVertex;
        int weight;

        @Override
        public int compareTo(Edge o) {
            if(o==null) {
                return 0;
            }
            if(this.weight<o.weight) {
                return -1;
            } else if(this.weight>o.weight) {
                return 1;
            }
            return 0;
        }
        public String toString() {
            if(startVertex<endVertex) {
                return startVertex+" "+endVertex+" "+ weight;
            } else {
                return endVertex+" "+startVertex+" "+ weight;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();

        /* Write Your Code Here
         * Complete the Rest of the Program
         * You have to take input and print the output yourself
         */
        Edge[] edges = new Edge[e];
        for (int i=0;i<e;i++) {
            Edge edge = new Edge();
            edge.startVertex = sc.nextInt();
            edge.endVertex = sc.nextInt();
            edge.weight = sc.nextInt();
            edges[i] = edge;
        }
        Arrays.sort(edges);
        int[] parents = new int[n];
        for (int i=0;i<n;i++) {
            parents[i] = i;
        }
        int count  = 0;
        int edgeNumber = 0;
        while (count<n-1) {
            Edge edge = edges[edgeNumber];
            int startParent = getTopParent(parents,edge.startVertex);
            int endParent = getTopParent(parents,edge.endVertex);
            if(startParent!=endParent) {
                System.out.println(edge);
                parents[edge.startVertex] = endParent;
                count++;
            }
            edgeNumber++;
        }
    }


    private static int getTopParent(int[] parents, int vertex) {
        if(parents[vertex]==vertex) {
            return vertex;
        }
        return getTopParent(parents,parents[vertex]);
    }


}