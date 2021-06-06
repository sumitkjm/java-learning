package com.dsa.dp.bitmasking;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class MehataAndRobbery {

    static class Pair implements Comparable<Pair>{
        long p;
        long w;


        @Override
        public int compareTo(Pair o) {
            if(o==null) {
                return 0;
            }
            if(this.p>o.p) {
                return 1;
            } else if(this.p<o.p) {
                return -1;
            }
            return 0;
        }
    }

    static long max(long a, long b) {
        return a>b?a:b;
    }

    public static void main(String[] args) {
        int n, w;
        Scanner in = new Scanner(System.in);
        n  = in.nextInt();
        w = in.nextInt();
        Pair[] pairs = new Pair[n];
        pairs[0] = new Pair();
        for(int i=0;i<n;i++) {
            pairs[i] = new Pair();
            pairs[i].p = in.nextInt();
            pairs[i].w = in.nextInt();
        }
        long [][][] dp = new long[2][n+1][w+1];
//        for (int i=0;i<2;i++) {
//            for (int j=0;j<n+1+j++) {
//                dp[i][j] = new []
//            }
//        }
        Arrays.sort(pairs);
        int[] primes = {1,2,3,5,7,11,13,17,19,23,29};
        // base case
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=w;j++) {
                dp[0][i][j] = dp[0][i-1][j];
                if(j>=pairs[i-1].w) {
                    dp[0][i][j] = max(dp[0][i][j],dp[0][i-1][j-(int)pairs[i-1].w]+pairs[i-1].p);
                }
            }
        }
        for (int prime=1;prime<=10;prime++) {
            int p = prime%2;
            for (int i=1;i<=n;i++) {
                for (int j=1;j<=w;j++) {
                    dp[p][i][j] = dp[p][i-1][j];
                    if(j>=pairs[i-1].w) {
                        dp[p][i][j] = max(dp[p][i][j],max(dp[p][i-1][j-(int)pairs[i-1].w]+pairs[i-1].p,
                                dp[p^1][i-1][j-(int)pairs[i-1].w]+pairs[i-1].p*primes[prime]));
                    }
                }
            }
        }
        System.out.println(dp[0][n][w]);
    }
}
