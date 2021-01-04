package com.dsa.dp;

import java.util.Scanner;

/**
 *  Maximum Sum Rectangle
 * Send Feedback
 * Given a 2D array, find the maximum sum rectangle in it. In other words find maximum sum over all rectangles in the matrix.
 * Input
 *
 * First line contains 2 numbers n and m denoting number of rows and number of columns. Next n lines contain m space separated integers denoting elements of matrix nxm.
 *
 * Output
 *
 * Output a single integer, maximum sum rectangle.
 *
 * Constraints
 *
 * 1<=n,m<=100
 *
 * Sample Input
 *
 * 4 5
 * 1 2 -1 -4 -20
 * -8 -3 4 2 1
 * 3 8 10 1 3
 * -4 -1 1 7 -6
 *
 * Sample Output
 *
 * 29
 */
public class MaxSumOfRectangle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int [][] arr = new int[m][n];
        for(int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(maxSum(arr,m,n));
    }

    public static int maxSum(int[][] arr, int m, int n) {
        int maxSum = Integer.MIN_VALUE;
        int [][] rectSum = new int[m][n];
        System.out.println("--------");
        for(int i=0;i<m;i++) {
            for (int j =0;j<n;j++) {
                int sum = 0;
                for(int k=i;k<m;k++) {
                    for(int l=j;l<n;l++) {
                        sum+=arr[k][l];
                    }
                }
                rectSum[i][j] = sum;
                System.out.print(sum+" ");
            }
            System.out.println();
        }

        int maxI = 0;
        int maxJ = 0;
        int maxK = 0;
        int maxL = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                // first end point i, j
                for(int k=i;k<m;k++) {
                    for(int l=j;l<n;l++) {
                        // second end point k,l
//                        if(i==1&&j==1&&k==3&&l==3) {
//                            System.out.println("hdre");
//                        }
                        int possibleAns = 0;
                        if(k<m-1 && l<n-1) {
                            possibleAns =  (rectSum[i][j] + rectSum[k+1][l+1]) - (rectSum[k+1][j] + rectSum[i][l+1]);
                        } else if(k<m-1 && l==n-1) {
                            possibleAns =  (rectSum[i][j]) - (rectSum[k+1][j]);
                        } else if(l<n-1 && k==m-1) {
                            possibleAns =  (rectSum[i][j]) - (rectSum[i][l+1]);
                        } else if(l==n-1 && k==m-1){
                            possibleAns = rectSum[i][j];
                        } else {
                            System.out.println("should never occur");
                        }

                       if(possibleAns>maxSum) {
                           maxSum = possibleAns;
                           maxI = i;
                           maxJ = j;
                           maxK = k;
                           maxL = l;
                       }
                    }
                }
            }
        }
        System.out.println("maxI="+maxI+" maxJ="+maxJ);
        System.out.println("maxK="+maxK+" maxL="+maxL);
        return maxSum;
    }
}
