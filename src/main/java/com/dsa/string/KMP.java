package com.dsa.string;

import java.util.Scanner;

public class KMP {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String pattern = in.next();
        if(kmp(str, pattern)) {
            System.out.println("Matched");
        } else {
            System.out.println("NO match found");
        }
    }

    private static boolean kmp(String str, String pattern) {
        boolean isPatternMatched;
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
            isPatternMatched = true;
        } else {
            isPatternMatched = false;
        }
        return isPatternMatched;
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
