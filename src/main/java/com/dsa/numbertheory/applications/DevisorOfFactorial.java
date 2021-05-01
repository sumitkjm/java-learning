package com.dsa.numbertheory.applications;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Divisors Of Factorial
 * Send Feedback
 * Given a number, find the total number of divisors of the factorial of the number.
 * Since the answer can be very large, print answer modulo 10^9+7.
 * Input
 *
 * The first line contains T, number of testcases.
 *
 *
 * T lines follows each containing the number N.
 *
 * Output
 *
 * Print T lines of output each containing the answer.
 *
 * Constraints
 *
 * 1 <= T <= 500
 *
 * 0 <= N <= 50000
 *
 * Sample Input:
 *
 * 3
 * 2
 * 3
 * 4
 *
 * Sample Output:
 *
 * 2
 * 4
 * 8
 */
public class DevisorOfFactorial {

    static int m = (int)Math.pow(10,9)+7;

    public static List<Integer> getPrimeNumbers(int n) {
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
        List<Integer> primeNums = new ArrayList<>();
        // starting from 2 to not include 1 as prime number
        for (int i=2;i<=n;i++) {
            if(arr[i]) {
                primeNums.add(i);
            }
        }
        return primeNums;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        List<Integer> primNums = getPrimeNumbers(50000);
        while (t-->0) {
            int n = in.nextInt();

            long totalCount = 1;
            for (Integer p : primNums) {
                long totalpCount = 0;
                for (int i=1;i*p<=n;i++) {
                    long pCount = n/(int)Math.pow(p,i);
                    totalpCount = addPCounts(pCount, totalpCount);
                }
                totalCount = mulTotalPCounts(totalpCount,totalCount);
            }
            System.out.println(totalCount);
        }
    }

    public static long mulTotalPCounts(long totalPCounts, long totalCount) {
        return ((totalCount %m) * (totalPCounts+1)%m)%m;
    }

    public static long addPCounts(long pCount, long totalCount) {
        return (totalCount%m + pCount%m)%m;
    }
}
