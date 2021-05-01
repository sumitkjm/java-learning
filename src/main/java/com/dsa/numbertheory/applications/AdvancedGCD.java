package com.dsa.numbertheory.applications;

import java.util.Scanner;

/**
 *  Advanced GCD
 * Send Feedback
 * Varun explained its friend Sanchit the algorithm of Euclides to calculate the GCD of two numbers. Then Sanchit implements the algorithm
 *
 * int gcd(int a, int b)
 * {
 *
 *     if (b==0)
 *     return a;
 *     else
 *     return gcd(b,a%b);
 * }
 *
 * and challenges to Varun to calculate gcd of two integers, one is a little integer and other integer has 250 digits.
 * Your task is to help Varun an efficient code for the challenge of Sanchit.
 * Input
 *
 * The first line of the input file contains a number representing the number of lines to follow. Each line consists of two number A and B (0 <= A <= 40000 and A <= B < 10^250).
 *
 * Output
 *
 * Print for each pair (A,B) in the input one integer representing the GCD of A and B..
 *
 * Sample Input:
 *
 * 2
 * 2 6
 * 10 11
 *
 * Sample Output:
 *
 * 2
 * 1
 */
public class AdvancedGCD {

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


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            int a = in.nextInt();
            String b = in.next();
            if(a==0) {
                System.out.println(a);
                continue;
            }
            // for example b = "2351" and a =40
            // break b into this format = b%a, by breaking b as string into 10s multiples
            System.out.println(gcd(a,getModOfString(b,b.length()-1,a)));
        }
    }

    public static int getModOfString(String str, int index, int a) {
        // base case
        if(index==0) {
            return Integer.valueOf(String.valueOf(str.charAt(0)))%a;
        }
        return ((getModOfString(str,index-1,a)*10) %a + Integer.valueOf(String.valueOf(str.charAt(index))) %a) %a;
    }
}
