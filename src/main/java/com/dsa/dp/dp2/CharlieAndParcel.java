package com.dsa.dp.dp2;

import java.util.Scanner;

/**
 *  Charlie and Pilots
 * Send Feedback
 * Charlie acquired airline transport company and to stay in business he needs to lower the expenses by any means possible. There are N pilots working for his company (N is even) and N/2 plane crews needs to be made. A plane crew consists of two pilots - a captain and his assistant. A captain must be older than his assistant. Each pilot has a contract granting him two possible salaries - one as a captain and the other as an assistant. A captain's salary is larger than assistant's for the same pilot. However, it is possible that an assistant has larger salary than his captain. Write a program that will compute the minimal amount of money Charlie needs to give for the pilots' salaries if he decides to spend some time to make the optimal (i.e. the cheapest) arrangement of pilots in crews.
 * Input
 *
 * The first line of input contains integer N, 2 ≤ N ≤ 10,000, N is even, the number of pilots working for the Charlie's company. The next N lines of input contain pilots' salaries. The lines are sorted by pilot's age, the salaries of the youngest pilot are given the first. Each of those N lines contains two integers separated by a space character, X i Y, 1 ≤ Y < X ≤ 100,000, a salary as a captain (X) and a salary as an assistant (Y).
 *
 * Output
 *
 * The first and only line of output should contain the minimal amount of money Charlie needs to give for the pilots' salaries.
 *
 * Sample Input
 *
 * 4
 * 5000 3000
 * 6000 2000
 * 8000 1000
 * 9000 6000
 *
 * Sample Output
 *
 * 19000
 */
public class CharlieAndParcel {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] c = new int[n];
        int [] a = new int[n];
        for (int i=0;i<n;i++) {
            c[i] = in.nextInt();
            a[i] = in.nextInt();
        }
        int na = 0;
        int nc = 0;
        int [][] dp = new int[n+1][n/2+1];
        System.out.println(getOptimalSalaryOfCrew(c,a,n,na,nc,n,dp));
    }

    private static int getOptimalSalaryOfCrew(int[] c, int[] a, int x,int na,int nc, int n,int[][]dp) {
        // if remaining pilots are equal to remaining captains count
        if(na-nc==x) {
            // return remaining salaries as captain
            int sum = 0;
            for(int i=0;i<x;i++) {
                sum+=c[i];
            }
            dp[n][na-nc] = sum;
            return sum;
        }
        if(dp[x][na-nc]>0) {
            return dp[x][na-nc];
        }
        int sumSalary = 0;
        // first pilot can only become an assistant
        if(na==nc) {
            sumSalary = a[0] + getOptimalSalaryOfCrew(arrayCopy(c,1),arrayCopy(a,1),x-1,na+1,nc,n,dp);
        } else {
            sumSalary = min(a[0] + getOptimalSalaryOfCrew(arrayCopy(c,1),arrayCopy(a,1),x-1,na+1,nc,n,dp),
                    c[0] + getOptimalSalaryOfCrew(arrayCopy(c,1),arrayCopy(a,1),x-1,na,nc+1,n,dp));
        }
        dp[x][na-nc] = sumSalary;
        return sumSalary;
    }

    private static int min(int a, int b) {
        return a<b?a:b;
    }

    private static int[] arrayCopy(int[]arr, int beginIndex) {
        int[] aCopy = new int[arr.length-beginIndex];
        for (int i=beginIndex;i<arr.length;i++) {
            aCopy[i-beginIndex] = arr[i];
        }
        return aCopy;
    }
 }
