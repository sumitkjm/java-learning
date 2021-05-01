package com.dsa.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Largest Piece
 * Send Feedback
 * It's Gary's birthday today and he has ordered his favourite square cake consisting of '0's and '1's . But Gary wants the biggest piece of '1's and no '0's . A piece of cake is defined as a part which consist of only '1's, and all '1's share an edge with each other on the cake. Given the size of cake N and the cake, can you find the count of '1's in the biggest piece of '1's for Gary ?
 * Input Format :
 *
 * The first line of input contains an integer, that denotes the value of N.
 * Each of the following N lines contain N space separated integers.
 *
 * Output Format :
 *
 * Print the count of '1's in the biggest piece of '1's, according to the description in the task.
 *
 * Constraints :
 *
 * 1 <= N <= 1000
 * Time Limit: 1 sec
 *
 * Sample Input 1:
 *
 * 2
 * 1 1
 * 0 1
 *
 * Sample Output 1:
 *
 * 3
 */
public class LargestCakePiece {

    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static int dfs(String[] edge, int n) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        int maxCount = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (visited[i][j] || edge[i].charAt(j)=='0') {
                    continue;
                }
                visited[i][j] =true;
                int count = 1;
                count +=visitDfs(edge,n,i,j,visited);
                if(count>maxCount) {
                    maxCount = count;
                }
            }
        }
        return maxCount;
    }

    public static int visitDfs(String[] edge, int n, int i, int j, boolean[][] visited) {
        int ans = 0;
        if(j<n-1&&edge[i].charAt(j+1)=='1'&&!visited[i][j+1]) {
            visited[i][j+1] = true;
            ans += 1 + visitDfs(edge,n,i,j+1,visited);
        }
        if(i>0&&edge[i-1].charAt(j)=='1'&&!visited[i-1][j]) {
            visited[i-1][j] = true;
            ans += 1 + visitDfs(edge,n,i-1,j,visited);
        }
        if(j>0&&edge[i].charAt(j-1)=='1'&&!visited[i][j-1]) {
            visited[i][j-1] = true;
            ans += 1 + visitDfs(edge,n,i,j-1,visited);
        }
        if(i<n-1&&edge[i+1].charAt(j)=='1'&&!visited[i+1][j]) {
            visited[i+1][j] = true;
            ans += 1 + visitDfs(edge,n,i+1,j,visited);
        }
        return ans;
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String[] takeInput() throws IOException {
        int n = Integer.parseInt(br.readLine().trim());


        String[] edge = new String[n];
        for (int i = 0; i < n; i++) {
            edge[i] = br.readLine().replaceAll("\\s", "");
        }
        return edge;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        String[] edge = takeInput();
        int ans = dfs(edge,edge.length);
        System.out.println(ans);
    }

}
