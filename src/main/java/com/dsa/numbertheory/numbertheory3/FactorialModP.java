package com.dsa.numbertheory.numbertheory3;

import java.util.Scanner;

/**
 *  Boring Factorials
 * Send Feedback
 * Sameer and Arpit want to overcome their fear of Maths and so they have been recently practicing Maths problems a lot. Aman, their friend has been helping them out. But as it goes, Sameer and Arpit have got bored of problems involving factorials. Reason being, the factorials are too easy to calculate in problems as they only require the residue modulo some prime and that is easy to calculate in linear time. So to make things interesting for them, Aman - The Mathemagician, gives them an interesting task. He gives them a prime number P and an integer N close to P, and asks them to find N! modulo P. He asks T such queries.
 * Input
 *
 * First line contains an integer T, the number of queries asked.
 *
 * Next T lines contains T queries of the form “N P”. (quotes for clarity)
 *
 * Output
 *
 * Output exactly T lines, containing N! modulo P.
 *
 * Constraints:
 *
 * 1 <= T <= 1000
 *
 * 1 < P <= 2*10^9
 *
 * 1 <= N <= 2*10^9
 *
 * Abs(N-P) <= 1000
 *
 * Sample Input:
 *
 * 3
 * 2 5
 * 5 11
 * 21 71
 *
 * Sample Output:
 *
 * 2
 * 10
 * 6
 */
public class FactorialModP {

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


    public static long factorialModP(long p, long n) {
        if(p<=n) {
            return 0;
        }
        long result = p-1;
        for(long a=n+1;a<p;a++) {
            // applying fermat's little theorem
            // a to the power (-1) mod p = a to the power p - 2 mod p, where p is prime
            result = (result * power(a,p-2,p)) % p;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            long n = in.nextLong();
            long p = in.nextLong();
            // p> n
            System.out.println(factorialModP(p,n));
        }
    }
}
