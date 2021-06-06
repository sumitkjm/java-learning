package com.dsa.dp;

import java.util.Scanner;

/**
 * Your goal is to find the number of ways to construct an array such that consecutive positions contain different values.
 *
 * Specifically, we want to construct an array with
 * elements such that each element between and , inclusive. We also want the first and last elements of the array to be and
 *
 * .
 *
 * Given
 * , and , find the number of ways to construct such an array. Since the answer may be large, only find it modulo
 *  n =4, k =3 and x = 2
 * .
 *
 * For example, for
 * , , , there are
 *
 * ways, as shown here:
 *
 * 1 | 2 | 1 | 2
 * 1 | 2 | 3 | 2
 * 1 | 3 | 1 | 2
 *
 * Complete the function countArray which takes input
 * , and
 *
 * . Return the number of ways to construct the array such that consecutive elements are distinct.
 *
 * Constraints
 *
 * Subtasks
 *
 *     For
 *
 * of the maximum score, and
 *
 * Sample Input
 * n=4, k = 3, x =2
 * , ,
 *
 * Sample Output
 * 4
 * Explanation
 */
public class ConstructArray {

    static int mod = 1000000007;


    private static long getCountOfArrays(int n, int k, int x) {
        // maintain 2 counts, one with count with 1's and other one non 1's.
        // initial value will of oneCount will be 1 as first element is given as 1
        long oneCount = 1;
        // initial value of nonOneCount will be 0 because first value can not be filled with non 1's
        long nonOneCount = 0;
        for (int i=1;i<n;i++) {
            long prevOneCount = oneCount;
            // 1's count will be nonOneCount multiply by k-1 values can be filled by current index
            oneCount = (nonOneCount * (k-1))%mod;
            // nonOne value will be previous One Count + nonOneCount into k-2 (why k-2? because you have to )
            nonOneCount = (prevOneCount + (nonOneCount * (k-2))%mod)%mod;
        }
        if(x==1) {
            return oneCount;
        } else {
            return nonOneCount;
        }
    }

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        System.out.println(getCountOfArrays(n,k,x));
    }
}
