package com.adhoc;

import java.util.Scanner;

public class CostOfString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        String string1 = scanner.nextLine();
        String string2 = scanner.nextLine();
        char[] str1 = string1.toCharArray();
        char[] str2 = string2.toCharArray();
        // calculate the cost of converting str1 to str2 with below rule
        // flip costs 1, swap costs |str[i]-str[j]| where i and are indices in str1
        int cost = 0;
        for(int i=0;i<n;) {
            if(str1[i]!=str2[i]) {
                cost++;
                // either flip or swap
                if(i<str1.length-1 && str1[i]!=str1[i+1] && str1[i+1]!=str2[i+1]) {
                    // swap
                    char temp = str1[i+1];
                    str1[i+1] = str1[i];
                    str1[i] = temp;
                    i +=2;
                    continue;
                } else {
                    // flip
                    str1[i] = str2[i];
                    i++;
                    continue;
                }
            }
            i++;
        }
        System.out.println(cost);
    }
}
