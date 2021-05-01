package com.dsa.string;

import java.util.Scanner;

/**
 * Longest Prefix Suffix code
 * adadabadadabadadad
 * 0 0 1 2 3 0 1 2 3 4 5 6 7 8 9 10 11 4
 */
public class LongestPrefixSuffix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String pattern = in.next();
        int[] lps = getLps(pattern);
        for (int i=0;i<pattern.length();i++) {
            System.out.print(lps[i]+" ");
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
