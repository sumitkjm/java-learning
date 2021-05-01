package com.dsa.string;

import java.util.Scanner;

public class ZAlgorithm {

    private static void printMatchedIndexes(int[] z, int patternLen) {
        boolean isFound = false;
        for(int i=0;i<z.length;i++) {
            if(z[i]==patternLen) {
                System.out.print(i-patternLen-1+" ");
                isFound = true;
            }
        }
        if(!isFound) {
            System.out.println("No Match Found");
        }
    }

    public static void main(String[] args) {
        String pattern = "abcdef";
        String text = "lksjdkaabcdefksdjsjabcdeflkajsdlj";
        Scanner in = new Scanner(System.in);
        pattern = in.next();
        text = in.next();
        int z[] = zAlgorithmBasic(pattern+"$"+text);
        printZArray(z);
        z = zAlgorithmOptimized2(pattern+"$"+text);
        printZArray(z);
        printMatchedIndexes(z,pattern.length());
    }

    private static void printZArray(int[] z) {
        for(int i=0;i<z.length;i++) {
            System.out.print(z[i]+" ");
        }
        System.out.println();
    }

    private static int[] zAlgorithmOptimized2(String str) {
        int [] z = new int[str.length()];
        z[0] = 0;
        int l=0,r =0;
        for (int i = 1; i< str.length(); i++) {
            if(i>r) {
                // i does not lie between l and r
                // z for this i does not exist previously
                l = i;
                r = i;
                while (r<str.length()&&str.charAt(r-l)==str.charAt(r)) {
                    r++;
                }
                z[i] = r-l;
                r--;
            } else {
                int k = i-l;
                if(z[k]+i<=r) {
                    // i lies between l and r
                    // z will exist previously
                    z[i] = z[k];
                } else {
                    // Some part of z is already included
                    // You have to start matching further
                    l = i;
                    while (r<str.length()&& str.charAt(r-l) == str.charAt(r)) {
                        r++;
                    }
                    z[i] = r-l;
                    r--;
                }
            }
        }
        return z;
    }


    private static int[] zAlgorithmOptimized(String str) {
        int [] z = new int[str.length()];
        z[0] = 0;
        int l=0,r =0;
        int j=0;
        for (int i = 1; i< str.length(); i++) {
            if(i>=l && i<=r) {
                if(z[i-l]+i<r) {
                    z[i] = z[i-l];
                    continue;
                }
            }
            j=0;
            int k=i;
            for(; j<i&&k< str.length(); j++) {
                if(str.charAt(k++)!= str.charAt(j)){
                    break;
                }
            }
            l = i;
            r = k-1;
            z[i] = j;
        }
        return z;
    }

    private static int[] zAlgorithmBasic(String str) {
        int [] z = new int[str.length()];
        z[0] = 0;
        for (int i = 1; i< str.length(); i++) {
            int j=0;
            int k=i;
            for(; j<i&&k< str.length(); j++) {
                if(str.charAt(k++)!= str.charAt(j)){
                    break;
                }
            }
            z[i] = j;
        }
        return z;
    }
}
