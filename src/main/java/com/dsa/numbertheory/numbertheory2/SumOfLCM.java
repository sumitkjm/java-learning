package com.dsa.numbertheory.numbertheory2;

import java.util.Scanner;

/**
 *  LCM SUM
 * Send Feedback
 * Given n, calculate and print the sum :
 *
 * LCM(1,n) + LCM(2,n) + .. + LCM(n,n)
 *
 * where LCM(i,n) denotes the Least Common Multiple of the integers i and n.
 * Input Format :
 *
 * Line 1 : Integer n
 *
 * Output Format :
 *
 * Required sum
 *
 * Constraints :
 * 1 <= T <= 300000
 * 1 <= n <= 1000000
 * Sample Input 1 :
 *
 * 5
 *
 * Sample Output 1 :
 *
 * 55
 *
 * Sample Input 2 :
 *
 * 2
 *
 * Sample Output 2 :
 *
 * 4
 */
public class SumOfLCM {

    static long [] phi = new long[1000000+1];
    static boolean phiPopulated = false;
    static long[] ans = new long[1000000 + 2];

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

    public static long lcmSum(int m) {
        int n = 1000000;
        for (int i = 1; i <= n; i++)
        {

            // Summation of d * ETF(d) where
            // d belongs to set of divisors of n
            for (int j = i; j <= n; j += i)
            {
                ans[j] += (i * phi[i]);
            }
        }

        long answer = ans[m];
        answer = (answer + 1) * m;
        answer = answer / 2;
        return answer;
    }

    public static void func(long n)
    {
        populatePhi(1000000);
        System.out.println(lcmSum((int)n));
        // Write your code here
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n;
        n = s.nextLong();
        func(n);

    }

}
