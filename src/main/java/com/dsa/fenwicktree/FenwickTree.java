package com.dsa.fenwicktree;

import java.util.Scanner;

/**
 *
 */
public class FenwickTree {

    public static void update(int index, int value, int[] bit, int n) {
        for(;index<=n;index+=(index&(-index))) {
            bit[index]+=value;
        }
    }

    public static int query(int index, int[] bit) {
        int sum =0;
        for(;index>0;index-=index&(-index)) {
            sum+=bit[index];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n+1];
        int[] bit = new int[n+1];
        for(int i=1;i<=n;i++) {
            arr[i] = in.nextInt();
            update(i,arr[i],bit,n);
        }
        System.out.println("sum of first 5 elements="+query(5,bit));
        System.out.println("Sum of elements from 2 index to 6 index="+(query(6,bit)-query(1,bit)));
    }
}
