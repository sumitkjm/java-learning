package com.dsa.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Minimum Count
 * Send Feedback
 * Given an integer N, find and return the count of minimum numbers required to represent N as a sum of squares.
 * That is, if N is 4, then we can represent it as : {1^2 + 1^2 + 1^2 + 1^2} and {2^2}. The output will be 1, as 1 is the minimum count of numbers required to represent N as sum of squares.
 * Input format :
 *
 * The first and the only line of input contains an integer value, 'N'.
 *
 *  Output format :
 *
 * Print the minimum count of numbers required.
 *
 * Constraints :
 *
 * 0 <= n <= 10 ^ 4
 *
 * Time Limit: 1 sec
 *
 * Sample Input 1 :
 *
 * 12
 *
 * Sample Output 1 :
 *
 * 3
 *
 * Explanation of Sample Output 1 :
 *
 * 12 can be represented as :
 * A) (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1)
 *
 * B) (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1) + (1^1)  + (2 ^ 2)
 *
 * C) (1^1) + (1^1) + (1^1) + (1^1) + (2 ^ 2) + (2 ^ 2)
 *
 * D) (2 ^ 2) + (2 ^ 2) + (2 ^ 2)
 *
 * As we can see, the output should be 3.
 *
 * Sample Input 2 :
 *
 * 9
 *
 * Sample Output 2 :
 *
 * 1
 */
public class MinCountSqRoot {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int n = Integer.parseInt(br.readLine().trim());
        System.out.println(minCount(n));
    }

    public static int minCount(int n) {
        //Your code goes here
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[0] = 1;
        for(int i=2;i<=n;i++) {
            int sqRt = (int)Math.sqrt(i);
            dp[i] = i;
            int possibleAns = i;
            for(int j=sqRt;j>1;j--) {
                int sq = j*j;
                if(sq==i) {
                    possibleAns = 1;
                    break;
                }
                if(sq>n) {
                    break;
                }
                if(n%sq==0) {
                    if(n/sq<possibleAns) {
                        possibleAns = n/sq;
                    }
                } else {
                    if(n/sq+dp[n%sq]<possibleAns) {
                        possibleAns = n/sq + dp[n%sq];
                    }
                }
            }
            if(dp[i]>possibleAns) {
                dp[i] = possibleAns;
            }
        }
        return dp[n];
    }

}
