package com.dsa.dp.bitmasking;

public class JobsMinCost {

    public static int min(int a, int b) {
        return a<b?a:b;
    }

    static int minCostIterative(int [][] cost, int n) {
        int [] dp = new int[1<<n];
        for (int i=0;i<(1<<n);i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int mask =0;mask<((1<<n)-1);mask++) {
            int temp = mask;
            // Count number of set bits
            int k = 0;
            while (temp>0) {
                if(temp%2==1) {
                    k++;
                }
                temp = temp/2;
            }
            for(int j=0;j<n;j++) {
                // get the bit which is unset, which means that can be used for further assignment
                if((mask&(1<<j))==0) {
                    // set the dp for the bit which is not
                    dp[mask|(1<<j)] = min(dp[mask|(1<<j)],dp[mask]+cost[k][j]);
                }
            }
        }
        return dp[(1<<n)-1];
    }

    static int minCost(int [][] cost, int n, int p, int mask, int[] dp) {
        if(p>=n) {
            return 0;
        }
        if(dp[mask]!=Integer.MAX_VALUE) {
            return dp[mask];
        }
        int minimum = Integer.MAX_VALUE;
        for (int j =0;j<n;j++) {
            if((mask&(1<<j))==0) {
                int ans = minCost(cost,n,p+1,mask|(1<<j),dp) + cost[p][j];
                if(ans<minimum) {
                    minimum = ans;
                }
            }
        }
        dp[mask] = minimum;
        return minimum;
    }

    public static void main(String[] args) {
        int [][] cost = {{10,2,6,5},{1,15,12,8},{7,8,9,3},{15,13,4,10}};
        int [] dp = new int[1<<4];
        for (int i=0;i<(1<<4);i++){
            dp[i] = Integer.MAX_VALUE;
        }
        System.out.println(minCost(cost,4,0,0,dp));
        System.out.println(minCostIterative(cost,4));

    }
}
