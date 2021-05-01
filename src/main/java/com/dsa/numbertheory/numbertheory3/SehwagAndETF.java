package com.dsa.numbertheory.numbertheory3;

import java.util.Scanner;

/**
 *  Sehwag And ETF
 * Send Feedback
 * Sehwag has been solving a lot of mathematical problems recently. He was learning ETF (Euler Totient Function) and found the topic quite interesting. So, he tried solving a question on ETF. He will be given two numbers L and R. He has to find the probability that the ETF of a number in the range [L, R] is divisible by a number K.
 * Input:
 *
 * The first line contains the number of test cases i.e. T.
 * The next T lines will contain three integers L, R and K.
 *
 * Output:
 *
 * Print the answer in a new line after rounding off the first 6 digits after the decimal place.
 *
 * Constraints:
 *
 * 1 <= T <= 10
 * 1 <= L <= R <= 10^12
 * 0 <= R - L <= 10^5
 * 1 <= K <= 10^6
 *
 * Sample Input:
 *
 * 3
 * 1 4 2
 * 2 5 2
 * 3 10 4
 *
 * Sample Output:
 *
 * 0.500000
 * 0.750000
 * 0.375000
 */
public class SehwagAndETF {

    static final int size = 1000001;
    // Seieve of Erotosthenes
// to compute all primes
    static void seiveOfEratosthenes(int []prime)
    {
        prime[0] = 1;
        prime[1] = 0;

        for (int i = 2; i * i < 1000001; i++)
        {

            // If prime
            if (prime[i] == 0)
            {
                for (int j = i * i; j < 1000001; j += i)
                {

                    // Mark all its multiples
                    // as non-prime
                    prime[j] = 1;
                }
            }
        }
    }

    // Function to find the probability of
// Euler's Totient Function in a given range
    static float probabiltyEuler(int []prime, int L,
                                 int R, int M)
    {
        int[] arr = new int[size];
        int []eulerTotient = new int[size];
        int count = 0;

        // Initializing two arrays
        // with values from L to R
        // for Euler's totient
        for (int i = L; i <= R; i++)
        {

            // Indexing from 0
            eulerTotient[i - L] = i;
            arr[i - L] = i;
        }

        for (int i = 2; i < 1000001; i++)
        {

            // If the current number is prime
            if (prime[i] == 0)
            {

                // Checking if i is prime factor
                // of numbers in range L to R
                for (int j = (L / i) * i; j <= R; j += i)
                {
                    if (j - L >= 0)
                    {

                        // Update all the numbers
                        // which has prime factor i
                        eulerTotient[j - L] = eulerTotient[j - L] /
                                i * (i - 1);

                        while (arr[j - L] % i == 0)
                        {
                            arr[j - L] /= i;
                        }
                    }
                }
            }
        }

        // If number in range has a
        // prime factor > Math.sqrt(number)
        for (int i = L; i <= R; i++)
        {
            if (arr[i - L] > 1)
            {
                eulerTotient[i - L] = (eulerTotient[i - L] / arr[i - L]) *
                        (arr[i - L] - 1);
            }
        }

        for (int i = L; i <= R; i++)
        {

            // Count those which are divisible by M
            if ((eulerTotient[i - L] % M) == 0)
            {
                count++;
            }
        }

        // Return the result
        return (float) (1.0 * count / (R + 1 - L));
    }

    // Driver Code
    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int []prime = new int[size];

        seiveOfEratosthenes(prime);
        while (t-->0) {
            int l = in.nextInt();
            int r = in.nextInt();
            int k = in.nextInt();
            System.out.println(String.format("%.06f", probabiltyEuler(prime, l, r, k)));
        }

    }


}
