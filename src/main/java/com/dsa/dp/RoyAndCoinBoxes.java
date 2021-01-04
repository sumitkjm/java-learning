package com.dsa.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Roy and Coin Boxes
 * Send Feedback
 *
 * Roy has N coin boxes numbered from 1 to N.
 * Every day he selects two indices [L,R] and adds 1 coin to each coin box starting from L to R (both inclusive).
 * He does this for M number of days.
 *
 * After M days, Roy has a query: How many coin boxes have atleast X coins.
 * He has Q such queries.
 *
 * Input
 *
 * First line contains N - number of coin boxes.
 * Second line contains M - number of days. Each of the next M lines consists of two space separated integers L and R. Followed by integer Q - number of queries.
 * Each of next Q lines contain a single integer X.a
 *
 * Output
 *
 * For each query output the result in a new line.
 *
 * Constraints
 *
 * 1 ≤ N ≤ 1000000
 *
 * 1 ≤ M ≤ 1000000
 *
 * 1 ≤ L ≤ R ≤ N
 *
 * 1 ≤ Q ≤ 1000000
 *
 * 1 ≤ X ≤ N
 *
 * Sample Input
 *
 * 7
 * 4
 * 1 3
 * 2 5
 * 1 2
 * 5 6
 * 4
 * 1
 * 7
 * 4
 * 2
 *
 * Sample Output
 *
 * 6
 * 0
 * 0
 * 4
 */
public class RoyAndCoinBoxes {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] l = new int[m];
        int[] r = new int[m];

        for(int i=0;i<m;i++) {
            l[i] = in.nextInt();
            r[i] = in.nextInt();
        }
        int q = in.nextInt();
        int [] queries = new int[q];
        for(int i=0;i<q;i++) {
            queries[i] = in.nextInt();
        }
        printQueryOutput(n,m,l,r,q,queries);
    }

    private static void printQueryOutput(int n, int m, int[] lArr, int[]rArr, int q, int[] queries) {
        int[] lArrDp = new int[1000001];
        int[] rArrDp = new int[1000001];
        for(int i=0;i<m;i++) {
            lArrDp[lArr[i]-1] +=1;
        }
        for(int i=0;i<m;i++) {
            rArrDp[rArr[i]-1] +=1;
        }

        int[] dp= new int[n+1];
        int carry = 0;
        for(int i=0;i<n;i++) {
            if(lArrDp[i]>0) {
                carry +=lArrDp[i];
            }
            dp[i] += carry;
            if(rArrDp[i]>0) {
                carry -=rArrDp[i];
            }
        }

        for(int i=0;i<n;i++) {
            System.out.print(dp[i]+" ");
        }
        int []dp2 = new int[1000001];

        for(int i=0;i<n;i++) {
            dp2[dp[i]] +=1;
        }

        for(int i=999999;i>=0;i--) {
            dp2[i] += dp2[i+1];
        }

        for(int i=0;i<q;i++) {
            System.out.println(dp2[queries[i]]);
        }

    }


}
