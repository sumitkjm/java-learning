package com.dsa.numbertheory.numbertheory3;

import java.util.Scanner;

/**
 *  Income On Nth Day
 * Send Feedback
 * Daulat Ram is an affluent business man. After demonetization, IT raid was held at his accommodation in which all his money was seized. He is very eager to gain his money back, he started investing in certain ventures and earned out of them. On the first day, his income was Rs. X, followed by Rs. Y on the second day. Daulat Ram observed his growth as a function and wanted to calculate his income on the Nth day.
 * The function he found out was FN = FN-1 + FN-2 + FN-1×FN-2
 * Given his income on day 0 and day 1, calculate his income on the Nth day (yeah Its that simple).
 * Input:
 *
 * The first line of input consists of a single integer T denoting number of test cases.
 *
 * Each of the next T lines consists of three integers F0, F1 and N respectively.
 *
 * Output:
 *
 * For each test case, print a single integer FN, as the output can be large, calculate the answer modulo 10^9+7.
 *
 * CONSTRAINTS:
 *
 * 1 ≤ T ≤ 10^5
 *
 * 0 ≤ F0, F1, N ≤ 10^9
 *
 * Sample Input :
 *
 * 2
 * 0 1 2
 * 1 2 4
 *
 * Sample Output:
 *
 * 1
 * 107
 *
 * Explanation
 *
 * In the second test case his income on day 0 is 1 and the income on day 1 is 2. We need to calculate his income on day 4.
 *
 * F0=1
 *
 * F1=2
 *
 * F2=1 + 2 + 1×2 = 5
 *
 * F3=2 + 5 + 2×5 = 17
 *
 * F4=5 + 17 + 5×17 = 107
 */
public class IncomeOfNthDay {

    static int m = 1000000007;

    public static void multiply(long[][] A, long[][]B, int m) {
        long a00 = ((A[0][0]*B[0][0])%m + (A[0][1]*B[1][0])%m)%m;
        long a01 = ((A[0][0]*B[0][1])%m + (A[0][1]*B[1][1])%m)%m;
        long a10 =  ((A[1][0]*B[0][0])%m + (A[1][1]*B[1][0])%m)%m;
        long a11 = ((A[1][0]*B[0][1])%m + (A[1][1]*B[1][1])%m)%m;
        A[0][0] = a00;
        A[0][1] = a01;
        A[1][0] = a10;
        A[1][1] = a11;
    }

    public static void powerFib(long[][]A, long n,int m) {
        if(n==0||n==1) {
            return;
        }
        powerFib(A, n/2, m);

        // multiplying A, n/2 and n/2
        multiply(A,A,m);
        if(n%2!=0) {
            long [][] M = {{1,1},{1,0}};
            multiply(A,M, m);
        }
    }

    public static long fib(long n,int m) {
        if(n==0) {
            return 0;
        }
        long [][] A = {{1,1},{1,0}};
        powerFib(A, n-1,m);
        return A[0][0];
    }


    static long power(long x, long y, long p)
    {
        long res = 1; // Initialize result
        x = x % p;   // Update x if it is more
        // than or equal to p
        while (y > 0)
        {
            // If y is odd, multiply
            // x with result
            if ((y & 1) > 0)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            long a = in.nextLong();
            long b = in.nextLong();
            long n = in.nextLong();
            if(n==0) {
                System.out.println(a);
                continue;
            }
            long fibNMinus1 = fib(n-1,m-1);
            long fibN = fib(n,m-1);
            long subRes1 = power(a+1,fibNMinus1,m);
            long subRes2 = power(b+1,fibN,m);
            long result = (subRes1 * subRes2)%m;
            System.out.println(result-1);
        }
    }
}
