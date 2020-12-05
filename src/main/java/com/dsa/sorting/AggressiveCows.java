package com.dsa.sorting;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class AggressiveCows {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int t = Integer.valueOf(in.nextLine().trim());
        for(int i=0;i<t;i++) {
            String[] input = in.nextLine().split(" ");
            int size = Integer.valueOf(input[0]);
            int nCow = Integer.valueOf(input[1]);
            int [] arr = new int[size];
            for(int j=0;j<size;j++) {
                arr[j] = Integer.valueOf(in.nextLine());
            }
            Arrays.sort(arr);
            int maxD = arr[size-1] - arr[0];
            System.out.println(largestMinDistance(arr,nCow, size, 0, maxD, 1));
        }

    }

    static int largestMinDistance(int [] arr, int nCow, int n, int minD, int maxD, int ans) {
        if(maxD<minD) {
            return ans;
        }
        int mid = (maxD+minD)/2;
        int nCowL = nCow;
        nCowL--;
        int cowP = 0;
        boolean cowAssigned = false;
        for(int i=1;i<n;i++) {
            if(arr[i]-arr[cowP]>=mid) {
                cowP = i;
                nCowL--;
            }
            if(nCowL==0) {
                cowAssigned = true;
                ans = mid;
                break;
            }
        }
        if(cowAssigned) {
            return largestMinDistance(arr, nCow, n, mid+1,maxD, ans);
        } else {
            return largestMinDistance(arr, nCow, n, 0, mid-1, ans);
        }
    }
}
