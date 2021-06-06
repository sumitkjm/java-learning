package com.dsa.dp;

import java.util.Scanner;

/**
 * Samantha and Sam are playing a numbers game. Given a number as a string, no leading zeros, determine the sum of all integer values of substrings of the string.
 *
 * Given an integer as a string, sum all of its substrings cast as integers. As the number may become large, return the value modulo
 *
 * .
 *
 *
 * Example
 * n = 42
 * Here is a string that has 3 integer substrings: 4, 2 and 42 . Their sum is 48
 *
 *
 *
 * Function Description
 *
 * Complete the substrings function in the editor below.
 *
 * substrings has the following parameter(s):
 *
 *     string n: the string representation of an integer
 *
 * Returns
 *
 *     int: the sum of the integer values of all substrings in
 *
 * , modulo
 *
 * Input Format
 *
 * A single line containing an integer as a string, without leading zeros.
 *
 * Constraints
 *
 * Sample Input 0
 *
 * 16
 *
 * Sample Output 0
 *
 * 23
 *
 * Explanation 0
 *
 * The substrings of 16 are 16, 1 and 6 which sum to 23.
 *
 * Sample Input 1
 *
 * 123
 *
 * Sample Output 1
 *
 * 164
 *
 * Explanation 1
 *
 * The substrings of 123 are 1, 2, 3, 12, 23, 123 which sum to 164.
 */
public class SamAndSubstring {

    static int mod = 1000000007;


    public static int substrings2(String n) {
        // Write your code here
        long lastValue = Integer.valueOf(String.valueOf(n.charAt(0)));
        long sum = lastValue;
        for (int i=1;i<n.length();i++) {
            lastValue = (lastValue * 10 + (Integer.valueOf(String.valueOf(n.charAt(i)))*(i+1))%mod)%mod;
            sum = (sum + lastValue)%mod;
        }
        return (int)sum;
    }


    public static int substrings(String n) {
        // Write your code here
        int sum = 0; //Integer.valueOf(n);
        for (int i=0;i<n.length()-1;i++) {
            for (int j=0;(i+j)<n.length()-1;j++) {
                sum = (sum + ((Integer.valueOf(n.substring(j, i + j + 1)) * 10) %mod+ Integer.valueOf(String.valueOf(n.charAt(i+j + 1))))%mod)%mod;
            }
        }
        for (int i=0;i<n.length();i++) {
            sum = (sum + Integer.valueOf(String.valueOf(n.charAt(i))))%mod;
        }
        return sum;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String strNum = in.next();
        System.out.println(substrings2(strNum));
    }
}
