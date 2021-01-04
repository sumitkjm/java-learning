package com.dsa.dp.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Edit Distance - Problem
 * Send Feedback
 * You are given two strings S and T of lengths M and N, respectively. Find the 'Edit Distance' between the strings.
 * Edit Distance of two strings is the minimum number of steps required to make one string equal to the other. In order to do so, you can perform the following three operations:
 *
 * 1. Delete a character
 * 2. Replace a character with another one
 * 3. Insert a character
 *
 * Note :
 *
 * Strings don't contain spaces in between.
 *
 *  Input format :
 *
 * The first line of input contains the string S of length M.
 *
 * The second line of the input contains the String T of length N.
 *
 * Output format :
 *
 * Print the minimum 'Edit Distance' between the strings.
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
 * abc
 * dc
 *
 * Sample Output 1 :
 *
 * 2
 *
 *  Explanation to the Sample Input 1 :
 *
 *  In 2 operations we can make string T to look like string S.
 * First, insert character 'a' to string T, which makes it "adc".
 *
 * And secondly, replace the character 'd' of string T with 'b' from the string S. This would make string T as "abc" which is also string S.
 *
 * Hence, the minimum distance.
 *
 * Sample Input 2 :
 *
 * whgtdwhgtdg
 * aswcfg
 *
 * Sample Output 2 :
 *
 * 9
 */
public class EditDistance {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {

        String s = br.readLine().trim();
        String t = br.readLine().trim();


        System.out.println(EditDistance.editDistance(s, t));
    }

    public static int editDistance(String s, String t) {
        //Your code goes here
        int m = s.length();
        int n = t.length();
        int [][] dp = new int[m+1][n+1];
        for (int i=0;i<=m;i++) {
            dp[i][0] = i;
        }

        for (int j=0;j<=n;j++) {
            dp[0][j] = j;
        }

        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(s.substring(m-i).charAt(0)==t.substring(n-j).charAt(0)) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
                dp[i][j] = min(1+dp[i-1][j-1],min(1+dp[i-1][j],1+dp[i][j-1]));
            }
        }
        return dp[m][n];
    }
    public static int min(int a, int b) {
        return a<b?a:b;
    }


}
