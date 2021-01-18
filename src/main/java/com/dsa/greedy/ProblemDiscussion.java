package com.dsa.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *  Problem discussion
 * Send Feedback
 * Harshit gave Aahad an array of size N and asked to minimize the difference between the maximum value and minimum value by modifying the array under the condition that each array element either increase or decrease by k(only once).
 * It seems difficult for Aahad so he asked for your help
 * Input Format
 *
 * The First line contains two space-separated integers: N,K
 * Next lines contain N space-separated integers denoting elements of the array
 *
 * Output Format
 *
 * The output contains a single integer denoting the minimum difference between maximum value and the minimum value in the array
 *
 * Constraints
 *
 * 1 =< N <= 10^5
 * 1 <= Ai,K <= 10^9
 *
 * Sample Input1:
 *
 * 3 6
 * 1 15 10
 *
 * Sample Output1:
 *
 * 5
 *
 * Explaination
 *
 * We change from 1 to 6, 15 to  9 and 10 to 4. Maximum difference is 5 (between 4 and 9). We can't get a lower difference.
 */
public class ProblemDiscussion {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        Integer[] arr = new Integer[n];
        for(int i=0;i<n;i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr,Collections.reverseOrder());
        int min = arr[0]+k;
        int max = arr[n-1]-k;
        if(min>max) {
            int temp = min;
            min = max;
            max = temp;
        }
        for (int i=1;i<n-1;i++) {
            // if item +k <=max or item-k >=min then continue you should not change min and max
            if(arr[i]+k<=max || arr[i]-k>=min) {
                continue;
            }
            // if we add then max will value will change so consider this as new max
            int newMax = arr[i]+k;
            // if we subtract then min will value will change and consider this as new min
            int newMin = arr[i]-k;
            if(newMax-min<max-newMin) {
                // that means we should choose newMax instead of newMin
                max = newMax;
            } else {
                // we will choose newMin and existing Max
                min = newMin;
            }
        }
        System.out.println(max-min);

    }

}
