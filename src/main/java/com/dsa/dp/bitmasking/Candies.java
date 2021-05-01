package com.dsa.dp.bitmasking;

import java.util.Scanner;

/**
 *  Candy
 * Send Feedback
 * Gary is a teacher at XYZ school. To reward his N students he bought a packet of N candies all with different flavours. But the problem is some students like certain flavour while some doesn't. Now Gary wants to know the number of ways he can distribute these N candies to his N students such that every student gets exactly one candy he likes.
 * Input Format :
 *
 * Line 1 : An integer N (1<= N <= 16) denoting number of students and candies.
 * Next N lines : N integers describing the preferences of one student. 1 at i'th (0 <= i < N) position denotes that this student likes i'th candy , 0 means he doesn't.
 *
 * Assume input to be 0-indexed based.
 * Output Format :
 *
 * Return the number of ways Gary can distribute these N candies to his N students such that every student gets exactly one candy he likes.``
 *
 * Sample Input:
 *
 * 3
 * 1 1 1
 * 1 1 1
 * 1 1 1
 *
 * Sample Output:
 *
 * 6
 */
public class Candies {

    static int MAXN = 16;

    static int getCount(int [][] like, int n, int p, int mask, int[] dp) {
        if(p>=n) {
            return 1;
        }
        if(dp[mask]!=-1) {
            return dp[mask];
        }
        int count = 0;
        for (int j =0;j<n;j++) {
            if((mask&(1<<j))==0 && like[p][j]==1) {
                count += getCount(like,n,p+1,mask|(1<<j),dp);
            }
        }
        dp[mask] = count;
        return count;
    }


    long solve(int[][] like,int N){

        //Write your code here.
        int [] dp = new int[1<<N];
        for (int i=0;i<(1<<N);i++) {
            dp[i] = -1;
        }
        return getCount(like,N, 0,0, dp);
    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n;
        int[][] like = new int[MAXN][MAXN];
        n = scan.nextInt();
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n; j++){
                like[i][j] = scan.nextInt();
            }
        }
        System.out.println(new Candies().solve(like,n));

    }

}
