package com.dsa.dp.dp2;

import java.util.Scanner;

/**
 *  PARTY - Problem
 * Send Feedback
 * You just received another bill which you cannot pay because you lack the money.
 * Unfortunately, this is not the first time to happen, and now you decide to investigate the cause of your constant monetary shortness. The reason is quite obvious: the lion's share of your money routinely disappears at the entrance of party localities.
 * You make up your mind to solve the problem where it arises, namely at the parties themselves. You introduce a limit for your party budget and try to have the most possible fun with regard to this limit.
 * You inquire beforehand about the entrance fee to each party and estimate how much fun you might have there. The list is readily compiled, but how do you actually pick the parties that give you the most fun and do not exceed your budget?
 * Write a program which finds this optimal set of parties that offer the most fun. Keep in mind that your budget need not necessarily be reached exactly. Achieve the highest possible fun level, and do not spend more money than is absolutely necessary.
 * Input
 *
 * The first line of the input specifies your party budget and the number n of parties.
 *
 * The following n lines contain two numbers each. The first number indicates the entrance fee of each party. Parties cost between 5 and 25 francs. The second number indicates the amount of fun of each party, given as an integer number ranging from 0 to 10.
 *
 * The budget will not exceed 500 and there will be at most 100 parties. All numbers are separated by a single space.
 *
 * There are many test cases. Input ends with 0 0.
 *
 * Output
 *
 * For each test case your program must output the sum of the entrance fees and the sum of all fun values of an optimal solution. Both numbers must be separated by a single space.
 *
 * Sample Input
 *
 * 50 10
 * 12 3
 * 15 8
 * 16 9
 * 16 6
 * 10 2
 * 21 9
 * 18 4
 * 12 4
 * 17 8
 * 18 9
 *
 * 50 10
 * 13 8
 * 19 10
 * 16 8
 * 12 9
 * 10 2
 * 12 8
 * 13 5
 * 15 5
 * 11 7
 * 16 2
 *
 * 0 0
 *
 * Sample Output
 *
 * 49 26
 * 48 32
 */
public class PartyProblem {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        while (true) {
            int budget = in.nextInt();
            int n = in.nextInt();
            if (budget==0 && n==0) {
                break;
            }
            int[] cost = new int[n];
            int[] fun = new int[n];
            readInput(cost,fun,n,in);
            int[][] dp1 = new int[n+1][budget+1];
            int[][] dp2 = new int[n+1][budget+1];
            for (int i=0;i<=n;i++) {
                for (int j=0;j<=budget;j++) {
                    dp1[i][j] = -1;
                }
            }
            String [][] dp3 = new String[n+1][budget+1];
            printSumOfOptimalValue(cost,fun,budget,n,dp1,dp2);
//            for (int i=0;i<=n;i++) {
//                for (int j=0;j<=budget;j++) {
//                    System.out.print(dp1[i][j]+ " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            System.out.println("----------------------------");
//            System.out.println();
//            for (int i=0;i<=n;i++) {
//                for (int j=0;j<=budget;j++) {
//                    System.out.print(dp2[i][j]+ " ");
//                }
//                System.out.println();
//            }
//            System.out.println("----------------------------");
//            System.out.println();
//            for (int i=0;i<=n;i++) {
//                for (int j=0;j<=budget;j++) {
//                    System.out.print(dp3[i][j]+ " ");
//                }
//                System.out.println();
//            }
            System.out.println(dp2[n][budget]+" "+dp1[n][budget]);
        }
    }

    private static int[] printSumOfOptimalValue(int[] cost, int[] fun, int budget, int n, int[][]dp1,int[][]dp2) {
//        if(n==0&&budget>0) {
//            System.out.println("Caught------"+budget);
//        }
        if(n==0||budget==0) {
            return new int[]{0,0};
        }
        if(dp1[n][budget]>-1) {
            return new int[]{dp1[n][budget],dp2[n][budget]};
        }
        int ans = 0;
        int wt = 0;
        if(cost[n-1]<=budget) {
            int[] opt1Arr = printSumOfOptimalValue(cost,fun,budget-cost[n-1],n-1,dp1,dp2);
            int opt1 = fun[n-1] + opt1Arr[0];
            int[] opt2Arr = printSumOfOptimalValue(cost,fun,budget,n-1,dp1,dp2);
            int opt2 = opt2Arr[0];
            int cost1 = cost[n-1] + opt1Arr[1];
            int cost2 = opt2Arr[1];
//            if(n==8||n==7) {
//                System.out.println("caught");
//            }
            if(opt1==opt2) {
                wt = cost1>cost2?cost2:cost1;
                ans = opt1;
            } else if(opt1>opt2) {
                wt = cost[n-1] + opt1Arr[1];
                ans = opt1;
            } else {
                ans = opt2;
                wt = opt2Arr[1];
            }
        } else {
            int[] opt1Arr = printSumOfOptimalValue(cost,fun,budget,n-1,dp1,dp2);
            ans = opt1Arr[0];
            wt = opt1Arr[1];;
        }
        dp1[n][budget] = ans;
        dp2[n][budget] = wt;
        return new int[]{ans,wt};
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }


    static void readInput(int[] cost, int []fun, int n, Scanner in) {
        for(int i=0;i<n;i++) {
            cost[i] = in.nextInt();
            fun[i] = in.nextInt();
        }

    }




}
