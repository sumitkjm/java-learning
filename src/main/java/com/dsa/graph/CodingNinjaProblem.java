package com.dsa.graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Coding Ninjas
 * Send Feedback
 * Given a NxM matrix containing Uppercase English Alphabets only. Your task is to tell if there is a path in the given matrix which makes the sentence “CODINGNINJA” .
 * There is a path from any cell to all its neighbouring cells. For a particular cell, neighbouring cells are those cells that share an edge or a corner with the cell.
 * Input Format :
 *
 * The first line of input contains two space separated integers N and M, where N is number of rows and M is the number of columns in the matrix.
 * Each of the following N lines contain M characters. Please note that characters are not space separated.
 *
 * Output Format :
 *
 * Print 1 if there is a path which makes the sentence “CODINGNINJA” else print 0.
 *
 * Constraints :
 *
 * 1 <= N <= 1000
 * 1 <= M <= 1000
 * Time Limit: 1 second
 *
 * Sample Input 1:
 *
 * 2 11
 * CXDXNXNXNXA
 * XOXIXGXIXJX
 *
 * Sample Output 1:
 *
 * 1
 */
public class CodingNinjaProblem {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String[] takeInput() throws IOException {
        String[] strNums;
        strNums = br.readLine().split("\\s");
        int size = 2;
        int[] input = new int[size];
        for (int i = 0; i < size; ++i) {
            input[i] = Integer.parseInt(strNums[i]);
        }


        String[] Graph = new String[input[0]];

        for (int i = 0; i < input[0]; ++i) {
            Graph[i] = br.readLine();
        }

        return Graph;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {


        String[] Graph = takeInput();
        System.out.println(new CodingNinjaProblem().solve(Graph,Graph.length,Graph[0].length()));


    }

    int solve(String[] graph , int n, int m)
    {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        String codingNinja = "CODINGNINJA";
        boolean ans = isWordTracaiable(graph,n,m,codingNinja);
        return ans?1:0;
    }

    public static boolean isWordTracaiable(String[] graph, int n, int m, String codingNinja) {
        boolean [][] visited = new boolean[n][m];
        for (int l = 0; l < n; l++) {
            for (int k =0;k<m;k++) {
                if(visited[l][k]) {
                    continue;
                }
                if(graph[l].charAt(k)==codingNinja.charAt(0)) {
                    visited[l][k] = true;
                    boolean ans = isWordTracaiable(graph,n-1,m-1,l, k,visited, codingNinja,1);
                    if(ans) {
                        return ans;
                    }
                }
            }
        }
        return false;
    }


    public static boolean isWordTracaiable(String[] graph, int n, int m, int vi, int vj, boolean[][] visited, String codingNinja, int wi) {
        if(wi>=codingNinja.length()) {
            return true;
        }
        // visit all direction
        boolean ans = false;
        if(vj<m && graph[vi].charAt(vj+1)==codingNinja.charAt(wi) && !visited[vi][vj+1]) {
            visited[vi][vj+1] = true;
            ans = isWordTracaiable(graph,n, m, vi,vj+1, visited,codingNinja,wi+1);
            if(ans) {
                return ans;
            }
        }
        if(vj>0&&graph[vi].charAt(vj-1)==codingNinja.charAt(wi)&& !visited[vi][vj-1]) {
            visited[vi][vj-1] = true;
            ans = isWordTracaiable(graph,n, m, vi,vj-1, visited,codingNinja,wi+1);
            if(ans) {
                return ans;
            }
        }
        if(vi<n&&graph[vi+1].charAt(vj)==codingNinja.charAt(wi)&& !visited[vi+1][vj]) {
            visited[vi+1][vj] = true;
            ans = isWordTracaiable(graph,n, m, vi+1,vj, visited,codingNinja,wi+1);
            if(ans) {
                return ans;
            }
        }
        if(vi>0&&graph[vi-1].charAt(vj)==codingNinja.charAt(wi)&& !visited[vi-1][vj]) {
            visited[vi-1][vj] = true;
            ans = isWordTracaiable(graph,n, m, vi-1,vj, visited,codingNinja,wi+1);
            if(ans) {
                return ans;
            }
        }
        if(vi<n && vj<m && graph[vi+1].charAt(vj+1)==codingNinja.charAt(wi)&& !visited[vi+1][vj+1]) {
            visited[vi+1][vj+1] = true;
            ans = isWordTracaiable(graph,n, m, vi+1,vj+1, visited,codingNinja,wi+1);
            if(ans) {
                return ans;
            }
        }
        if(vi>0&&vj>0&&graph[vi-1].charAt(vj-1)==codingNinja.charAt(wi) && !visited[vi-1][vj-1]) {
            visited[vi-1][vj-1] = true;
            ans = isWordTracaiable(graph,n, m, vi-1,vj-1, visited,codingNinja,wi+1);
            if(ans) {
                return ans;
            }
        }
        if(vi>0&&vj<m&&graph[vi-1].charAt(vj+1)==codingNinja.charAt(wi)&& !visited[vi-1][vj+1]) {
            visited[vi-1][vj+1] = true;
            ans = isWordTracaiable(graph,n, m, vi-1,vj+1, visited,codingNinja,wi+1);
            if(ans) {
                return ans;
            }
        }
        if(vi<n&&vj>0&&graph[vi+1].charAt(vj-1)==codingNinja.charAt(wi)&& !visited[vi+1][vj-1]) {
            visited[vi+1][vj-1] = true;
            return isWordTracaiable(graph,n, m, vi+1,vj-1, visited,codingNinja,wi+1);
        }
        return false;
    }


}
