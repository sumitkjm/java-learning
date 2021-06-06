package com.geeksforgeeks.bit;

/**
 * Detect if two integers have opposite signs
 *
 *     Difficulty Level : Easy
 *     Last Updated : 22 Mar, 2021
 *
 * Given two signed integers, write a function that returns true if the signs of given integers are different, otherwise false. For example, the function should return true -1 and +100, and should return false for -100 and -200. The function should not use any of the arithmetic operators.
 * Let the given integers be x and y. The sign bit is 1 in negative numbers, and 0 in positive numbers. The XOR of x and y will have the sign bit as 1 iff they have opposite sign. In other words, XOR of x and y will be negative number number iff x and y have opposite signs. The following code use this logic.
 */
public class TwoIntegersOpposite {

    public static void main(String[] args) {
        int x = 100;
        int y = -200;
        if((x^y)<0) {
            System.out.println("x and y are opposite signs");
        } else {
            System.out.println("x and y are same signs");
        }
    }
}