package com.dsa.dp;

import java.util.Scanner;

/**
 *  StairCase Problem
 * Send Feedback
 * A child runs up a staircase with 'n' steps and can hop either 1 step, 2 steps or 3 steps at a time. Implement a method to count and return all possible ways in which the child can run-up to the stairs.
 * Input format :
 *
 * The first and the only line of input contains an integer value, 'n', denoting the total number of steps.
 *
 * Output format :
 *
 * Print the total possible number of ways.
 *
 *  Constraints :
 *
 * 0 <= n <= 10 ^ 4
 *
 * Time Limit: 1 sec
 *
 * Sample Input 1:
 *
 * 4
 *
 * Sample Output 1:
 *
 * 7
 *
 * Sample Input 2:
 *
 * 10
 *
 * Sample Output 2:
 *
 * 274
 */
public class StairCase {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(staircase(n));
    }

    public static long staircase(int n) {
        //Your code goes here
        long[] output = new long[n+1];
        return numOfCombs(n,output);
    }

    public static long numOfCombs(int n, long[]output) {

        if(n==0) {
            return 1;
        }
        if(n==1) {
            return 1;
        }

        if(n==2) {
            return 2;
        }

        if(output[n]>0) {
            return output[n];
        }

        long totalNum = numOfCombs(n-1, output) + numOfCombs(n-2,output)+ numOfCombs(n-3,output);
        output[n] = totalNum;
        return totalNum;

    }

}
