package com.dsa.numbertheory.numbertheory3;

import java.util.Scanner;

/**
 *  Innocent Swaps and His Emotions
 * Send Feedback
 * There are only three phases in Swaps life: Sleep, Play and Study. Also, there are two types of emotions Swaps experiences: Happy and Sad. Each phase of his life brings either kind of emotions.
 * The sleep and the play phase makes Swaps happy whereas the study phase makes him sad. Quite obvious, isn't it? But we know that life isn't that great, one cannot be happy all the time.
 * Swaps, being a very sensitive guy, doesn't like to mix his emotions in a particular day. So each day, he is in exactly one of the three phases.
 * Given N which denotes the number of days and K which denotes the exact number of days Swaps needs to be happy out of these N days, can you tell him in how many ways can he achieve this? Since the output value can be very large, take modulo with 1000000007(10^9+7)
 * Input:
 *
 * The first line of the input contains T, denoting the number of test cases.
 *
 * The next T lines contain two space-separated integers N and K.
 *
 * Output:
 *
 * For each test-case, output a single integer, the number of ways modulo 1000000007(10^9+7).
 *
 * Constraints:
 *
 * 1 ≤ T ≤ 10^5
 * 1 ≤ N ≤ 10^6
 * 1 ≤ K ≤ 10^6
 * K ≤ N
 *
 * Sample Input :
 *
 * 3
 * 1 1
 * 2 1
 * 3 2
 *
 * Sample Output:
 *
 * 2
 * 4
 * 12
 *
 * Explanation
 *
 * In the first test case, he needs to be happy on Day 1. Hence, answer is 2 (He can either play or sleep).
 *
 * In the second test case, he can be happy either on Day 1 or Day 2. So number of ways = 4.
 */
public class InnocentSwapsAndHisEmotions {

    public static int m = 1000000007;
    static long [] powerArr = new long[1000001];
    static long [] factArr = new long[1000001];

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

    public static long factModP(int n, int p) {
        if(n==0) {
            return 1;
        }
        if(factArr[n]!=0) {
            return factArr[n];
        }
        long fact = 1;
        for (int i=1;i<=n;i++) {
            fact = (fact * i)%p;
            factArr[i] = fact;
        }
        return factArr[n];
    }


    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        factModP(1000000,m);
        while (t-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            long a = power(2,k,m);
            long b = factModP(n,m);
            long c = power(factModP(n-k,m),m-2,m);
            long d = power(factModP(k,m),m-2,m);
            long subR1 = (a*b)%m;
            long subR2 = (c*d)%m;
            long result = (subR1 * subR2)%m;
//            long result = ((a *b)%m * (c*d)%m)%m;
            System.out.println(result);
        }

    }

}
