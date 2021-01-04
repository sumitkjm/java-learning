package com.dsa.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Magic Grid Problem
 * Send Feedback
 * You are given a magic grid A with R rows and C columns. In the cells of the grid, you will either get magic juice, which increases your strength by |A[i][j]| points, or poison, which takes away |A[i][j]| strength points. If at any point of the journey, the strength points become less than or equal to zero, then you will die.
 * You have to start from the top-left corner cell (1,1) and reach at the bottom-right corner cell (R,C). From a cell (i,j), you can only move either one cell down or right i.e., to cell (i+1,j) or cell (i,j+1) and you can not move outside the magic grid. You have to find the minimum number of strength points with which you will be able to reach the destination cell.
 * Input format:
 *
 * The first line contains the number of test cases T. T cases follow. Each test case consists of R C in the first line followed by the description of the grid in R lines, each containing C integers. Rows are numbered 1 to R from top to bottom and columns are numbered 1 to C from left to right. Cells with A[i][j] < 0 contain poison, others contain magic juice.
 *
 * Output format:
 *
 * Output T lines, one for each case containing the minimum strength you should start with from the cell (1,1) to have a positive strength through out his journey to the cell (R,C).
 *
 * Constraints:
 *
 * 1 ≤ T ≤ 5
 * 2 ≤ R, C ≤ 500
 * -10^3 ≤ A[i][j] ≤ 10^3
 * A[1][1] = A[R][C] = 0
 * Time Limit: 1 second
 *
 * Sample Input 1:
 *
 * 3
 * 2 3
 * 0 1 -3
 * 1 -2 0
 * 2 2
 * 0 1
 * 2 0
 * 3 4
 * 0 -2 -3 1
 * -1 4 0 -2
 * 1 -2 -3 0
 *
 * Sample Output 1:
 *
 * 2
 * 1
 * 2
 */
public class MagicGrid {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[][] takeInput() throws IOException {

        String[] nm;
        nm = br.readLine().split("\\s");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int arr[][]=new int[n][m];

        if (n == 0) {
            return arr;
        }


        String[] strNums;
        for (int i = 0; i < n; ++i) {
            strNums = br.readLine().split("\\s");
            for (int j = 0; j < m; ++j){
                arr[i][j] = Integer.parseInt(strNums[j]);
            }

        }

        return arr;
    }

    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(br.readLine().trim());
        while (t!=0){
            int[][] grid = takeInput();
            System.out.println(MagicGrid.getMinimumStrength(grid));
            t--;
        }

    }

    public static int getMinimumStrength(int[][] grid) {

        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        int[][] dp = new int[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                dp[i][j] = -1;
            }
        }

        return getMinStrength(grid, 0, 0, grid.length-1, grid[0].length-1, dp);

    }

    public static int getMinStrength(int[][] grid, int si, int sj, int ei, int ej, int[][]dp) {
        if(si==ei && sj==ej) {
            return 0;
        }

        if(si==ei-1 && sj==ej) {
            return 1;
        }

        if(si==ei && sj==ej-1) {
            return 1;
        }
        if(dp[si][sj]>0) {
            return dp[si][sj];
        }
        int opt1 = -1;
        if(si<ei) {
            opt1 = getMinStrength(grid,si+1,sj,ei,ej,dp) - grid[si+1][sj];
            if(opt1<1) {
                opt1 = 1;
            }
        }
        int opt2 = -1;
        if(sj<ej) {
            opt2 = getMinStrength(grid, si,sj+1,ei,ej,dp) - grid[si][sj+1];
            if(opt2<1) {
                opt2 = 1;
            }
        }
        int min = 1;
        if(opt1>0 && opt2>0) {
            min = min(opt1,opt2);
        } else if(opt1>0) {
            min = opt1;
        } else if(opt2>0) {
            min= opt2;
        } else {
            System.out.println("should never occur");
        }
        dp[si][sj] = min;
        return min;
    }

    static int min(int a, int b) {
        return a>b?b:a;
    }

}
