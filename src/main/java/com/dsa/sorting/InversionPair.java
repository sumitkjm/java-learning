package com.dsa.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InversionPair {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static long[] takeInput() throws NumberFormatException, IOException {
        int n = Integer.parseInt(br.readLine().trim());
        long[] arr = new long[n];

        if (n == 0) {
            return arr;
        }

        String[] input = br.readLine().trim().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(input[i]);
        }

        return arr;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        long[] arr = takeInput();
        System.out.println(InversionPair.getInversions(arr));
        for (int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static long getInversions(long arr[]) {
        //Your code goes here
        return getTotalInversions(arr, arr.length, 0, arr.length-1);
    }

    static long getTotalInversions(long[] arr, int n, int start, int end) {
        if((end-start)==0){
                return 0;
        }
        int mid = (start+end)/2;
        long inv1 =  getTotalInversions(arr, n,start,mid);
        long inv2 =  getTotalInversions(arr, n, mid+1,end);
        long [] tempArray = new long[end-start+1];
        long invCount = 0;
        int k = 0;
        int j = mid+1;
        int i=start;
        System.out.print("first sub array ");
        for(int l=start;l<=mid;l++) {
            System.out.print(arr[l]+" ");
        }
        System.out.print("second sub array ");
        for(int l=mid+1;l<=end;l++) {
            System.out.print(arr[l]+" ");
        }
        for(;i<=mid&&j<=end;) {
            if(arr[i]<arr[j]) {
                tempArray[k] = arr[i];
                i++;
            } else {
                // inversion
                tempArray[k] = arr[j];
                j++;
                invCount = invCount + (mid - i + 1);
            }
            k++;
        }
        while(j<=end) {
            tempArray[k] = arr[j];
            j++;
            k++;
        }
        while(i<=mid) {
            tempArray[k] = arr[i];
            i++;
            k++;
        }
        for(i=0,j=start;i<tempArray.length;i++,j++) {
            arr[j] = tempArray[i];
        }
        System.out.println("inv1:"+inv1+"inv2:"+inv2+"invCount:"+invCount);
        return inv1+inv2+invCount;
    }


}
