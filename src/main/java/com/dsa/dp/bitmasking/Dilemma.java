package com.dsa.dp.bitmasking;

import java.util.Scanner;

/**
 *  Dilemma
 * Send Feedback
 * Abhishek, a blind man recently bought N binary strings all of equal length .A binary string only contains '0's and '1's . The strings are numbered from 1 to N and all are distinct, but Abhishek can only differentiate between these strings by touching them. In one touch Abhishek can identify one character at a position of a string from the set. Find the minimum number of touches T Abhishek has to make so that he learn that all strings are different .
 * Constraints :
 * 2<=N<=10
 * 1<=|L|<=100 , L is length of the strings .
 * Input Format:
 *
 * Line 1 : An Integer N, denoting the number of binary strings .
 * Next N lines : strings of equal length
 *
 * Output Format:
 *
 * Return the minimum number of touches
 *
 * Sample Input :
 *
 * 2
 * 111010
 * 100100
 *
 * Sample Output :
 *
 * 2
 */
public class Dilemma {

    static int min(int a, int b) {
        return a>b?b:a;
    }

    public static int findTouches(int pos, int mask, String[] input, int[][] dp) {

        // check if only one bit is set
        if((mask&(mask-1))==0) {
            return 0;
        }
        if(pos<0) {
            return 10000;
        }
        if(dp[pos][mask]!=Integer.MAX_VALUE) {
            return dp[pos][mask];
        }
        int newmask1 = 0;
        int newmask2 =0;
        int touches = 0;
        for (int i=0;i<input.length;i++) {
            if((mask&(1<<i))!=0) {
                touches++;
                if(input[i].charAt(pos)=='0') {
                    newmask1 |=(1<<i);
                } else {
                    newmask2 |=(1<<i);
                }
            }
        }
        int ans1 = findTouches(pos-1, newmask1,input,dp) + findTouches(pos-1,newmask2,input,dp) + touches;
        int ans2 = findTouches(pos-1,mask,input,dp);
        int ans = min(ans1,ans2);
        dp[pos][mask] = ans;
        return ans;
    }

    public static int minimumTouchRequired(int n, String[] input) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, they are passed as function arguments.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        int[][] dp = new int[input.length][1<<(n+1)];
        for (int i=0;i<n;i++) {
            for (int j=0;j<1<<(n+1);j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return findTouches(input.length-1,(1<<n)-1,input,dp);
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String [] v = new String[n];
        for(int i = 0; i < n; i++)
        {
            v[i] = scan.next();
        }
        System.out.println(minimumTouchRequired(n,v));
    }

}
