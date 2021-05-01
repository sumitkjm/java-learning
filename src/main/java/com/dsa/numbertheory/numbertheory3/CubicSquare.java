package com.dsa.numbertheory.numbertheory3;

import java.util.Scanner;

/**
 *  Cubic Square
 * Send Feedback
 * Varun is learning method of successive squaring so that he can calculate a^b mod m quickly. To give himself practice he wrote many tuples of a, b and m and went to school thinking that he will do it after school.
 * After school he found that tuples he wrote are modified by his little sister. His sister converted each b into base 3. Varun wrote everything in base 10.
 * Help Varun to do his excercise.
 * Input
 *
 * First line of input contains a number T, number of test cases. Then T test cases follows each containing three numbers a (<=10^9), b and m (<=10^5) (a in base 10, b in base 3 and m in base 10). Number of digits in b will be less than 250.
 *
 * Output
 *
 * Output a number for each test case a^b mod m in base 10.
 *
 * Sample Input:
 *
 * 2
 * 2 10 10
 * 3 21101 19
 *
 * Sample Output:
 *
 * 8
 * 3
 */
public class CubicSquare {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-->0) {
            long a = in.nextLong();
            String b = in.next();
            long m = in.nextLong();
            long result = 1;
            for (int i=b.length()-1;i>=0;i--) {
                int numOfB = Integer.valueOf(String.valueOf(b.charAt(i)));
                if(numOfB==0) {
                    // do nothing;
                }
                if(numOfB==1) {
                    result = (result * a)%m;
                }
                if(numOfB==2) {
                    result = ((result * a)%m * a)%m;
                }
                a = ((a * a)%m * a)%m;
            }
            System.out.println(result);
        }
    }
}
