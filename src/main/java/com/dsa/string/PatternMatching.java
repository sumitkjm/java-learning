package com.dsa.string;

import java.util.Scanner;

public class PatternMatching {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String pattern = in.next();
        System.out.println(isPatternFound(str,pattern)?"Found":"NOT Found");
    }

    private static boolean isPatternFound(String str, String pattern) {
        int n = str.length();
        int m = pattern.length();
        boolean isFound = false;
        for(int i=0;i<=n-m;i++) {
            isFound = true;
            for(int j=0;j<m;j++) {
                if (pattern.charAt(j) != str.charAt(i+j)) {
                    isFound = false;
                    break;
                }
            }
            if(isFound) {
                return isFound;
            }
        }
        return false;
    }
}
