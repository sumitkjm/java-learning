package com.dsa.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Weighted Job Scheduling
 * Send Feedback
 * You are given N jobs where every job is represented as:
 * 1.Start Time
 * 2.Finish Time
 * 3.Profit Associated
 * Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
 * Input
 *
 * The first line of input contains one integer denoting N.
 * Next N lines contains three space separated integers denoting the start time, finish time and the profit associated with the ith job.
 *
 * Output
 *
 * Output one integer, the maximum profit that can be achieved.
 *
 * Constraints
 * 1 ≤ N ≤ 10^6
 * 1 ≤ ai, di, p ≤ 10^6
 * Sample Input
 *
 * 4
 * 3 10 20
 * 1 2 50
 * 6 19 100
 * 2 100 200
 *
 * Sample Output
 *
 * 250
 */
public class WeightedJobScheduling {

    static class Job implements Comparable<Job>{
        int si;

        int di;

        int pi;


        @Override
        public int compareTo(Job o) {
            if(di>o.di) {
                return 1;
            } else if(di<o.di){
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        // Write your code here

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Job [] jobs = new Job[n];
        for (int i=0;i<n;i++) {
            Job job = new Job();
            job.si = in.nextInt();
            job.di = in.nextInt();
            job.pi = in.nextInt();
            jobs[i] =job;
        }
        Arrays.sort(jobs);
        for (int i=0;i<n;i++) {
            System.out.println(jobs[i].si+" "+jobs[i].di+" "+jobs[i].pi);
        }
        int[] dp = new int[n];
        maxProfitByNsquare(n, jobs, dp);
        System.out.println(dp[n-1]);
    }

    private static void maxProfitByNsquare(int n, Job[] jobs, int[] dp) {
        dp[0] = jobs[0].pi;
        for (int i = 1; i< n; i++) {
            int maxProfit = jobs[i].pi> dp[i-1]? jobs[i].pi: dp[i-1];
            for (int j=0;j<i;j++) {
                if(jobs[i].si>= jobs[j].di) {
                    if(dp[j]+ jobs[i].pi>maxProfit) {
                        maxProfit = dp[j]+ jobs[i].pi;
                    }
                } else {
                    break;
                }
            }
            dp[i] = maxProfit;
        }
    }

    private static int maxProfitByBinarySearch(int n, Job[] jobs, int[] dp) {
        dp[0] = jobs[0].pi;
        for (int i = 1; i< n; i++) {
            int maxProfit = jobs[i].pi> dp[i-1]? jobs[i].pi: dp[i-1];
            int mid = i/2;
            //
        }
        return dp[n-1];
    }



}
