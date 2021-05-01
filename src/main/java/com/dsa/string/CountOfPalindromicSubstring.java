package com.dsa.string;

import java.util.Scanner;

/**
 *  Palindromic Substrings
 * Send Feedback
 * Given a string S, count and return the number of substrings of S that are palindrome.
 * Single length substrings are also palindromes. You just need to calculate the count, not the substrings.
 * Input Format :
 *
 * String S
 *
 * Output Format :
 *
 * count of palindrome substrings
 *
 * Constraints :
 * 1 <= Length of S <= 1000
 * Sample Input :
 *
 * aba
 *
 * Sample Output :
 *
 * 4
 *
 * **Note : Here 4 corresponds to ("a","b","a","aba").
 */
public class CountOfPalindromicSubstring {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        int count = countPalindromeSubstrings(input);
        System.out.println(count);
    }


    public static int countPalindromeSubstrings(String str) {
        // Write your code here
        int count = 0;
        for(int i=1;i<str.length();i++) {
            int j = i-1;
            int k = i+1;
            while (j>=0&&k<str.length()&&str.charAt(j)==str.charAt(k)) {
                j--;
                k++;
                count++;
            }
            if(i<str.length()-1&&str.charAt(i)==str.charAt(i+1)) {
                count++;
                j = i-1;
                k = i+2;
                while (j>=0&&k<str.length()&&str.charAt(j)==str.charAt(k)) {
                    j--;
                    k++;
                    count++;
                }
            }

        }
        return count+str.length();

    }

}
