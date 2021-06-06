package com.dsa.codechef.dp;

import java.util.Scanner;

/**
 * https://www.codechef.com/LRNDSA07/problems/AMSGAME2
 * Chef is playing a game on a sequence of N positive integers, say A1,A2,...AN
 *
 * The game is played as follows.
 *
 *     If all the numbers are equal, the game ends.
 *     Otherwise
 *         Select two numbers which are unequal
 *         Subtract the smaller number from the larger number
 *         Replace the larger number with the result from above
 *
 * Chef has already figured out that the game always terminates. He also knows, for a given sequence of integers, the game will always terminate on the same value, no matter how the game is played. Chef wants you to simulate the game for him and tell him if the game terminates on 1
 *
 * .
 *
 * In fact, there may be many such games. Given a sequence of integers Chef wants to know the number of sub-sequences of the given sequence, for which, playing the above game on the subsuquence will terminate on 1
 * . A sub-sequence can be obtained from the original sequence by deleting 0
 *
 * or more integers from the original sequence. See the explanation section for clarity.
 * Input
 *
 *     The first line of the input contains an integer T
 *
 * , the number of test cases. Then follow the description of T
 * test cases.
 * The first line of each test case contains a single integer N
 * , the length of the sequence.
 * The second line contains N
 *
 *     positive integers, each separated by a single space.
 *
 * Output
 *
 * For each test case, output a single integer - the number of sub-sequences of the original sequence, such that, playing the game on the sub-sequence results in ending the game with all the values equal to 1
 *
 * .
 * Constraints
 *
 *     1≤T≤100
 *
 * 1≤N≤60
 * 1≤Ai≤104
 * All Ai
 *
 *     will be distinct.
 *
 * Sample Input
 *
 * 3
 * 4
 * 2 3 5 7
 * 4
 * 3 4 8 16
 * 3
 * 6 10 15
 *
 * Sample Output
 *
 * 11
 * 7
 * 1
 *
 * Explanation
 *
 * Test Case 1: The following 11
 *
 * sub-sequences are counted.
 *
 *     {2,3}
 *
 * {2,5}
 * {2,7}
 * {3,5}
 * {3,7}
 * {5,7}
 * {2,3,5}
 * {2,3,7}
 * {2,5,7}
 * {3,5,7}
 * {2,3,5,7}
 *
 * Test Case 2: The following 7
 *
 * sub-sequences are counted.
 *
 *     {3,4}
 *
 * {3,8}
 * {3,16}
 * {3,4,8}
 * {3,4,16}
 * {3,8,16}
 * {3,4,8,16}
 *
 * Test Case 3: There are 8
 * subsequences of {6,10,15}
 *
 *     {}
 *
 * => The game cannot be played on this sub-sequence
 * {6}
 * => The game cannot be played on this sub-sequence
 * {10}
 * => The game cannot be played on this sub-sequence
 * {15}
 * => The game cannot be played on this sub-sequence
 * {6,10}
 * => The game cannot end at {1,1}
 * {6,15}
 * => The game cannot end at {1,1}
 * {10,15}
 * => The game cannot end at {1,1}
 * {6,10,15}
 * => The game ends at {1,1,1}. Hence this is the only sub-sequence that is counted in the result.
 */
public class SubtractionGame2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i=0;i<n;i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(countSubSeqEndingResultOne(arr,n));
        }
    }

    public static int gcd(int a,int b)
    {
        if(a<b) {
            return gcd(b,a);
        }
        if(b==0) {
            return a;
        }
        return gcd(b,a%b);
    }

    private static long countSubSeqEndingResultOne(int[] arr, int n) {
        long[] gcd = new long[10001];
        gcd[arr[0]] = 1;
        for (int i=1;i<n;i++) {
                for(int k=1;k<10001;k++) {
                    if(gcd[k]!=0) {
                        gcd[gcd(k,arr[i])]+=gcd[k];
                    }
                }
                gcd[arr[i]]++;
        }
        return gcd[1];
    }
}
