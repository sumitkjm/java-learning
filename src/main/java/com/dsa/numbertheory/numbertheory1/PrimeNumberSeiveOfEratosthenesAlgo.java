package com.dsa.numbertheory.numbertheory1;

import java.util.Scanner;

public class PrimeNumberSeiveOfEratosthenesAlgo {

      public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean [] arr = new boolean[n+1];
        for(int i=2;i<arr.length;i++) {
            arr[i] = true;
        }
        for(int i=2;i*i<=n;i++) {
            int j=i;
            while (i*j<=n) {
                arr[i*j] = false;
                j++;
            }
        }
        int primeNumCount = 0;
        for (int i=1;i<=n;i++) {
            if(arr[i]) {
                primeNumCount++;
            }
        }
        System.out.println(primeNumCount);
    }

}
