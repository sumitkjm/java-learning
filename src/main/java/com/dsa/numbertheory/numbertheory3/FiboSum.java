package com.dsa.numbertheory.numbertheory3;

import java.util.Scanner;

/**
 *  Fibonacci Sum
 * Send Feedback
 * The fibonacci sequence is defined by the following relation:
 *
 *  F(0) = 0
 *  F(1) = 1
 *  F(N) = F(N - 1) + F(N - 2), N >= 2
 *
 * Your task is very simple. Given two non-negative integers N and M (N <= M), you have to calculate and return the sum (F(N) + F(N + 1) + ... + F(M)) mod 1000000007.
 * Input Format :
 *
 * Two non-negative integers N and M. (N <= M)
 *
 * Output Format :
 *
 * A single line containing the answer for the task.
 *
 * Sample Input :
 *
 * 10
 * 19
 *
 * Sample Output :
 *
 * 10857
 */
public class FiboSum {

    static int mod = 1000000007;

    public static void multiply(long[][] A, long[][]B) {
        long a00 = ((A[0][0]*B[0][0])%mod + (A[0][1]*B[1][0])%mod)%mod;
        long a01 = ((A[0][0]*B[0][1])%mod + (A[0][1]*B[1][1])%mod)%mod;
        long a10 =  ((A[1][0]*B[0][0])%mod + (A[1][1]*B[1][0])%mod)%mod;
        long a11 = ((A[1][0]*B[0][1])%mod + (A[1][1]*B[1][1])%mod)%mod;
        A[0][0] = a00;
        A[0][1] = a01;
        A[1][0] = a10;
        A[1][1] = a11;
    }

    public static void power(long[][]A, long n) {
        if(n==0||n==1) {
            return;
        }
        power(A, n/2);

        // multiplying A, n/2 and n/2
        multiply(A,A);
        if(n%2!=0) {
            long [][] M = {{1,1},{1,0}};
            multiply(A,M);
        }
    }

    public static long fib(long n) {
        if(n==0) {
            return 0;
        }
        long [][] A = {{1,1},{1,0}};
        power(A, n-1);
        return A[0][0];
    }


    public static long fiboSum(long n, long m) {
        // Write your code here
        // Sum of numbers from n to m will be
        // = F(m+2) - F(n+1)
        // sum of fibo numbers
        // Sn = Fn+2 -1
        if(n==m) {
            return fib(n+2)%m -1;
        }
        long a = fib(m+2);
        long b = fib(n+1);
        // handle negative scenario
        long sum = (a%mod - b%mod) + mod;
        return (int)(sum%mod);
    }



    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long m,n;
        n = s.nextLong();
        m = s.nextLong();
        System.out.println(fiboSum(m,n));

    }
}
