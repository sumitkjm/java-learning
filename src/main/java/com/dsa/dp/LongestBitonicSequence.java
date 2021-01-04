package com.dsa.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Largest Bitonic Subarray
 * Send Feedback
 * You are given an array of positive integers as input. Write a code to return the length of the largest such subsequence in which the values are arranged first in strictly ascending order and then in strictly descending order.
 * Such a subsequence is known as bitonic subsequence. A purely increasing or purely decreasing subsequence will also be considered as a bitonic sequence with the other part empty.
 * Note that the elements in bitonic subsequence need not be consecutive in the given array but the order should remain same.
 * Input Format:
 *
 * Line 1 : A positive Integer N, i.e., the size of array
 * Line 2 : N space-separated integers as elements of the array
 *
 * Output Format:
 *
 * Length of Largest Bitonic subsequence
 *
 * Input Constraints:
 *
 * 1<= N <= 100000
 *
 * Sample Input 1:
 *
 * 6
 * 15 20 20 6 4 2
 *
 * Sample Output 1:
 *
 * 5
 *
 * Sample Output 1 Explanation:
 *
 * Here, longest Bitonic subsequence is {15, 20, 6, 4, 2} which has length = 5.
 *
 * Sample Input 2:
 *
 * 2
 * 1 5
 *
 * Sample Output 2:
 *
 * 2
 *
 * Sample Input 3:
 *
 * 2
 * 5 1
 *
 * Sample Output 3:
 *
 * 2
 */


public class LongestBitonicSequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(longestBitonicSubarray(arr));
    }

    // function to reverse an array
    static void revereseArr(int arr[])
    {
        int i = 0;
        int j = arr.length - 1;
        while (i < j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;j--;
        }
    }

    public static int[] lds(int[] arr) {
        int[] lbs = new int[arr.length];
        for(int i=0;i<arr.length;i++) {
            lbs[i] = 1;
        }
        for(int i=arr.length-2;i>=0;i--) {
            for(int j=i+1;j<arr.length;j++) {
                if(arr[j]>arr[i]) {
                    continue;
                }
                int possibleLds = lbs[j] +1;
                if(possibleLds>lbs[i]) {
                    lbs[i] = possibleLds;
                }
            }
        }
        int maxLds = 0;
        for (int i=0;i<lbs.length;i++) {
            if(lbs[i]>maxLds) {
                maxLds = lbs[i];
            }
        }
        return lbs;
    }

    public static int[] lis(int[] arr) {
        int [] lis = new int[arr.length];

        for(int i=0;i<arr.length;i++) {
            lis[i] = 1;
        }

        for(int i=1;i<arr.length;i++) {
            for (int j=i-1;j>=0;j--) {
                if(arr[j]>arr[i]) {
                    continue;
                }
                int possibleAns = lis[j] +1;
                if(possibleAns>lis[i]) {
                    lis[i] = possibleAns;
                }
            }
        }
        int maxLis = 0;
        for (int i=0;i<lis.length;i++) {
            if(maxLis<lis[i]) {
                maxLis = lis[i];
            }
        }
        return lis;
    }


    public static int longestBitonicSubarray(int[] arr){
        int [] lis = lis(arr);
        int [] lds = lds(arr);
        int maxLds = 0;
        for(int i=0;i<arr.length;i++) {
            if(maxLds<lis[i]+lds[i]-1) {
                maxLds = lis[i] +lds[i] -1;
            }
        }
        return maxLds;
    }



}
