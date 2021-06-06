package com.dsa.dp;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  Hasan and Trip
 * Send Feedback
 * Hasan has finally finished his final exams and he decided to go in a trip among cities in Syria.
 * There are N cities in Syria and they are numbered from 1 to N, each city has coordinates on plane, i-th city is in (Xi, Yi).
 * Hasan is in first city and he wants to visit some cities by his car in the trip but the final destination should be N-th city and the sequence of cities he will visit should be increasing in index (i.e. if he is in city i he can move to city j if and only if i < j ).
 * Visiting i-th city will increase Hasan's happiness by Fi units (including first and last cities), also Hasan doesn't like traveling too much, so his happiness will decrease by total distance traveled by him.
 * Help Hasan by choosing a sequence of cities to visit which maximizes his happiness.
 * Input format:
 *
 * First line contain integer N.
 * Next N lines contains three integers each, i-th line contains coordinates of i-th city Xi, Yi and Fi.
 *
 * Output format:
 *
 * Output one number rounded to 6 digits after floating point, the maximum possible happiness Hasan can get. Note: If answer is 2 print 2.000000
 *
 * Constraints:
 *
 * 1 <= N <= 3,000
 * 0 <= Xi, Yi, Fi <= 100,000
 *
 * Sample Input
 *
 * 3
 * 0 0 1
 * 3 1 1
 * 6 0 9
 *
 * Sample Output
 *
 * 4.675445
 */
public class HassanAndTrips {

    private static int m = 1000000007;

   private static DecimalFormat df = new DecimalFormat("#.000000");

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int h;


        @Override
        public int compareTo(Point o) {
            if(o.x>x) {
                return -1;
            } else if(o.x<x) {
                return 1;
            } else {
                if(o.y>y) {
                    return -1;
                } else if(o.y<y) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static double getHappynessByDistance(int x1, int y1, int x2, int y2) {
        long a = Math.abs(x2-x1);
        long b = Math.abs(y2-y1);
        long x = (a * a);
        long y = (b * b);
        long sum = (x + y);
        return Math.sqrt(sum);
    }

    public static double max(double a, double b) {
        return a>b?a:b;
    }

    public static double getMaxHappynessByIterative(Point[] points, int n) {
        double[] dp = new double[n];
        dp[0] = points[0].h;
        for (int i=1;i<n;i++) {
            dp[i] = Integer.MIN_VALUE;
            for (int j=0;j<i;j++) {
                double x = getHappynessByDistance(points[j].x,points[j].y,points[i].x,points[i].y);
                dp[i] = max(dp[i],dp[j]-x);
            }
            dp[i]+=points[i].h;
        }
        return dp[n-1];
    }

    public static double getMaxHappyness(Point[] points, int n, double[] dp) {
        if(n<=0) {
            return 0;
        }
        if(dp[n]!=0) {
            return dp[n];
        }
        double maxH = Integer.MIN_VALUE;
        for(int i=0;i<n-1;i++) {
            double x =  getHappynessByDistance(points[i].x,points[i].y,points[n].x,points[n].y);
            maxH = max(getMaxHappyness(points,i,dp)-x,maxH);
            maxH+=points[i].h;
        }
        dp[n] = maxH;
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Point[] points = new Point[n];
        for (int i=0;i<n;i++) {
            Point point = new Point();
            point.x = in.nextInt();
            point.y = in.nextInt();
            point.h = in.nextInt();
            points[i] = point;
        }
        double []dp = new double[n+1];
//        Arrays.sort(points);
        System.out.println(df.format(getMaxHappynessByIterative(points,n)));
        System.out.println(df.format(getMaxHappyness(points,n,dp)));
    }
}
