package com.dsa.dp.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  LCS - Problem
 * Send Feedback
 * Given two strings, S and T with lengths M and N, find the length of the 'Longest Common Subsequence'.
 * For a string 'str'(per se) of length K, the subsequences are the strings containing characters in the same relative order as they are present in 'str,' but not necessarily contiguous. Subsequences contain all the strings of length varying from 0 to K.
 * Example :
 *
 * Subsequences of string "abc" are:  ""(empty string), a, b, c, ab, bc, ac, abc.
 *
 * Input format :
 *
 * The first line of input contains the string S of length M.
 *
 * The second line of the input contains the String T of length N.
 *
 * Output format :
 *
 * Print the length of the 'Longest Common Subsequence'.
 *
 * Constraints :
 *
 * 0 <= M <= 10 ^ 3
 * 0 <= N <= 10 ^ 3
 *
 * Time Limit: 1sec
 *
 * Sample Input 1 :
 *
 * adebc
 * dcadb
 *
 * Sample Output 1 :
 *
 * 3
 *
 * Explanation of the Sample Input 1 :
 *
 * Both the strings contain a common subsequence 'adb', which is the longest common subsequence with length 3.
 *
 * Sample Input 2 :
 *
 * ab
 * defg
 *
 * Sample Output 2 :
 *
 * 0
 *
 * Explanation of the Sample Input 2 :
 *
 * The only subsequence that is common to both the given strings is an empty string("") of length 0.
 */
public class LCSProblem {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {

        String s = br.readLine().trim();
        String t = br.readLine().trim();


        System.out.println(LCSProblem.lcsIterative(s, t));
        System.out.println(LCSProblem.lcs2(s, t));

    }

    public static int lcs(String s, String t) {
        //Your code goes here
        if(s==null||s.equals("")||t==null||t.equals("")) {
            return 0;
        }
        if(s.charAt(0)==t.charAt(0)) {
            return 1 + lcs(s.length()==1?"":s.substring(1),t.length()==1?"":t.substring(1));
        } else {
            int opt1 = lcs(s.length()==1?"":s.substring(1),t);
            int opt2 = lcs(s,t.length()==1?"":t.substring(1));
            return opt1>opt2?opt1:opt2;
        }
    }

    public static int lcs2(String s, String t) {
        //Your code goes here
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++) {
            for (int j=0;j<=n;j++) {
                dp[i][j] = -1;
            }
        }
        return lcs2Helper(s,t,m,n,dp);
    }

    public static int lcs2Helper(String s, String t, int m, int n, int [][]dp) {
        if(m==0||n==0) {
            return 0;
        }
        if(dp[m][n]>-1) {
            return dp[m][n];
        }
        int ans = 0;
        if(s.charAt(0)==t.charAt(0)) {
            ans = 1 + lcs2Helper(s.length()==1?"":s.substring(1),t.length()==1?"":t.substring(1),m-1,n-1,dp);
        } else {
            int opt1 = lcs2Helper(s.length()==1?"":s.substring(1),t,m-1,n,dp);
            int opt2 = lcs2Helper(s,t.length()==1?"":t.substring(1),m,n-1,dp);
            ans = opt1>opt2?opt1:opt2;
        }
        dp[m][n] = ans;
        return ans;
    }


    public static int lcsIterative(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        // fill first row with 0
        for(int i=0;i<=n;i++) {
            dp[0][i] = 0;
        }
        // fill first column with 0
        for(int i=0;i<=m;i++) {
            dp[i][0] = 0;
        }

        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(s.substring(m-i).charAt(0)==t.substring(n-j).charAt(0)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    static int max(int a, int b) {
        return a>b?a:b;
    }


}
