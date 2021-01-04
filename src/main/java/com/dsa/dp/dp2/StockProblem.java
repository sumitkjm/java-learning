package com.dsa.dp.dp2;

import java.util.Scanner;

/**
 *  Trader Profit
 * Send Feedback
 * Mike is a stock trader and makes a profit by buying and selling stocks. He buys a stock at a lower price and sells it at a higher price to book a profit. He has come to know the stock prices of a particular stock for n upcoming days in future and wants to calculate the maximum profit by doing the right transactions (single transaction = buying + selling). Can you help him maximize his profit?
 * Note: A transaction starts after the previous transaction has ended. Two transactions can't overlap or run in parallel.
 * The stock prices are given in the form of an array A for n days.
 * Given the stock prices and a positive integer k, find and print the maximum profit Mike can make in at most k transactions.
 * Input Format
 *
 * The first line of input contains an integer q denoting the number of queries.
 *
 * The first line of each test case contains a positive integer k, denoting the number of transactions.
 *
 * The second line of each test case contains a positive integer n, denoting the length of the array A.
 *
 * The third line of each test case contains n space-separated positive integers, denoting the prices of each day in the array A.
 *
 * Constraints
 *
 * 1<=q<=100
 *
 * 0<k<10
 *
 * 2<=n<=30
 *
 * 0<=elements of array A<=1000
 *
 * Output Format
 *
 * For each query print the maximum profit earned by Mike on a new line.
 *
 * Sample Input
 *
 * 3
 * 2
 * 6
 * 10 22 5 75 65 80
 * 3
 * 4
 * 20 580 420 900
 * 1
 * 5
 * 100 90 80 50 25
 *
 * Sample Output
 *
 * 87
 * 1040
 * 0
 */
public class StockProblem {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i=0;i<t;i++) {
            int maxTxn = in.nextInt();
            int n = in.nextInt();
            int [] arr = new int[n];
            for (int j=0;j<n;j++) {
                arr[j] = in.nextInt();
            }
            System.out.println(maxProfit(arr,n,maxTxn));
        }
    }

    private static int maxProfit(int[] arr, int n, int maxTxn) {
        int [][][] dp = new int[n+1][maxTxn+1][2];
        for (int i=0;i<=n;i++) {
            for (int j=0;j<=maxTxn;j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        int ans = max(maxProfitHelper(arr,n-1,maxTxn,1,dp,arr[n-1]),
                maxProfitHelper(arr,n-1,maxTxn,0,dp,arr[n-1]));
        for (int i=0;i<=n;i++) {
            for (int j=0;j<=maxTxn;j++) {
                System.out.print(dp[i][j][0]+" ");
                System.out.print(dp[i][j][1]+" ");
            }
            System.out.println();
        }
        return ans;
    }

    private static int maxProfitHelper(int[]arr, int n,int maxTxn, int isBuy, int[][][]dp,int stock) {
        if(maxTxn==0||n==0) {
            return 0;
        }
        if(dp[n][maxTxn][isBuy]>-1) {
//            return dp[n][maxTxn][isBuy];
        }
        // if isBuy is false that means we can sell
        if(isBuy==1) {
            if (stock - arr[n - 1] > 0) {
                // it's profit, so we should buy
                dp[n][maxTxn][isBuy] = max(stock - arr[n-1]+maxProfitHelper(arr,n-1,maxTxn-1,0, dp, arr[n-1]),
                        maxProfitHelper(arr,n-1,maxTxn,1, dp, stock));
            } else {
                dp[n][maxTxn][isBuy] = maxProfitHelper(arr,n-1,maxTxn,1, dp, stock);
            }
        } else {
           if(n==1) {
               // can not sell
               return 0;
           } else {
               dp[n][maxTxn][isBuy] = max(maxProfitHelper(arr,n-1,maxTxn,1,dp,arr[n-1]),
                       maxProfitHelper(arr, n-1,maxTxn,0,dp,stock));
           }
        }
        return dp[n][maxTxn][isBuy];
    }

    private static int max(int a, int b) {
        return a>b?a:b;
    }

}
