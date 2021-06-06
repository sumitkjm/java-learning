package com.dsa.dp;

import java.util.*;
import java.lang.*;
import java.io.*;

class MaximumSumRectangle
{

    private static int maxSumRectKadnes(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int maxSumRect = Integer.MIN_VALUE;
        for (int l=0;l<n-1;l++) {
            int[] sumArr = new int[m];
            for (int r=l+1;r<n;r++) {
                //updateSumArray
                updateSumArray(sumArr,mat,m,r);
                // Kadane's Algo to find max sum
                int maxSumSoFar = findMaxSum(sumArr,m);
                if(maxSumSoFar>maxSumRect) {
                    maxSumRect = maxSumSoFar;
                }
            }
        }

        return maxSumRect;
    }

    private static int findMaxSum(int[] sumArr, int m) {
        int maxSumSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        for (int i=0;i<m;i++) {
            maxEndingHere+= sumArr[i];
            if(maxSumSoFar<maxEndingHere) {
                maxSumSoFar = maxEndingHere;
            }
            if(maxEndingHere<0) {
                maxEndingHere = 0;
            }
        }
        return maxSumSoFar;
    }

    private static void updateSumArray(int[] sumArr, int[][]mat, int m, int r) {
        for (int i=0;i<m;i++) {
            sumArr[i] += mat[i][r];
        }
    }

    // submatrix
    private static int maxSumRectangle(int[][] mat)
    {
        int m = mat.length;
        int n = mat[0].length;
        int preSum[][] = new int[m + 1][n];

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                preSum[i + 1][j] =
                        preSum[i][j] + mat[i][j];
            }
        }

        int maxSum = -1;
        int minSum = Integer.MIN_VALUE;
        int negRow = 0, negCol = 0;
        int rStart = 0, rEnd = 0, cStart = 0, cEnd = 0;
        for (int rowStart = 0;
             rowStart < m;
             rowStart++)
        {
            for (int row = rowStart; row < m; row++)
            {
                int sum = 0;
                int curColStart = 0;
                for (int col = 0; col < n; col++)
                {
                    sum += preSum[row + 1][col]
                            - preSum[rowStart][col];
                    if (sum < 0) {
                        if (minSum < sum) {
                            minSum = sum;
                            negRow = row;
                            negCol = col;
                        }
                        sum = 0;
                        curColStart = col + 1;
                    }
                    else if (maxSum < sum)
                    {
                        maxSum = sum;
                        rStart = rowStart;
                        rEnd = row;
                        cStart = curColStart;
                        cEnd = col;
                    }
                }
            }
        }

        // Printing final values
        if (maxSum == -1) {
            System.out.println("from row - " + negRow
                    + " to row - " + negRow);
            System.out.println("from col - " + negCol
                    + " to col - " + negCol);
        }
        else {
            System.out.println("from row - " + rStart
                    + " to row - " + rEnd);
            System.out.println("from col - " + cStart
                    + " to col - " + cEnd);
        }
        return maxSum == -1 ? minSum : maxSum;
    }

    // Driver Code
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int [][] arr = new int[m][n];
        for(int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                arr[i][j] = in.nextInt();
            }
        }

        // Function call
        System.out.println(maxSumRectangle(arr));
        System.out.println(maxSumRectKadnes(arr));
    }
}

// This code is contributed by Nayanava De