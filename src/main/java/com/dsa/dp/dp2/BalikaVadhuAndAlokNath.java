package com.dsa.dp.dp2;

import java.util.Scanner;

/**
 *  Balika Vadhu- Problem
 * Send Feedback
 * Anandi and Jagya were getting married again when they have achieved proper age. Dadi Sa invited Alok Nath to do the kanyadaan and give blessings. Alok Nath has 2 blessings. Each bessing is in the form of a string consisting of lowercase charaters(a-z) only. But he can give only one blessing of K length because some priest told him to do so. Thus he decides to generate a blessing using the other two blessings. While doing this he wants to ensure that happiness brought into their life by his blessing is maximum.
 * The generated blessing is a common subsequence of length K of the two blessings he has. Happiness of the blessing he generates is calculated by the sum of ASCII values of characters in the blessing and he wants the happiness to be maximum. If he is not able to generate a common subsequence of length K then the happiness is 0 (zero). Alok Nath comes to you and asks you to find the maximum happiness that can be generated by the two blessings he has.
 * Input Specification
 *
 * First line consists of number of test cases t. Each test case consists of two strings b1 (blessing 1),b2 (blessing 2) and an integer K, each of them in separate lines.
 *
 * Output Specification
 *
 * Output consists of t lines each containing an integer denoting the maximum happiness value that can be generated by the two blessings.
 *
 * Constraint
 *
 * 1 <= t <= 50
 *
 * 1 <= length(b1) , length(b2) <= 100
 *
 * 1 <= K <= 100
 *
 * Sample Input
 *
 * 2
 * asdf
 * asdf
 * 3
 * anandi
 * jagya
 * 3
 *
 * Sample Output
 *
 * 317
 * 0
 */
public class BalikaVadhuAndAlokNath {

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int i=0;i<t;i++) {
            String s1 = in.next();
            String s2 = in.next();
            int k = in.nextInt();
            System.out.println(maxBlessings(s1,s2,k));
        }
    }

    private static int maxBlessings(String s1, String s2, int k) {
        int m = s1.length();
        int n = s2.length();
        int[][][] dp = new int[m+1][n+1][k+1];
        for (int i=0;i<=m;i++) {
            for (int j=0;j<=n;j++) {
                for (int l=0;l<=k;l++) {
                    dp[i][j][l] = -1;
                }
            }
        }
        int ans = maxBlessingsHelper(s1,s2,k,m,n,dp);
        return ans<0?0:ans;
    }

    private static int maxBlessingsHelper(String s1, String s2, int k, int m, int n, int[][][]dp) {
        if(k==0) {
            return 0;
        }
        if(m==0||n==0) {
            return Integer.MIN_VALUE;
        }
        if(dp[m][n][k]!=-1) {
            return dp[m][n][k];
        }
        int ans = 0;
        if(s1.charAt(0)==s2.charAt(0)) {
            ans =  max(
                    max((int)s1.charAt(0)+ maxBlessingsHelper(s1.length()==1?"":s1.substring(1),s2.length()==1?"":s2.substring(1),k-1,m-1,n-1,dp),
                    maxBlessingsHelper(s1.length()==1?"":s1.substring(1),s2,k,m-1,n,dp))
                    ,
                    maxBlessingsHelper(s1,s2.length()==1?"":s2.substring(1),k,m,n-1,dp));
        } else {
            ans = max(maxBlessingsHelper(s1.length()==1?"":s1.substring(1),s2,k,m-1,n,dp),
                    maxBlessingsHelper(s1,s2.length()==1?"":s2.substring(1),k,m,n-1,dp));
        }
         dp[m][n][k] = ans;
        return ans;
    }

    static int max(int a, int b) {
        return a>b?a:b;
    }


}
