package com.dsa.dp;

import java.util.Scanner;

/**
 *  Boredom
 * Send Feedback
 * Gary is bored and wants to play an interesting but tough game . So he figured out a new board game called "destroy the neighbours" . In this game there are N integers on a board. In one move, he can pick any integer x from the board and then all the integers with value x+1 or x-1 gets destroyed .This move will give him x points.
 * He plays the game until the board becomes empty . But as he want show this game to his friend Steven, he wants to learn techniques to maximise the points to show off . Can you help Gary in finding out the maximum points he receive grab from the game ?
 * Input Format :
 *
 * Line 1 : Integer N
 * Line 2 : A list of N integers
 *
 * Output Format :
 *
 * Maximum points Gary can recieve from the Game setup
 *
 * Constraints :
 * 1<=N<=10^5
 * 1<=A[i]<=1000
 * Sample Input :
 *
 * 2
 * 1 2
 *
 * Sample Output :
 *
 * 2
 */
public class BoredomeGame {

    public static int[] A=new int [100005];
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        for(int i=0;i<n;i++)
        {
            A[i]=scan.nextInt();
        }
        System.out.println(new BoredomeGame().solve(n,A));
    }


    public int solve(int n,int A[])
    {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input. Input is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        int [][] freqs = new int[1000+1][1];
        for(int i=0;i<n;i++) {
            freqs[A[i]][0]++;
        }

        int [] dp = new int[1000+1];
        dp[0] = 0;
        dp[1] = freqs[1][0];
        for(int i=2;i<=1000;i++) {
            dp[i] = max(dp[i-2] + i*freqs[i][0], dp[i-1]);
        }
        return dp[1000];
    }

    public int max(int a, int b) {
        return a>b?a:b;
    }

}
