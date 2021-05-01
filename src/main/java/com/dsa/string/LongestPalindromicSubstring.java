package com.dsa.string;

import java.util.Scanner;

public class LongestPalindromicSubstring {

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        int longestSubstr = getLongestSubstr(str);
        int longestSubStrOpt = getLongestSubstrOpt(str);
        System.out.println("longestSubstr="+longestSubstr+" longestSubStrOpt="+longestSubStrOpt);
    }

    // Complexity is order of O(n2)
    private static int getLongestSubstrOpt(String str) {
        int longestSubStr = 1;
        for(int i=1;i<str.length();i++) {
            int j = i-1;
            int k = i+1;
            while (j>=0&&k<str.length()&&str.charAt(j)==str.charAt(k)) {
                j--;
                k++;
            }
            k--;
            j++;
            if(k-j>longestSubStr) {
                longestSubStr = k-j;
            }
            if(str.charAt(i)==str.charAt(i+1)) {
                 j = i-1;
                 k = i+2;
                while (j>=0&&k<str.length()&&str.charAt(j)==str.charAt(k)) {
                    j--;
                    k++;
                }
                k--;
                j++;
                if(k-j>longestSubStr) {
                    longestSubStr = k-j;
                }
            }

        }
        return longestSubStr;
    }

    // O(n3) complexity naive's approach
    private static int getLongestSubstr(String str) {
        int longestSubstr =1 ;
        for (int i = 0; i< str.length(); i++) {
            String subStr = str.substring(0,i);
            for (int j = i+1; j< str.length()-1; j++) {
                subStr += str.substring(i,j);
                if(longestSubstr <subStr.length()&& isPalindrom(subStr)) {
                    longestSubstr = subStr.length();
                }
            }
        }
        return longestSubstr;
    }

    private static boolean isPalindrom(String str) {
        int i=0;
        int j=str.length()-1;
        while (i<j && str.charAt(i)==str.charAt(j)) {
            i++;
            j--;
        }
        if(i>=j) {
            return true;
        } else {
            return false;
        }
    }

}
