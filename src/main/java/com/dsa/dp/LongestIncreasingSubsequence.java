package com.dsa.dp;

import java.util.Scanner;

public class LongestIncreasingSubsequence {

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = in.nextInt();
        }
        int [] lisArr = new int[n];
        System.out.println(lis(arr,lisArr,n));
    }

    public static int lis(int[] arr, int[] lisArr, int n) {
        if(n==1) {
            return 1;
        }
        int lis = lis(arr, lisArr, n-1);
        lisArr[n-2] = lis;
        int finalLis = 1;

        for(int i=n-2;i>=0;i--) {
            if(arr[n-1]>arr[i]) {
                if(lisArr[i]+1>finalLis) {
                    finalLis = lisArr[i] +1;
                }
            }
        }
        return finalLis;
    }
}
