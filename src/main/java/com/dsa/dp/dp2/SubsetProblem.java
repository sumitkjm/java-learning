package com.dsa.dp.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Subset Sum - Problem
 * Send Feedback
 * You are given a set of N integers. You have to return true if there exists a subset that sum up to K, otherwise return false.
 * Input Format
 *
 * The first line of the test case contains an integer 'N' representing the total elements in the set.
 *
 * The second line of the input contains N integers separated by a single space.
 *
 * The third line of the input contains a single integer, denoting the value of K.
 *
 * Output Format
 *
 * Output Yes if there exists a subset whose sum is k, else output No.
 *
 * Constraints :
 *
 * 1 <= N <= 10^6
 * 1 <= a[i] <= 10^3, where a[i] denotes an element of the set
 * 1 <= K <= 10^3
 *
 * Time Limit: 1 sec
 *
 * Sample Input 1 :
 *
 * 4
 * 4 3 5 2
 * 13
 *
 * Sample Output 1 :
 *
 * No
 *
 * Sample Input 2 :
 *
 * 5
 * 4 2 5 6 7
 * 14
 *
 * Sample Output 2 :
 *
 * Yes
 */
public class SubsetProblem {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int size = Integer.parseInt(br.readLine().trim());
        int[] input = new int[size];

        if (size == 0) {
            System.out.print("No");
            return;
        }

        String[] strNums;
        strNums = br.readLine().split("\\s");

        for (int i = 0; i < size; ++i) {
            input[i] = Integer.parseInt(strNums[i]);
        }

        int sum = Integer.parseInt(br.readLine().trim());
        if (isSubsetPresent(input,size,sum)){
            System.out.print("Yes");
        }
        else{
            System.out.print("No");
        }


    }


    static boolean isSubsetPresent(int[] arr, int n, int sum) {

        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        int[][] dp = new int[n+1][sum+1];
        for (int i=0;i<=n;i++) {
            for (int j=0;j<=sum;j++) {
                dp[i][j] = -1;
            }
        }
        return isSubsetPresetHelper(arr,n,sum,dp);
    }

    static boolean isSubsetPresetHelper(int []arr, int n, int sum, int[][]dp) {
        if(n==0 || sum==0) {
            return false;
        }
        if(arr[n-1]==sum) {
            return true;
        }
        if(dp[n][sum]>-1) {
            return dp[n][sum]==0?false:true;
        }
        boolean isSubSetPresent = false;
        if(arr[n-1]<sum) {
            isSubSetPresent = isSubsetPresetHelper(arr, n-1,sum-arr[n-1],dp);
            if(isSubSetPresent) {
                System.out.print(arr[n-1]+" ");
            }

            isSubSetPresent = isSubSetPresent || isSubsetPresetHelper(arr,n-1,sum,dp);

        } else {
            isSubSetPresent = isSubsetPresetHelper(arr, n-1,sum,dp);
        }
        dp[n][sum] = isSubSetPresent?1:0;
        return isSubSetPresent;
    }

}
