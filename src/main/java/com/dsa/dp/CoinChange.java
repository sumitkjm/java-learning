package com.dsa.dp;

import java.util.Scanner;

/**
 *  Coin Change Problem
 * Send Feedback
 * For the given infinite supply of coins of each of denominations, D = {D0, D1, D2, D3, ...... Dn-1}. You need to figure out the total number of ways W, in which you can make the change for Value V using coins of denominations D.
 * Return 0 if the change isn't possible.
 * Input Format
 *
 * The first line of the input contains an integer value N, which denotes the total number of denominations.
 *
 * The second line of input contains N values, separated by a single space. These values denote the value of denomination.
 *
 * The third line of the input contains an integer value, that denotes the value of V.
 *
 * Output Format
 *
 * Print the total total number of ways i.e. W.
 *
 * Constraints :
 *
 * 1 <= n <= 10
 * 1 <= V <= 1000
 *
 * Time Limit: 1sec
 *
 * Sample Input 1 :
 *
 * 3
 * 1 2 3
 * 4
 *
 * Sample Output 1 :
 *
 * 4
 *
 * Explanation to Sample Input 1 :
 *
 * Number of ways are - 4 total i.e. (1,1,1,1), (1,1, 2), (1, 3) and (2, 2).
 *
 * Sample Input 2 :
 *
 * 6
 * 1 2 3 4 5 6
 * 250
 *
 * Sample Output 2 :
 *
 * 13868903
 */

public class CoinChange {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numD = in.nextInt();
        int[] dArr = new int[numD];
        for(int i=0;i<numD;i++) {
            dArr[i] = in.nextInt();
         }

        int n = in.nextInt();

        long[][] output = new long[n+1][numD+1];
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=numD;j++) {
                output[i][j] = -1;
            }
        }
        System.out.println(change(n,dArr,numD,output));
    }

    public static long change(int n, int[] dArr, int numD, long[][] output) {
        if(n==0) {
            return 1;
        }

        if(n<0) {
            return 0;
        }
        if(numD==0) {
            return 0;
        }
        if(output[n][numD]>-1) {
            return output[n][numD];
        }
        long firstChange = change(n-dArr[0],dArr,numD,output);
        int[] copydArr = new int[numD-1];
        for (int i=1;i<dArr.length;i++) {
            copydArr[i-1] = dArr[i];
        }
        long secondChange = change(n,copydArr,numD-1,output);
        long change = firstChange + secondChange;
        output[n][numD] = change;
        return change;
    }
}
