package com.dsa.greedy;

import java.util.Scanner;

/**
 *  Winning Lottery
 * Send Feedback
 * Harshit knows by his resources that this time the winning lottery number is the smallest number whose sum of the digits is S and the number of digits is D. You have to help Harshit and print the winning lottery number.
 * Input Format
 *
 * The Input line contains two space-separated integers: S,D
 *
 * Output Format
 *
 * The output contains a single integer denoting the winning lottery number
 *
 * Constraints
 *
 * 1 <= D <= 1000
 * 1 <= S <= 9*D
 * Time Limit: 1 second
 *
 * Sample Input1:
 *
 * 9 2
 *
 * Sample Output1:
 *
 * 18
 *
 * Explanation
 *
 * There are many other possible numbers like 45, 54, 90, etc with the sum of digits as 9 and number of digits as 2. The smallest of them is 18
 */
public class WinningLottery {

    public static void main(String[] args) {
        // Write your code here

        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int d = in.nextInt();
        if(d==1) {
            System.out.println(s);
            return;
        }
        int numberOfNines = (s-1)/9;
        // it has to be minimum 1 for >=2 digits number
        int firstDigitValue = 1;
        String minValue = "";
        if(numberOfNines>0) {
            // calculate first digit value
            for (int i=0;i<numberOfNines;i++) {
                minValue+="9";
            }
            if(d==numberOfNines) {
                firstDigitValue +=(s-1)%9;
            } else {
                minValue = (s-1)%9 + minValue;
            }
            for (int i=0;i<d-numberOfNines;i++) {
                minValue = "0"+minValue;
            }
            minValue = firstDigitValue+""+minValue;
        } else {
            minValue=firstDigitValue+""+(s-1%9);
        }
        System.out.println(minValue);
    }


}
