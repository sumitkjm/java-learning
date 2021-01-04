package com.dsa.dp.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Shortest Subsequence
 * Send Feedback
 * Gary has two string S and V. Now, Gary wants to know the length shortest subsequence in S, which is not a subsequence in V.
 * Note: The input data will be such that there will always be a solution.
 * Input Format :
 *
 * The first line of input contains a string, that denotes the value of S. The following line contains a string, that denotes the value of V.
 *
 * Output Format :
 *
 * Length of shortest subsequence in S such that it is not a subsequence in V
 *
 * Constraints:
 *
 * 1 <= N <= 1000
 * 1 <= M <= 1000
 * Time Limit: 1 second
 *
 * Sample Input 1:
 *
 * babab
 * babba
 *
 * Sample Output 1:
 *
 * 3
 *
 * Explanation:
 *
 * "aab" is the shortest subsequence which is present in S and absent in V.
 */
public class ShortestSubsequence {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String S,V;


        S = br.readLine();
        V = br.readLine();
        System.out.println(new ShortestSubsequence().solve(S,V));

    }

    public int solve(String S, String V) {
        int[][] dp = new int[S.length()+1][V.length()+1];
        for (int i=0;i<=S.length();i++) {
            for (int j=0;j<=V.length();j++) {
                dp[i][j] = -1;
            }
        }
        return solveHelper(S,V,dp);
    }

    public int solveHelper(String S,String V,int[][]dp){

        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        if(S.equals("") && V.equals("")) {
            return 0;
        }
        if(S.equals("") && V.length()>0) {
//            System.out.println("here");
            return V.length()+1;
        }
        if(S.length()>0&&V.equals("")) {
            return S.length();
        }
        if(dp[S.length()][V.length()]>-1) {
            return dp[S.length()][V.length()];
        }
        // include first character in subsequence
        // then find first occurrence of first character in Second string
        int occ = -1;
        for(int i=0;i<V.length();i++) {
            if(V.charAt(i)==S.charAt(0)) {
                occ = i;
                break;
            }
        }
        String SN = S.substring(1);
        String VN = "";
        if(occ!=-1) {
            VN = V.substring(occ+1);
        } else {
            return 1;
        }
        int min = 0;
           min = min(
                    // include first character in subsequence
                    1+ solve(S.substring(1),VN),

                    // not include first character in subsequence
                    solve(S.substring(1),V)
            );
        dp[S.length()][V.length()] = min;
        return min;

    }

    public int min(int a, int b) {
        return a>b?b:a;
    }

}
