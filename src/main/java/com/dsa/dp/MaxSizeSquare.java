package com.dsa.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Maximum Square Matrix With All Zeros
 * Send Feedback
 * Given an NxM matrix that contains only 0s and 1s, find out the size of the maximum square sub-matrix with all 0s. You need to return the size of the square matrix with all 0s.
 * Input format :
 *
 * The first line of the test case contains two integer values, 'N' and 'M', separated by a single space. They represent the 'rows' and 'columns' respectively.
 *
 * Second-line onwards, the next 'N' lines or rows represent the ith row values.
 *
 * Each of the ith rows constitutes column values separated by a single space (Either 0 or 1).
 *
 * Output Format:
 *
 * Print the size of maximum square sub-matrix.
 *
 *  Constraints :
 *
 * 0 <= N <= 10^4
 * 0 <= M <= 10^4
 *
 * Time Limit: 1 sec
 *
 * Sample Input 1:
 *
 * 3 3
 * 1 1 0
 * 1 1 1
 * 1 1 1
 *
 * Sample Output 1:
 *
 * 1
 *
 * Sample Input 2:
 *
 * 4 4
 * 0 0 0 0
 * 0 0 0 0
 * 0 0 0 0
 * 0 0 0 0
 *
 * Sample Output 2:
 *
 * 4
 */
public class MaxSizeSquare {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[][] takeInput() throws IOException {

        String[] nm;
        nm = br.readLine().split("\\s");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int arr[][]=new int[n][m];

        if (n == 0) {
            return arr;
        }


        String[] strNums;
        for (int i = 0; i < n; ++i) {
            strNums = br.readLine().split("\\s");
            for (int j = 0; j < m; ++j){
                arr[i][j] = Integer.parseInt(strNums[j]);
            }

        }

        return arr;
    }

    public static void main(String[] args) throws IOException {

        int[][] arr = takeInput();
        System.out.println(findMaxSquareWithAllZeros(arr));
    }

    public static int findMaxSquareWithAllZeros(int[][] arr) {
        int m = arr.length;
        if(m==0) {
            return 0;
        }
        int n = arr[0].length;
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) {
            if(arr[i][0]==0) {
                dp[i][0] = 1;
            }
        }

        for(int j=0;j<n;j++) {
            if(arr[0][j]==0) {
                dp[0][j] = 1;
            }
        }

        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                if(arr[i][j]==1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = min(dp[i][j-1],min(dp[i-1][j-1],dp[i-1][j])) +1;
            }
        }
        int maxSquareSize = Integer.MIN_VALUE;
        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if(maxSquareSize<dp[i][j]) {
                    maxSquareSize = dp[i][j];
                }
            }
        }
        return maxSquareSize;
    }

    public static int min(int a, int b) {
        return a>b?b:a;
    }


}
