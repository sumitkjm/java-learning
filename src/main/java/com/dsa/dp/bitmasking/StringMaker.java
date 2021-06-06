package com.dsa.dp.bitmasking;

import java.util.Scanner;

/**
 *  String Maker
 * Send Feedback
 * According to Ancient Ninjas , string making is an art form . There are various methods of string making , one of them uses previously generated strings to make the new one . Suppose you have two strings A and B , to generate a new string C , you can pick a subsequence of characters from A and a subsequence of characters from B and then merge these two subsequences to form the new string.
 * Though while merging the two subsequences you can not change the relative order of individual subsequences. What this means is - suppose there two characters Ai and Aj in the subsequence chosen from A , where i < j , then after merging if i acquires position k and j acquires p then k<p should be true and the same apply for subsequence from C.
 * Given string A , B , C can you count the number of ways to form string C from the two strings A and B by the method described above. Two ways are different if any of the chosen subsequence is different .
 * As the answer could be large so return it after modulo 10^9+7 .
 * Input Format :
 *
 * Line 1 : String A
 * Line 2 : String B
 * Line 3 : String C
 *
 * Output Format :
 *
 * The number of ways to form string C
 *
 * Constraints :
 * 1 <= |A|, |B|, |C| <= 50
 * Sample Input :
 *
 * abc
 * abc
 * abc
 *
 * Sample Output :
 *
 * 8
 */
public class StringMaker {

    static int mod = 1000000007;

    public static int solve(String a, String b, String c) {
        //Your code goes here
        int[][][]dp = new int[a.length()+1][b.length()+1][c.length()+1];
        for (int i=0;i<=a.length();i++) {
            for (int j=0;j<=b.length();j++) {
                for (int k=0;k<=c.length();k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return solveDp(a,b,c,a.length(),b.length(),c.length(),dp);
    }

    public static int solveDp(String a, String b, String c, int a1, int b1, int c1, int[][][] dp) {
        if(c1==0) {
            return 1;
        }
        if(a1<=0&&b1<=0) {
            return 0;
        }
        if(dp[a1][b1][c1]!=-1) {
            return dp[a1][b1][c1];
        }
        int count = 0;
        for(int j = a1-1;j>=0;j--) {
            if(c.charAt(c1-1)==a.charAt(j)) {
                count = (count + solveDp(a,b,c,j,b1,c1-1,dp))%mod;
            }
        }
        for (int k=b1-1;k>=0;k--) {
            if(c.charAt(c1-1)==b.charAt(k)) {
                count = (count + solveDp(a,b,c,a1,k,c1-1,dp))%mod;
            }
        }
        dp[a1][b1][c1] = count;
        return count;
    }

    public static int[] color = new int[105];

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        String str = "abc";
//        System.out.println(str.substring(0,3));
        int n;
        Scanner scan = new Scanner(System.in);
        String a,b,c;
        a=scan.nextLine();
        b=scan.nextLine();
        c=scan.nextLine();
        System.out.println(solve(a,b,c));
    }

}
