package com.dsa.numbertheory.numbertheory3;

import java.util.Scanner;

public class GCDExtrem {

    static long [] phi = new long[1000001+1];
    static boolean phiPopulated = false;

    public static void populatePhi(int n) {
        if(phiPopulated) {
            return;
        }
        for (int i=1;i<=n;i++) {
            phi[i] = i;
        }
        for (int i=2;i<=n;i++) {
            if(phi[i]==i) {
                phi[i] = i - 1;
                for (int j = 2 * i; j <= n; j += i) {
                    phi[j] = (phi[j] * (i - 1)) / i;
                }
            }
        }
        phiPopulated = true;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        populatePhi(1000000);
        long ans = 0;
        long[] result = sumOfPairs(1000000);
        while (n!=0) {
            System.out.println(result[n]);
            n=in.nextInt();
        }
    }

    private static long[] sumOfPairs(int n) {
        long[] result = new long[n +1];
        for(int i = 1; i<= n; i++) {
            for (int j = 2; i*j<= n; j++) {
                result[i*j] += i*phi[j];
            }
        }
        for (int i = 2; i<= n; i++) {
            result[i]+=result[i-1];
        }
        return result;
    }
}
