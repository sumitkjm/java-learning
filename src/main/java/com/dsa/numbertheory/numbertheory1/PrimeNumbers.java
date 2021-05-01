package com.dsa.numbertheory.numbertheory1;

import java.util.Scanner;

/**
 *  Find Prime Numbers From 1 to N
 * Send Feedback
 * Given a number N, find number of primes in the range [1,N].
 * Input:
 *
 * The only line of input consists of a number N
 *
 * Output:
 *
 * Print the number of primes in the range [1,N].`
 *
 * Constraints:
 *
 * 1≤N≤1000000
 *
 * Sample Input :
 *
 * 3
 *
 * Sample Output -
 *
 * 2
 *
 * Code Pair
 *
 * 1
 *
 * import java.util.Scanner;
 *
 * 2
 *
 * ​
 *
 * 3
 *
 * public class Main {
 *
 * 4
 *
 * ​
 *
 * 5
 *
 *
 *
 * 6
 *
 *     public static void main(String[] args) {
 *
 * 7
 *
 *         // Write your code here
 *
 * 8
 *
 * ​
 *
 * 9
 *
 *     }
 *
 * 10
 *
 * ​
 *
 * 11
 *
 * }
 *
 * CUSTOM INPUT
 *
 * SUBMIT SOLUTION
 *
 * PREVIOUS
 *
 * NEXT
 */
public class PrimeNumbers {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int primeNumCount = 0;
        for (int i=1;i<=n;i++) {
            if(isPrimeNumber(i)) {
                primeNumCount++;
            }
        }
        System.out.println(primeNumCount);
    }


    public static boolean isPrimeNumber(int num) {
        int sqrt = (int) Math.sqrt(num);
        double sqrtd = Math.sqrt(num);
        int factorCount = 0;
        for (int i=1;i<=sqrt;i++) {
            if(num%i==0) {
                if(i==sqrtd) {
                    factorCount++;
                } else {
                    factorCount+=2;
                }
            }
        }
        return factorCount==2?true:false;
    }

}
