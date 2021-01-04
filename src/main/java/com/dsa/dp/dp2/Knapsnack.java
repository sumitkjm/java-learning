package com.dsa.dp.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Knapsnack - Problem
 * Send Feedback
 * A thief is robbing a store and can carry a maximal weight of W into his knapsack. There are N items and ith item weighs wi and is of value vi. Considering the constraints of maximum weight that knapsack can carry, you have to find and return the maximum value that thief can generate by stealing items.
 * Note
 *
 * Space complexity should be O(W).
 *
 * Input Format :
 *
 * The first line contains an integers, that denote the value of N. The following line contains N space separated integers, that denote the values of weight of items. The following line contains N space separated integers, that denote the values associated with the items. The following line contains an integer that denote the value of W. W denotes maximum weight that thief can carry.
 *
 * Output Format :
 *
 * The first and only line of output contains the maximum value that thief can generate, as described in the task.
 *
 * Constraints
 *
 * 1 <= N <= 10^4
 * 1<= wi <= 100
 * 1 <= vi <= 100
 * Time Limit: 1 second
 *
 * Sample Input 1 :
 *
 * 4
 * 1 2 4 5
 * 5 4 8 6
 * 5
 *
 * Sample Output 1 :
 *
 * 13
 */
public class Knapsnack {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[] takeInput(int size) throws IOException {

        int[] input = new int[size];

        if (size == 0) {
            return input;
        }

        String[] strNums;
        strNums = br.readLine().split("\\s");

        for (int i = 0; i < size; ++i) {
            input[i] = Integer.parseInt(strNums[i]);
        }

        return input;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        int size = Integer.parseInt(br.readLine().trim());
        int[] wt = takeInput(size);
        int[] val = takeInput(size);
        int w = Integer.parseInt(br.readLine().trim());
        System.out.print(Knapsnack.knapsack(wt, val, size, w));


    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Change in the given tree itself.
         * No need to return or print the output.
         * Taking input and printing output is handled automatically.
         */
        int[][] dp = new int[n+1][maxWeight+1];
        for (int i=0;i<=n;i++) {
            for (int j =0;j<=maxWeight;j++) {
                dp[i][j] = -1;
            }
        }
        int ans= knapsack(weight,value,n,maxWeight,dp);
        return ans;

    }

    static int knapsack(int[] wt,int[]value, int n, int maxWt, int[][]dp) {
        if(n==0||maxWt<=0) {
            return 0;
        }

        if(dp[n][maxWt]>=0) {
            return dp[n][maxWt];
        }
        int ans = 0;
        if(wt[n-1] <= maxWt) {
            ans = max(value[n-1] + knapsack(wt, value, n - 1, maxWt - wt[n - 1], dp),
                    knapsack(wt, value, n - 1, maxWt, dp));
        } else {
            ans = knapsack(wt,value,n-1,maxWt,dp);
        }
        dp[n][maxWt] = ans;
        return ans;
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }


}
