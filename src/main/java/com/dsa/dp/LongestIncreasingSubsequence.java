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
        System.out.println(lisIterative(arr));
    }

    public static int lisIterative(int[] arr) {
        int n = arr.length;
        int [] dp = new int[n];
        dp[0] = 1;
        for (int i=1;i<n;i++ ) {
            int possibleLis = 1;
            for (int j = i-1;j>=0;j--) {
                if(arr[i]<=arr[j]) {
                    continue;
                }
                if((dp[j]+1)>possibleLis) {
                    possibleLis = dp[j] +1;
                }
            }
            dp[i] = possibleLis;
        }
        int lis = 1;
        for (int i=0;i<n;i++) {
            if(dp[i]>lis) {
                lis = dp[i];
            }
        }
        return lis;
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
