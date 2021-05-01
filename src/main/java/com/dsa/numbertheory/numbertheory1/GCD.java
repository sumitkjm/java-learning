package com.dsa.numbertheory.numbertheory1;

import java.util.Scanner;

/**
 * Calculate and return GCD of two given numbers x and y. Numbers are within range of Integer.
 * Input format :
 *
 * x and y (separated by space)
 *
 * Output format :
 *
 * GCD of x and y
 *
 * Sample Input 1:
 *
 * 20
 * 5
 *
 * Sample Output 1:
 *
 * 5
 *
 * Sample Input 2:
 *
 * 96
 * 14
 *
 * Sample Output 2:
 *
 * 2
 */
public class GCD {

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int a,b;
        a = s.nextInt();
        b = s.nextInt();
        System.out.println(gcd(a,b));

    }

    public static int gcd(int a,int b)
    {
        if(a<b) {
            return gcd(b,a);
        }
        if(b==0) {
             return a;
        }
        return gcd(b,a%b);
    }

}
