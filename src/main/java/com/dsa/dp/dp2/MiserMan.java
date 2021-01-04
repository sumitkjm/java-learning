package com.dsa.dp.dp2;

import java.util.Scanner;

/**
 *  Miser Man
 * Send Feedback
 * Jack is a wise and miser man. Always tries to save his money.
 * One day, he wants to go from city A to city B. Between A and B, there are N number of cities(including B and excluding A) and in each city there are M buses numbered from 1 to M. And the fare of each bus is different. Means for all N*M busses, fare (K) may be different or same. Now Jack has to go from city A to city B following these conditions:
 * 1. At every city, he has to change the bus.
 * 2. And he can switch to only those buses which have number either equal or 1 less or 1 greater to the previous.
 * You are to help Jack to go from A to B by spending the minimum amount of money.
 * N, M, K <= 100.
 * Input
 *
 * Line 1:    N M
 *
 * Line 2:    NxM Grid
 *
 * Each row lists the fares the M busses to go form the current city to the next city.
 *
 * Output
 *
 * Single Line containing the minimum amount of fare that Jack has to give.
 *
 * Sample Input
 *
 * 5 5
 * 1  3  1  2  6
 * 10 2  5  4  15
 * 10 9  6  7  1
 * 2  7  1  5  3
 * 8  2  6  1  9
 *
 * Sample Output
 *
 * 10
 */
public class MiserMan {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int [][] arr = new int[n][m];
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(getMinFairAmt(arr, n, m));
    }

    private static int getMinFairAmt(int[][] arr, int n, int m) {
        int[][] dp = new int[n][m];
        // fill last row of dp array as same value
            for (int j=0;j<m;j++) {
                dp[n-1][j] = arr[n-1][j];
            }
        for(int i=n-2;i>=0;i--) {
            for(int j=0;j<m;j++) {
                if(j==0) {
                    dp[i][j] = arr[i][j]+min(dp[i + 1][j],dp[i+1][j+1]);
                } else if(j==m-1) {
                    dp[i][j] = arr[i][j]+min(dp[i+1][j-1],dp[i+1][j]);
                } else {
                    dp[i][j] = arr[i][j]+min(
                            min(dp[i+1][j-1], dp[i+1][j]), dp[i+1][j+1]);
                }
            }
        }
        int minValue = Integer.MAX_VALUE;
        for (int j=0;j<m;j++) {
            if(minValue>dp[0][j]) {
                minValue = dp[0][j];
            }
        }
        return minValue;
    }

    public static int min(int a, int b) {
        return a>b?b:a;
    }

}
