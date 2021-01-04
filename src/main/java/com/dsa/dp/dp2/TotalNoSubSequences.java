package com.dsa.dp.dp2;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Distinct Subsequences
 * Send Feedback
 * Given a string, count the number of distinct subsequences of it ( including empty subsequence ). For the uninformed, A subsequence of a string is a new string which is formed from the original string by deleting some of the characters without disturbing the relative positions of the remaining characters.
 * For example, "AGH" is a subsequence of "ABCDEFGH" while "AHG" is not.
 * Input
 *
 * First line of input contains an integer T which is equal to the number of test cases. You are required to process all test cases. Each of next T lines contains a string s.
 *
 * Output
 *
 * Output consists of T lines. Ith line in the output corresponds to the number of distinct subsequences of ith input string. Since, this number could be very large, you need to output ans%1000000007 where ans is the number of distinct subsequences.
 *
 * Constraints and Limits
 *
 * T ≤ 100, length(S) ≤ 100000
 *
 * All input strings shall contain only uppercase letters.
 *
 * Sample Input
 *
 * 3
 * AAA
 * ABCDEFG
 * CODECRAFT
 *
 * Sample Output
 *
 * 4
 * 128
 * 496
 */
public class TotalNoSubSequences {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i=0;i<t;i++) {
            String str = in.next();
            System.out.println(countSubSeq(str, str.length()));
        }
    }

    private static int countSubSeq(String str, int length) {
        int m = (int)Math.pow(10,9)+7;
        long[] dp = new long[length+2];
        int[] dp2 = new int[256];
        Arrays.fill(dp2,-1);
        dp[0] = 1;
        for (int i=1;i<=length;i++) {
            dp[i] = 2*dp[i-1];
            if(dp2[(int)str.charAt(i-1)]!=-1) {
                dp[i] -= dp[dp2[(int)str.charAt(i-1)]];
            }
            dp2[(int)str.charAt(i-1)] = i-1;
        }

        return (int)dp[length]%m;
    }


}
