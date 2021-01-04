package com.dsa.dp;

import java.util.Scanner;

/**
 *  Count BSTs
 * Send Feedback
 * Given an integer N, find and return the count of unique Binary search trees (BSTs) are possible with nodes valued from 1 to N.
 * Output count can be very large, so return the count modulo 10^9+7.
 * Input Format :
 *
 * Integer n
 *
 * Output Format :
 *
 * Count of BSTs
 *
 * Contraints :
 * 1<= N <=1000
 * Sample Input 1:
 *
 * 8
 *
 * Sample Output 1:
 *
 * 1430
 *
 * Sample Input 2:
 *
 * 3
 *
 * Sample Output 2:
 *
 * 5
 */
public class CountBSTs {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        System.out.println(countTrees(x));
    }

    public static int countTrees(int x) {
        //Your code goes here
        long[] dp = new long[x+1];
        dp[0] = 1;
        dp[1] = 1;
//        dp[2] = 2;
        int m = (int)Math.pow(10,9)+ 7;
        for(int n=2;n<=x;n++) {
            long count = 0;
            for(int k=1;k<=n;k++) {
                count += (dp[k-1]%m)*(dp[n-k]%m);
            }
            dp[n] = count;
        }
        return (int)dp[x]%m;

    }


}
