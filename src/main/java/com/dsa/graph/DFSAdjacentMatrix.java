package com.dsa.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * prints all vertices of the connected graph using Defth First Search(DFS) Algorithm
 * input:
 * first line contains number of vertices separated by number of edges
 * second line contains vertices
 * third line onwards contains all edges
 * output:
 * vertices of the graph
 *
 * Example:
 * input:
 * 4 3
 * 1 0 2 3
 * 0 1
 * 1 2
 * 2 3
 * output:
 * 0 1 2 3
 */
public class DFSAdjacentMatrix {

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
        boolean [] visited = new boolean[n];
        printDFS(adjMatrix,n,0,visited);
    }

    public static void print(int[][] adjMatrix, int n) {
        boolean [] visited = new boolean[n];
        for (int l = 0; l < n; l++) {
            if(visited[l]) {
                continue;
            }
            visited[l] = true;
            printDFS(adjMatrix,n,l,visited);
        }
    }


    public static void printDFS(int[][]adjMatrix, int n, int sv, boolean[] visited) {
        System.out.println(sv);
        visited[sv] = true;
        for (int i=0;i<n;i++) {
            // to avoid sv to sv connection
            if(i==sv) {
                continue;
            }
            // if sv and i both are connected then visit that node further
            if(adjMatrix[sv][i]==1) {
                // if i'th is already visited then continue
                if(visited[i]) {
                    continue;
                }
                printDFS(adjMatrix, n, i, visited);
            }
        }
    }
}
