package com.dsa.dp.dp2;

import java.util.Scanner;

/**
 *  Smallest Super-Sequence
 * Send Feedback
 *  Given two strings S and T with lengths M and N. Find and return the length of their shortest 'Super Sequence'.
 *  The shortest 'Super Sequence' of two strings is defined as the smallest string possible that will have both the given strings as its subsequences.
 * Note :
 *
 * If the two strings do not have any common characters, then return the sum of the lengths of the two strings.
 *
 * Input Format:
 *
 * The first only line of input contains a string, that denotes the value of string S. The following line contains a string, that denotes the value of string T.
 *
 * Output Format:
 *
 * Length of the smallest super-sequence of given two strings.
 *
 * Constraints :
 *
 * 0 <= M <= 10 ^ 3
 * 0 <= N <= 10 ^ 3
 *
 * Time Limit: 1 sec
 *
 * Sample Input 1 :
 *
 * ab
 * ac
 *
 * Sample Output 1 :
 *
 * 3
 *
 * Explanation of Sample Output 1 :
 *
 * Their smallest super sequence can be "abc" which has length = 3.
 *
 * Sample Input 2 :
 *
 * pqqrpt
 * qerepct
 *
 * Sample Output 2 :
 *
 * 9
 */
public class SmallestSuperSubSeq {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String t = in.next();
        int n = s.length();

        System.out.println(smallestSupSeq(s,t,n));
    }

    private static int smallestSupSeq(String s, String t, int n) {
        int [][] dp = new int[n+1][n+1];
        for (int i=n;i>=0;i--) {
            dp[i][n] = n-i;
        }
        for (int i=n;i>=0;i--) {
            dp[n][i] = n-i;
        }
        for (int i=n-1;i>=0;i--) {
            for (int j=n-1;j>=0;j--) {
                if(s.charAt(i)==t.charAt(j)) {
                    dp[i][j] = 1 + dp[i+1][j+1];
                } else {
                    dp[i][j] = 1 + min(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }
    private static int min(int a, int b) {
        return a>b?b:a;
    }
}
