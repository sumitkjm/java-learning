package com.dsa.dp.dp2;

import java.util.Scanner;

/**
 *  Square Brackets
 * Send Feedback
 * You are given:
 *
 * a positive integer n,
 * an integer k, 1<=k<=n,
 * an increasing sequence of k integers 0 < s1 < s2 < ... < sk <= 2n.
 *
 * What is the number of proper bracket expressions of length 2n with opening brackets appearing in positions s1, s2,...,sk?
 * Illustration
 * Several proper bracket expressions:
 *
 * [[]][[[]][]]
 * [[[][]]][][[]]
 *
 * An improper bracket expression:
 *
 * [[[][]]][]][[]]
 *
 * There is exactly one proper expression of length 8 with opening brackets in positions 2, 5 and 7.
 * Task
 * Write a program which for each data set from a sequence of several data sets:
 * 1. reads integers n, k and an increasing sequence of k integers from input,
 * 2. computes the number of proper bracket expressions of length 2n with opening brackets appearing at positions s1,s2,...,sk,
 * 3. writes the result to output.
 * Input
 *
 * The first line of the input file contains one integer d, 1 <= d <= 10, which is the number of data sets. The data sets follow. Each data set occupies two lines of the input file. The first line contains two integers n and k separated by single space, 1 <= n <= 19, 1 <= k <= n. The second line contains an increasing sequence of k integers from the interval [1;2n] separated by single spaces.
 *
 * Output
 *
 * The i-th line of output should contain one integer - the number of proper bracket expressions of length 2n with opening brackets appearing at positions s1, s2,...,sk.
 *
 * Sample Input
 *
 * 5
 * 1 1
 * 1
 * 1 1
 * 2
 * 2 1
 * 1
 * 3 1
 * 2
 * 4 2
 * 5 7
 *
 * Sample Output
 *
 * 1
 * 0
 * 2
 * 3
 * 2
 */
public class BracketsProblem {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int dataSets = in.nextInt();

        for (int i=0;i<dataSets;i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            boolean[] seq = new boolean[2*n];
            for (int j=0;j<k;j++) {
                seq[in.nextInt()-1] = true;
            }
            int[][][] dp = new int[2*n+1][n+1][n+1];
            for (int l=0;l<=2*n;l++) {
                for(int m=0;m<=n;m++) {
                    for (int o=0;o<=n;o++) {
                        dp[l][m][o] = -1;
                    }
                }
            }
            System.out.println(getAllPossibleBrackets(n, seq,0,0,2*n,dp));
        }
    }

    private static int getAllPossibleBrackets(int n, boolean[] seq, int no, int nc,int x,int [][][]dp) {
        if(no>n||nc>n) {
            return 0;
        }
        if(no==n&&nc==n) {
            return 1;
        }
        if(dp[x][no][nc]>-1) {
            return dp[x][no][nc];
        }
        int totalCount = 0;
        int currentIndex = no+nc;
        if(no==nc || seq[currentIndex]) {
            totalCount = getAllPossibleBrackets(n, seq,no+1,nc,x-1,dp);
        } else if(no==n) {
            totalCount = getAllPossibleBrackets(n,seq,no,nc+1,x-1,dp);
        } else {
            totalCount = getAllPossibleBrackets(n, seq,no+1,nc,x-1,dp) +
                    getAllPossibleBrackets(n,seq,no,nc+1,x-1,dp);
        }
        dp[x][no][nc] = totalCount;
        return totalCount;
    }
}
