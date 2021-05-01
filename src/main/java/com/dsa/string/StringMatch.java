package com.dsa.string;

import java.util.Scanner;

/**
 *  String Search
 * Send Feedback
 * Given two strings S and T, write a function to find if T is present as a substring inside S or not. If yes, return the starting index otherwise return -1.
 *
 * Input format :
 *
 * Line 1 : String S
 *
 * Line 2 : String T
 * Sample Input 1:
 *
 * WelcomeBack
 * come
 *
 * Sample Output 1:
 *
 * 3
 *
 * Sample Input 2:
 *
 * WelcomeBack
 * code
 *
 * Sample Output 2:
 *
 * -1
 */
public class StringMatch {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String pattern = in.next();
        System.out.println(kmp(str,pattern));
    }

    private static int kmp(String str, String pattern) {
        int strlen = str.length();
        int patternLen = pattern.length();
        int i = 0;
        int j = 0;
        int[] lps = getLps(pattern);
        while (i<strlen&&j<patternLen) {
            if(str.charAt(i)== pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if(j>0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }
        }
        if(j==patternLen) {
            return i-j;
        } else {
            return -1;
        }
    }

    private static int[] getLps(String pattern) {
        int[] lps = new int[pattern.length()];
        int j = 0;
        int i = 1;
        while (i<pattern.length()) {
            if(pattern.charAt(i)==pattern.charAt(j)) {
                lps[i] = j+1;
                i++;
                j++;
            } else {
                if(j>0) {
                    j = lps[j-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

}
