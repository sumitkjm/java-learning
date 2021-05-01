package com.dsa.numbertheory.numbertheory3;

/**
 * It's the program where using matrix in O(log(n)) complexity,
 * Fibonaaci
 */
public class NthFibonaaciByMatrix {

    public static void multiply(int[][] A, int[][]B) {
        int a00 = A[0][0]*B[0][0] + A[0][1]*B[1][0];
        int a01 = A[0][0]*B[0][1] + A[0][1]*B[1][1];
        int a10 =  A[1][0]*B[0][0] + A[1][1]*B[1][0];
        int a11 = A[1][0]*B[0][1] + A[1][1]*B[1][1];
        A[0][0] = a00;
        A[0][1] = a01;
        A[1][0] = a10;
        A[1][1] = a11;
    }

    public static void power(int[][]A, int n) {
        if(n==0||n==1) {
            return;
        }
        power(A, n/2);

        // multiplying A, n/2 and n/2
        multiply(A,A);
        if(n%2!=0) {
            int [][] M = {{1,1},{1,0}};
            multiply(A,M);
        }
    }

    public static int fib(int n) {
        if(n==0) {
            return 0;
        }
        int [][] A = {{1,1},{1,0}};
        power(A, n-1);
        return A[0][0];
    }

    public static void main(String[] args) {

        System.out.println(fib(10));
    }
}
